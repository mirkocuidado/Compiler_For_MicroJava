package rs.ac.bg.etf.pp1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;

public class SemanticPass extends VisitorAdaptor {
	boolean errorDetected = false;
	
	/********** FUNCTION THINGS **********/
	Obj currentMethod = null;
	Struct returnForMethod = null;
	boolean returnFound = false;
	int number_of_method_formal_parameters = 0;
	
	/********** PROGRAM VARIABLE COUNT **********/
	int nVars;

	/********** NUMBER OF _________ (something) **********/
	int varDeclCount = 0;
	int constDeclCount = 0;
	
	/********** VALUES FOR VARIABLES **********/
	int constValue; char charValue; boolean booleanValue;
	
	/********** CURRENT TYPE FOR VARIABLES OR FORMAL PARAM DECLARATION (of nonterminal Type) **********/
	Struct current_variable_definition_type = null;
	
	/********** CURRENT VARIABLE WE ARE USING **********/
	Obj current_variable_in_use = null;
	Obj current_method_we_are_using = null;
	
	/********** CURRENT VARIABLE WE ARE USING **********/
	boolean if_we_are_using_an_array = false;
	
	/********** IF WE HAVE DESIGNATOR STATEMENT WITH ASSIGN OPERATION **********/
	boolean assignOperationFlag = false;
	
	/********** MARK THE BEGINNING OF DO...WHILE **********/
	int do_while_flag = 0;
	
	/********** MARK THE BEGINNING OF A FUNCTION **********/
	boolean open_method = false;
	
	/********** LIST OF ACTUAL PARAMETERS **********/
	List<Expr> list_of_actual_parameters = new ArrayList<>();
	List<Obj> list_of_function_calls = new ArrayList<>();
	List<List<Expr>> stack = new ArrayList<>();
	
	
	
	
	Logger log = Logger.getLogger(getClass());

	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" ON LINE ").append(line);
		log.error(msg.toString());
	}

	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message); 
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" ON LINE ").append(line);
		log.info(msg.toString());
	}
	
	private String getTypeAsString(int num) {
		if(num==0) return "void";
		else if(num==1) return "int";
		else if(num==2) return "char";
		else if(num==3) return "array";
		else if(num==5) return "boolean";
		else return "No type recognised!";
	}
	
	
	/********** READ PROGRAM NAME **********/
	public void visit(ProgramName programName){
    	programName.obj = Symbol_Table.insert(Obj.Prog, programName.getProgramName(), Symbol_Table.noType);
    	report_info("PROGRAM NAME DETECTED => "+ programName.getProgramName(), programName);
    	Symbol_Table.openScope();
    }
	
	/********** END OF PROGRAM **********/
    public void visit(Program program){
    	nVars = Symbol_Table.currentScope.getnVars();
    	Symbol_Table.chainLocalSymbols(program.getProgramName().obj);
    	report_info("PROGRAM ENDED => "+ program.getProgramName().getProgramName(), program);
    	Symbol_Table.closeScope();
    }
    
    
    
    
    /********** GLOBAL VARIABLES **********/
  
    
    
  
    /********** READ THE TYPE OF OUR VARIABLE(S) AND PUT IT IN THE SYMBOL TABLE **********/
	public void visit(VarDeclClass varDecl){
		report_info("VARIABLES OF TYPE "+ varDecl.getType().getTypeName() + " HAVE BEEN DEFINED!", varDecl);
		current_variable_definition_type = null;
	}
    
	/********** READ OUR VARIABLE(S) **********/
	public void visit(VarClassNoArray varDecl){
		varDeclCount++;
		Obj error_handler = Symbol_Table.find(varDecl.getVarName());
		if(error_handler!= Symbol_Table.noObj) {
			// error if it already exists in the table
			report_error("ERROR! VARIABLE " + varDecl.getVarName() + " ALREADY DEFINED IN SYMBOL TABLE! ", null);
		}
		if(current_variable_definition_type == null) {
			// this must mean that somebody did not define a type for this variable
			report_error("ERROR! VARIABLE " + varDecl.getVarName() + " BEING DEFINED, BUT NO TYPE PROVIDED! ", null);
		}
		// if we came here, we can add it to the Symbol_Table
		Obj varNode = Symbol_Table.insert(Obj.Var, varDecl.getVarName(), current_variable_definition_type);
		report_info("VARIABLE " + varNode.getName() + " IS BEING DEFINED!", varDecl);
	}
	
	/********** READ OUR ARRAY VARIABLE(S) **********/
	public void visit(VarClassArray varDecl){
		varDeclCount++;
		Obj error_handler = Symbol_Table.find(varDecl.getVarName());
		if(error_handler!= Symbol_Table.noObj) {
			// error if it already exists in the table
			report_error("ERROR! ARRAY VARIABLE " + varDecl.getVarName() + " ALREADY DEFINED IN SYMBOL TABLE! ", null);
		}
		if(current_variable_definition_type == null) {
			// this must mean that somebody did not define a type for this variable
			report_error("ERROR! ARRAY VARIABLE " + varDecl.getVarName() + " BEING DEFINED, BUT NO TYPE PROVIDED! ", null);
		}
		// if we came here, we can add it to the Symbol_Table
		Obj varNode = Symbol_Table.insert(Obj.Var, varDecl.getVarName(), new Struct(Struct.Array,current_variable_definition_type));
		report_info("ARRAY VARIABLE " + varNode.getName() + " IS BEING DEFINED!", varDecl);
	}

	/********** NUMBER VALUE ANALYSE **********/
	public void visit(ValuesNumber constValueNumber) {
		constValue = constValueNumber.getNumValue();
		report_info("CONST VALUE " + constValue + " IS BEING USED!", constValueNumber);
		constValueNumber.struct = Symbol_Table.intType;
	}
	
	/********** BOOLEAN VALUE ANALYSE **********/
	public void visit(ValuesBoolean constValueBool) {
		constValueBool.struct = Symbol_Table.boolType;
		String s = constValueBool.getVal();
		if(s.equals("true")) {
			constValue = 1;
		}
		else if(s.equals("false")) {
			constValue = 0;
		}
		else {
			report_error("ERROR! CONST BOOLEAN VARIABLE BEING DEFINED, BUT NO CORRECT VALUE! ", null);
		}
		report_info("CONST VALUE " + s + " IS BEING USED!", constValueBool);
	}
	
	/********** CHAR VALUE ANALYSE **********/
	public void visit(ValuesChar constValueChar) {
		constValueChar.struct = Symbol_Table.charType;
		charValue = constValueChar.getVal();
		report_info("CONST VALUE " + charValue + " IS BEING USED!", constValueChar);
		
		constValue = charValue;
	}
	
	
	
	
	/********** GLOBAL CONSTANTS **********/
	
	
	
	
	/********** CONST VARIABLE DEFINITION **********/
	public void visit(ConstListNoArray constDecl) {
		constDeclCount++;
		Obj error_handler = Symbol_Table.find(constDecl.getConstVarName());
		if(error_handler!= Symbol_Table.noObj) {
			report_error("ERROR! CONST VARIABLE " + constDecl.getConstVarName() + " ALREADY DEFINED IN SYMBOL TABLE! ", null);
		}
		if(!constDecl.getValueOptions().struct.assignableTo(current_variable_definition_type)) {
			report_error("ERROR! CONST VARIABLE " + constDecl.getConstVarName() + " WITH MULTIPLE TYPES! ", null);
		}
		Obj varNode = Symbol_Table.insert(Obj.Con, constDecl.getConstVarName(), current_variable_definition_type);
		varNode.setAdr(constValue);
		report_info("CONST VARIABLE " + constDecl.getConstVarName() + " IS BEING DEFINED!", constDecl);
	}
	
	
	
	
	/********** TYPE **********/
	
	
	
	
	/********** GET THE TYPE OF THE VARIABLE(S) **********/
    public void visit(Type type){
		Obj typeNode = Symbol_Table.find(type.getTypeName());
	
		if(typeNode == Symbol_Table.noObj){
			report_error("TYPE " + type.getTypeName() + " NOT FOUND IN SYMBOL TABLE! ", null);
			type.struct = Symbol_Table.noType;
		}else{
			if(Obj.Type == typeNode.getKind()){
				type.struct = typeNode.getType();
			}else{
				report_error("ERROR! " + type.getTypeName() + " IS NOT A VALID!", type);
				type.struct = Symbol_Table.noType;
			}
		}
	
	current_variable_definition_type = type.struct;
    }
    
    
    
    
    /********** FUNCTION **********/
    
    
    
    
    /********** GET FUNCTION NAME AND RETURN VALUE **********/
    public void visit(ReturnValueClassNoVoid methodTypeName){
    	currentMethod = Symbol_Table.insert(Obj.Meth, methodTypeName.getMethName(), methodTypeName.getType().struct);
    	if(Symbol_Table.find(methodTypeName.getMethName()) == Symbol_Table.noObj) {
    		report_error("ERROR! " + methodTypeName.getMethName() + " ALREADY EXISTS IN THE SYMBOL TALBE!", methodTypeName);
    	}
    	returnForMethod = methodTypeName.getType().struct;
    	methodTypeName.obj = currentMethod;
    	Symbol_Table.openScope();
		report_info("FUNCTION " + methodTypeName.getMethName() + " WITH RETURN VALUE OF TYPE " + getTypeAsString(methodTypeName.getType().struct.getKind()), methodTypeName);
    }
    
    /********** GET FUNCTION NAME AND VOID FOR RETURN VALUE **********/
    public void visit(ReturnValueClassVoid methodTypeName){
    	currentMethod = Symbol_Table.insert(Obj.Meth, methodTypeName.getMethName(), Symbol_Table.noType);
    	if(Symbol_Table.find(methodTypeName.getMethName()) == Symbol_Table.noObj) {
    		report_error("ERROR! " + methodTypeName.getMethName() + " ALREADY EXISTS IN THE SYMBOL TALBE!", methodTypeName);
    	}
    	returnForMethod = Symbol_Table.noType;
    	methodTypeName.obj = currentMethod;
    	Symbol_Table.openScope();
		report_info("FUNCTION " + methodTypeName.getMethName() + " WITH VOID AS RETURN VALUE! ", methodTypeName);
    }

    /********** FUNCTION ENDING **********/
    public void visit(MethodDecl methodDecl){
    	if(!returnFound && returnForMethod != Symbol_Table.noType){
			report_error("SEMANTIC ERROR ON LINE " + methodDecl.getLine() + ": FUNCTION " + currentMethod.getName() + " DOES NOT HAVE A RETURN STATEMENT!", null);
			return;
    	}
    	currentMethod.setLevel(number_of_method_formal_parameters);
    	Symbol_Table.chainLocalSymbols(currentMethod);
    	Symbol_Table.closeScope();
    	
    	report_info("END OF DEFINING FUNCTION " + currentMethod.getName(), null);
    	
    	returnFound = false;
    	currentMethod = null;
    	number_of_method_formal_parameters = 0;
    }
    
    
    
    
    /********** RETURN STATEMENT **********/
    
    
    
    
    /********** RETURN STATEMENT EXPRESSION **********/
    public void visit(ReturnExpr returnParam) {
    	
    	if(open_method == false) {
    		report_error("RETURN OUT OF METHOD DECLARATION! " , returnParam);
			return;
    	}
    	
    	Struct s1 = currentMethod.getType();
    	Struct s2 = returnParam.getExpr().struct;

    	returnFound = true;
    	
    	if(s1.getKind() == Struct.Array && s2.getKind() == Struct.Array) {
    		// ako su oba niz
    		if(s1.getElemType().getKind() == s2.getElemType().getKind()) {
    			// ako su istog tipa
    			report_info("SUCCESSFUL RETURN! TYPES: " + getTypeAsString(s1.getElemType().getKind()) + " AND " + getTypeAsString(s2.getElemType().getKind()) + " !", returnParam);
    			return;
    		}
    		else {
    			report_error("RETURN FAILED! TYPES: " + getTypeAsString(s1.getElemType().getKind()) + " AND " + getTypeAsString(s2.getElemType().getKind()) + " !", returnParam);
    			return;
    		}
    	}
    	else if(s1.getKind() == Struct.Array) {
    		// ako je samo prvo niz
    		if(s1.getElemType().assignableTo(s2)) {
    			// ako su assignable
    			report_info("SUCCESSFUL RETURN! TYPES: " + getTypeAsString(s1.getElemType().getKind()) + " AND " + getTypeAsString(s2.getKind()) + " !", returnParam);
    			return;
    		}
    		else {
    			report_error("RETURN FAILED! TYPES: " + getTypeAsString(s1.getElemType().getKind()) + " AND " + getTypeAsString(s2.getKind()) + " !", returnParam);
    			return;
    		}
    	}
    	else if(s2.getKind() == Struct.Array) {
    		// ako je samo prvo niz
    		if(s2.getElemType().assignableTo(s1)) {
    			// ako su assignable
    			report_info("SUCCESSFUL RETURN! TYPES: " + getTypeAsString(s1.getKind()) + " AND " + getTypeAsString(s2.getElemType().getKind()) + " !", returnParam);
    			return;
    		}
    		else {
    			report_error("RETURN FAILED! TYPES: " + getTypeAsString(s1.getKind()) + " AND " + getTypeAsString(s2.getElemType().getKind()) + " !", returnParam);
    			return;
    		}
    	}
    	else if(!s1.assignableTo(s2)) {
    		// ako nijedan nije niz i assignable su
    		report_error("RETURN FAILED! TYPES: " + getTypeAsString(s1.getKind()) + " AND " + getTypeAsString(s2.getKind()) + " !", returnParam);
			return;
    	}
    	else {
    		// ako nijedan nije niz i nisu assignable
    		report_info("SUCCESSFUL RETURN! TYPES: " + getTypeAsString(s1.getKind()) + " AND " + getTypeAsString(s2.getKind()) + " !", returnParam);
			return;
    	}
    }
    
    /********** RETURN STATEMENT VOID**********/
    public void visit(ReturnNoExpr returnParam) {
    	
    	if(open_method == false) {
    		report_error("RETURN OUT OF METHOD DECLARATION! " , returnParam);
			return;
    	}
    	
    	Struct s1 = currentMethod.getType();
    	
    	if(s1!=Symbol_Table.noType) {
    		report_error("ONLY return; BUT THE FUNCTION ISN'T VOID!", returnParam);
    		return;
    	}
    	else {
    		report_info("return; AND THE FUNCTION IS VOID!", returnParam);
    		returnFound = true;
    	}
    }
    
    
    
    

    /********** FORMAL PARAMETERS **********/
    
    
    
    
    /********** FORMAL PARAMETERS FOR FUNCTIONS - NO ARRAY **********/
    public void visit(FormalParamDeclClassNoArray formalParam) {
    	number_of_method_formal_parameters++;
    	Obj getObjectFromSymbolTable = Symbol_Table.find(formalParam.getFormalParamName());
    	if(getObjectFromSymbolTable!=Symbol_Table.noObj && getObjectFromSymbolTable.getLevel()==1) {
    		// already exists and is of this level
    		report_error("VARIABLE OF THIS NAME ALREADY DEFINED!", null);
    	}
    	else{
    		// already defined, but as a global or not defined at all
    		Obj helper = Symbol_Table.insert(Obj.Var, formalParam.getFormalParamName(), current_variable_definition_type);
    		helper.setLevel(1);
    		helper.setFpPos(number_of_method_formal_parameters-1);
    		report_info("FORMAL PARAMETER " + helper.getName() + " DEFINED AND IT IS OF TYPE " + getTypeAsString(helper.getType().getKind()), formalParam);
    	}
    }
    
    /********** FORMAL PARAMETERS FOR FUNCTIONS - ARRAY **********/
    public void visit(FormalParamDeclClassArray formalParamArray) {
    	number_of_method_formal_parameters++;
    	Obj getObjectFromSymbolTable = Symbol_Table.find(formalParamArray.getFormalParamName());
    	if(getObjectFromSymbolTable!=Symbol_Table.noObj && getObjectFromSymbolTable.getLevel()==1) {
    		// already exists and is this level
    		report_error("VARIABLE OF THIS KIND ALREADY DEFINED!", null);
    	}
    	else{
    		// already defined, but as a global or not defined at all
    		Obj helper = Symbol_Table.insert(Obj.Var, formalParamArray.getFormalParamName(), new Struct(Struct.Array, current_variable_definition_type));
    		helper.setLevel(1);
    		helper.setFpPos(number_of_method_formal_parameters-1);
    		report_info("FORMAL ARRAY PARAMETER " + helper.getName() + " DEFINED AND IT IS OF TYPE " + getTypeAsString(helper.getType().getElemType().getKind()), formalParamArray);
    	}
    }
    
    
    
    
    
    
    /********** IMPORTANT ***********
				~~~~~~~~~~ 
		READ THE CURRENT VARIABLE WE ARE USING
				~~~~~~~~~~ 
     **********  IMPORTANT ***********/
    
    public void visit(Designator designator) {
    	current_variable_in_use = Symbol_Table.find(designator.getName());
    	if(current_variable_in_use == Symbol_Table.noObj) {
    		report_error("VARIABLE OF NAME " + designator.getName() + " IS NOT DEFINED!", null);
    		return;
    	}
    	designator.struct = current_variable_in_use.getType();
    	
    	if(current_variable_in_use.getKind() == Obj.Meth) {
    		list_of_function_calls.add(current_variable_in_use);
    		current_method_we_are_using = list_of_function_calls.get(list_of_function_calls.size()-1);
    		stack.add(new ArrayList<>());
    		list_of_actual_parameters = stack.get(stack.size()-1);
    	}
    	//else current_method_we_are_using = null; pravi probleme, a i mislim da ne treba da postoji
    	
    	if(current_variable_in_use.getType().getKind() != Struct.Array && if_we_are_using_an_array==true) {
    		report_error("ARRAY VARIABLE OF NAME " + designator.getName() + " USED WITH [ ]!", null);
    	}
    	
    	report_info("CURRENT VARIABLE IN USE IS " + current_variable_in_use.getName() + " AND IT IS OF TYPE " + getTypeAsString(current_variable_in_use.getType().getKind()), null);
    }
     
    
    
    
    /********** DESIGNATOR STATEMENT -> ++, --, FUNCTION CALL AND ASSIGN **********/
    
    
    
    
    /********** ONLY INTEGERS HAVE ++ **********/
    public void visit(DesignatorStatementPlusPlus param) {
    	if(current_variable_in_use.getType().getKind() == Struct.Int || (current_variable_in_use.getType().getKind() == Struct.Array && current_variable_in_use.getType().getElemType().getKind() == Struct.Int)) {
    		report_info("USING ++ ON " + current_variable_in_use.getName(), param);
    	}
    	else {
    		report_error("VARIABLE OF NAME " + current_variable_in_use.getName() + " IS NOT AN INTEGER AND IS USED WITH ++!", null);
    	}
    	
    	if(if_we_are_using_an_array == false && current_variable_in_use.getType().getKind() == Struct.Array) {
    		report_error("VARIABLE ARRAY OF NAME " + current_variable_in_use.getName() + " CAN NOT BE USED LIKE THIS WITH ++!", null);
    	}
    }
    
    /********** ONLY INTEGERS HAVE -- **********/
    public void visit(DesignatorStatementMinusMinus param) {
    	if(current_variable_in_use.getType().getKind() == Struct.Int || (current_variable_in_use.getType().getKind() == Struct.Array && current_variable_in_use.getType().getElemType().getKind() == Struct.Int)) {
    		report_info("USING -- ON " + current_variable_in_use.getName(), param);
    	}
    	else {
    		report_error("VARIABLE OF NAME " + current_variable_in_use.getName() + " IS NOT AN INTEGER AND IS USED WITH --!", null);
    	}
    	
    	if(if_we_are_using_an_array == false && current_variable_in_use.getType().getKind() == Struct.Array) {
    		report_error("VARIABLE ARRAY OF NAME " + current_variable_in_use.getName() + " CAN NOT BE USED LIKE THIS WITH --!", null);
    	}
    }

    /********** ONLY CALL GLOBAL FUNCTIONS **********/
    public void visit(DesignatorStatementActualParameters function) {
    	if(current_method_we_are_using == null || current_method_we_are_using.getKind() != Obj.Meth) {
    		report_error("THE CURRENT METHOD WE ARE TRYING TO USE IS NOT A FUNCTION!", null);
    	}
    	else {
    		report_info("GLOBAL METHOD " + current_method_we_are_using.getName() + " HAS BEEN CALLED!", null);
    	}
    }
    
    /********** DESIGNATOR STATEMENT TYPE PROPAGATION **********/
    public void visit(DesignatorStatementOptionsClassAssignExpression assignDesignator) {
    	assignDesignator.struct = assignDesignator.getExpr().struct;
    }

    
    /********** IMPORTANT ***********
				~~~~~~~~~~ 
		DESIGNATOR STATEMNT -> CHECK IF TYPES ON BOTH SIDES OF EQUAL ARE THE SAME
				~~~~~~~~~~ 
     **********  IMPORTANT ***********/
    public void visit(DesignatorStatementClass designatorStatement) {
    	if(assignOperationFlag == true) {
	    	Struct s1 = designatorStatement.getDesignator().struct;
	    	Struct s2 = designatorStatement.getDesignatorStatementOptions().struct;
	    	
	    	assignOperationFlag = false;
	    	
	    	if(s1 == null || s2 == null) {
	    		report_error("ASSIGNEMENT FAILED! ", designatorStatement);
    			return;
	    	}
	    	
	    	if(s1.getKind() == Struct.Array && s2.getKind() == Struct.Array) {
	    		// ako su oba niz
	    		if(s1.getElemType().getKind() == s2.getElemType().getKind()) {
	    			// ako su istog tipa
	    			report_info("SUCCESSFUL ASSIGNEMENT! TYPES: " + getTypeAsString(s1.getElemType().getKind()) + " AND " + getTypeAsString(s2.getElemType().getKind()) + " !", designatorStatement);
	    			return;
	    		}
	    		else {
	    			report_error("ASSIGNEMENT FAILED! TYPES: " + getTypeAsString(s1.getElemType().getKind()) + " AND " + getTypeAsString(s2.getElemType().getKind()) + " !", designatorStatement);
	    			return;
	    		}
	    	}
	    	else if(s1.getKind() == Struct.Array) {
	    		// ako je samo prvo niz
	    		if(s1.getElemType().assignableTo(s2)) {
	    			// ako su assignable
	    			report_info("SUCCESSFUL ASSIGNEMENT! TYPES: " + getTypeAsString(s1.getElemType().getKind()) + " AND " + getTypeAsString(s2.getKind()) + " !", designatorStatement);
	    			return;
	    		}
	    		else {
	    			report_error("ASSIGNEMENT FAILED! TYPES: " + getTypeAsString(s1.getElemType().getKind()) + " AND " + getTypeAsString(s2.getKind()) + " !", designatorStatement);
	    			return;
	    		}
	    	}
	    	else if(s2.getKind() == Struct.Array) {
	    		// ako je samo prvo niz
	    		if(s2.getElemType().assignableTo(s1)) {
	    			// ako su assignable
	    			report_info("SUCCESSFUL ASSIGNEMENT! TYPES: " + getTypeAsString(s1.getKind()) + " AND " + getTypeAsString(s2.getElemType().getKind()) + " !", designatorStatement);
	    			return;
	    		}
	    		else {
	    			report_error("ASSIGNEMENT FAILED! TYPES: " + getTypeAsString(s1.getKind()) + " AND " + getTypeAsString(s2.getElemType().getKind()) + " !", designatorStatement);
	    			return;
	    		}
	    	}
	    	else if(!s1.assignableTo(s2)) {
	    		// ako nijedan nije niz i assignable su
	    		report_error("ASSIGNEMENT FAILED! TYPES: " + getTypeAsString(s1.getKind()) + " AND " + getTypeAsString(s2.getKind()) + " !", designatorStatement);
				return;
	    	}
	    	else {
	    		// ako nijedan nije niz i nisu assignable
	    		report_info("SUCCESSFUL ASSIGNEMENT! TYPES: " + getTypeAsString(s1.getKind()) + " AND " + getTypeAsString(s2.getKind()) + " !", designatorStatement);
				return;
	    	}
    	}
    }
    
    
    
    
    /********** FLAG AND COUNTER SETTERS **********/
    
    
    
    
    /********** MARK THE BEGINNING OF DO...WHILE **********/
    public void visit(DoKeyWord param) {
    	do_while_flag++;
    	report_info("BEGINNING OF A DO...WHILE LOOP!", param);
    }
    
    /********** MARK THE END OF DO...WHILE **********/
    public void visit(DoWhileStatement param) {
    	do_while_flag--;
    	report_info("ENDING OF A DO...WHILE LOOP!", param);
    }
    
    /********** MARK NOT USING ARRAYS **********/
    public void visit(NoOptionalDesignatorClass param) {
    	if_we_are_using_an_array = false;
    }
    
    /********** MARK USING ARRAYS **********/
    public void visit(OptionalDesignatorArray param) {
    	if_we_are_using_an_array = true;
    	if(param.getExpr().struct.getKind() != Struct.Int) {
    		report_error("EXPR CAN NOT BE OF TYPE WHICH IS NOT INT!", param);
    	}
    }
    
    /********** CATCH THE USE OF BREAK OUTSIDE DO...WHILE **********/
    public void visit(BreakKeyWordClass param) {
    	if(do_while_flag == 0) {
    		report_error("BREAK NOT USED IN DO...WHILE !", param);
    	}
    	else report_info("USING BREAK KEYWORD!", param);
    }
    
    /********** CATCH THE USE OF CONTINUE OUTSIDE DO...WHILE **********/
    public void visit(ContinueKeyWord param) {
    	if(do_while_flag == 0) {
    		report_error("BREAK NOT USED IN DO...WHILE !", param);
    	}
    	else report_info("USING CONTINUE KEYWORD!", param);
    }
    
    /********** CATCH THE USE OF '=' IN DESIGNATOR STATEMENT **********/
    public void visit(AssignOpClass param) {
    	assignOperationFlag = true;
    	report_info("BEGINNING OF AN ASSIGNEMENT!", param);
    }
    
    /********** CATCH THE OPENING OF A FUNCTION **********/
    public void visit(LBraceClass param) {
    	open_method = true;
    	report_info("METHOD BODY STARTING!", param);
    }
    
    /********** CATCH THE CLOSING OF A FUNCTION **********/
    public void visit(RBraceClass param) {
    	open_method = false;
    	report_info("METHOD BODY ENDING!", param);
    }
    
    
    
    
    /********** READ **********/
    
    
    
    
    public void visit(ReadClass read) {
    	String designator_in_use = read.getDesignator().getName();
    	Obj in_symbol_table = Symbol_Table.find(designator_in_use);
    	if(in_symbol_table.getKind()!=Obj.Var) {
    		report_error("DESIGNATOR IN USE " + designator_in_use + " IS NOT A VARIABLE !", read);
    	}
    	else if(in_symbol_table.getType().getKind() != Struct.Bool && in_symbol_table.getType().getKind() != Struct.Char && in_symbol_table.getType().getKind() != Struct.Int) {
    		if(in_symbol_table.getType().getKind() == Struct.Array) {
    			if(if_we_are_using_an_array == false) {
    				report_error("DESIGNATOR IN USE " + designator_in_use + " IS A PURE ARRAY WHICH IS NOT ALLOWED IN READ!", read);
    			}
    			else { 
    				report_info("ARRAY " + designator_in_use + " IN USE IN FUNCTION READ", read);
    			}
    		}
    		else report_error("DESIGNATOR IN USE " + designator_in_use + " IS NOT A VARIABLE OF TYPE BOOL, CHAR OR INT OR AN ARRAY!", read);
    	}
    	
    }
    
    
    
    
    /********** PRINT **********/
    
    
    
    
    public void visit(PrintStmt printParam) {
    	Struct s0 = printParam.getExpr().struct;
    	
    	if(s0.getKind() != Struct.Int && s0.getKind() != Struct.Char && s0.getKind() != Struct.Bool) {
    		report_error("EXPRESSION USED IN PRINT IS NOT A VARIABLE OF TYPE BOOL, CHAR OR INT OR AN ARRAY!", printParam);
    	}
    	else {
    		report_info("PRINT FUNCTION CALL IN USE!", printParam);
    	}
    }
    
    
    
    
    /********** FACTOR **********/
    
    
    
    
    /********** NUMBER CONST VISIT FOR FACTOR **********/
    public void visit(NumberConst numberConst) {
    	numberConst.struct = Symbol_Table.intType;
    	report_info("CONST NUMBER " + numberConst.getVal(), numberConst);
    }
    
    /********** CHAR CONST VISIT FOR FACTOR **********/
    public void visit(CharConst charConst) {
    	charConst.struct = Symbol_Table.charType;
    	report_info("CONST CHAR " + charConst.getVal(), charConst);
    }
    
    /********** BOOL CONST VISIT FOR FACTOR **********/
    public void visit(BooleanClassConstFactor boolConst) {
    	boolConst.struct = Symbol_Table.boolType;
    	report_info("CONST CHAR " + boolConst.getVal(), boolConst);
    }
    
    /********** KEYWORD NEW VISIT FOR FACTOR **********/
    public void visit(NewFactorClass neww) {
    	neww.struct = new Struct(Struct.Array, current_variable_definition_type);
    	report_info("USAGE OF KEYWORD NEW!", neww);
    }
    
    /********** KEYWORD NEW FALSE USAGE **********/
    public void visit(NoFactorOptionalSecondClass param) {
    	report_error("NO PROPER USAGE OF THIS FACTOR AND KEYWORD NEW!", param);
    }
    
    /********** EXPRESSION IN PARENTHESIS TYPE PROPAGATION **********/
    public void visit(ParenFactorClass expressionInParenthesis) {
    	expressionInParenthesis.struct = expressionInParenthesis.getExpr().struct;
    }
    
    
    
    /********** IMPORTANT ***********
      			~~~~~~~~~~ 
     	USAGE OF EITHER A SIMPLE VARIABLE OR AN ARRAY VARIABLE
     			~~~~~~~~~~ 
    **********  IMPORTANT ***********/
    
    public void visit(DesignatorClass factorDesignator) {
    	if(current_variable_in_use.getType().getKind()==Struct.Array && if_we_are_using_an_array==true) {
    		report_info("WE ARE USING AN ARRAY OF TYPE " + getTypeAsString(current_variable_in_use.getType().getElemType().getKind()), factorDesignator);
    		factorDesignator.struct = current_variable_in_use.getType().getElemType();
    	}
    	else {
    		report_info("WE ARE USING A VARIABLE OF TYPE " + getTypeAsString(current_variable_in_use.getType().getKind()), factorDesignator);
    		factorDesignator.struct = current_variable_in_use.getType();
    	}
    }

    
    
    
    /********** TERM **********/
    
    
    
    
    /********** TERM CHECK -> IF THE TYPES ARE COMPATIBLE **********/
    public void visit(TermClass term) {
    	Struct t1 = term.getFactor().struct;
    	Struct t2 = term.getTermOptionalList().struct;
    	
    	if(t2 == null) {
    		// term consists of only one term
    		term.struct = t1;
    		report_info("USING ONLY ONE EXPRESSION IN TERM!", term);
    	}
    	else if(t1.equals(t2) && t1 == Symbol_Table.intType){
    		term.struct = t1;
    		report_info("USING INTEGERS IN TERM!", term);
    	}else{
			report_error("ERROR ON LINE "+ term.getLine()+" : TYPES WHICH ARE NOT COMPATIBLE", null);
			term.struct = Symbol_Table.noType;
    	}
    }
    
    /**********  TERM -> TERM_OPTIONAL_LIST CHECK -> IF TYPES ARE COMPATIBLE **********/
    public void visit(TermOptionalListClass term) {
    	Struct t1 = term.getTermOptionalList().struct;
    	Struct t2 = term.getFactor().struct;
    	
    	if(t1 == null) {
    		// no adding done
    		term.struct = t2;
    		report_info("USING ONLY ONE EXPRESSION IN TERM!", term);
    	}
    	else if(t1.equals(t2) && t1 == Symbol_Table.intType) {
    		term.struct = t1;
    		report_info("USING INTEGERS IN TERM!", term);
    	}
    	else {
    		report_error("ERROR ON LINE "+ term.getLine()+" : TYPES WHICH ARE NOT COMPATIBLE", null);
			term.struct = Symbol_Table.noType;
    	}
    }
    
    /**********  TERM -> NO TERM **********/
    public void visit(NoTermOptionalListClass noterm) {
    	noterm.struct = null;
    }
    
    
    
    
    /********** EXPRESSION **********/
    
    
    
    
    /********** EXPRESSION NEGATIVE USAGE **********/
    public void visit(ExprNegativeClass exprNeg) {
    	if(exprNeg.getExprPositive().struct.getKind()!=Struct.Int) {
    		report_error("ERROR ON LINE "+ exprNeg.getLine()+" : CAN'T USE MINUS AND EXPR", null);
    	}
    	exprNeg.struct = exprNeg.getExprPositive().struct;
    }
    
    /**********  EXPRESSION -> CHECK IF TYPES ARE INTEGER **********/
    public void visit(ExprFirstOptionClassWithMinus exprTwoThings) {
    	Struct t1 = exprTwoThings.getTerm().struct;
    	Struct t2 = exprTwoThings.getExprOptionalList().struct;
    	
    	if(t2 == null) {
    		// no adding done
    		exprTwoThings.struct = t1;
    		report_info("USING ONLY ONE EXPRESSION IN TERM!", exprTwoThings);
    	}
    	else if(t1.equals(t2) && t1 == Symbol_Table.intType) {
    		exprTwoThings.struct = t1;
    		report_info("USING INTEGERS IN TERM!", exprTwoThings);
    	}
    	else {
    		report_error("ERROR ON LINE "+ exprTwoThings.getLine()+" : TYPES WHICH ARE NOT COMPATIBLE", null);
    		exprTwoThings.struct = Symbol_Table.noType;
    	}
    }
    
    /********** EXPRESSION -> TYPE PROPAGATION **********/
    public void visit(NormalExpressionClass normal) {
    	normal.struct = normal.getExprPositive().struct;
    }
    
    /********** EXPRESSION -> TYPE PROPAGATION **********/
    public void visit(ExprSecondOptionClassMinus exprNormal) {
    	exprNormal.struct = exprNormal.getExprNegative().struct;
    }
    
    /********** EXPRESSION -> TYPE PROPAGATION **********/
    public void visit(ClassOneClass expr) {
    	expr.struct = expr.getExprOne().struct;
    }
    
    /********** EXPRESSION -> CHECK IF TYPES ARE INTEGER **********/
    public void visit(ExprOptionalListClass exprTwoThings) {
    	Struct t1 = exprTwoThings.getExprOptionalList().struct;
    	Struct t2 = exprTwoThings.getTerm().struct;
    	
    	if(t1 == null) {
    		// no multiplying done
    		exprTwoThings.struct = t2;
    		report_info("USING ONLY ONE EXPRESSION IN TERM!", exprTwoThings);
    	}
    	else if(t1.equals(t2) && t1 == Symbol_Table.intType) {
    		exprTwoThings.struct = t1;
    		report_info("USING INTEGERS IN TERM!", exprTwoThings);
    	}
    	else {
    		report_error("ERROR ON LINE "+ exprTwoThings.getLine()+" : TYPES WHICH ARE NOT COMPATIBLE", null);
    		exprTwoThings.struct = Symbol_Table.noType;
    	}
    }
    
    /********** EXPRESSION -> TYPE PROPAGATION **********/
    public void visit(NoExprOptionalListClass expr) {
    	expr.struct = null;
    }
    

    
    
    /********** CONDITION **********/
    
    
    
    
    /********** CONDFACT -> CHECK IF TYPES ARE OF SAME TYPE**********/
    public void visit(CondFactClass condFact) {
    	Struct s1 = condFact.getExprOne().struct;
    	Struct s2 = condFact.getCondFactOptional().struct;
    	
    	condFact.struct = new Struct(Struct.Bool);
    	
    	if(s2 == null) {
    		// only one Expr provided for the CondFact, so it has to be of type Bool
    		if(s1.getKind() != Struct.Bool) {
    			report_error("ERROR ON LINE "+ condFact.getLine()+" : CONDFACT CAN'T CONSIST OF ONE EXPR WHICH IS NOT BOOL", condFact);
    		}
    		else {
    			report_info("USING ONLY ONE BOOLEAN IN TERM!", condFact);
    		}
    	}
    	else if(s1.getKind() != s2.getKind()) {
    		report_error("ERROR ON LINE "+ condFact.getLine()+" : CONDFACT WITH VARIOUS TYPES", condFact);
    	}
    	else {
    		report_info("USING TWO EXPRESSIONS OF TYPE " + getTypeAsString(s1.getKind()), condFact);
    	}
    }

    /********** CONDFACT -> TYPE PROPAGATION **********/
    public void visit(CondFactOptionalClass condFactOptional) {
    	condFactOptional.struct = condFactOptional.getExprOne().struct;
    }
    
    /********** CONDFACT -> TYPE PROPAGATION**********/
    public void visit(NoCondFactOptionalClass noCondFact) {
    	noCondFact.struct = null;
    }
    
    
    
    
    /********** ACTUAL PARAMETERS **********/
    
    
    
    
    /********** IMPORTANT ***********
				~~~~~~~~~~ 
		CHECK THE TYPES OF ACTUAL PARAMETERS
				~~~~~~~~~~ 
     **********  IMPORTANT ***********/
    public void visit(Actuals actuals) {
    	if(current_method_we_are_using == null) {
			report_error("NO METHOD IN USE!", null);
    		return;
    	}
    	
    	Collection<Obj> real_parameters_from_symbol_table = Symbol_Table.find(current_method_we_are_using.getName()).getLocalSymbols();
    	ArrayList<Obj> list_helper = new ArrayList<>(real_parameters_from_symbol_table);
    	
    	if(list_of_actual_parameters.size()!=current_method_we_are_using.getLevel()) {
    		report_error("NOT THE SAME NUMBER OF ARGUMENTS (from Actuals) !", actuals);
    		return;
    	}
    	
    	for(int i=0; i<list_of_actual_parameters.size(); i++) {
    		if(list_of_actual_parameters.get(i).struct.getKind() != list_helper.get(i).getType().getKind()) {
    			report_error("NOT THE SAME TYPE OF ARGUMENTS AT ARGUMENT (from Actuals) !" + (i+1), actuals);
    		}
    		else {
    			report_info("TYPE -> " + getTypeAsString(list_of_actual_parameters.get(i).struct.getKind()) + " <- AND -> " + getTypeAsString(list_helper.get(i).getType().getKind()) + " <-", actuals);
    		}
    	}
    	
    	list_of_function_calls.remove(list_of_function_calls.size()-1); // remove the current method used from the stack of methods
    	if(list_of_function_calls.size()>0) // if there is more methods left 
    		current_method_we_are_using = list_of_function_calls.get(list_of_function_calls.size()-1); // get the previous one
    	
    	stack.remove(stack.size()-1); // remove the current list of actual parameters from the stack of actual parameters
    	if(stack.size()>0) // if there is more lists of actual parameters
    		list_of_actual_parameters = stack.get(stack.size()-1); // get the previous one
    }
    
    /********** ACTUAL PARAMS -> CHECK THE TYPES **********/
    public void visit(NoActuals noActuals) {
    	Obj helper_object = Symbol_Table.find(current_method_we_are_using.getName());
    	
    	if(helper_object == Symbol_Table.noObj) {
    		report_error("FUNCTION YOU ARE TRYING TO USE DOES NOT EXIST !", noActuals);
    		return;
    	}
    	else if(helper_object.getLevel() > 0) {
    		// if current method has parameters, but there are no actual ones
    		report_error("NOT THE SAME NUMBER OF ARGUMENTS (from noActuals) !", noActuals);
    		return;
    	}
    }
    
    /********** ACTUAL PARAMS -> ADD EXPRESSIONS TO THE LIST **********/
    public void visit(ActualParams actualParamsAtTheEnd) {
    	list_of_actual_parameters.add(actualParamsAtTheEnd.getExpr());
    }
    
    /********** ACTUAL PARAMS -> ADD EXPRESSIONS TO THE LIST **********/
    public void visit(ActualParam actualParamSingle) {
    	list_of_actual_parameters.add(actualParamSingle.getExpr());
    }
    
    
    
    
    /********** TERNARY **********/
    public void visit(ClassTwoClass ternary) {
    	Struct s1 = ternary.getExprOne().struct;
    	Struct s2 = ternary.getExprOne1().struct;
    	
    	ternary.struct = s1;
    	
    	if(s1.getKind() == Struct.Array && s2.getKind() == Struct.Array) {
    		// ako su oba niz
    		if(s1.getElemType().getKind() == s2.getElemType().getKind()) {
    			// ako su istog tipa
    			report_info("SUCCESSFUL TERNARY! TYPES: " + getTypeAsString(s1.getElemType().getKind()) + " AND " + getTypeAsString(s2.getElemType().getKind()) + " !", ternary);
    			return;
    		}
    		else {
    			report_error("TERNARY FAILED! TYPES: " + getTypeAsString(s1.getElemType().getKind()) + " AND " + getTypeAsString(s2.getElemType().getKind()) + " !", ternary);
    			return;
    		}
    	}
    	else if(s1.getKind() == Struct.Array) {
    		// ako je samo prvo niz
    		if(s1.getElemType().assignableTo(s2)) {
    			// ako su assignable
    			report_info("SUCCESSFUL TERNARY! TYPES: " + getTypeAsString(s1.getElemType().getKind()) + " AND " + getTypeAsString(s2.getKind()) + " !", ternary);
    			return;
    		}
    		else {
    			report_error("TERNARY FAILED! TYPES: " + getTypeAsString(s1.getElemType().getKind()) + " AND " + getTypeAsString(s2.getKind()) + " !", ternary);
    			return;
    		}
    	}
    	else if(s2.getKind() == Struct.Array) {
    		// ako je samo prvo niz
    		if(s2.getElemType().assignableTo(s1)) {
    			// ako su assignable
    			report_info("SUCCESSFUL TERNARY! TYPES: " + getTypeAsString(s1.getKind()) + " AND " + getTypeAsString(s2.getElemType().getKind()) + " !", ternary);
    			return;
    		}
    		else {
    			report_error("TERNARY FAILED! TYPES: " + getTypeAsString(s1.getKind()) + " AND " + getTypeAsString(s2.getElemType().getKind()) + " !", ternary);
    			return;
    		}
    	}
    	else if(!s1.assignableTo(s2)) {
    		// ako nijedan nije niz i assignable su
    		report_error("TERNARY FAILED! TYPES: " + getTypeAsString(s1.getKind()) + " AND " + getTypeAsString(s2.getKind()) + " !", ternary);
			return;
    	}
    	else {
    		// ako nijedan nije niz i nisu assignable
    		report_info("SUCCESSFUL TERNARY! TYPES: " + getTypeAsString(s1.getKind()) + " AND " + getTypeAsString(s2.getKind()) + " !", ternary);
			return;
    	}
    }

    

    
    public boolean passed(){
    	return !errorDetected;
    }   
}
