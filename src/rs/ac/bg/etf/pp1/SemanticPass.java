package rs.ac.bg.etf.pp1;

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
	
	/********** CURRENT VARIABLE WE ARE USING **********/
	boolean if_we_are_using_an_array = false;
	
	/********** MARK THE BEGINNING OF DO...WHILE **********/
	boolean do_while_flag = false;
	
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
			report_error("ERROR! VARIABLE " + varDecl.getVarName() + " ALREADY DEFINED IN SYMBOL TABLE! ", null);
		}
		if(current_variable_definition_type == null) {
			report_error("ERROR! VARIABLE " + varDecl.getVarName() + " BEING DEFINED, BUT NO TYPE PROVIDED! ", null);
		}
		Obj varNode = Symbol_Table.insert(Obj.Var, varDecl.getVarName(), current_variable_definition_type);
		report_info("VARIABLE " + varDecl.getVarName() + " IS BEING DEFINED!", varDecl);
	}
	
	/********** READ OUR ARRAY VARIABLE(S) **********/
	public void visit(VarClassArray varDecl){
		varDeclCount++;
		Obj error_handler = Symbol_Table.find(varDecl.getVarName());
		if(error_handler!= Symbol_Table.noObj) {
			report_error("ERROR! ARRAY VARIABLE " + varDecl.getVarName() + " ALREADY DEFINED IN SYMBOL TABLE! ", null);
		}
		if(current_variable_definition_type == null) {
			report_error("ERROR! ARRAY VARIABLE " + varDecl.getVarName() + " BEING DEFINED, BUT NO TYPE PROVIDED! ", null);
		}
		Obj varNode = Symbol_Table.insert(Obj.Var, varDecl.getVarName(), new Struct(Struct.Array,current_variable_definition_type));
		report_info("ARRAY VARIABLE " + varDecl.getVarName() + " IS BEING DEFINED!", varDecl);
	}

	/********** NUMBER VALUE ANALYSE **********/
	public void visit(ValuesNumber constValueNumber) {
		constValue = constValueNumber.getNumValue();
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
	}
	
	/********** CHAR VALUE ANALYSE **********/
	public void visit(ValuesChar constValueChar) {
		constValueChar.struct = Symbol_Table.charType;
		charValue = constValueChar.getVal();
		constValue = charValue;
	}
	
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
    
    /********** GET METHOD NAME AND RETURN VALUE **********/
    public void visit(ReturnValueClassNoVoid methodTypeName){
    	currentMethod = Symbol_Table.insert(Obj.Meth, methodTypeName.getMethName(), methodTypeName.getType().struct);
    	if(Symbol_Table.find(methodTypeName.getMethName()) == Symbol_Table.noObj) {
    		report_error("ERROR! " + methodTypeName.getMethName() + " ALREADY EXISTS IN THE SYMBOL TALBE!", methodTypeName);
    	}
    	returnForMethod = methodTypeName.getType().struct;
    	methodTypeName.obj = currentMethod;
    	Symbol_Table.openScope();
		report_info("FUNCTION " + methodTypeName.getMethName() + " WITH RETURN VALUE! ", methodTypeName);
    }
    
    /********** GET METHOD NAME AND VOID FOR RETURN VALUE **********/
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
			report_error("Semanticka greska na liniji " + methodDecl.getLine() + ": funkcija " + currentMethod.getName() + " nema return iskaz!", null);
    	}
    	currentMethod.setLevel(number_of_method_formal_parameters);
    	Symbol_Table.chainLocalSymbols(currentMethod);
    	Symbol_Table.closeScope();
    	
    	returnFound = false;
    	currentMethod = null;
    	number_of_method_formal_parameters = 0;
    }
    
    /********** FORMAL PARAMETERS FOR FUNCTIONS - NO ARRAY **********/
    public void visit(FormalParamDeclClassNoArray formalParam) {
    	number_of_method_formal_parameters++;
    	Obj getObjectFromSymbolTable = Symbol_Table.find(formalParam.getFormalParamName());
    	if(getObjectFromSymbolTable!=Symbol_Table.noObj && getObjectFromSymbolTable.getLevel()==1) {
    		// already exists and is this level
    		report_error("VARIABLE OF THIS NAME ALREADY DEFINED!", null);
    	}
    	else{
    		// already defined, but as a global or not defined at all
    		Obj helper = Symbol_Table.insert(Obj.Var, formalParam.getFormalParamName(), current_variable_definition_type);
    		helper.setLevel(1);
    		helper.setFpPos(number_of_method_formal_parameters-1);
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
    	}
    }
    
    /********** READ THE CURRENT VARIABLE WE ARE USING **********/
    public void visit(Designator designator) {
    	current_variable_in_use = Symbol_Table.find(designator.getName());
    	if(current_variable_in_use == Symbol_Table.noObj) {
    		report_error("VARIABLE OF NAME " + designator.getName() + " IS NOT DEFINED!", null);
    	}
    	else {
    		if(current_variable_in_use.getType().getKind() == Struct.Array && if_we_are_using_an_array == false) {
    			report_error("ARRAY OF NAME " + designator.getName() + " IS NOT BEING PROPERLY USED!", null);
    		}
    		report_info("USING DESIGNATOR " + current_variable_in_use.getName(), designator);
    	}
    	if_we_are_using_an_array = false;
    }
    
    /********** NOTICE THAT WE ARE OUGHT TO USE AN ARRAY **********/
    public void visit(OptionalDesignatorArray param) {
    	if_we_are_using_an_array = true;
    }
    
    /********** ONLY INTEGERS HAVE ++ **********/
    public void visit(DesignatorStatementPlusPlus param) {
    	if(current_variable_in_use.getType().getKind() == Struct.Int || (current_variable_in_use.getType().getKind() == Struct.Array && current_variable_in_use.getType().getElemType().getKind() == Struct.Int)) {
    		report_info("USING ++ ON " + current_variable_in_use.getName(), param);
    	}
    	else {
    		report_error("VARIABLE OF NAME " + current_variable_in_use.getName() + " IS NOT AN INTEGER AND IS USED WITH ++!", null);
    	}
    }
    
    /********** ONLY INTEGERS HAVE -- **********/
    public void visit(DesignatorStatementMinusMinus param) {
    	if(current_variable_in_use.getType().getKind() == Struct.Int || (current_variable_in_use.getType().getKind() == Struct.Array && current_variable_in_use.getType().getElemType().getKind() == Struct.Int)) {
    		report_info("USING -- ON " + current_variable_in_use.getName(), param);
    	}
    	else {
    		report_error("VARIABLE OF NAME " + current_variable_in_use.getName() + " IS NOT AN INTEGER AND IS USED WITH ++!", null);
    	}
    }

    /********** ONLY CALL GLOBAL FUNCTIONS **********/
    public void visit(DesignatorStatementActualParameters function) {
    	if(current_variable_in_use.getKind() != Obj.Meth) {
    		report_error("THE " + current_variable_in_use.getName() + " IS NOT A FUNCTION!", null);
    	}
    	if(current_variable_in_use.getLevel() != 0) {
    		report_error("THE " + current_variable_in_use.getName() + " IS NOT A GLOBAL FUNCTION!", null);
    	}
    	else {
    		report_info("GLOBAL METHOD " + current_variable_in_use.getName() + " HAS BEEN CALLED!", null);
    	}
    }
    
    /********** MARK THE BEGINNING OF DO...WHILE **********/
    public void visit(DoKeyWord param) {
    	do_while_flag = true;
    }
    
    /********** CATCH THE USE OF BREAK OUTSIDE DO...WHILE **********/
    public void visit(BreakKeyWordClass param) {
    	if(do_while_flag != true) {
    		report_error("BREAK NOT USED IN DO...WHILE !", param);
    	}
    	do_while_flag = false;
    }
    
    /********** CATCH THE USE OF CONTINUE OUTSIDE DO...WHILE **********/
    public void visit(ContinueKeyWord param) {
    	if(do_while_flag != true) {
    		report_error("BREAK NOT USED IN DO...WHILE !", param);
    	}
    	do_while_flag = false;
    }
    
    /********** READ FUNCTION **********/
    public void visit(ReadClass read) {
    	String designator_in_use = read.getDesignator().getName();
    	Obj in_symbol_table = Symbol_Table.find(designator_in_use);
    	if(in_symbol_table.getKind()!=Obj.Var) {
    		report_error("DESIGNATOR IN USE " + designator_in_use + " IS NOT A VARIABLE !", read);
    	}
    	else if(in_symbol_table.getType().getKind() != Struct.Bool && in_symbol_table.getType().getKind() != Struct.Char && in_symbol_table.getType().getKind() != Struct.Int) {
    		if(in_symbol_table.getType().getKind() == Struct.Array) {
    			report_info("ARRAY " + designator_in_use + " IN USE IN FUNCTION READ", read);
    		}
    		else report_error("DESIGNATOR IN USE " + designator_in_use + " IS NOT A VARIABLE OF TYPE BOOL, CHAR OR INT!", read);
    	}
    	
    }
    
    
    
    /*
    public void visit(Designator designator){
    	Obj obj = Symbol_Table.find(designator.getName());
    	if(obj == Symbol_Table.noObj){
			report_error("Greska na liniji " + designator.getLine()+ " : ime "+designator.getName()+" nije deklarisano! ", null);
    	}
    	designator.obj = obj;
    }
    
    
    public void visit(FuncCall funcCall){
    	Obj func = funcCall.getDesignator().obj;
    	if(Obj.Meth == func.getKind()){
			report_info("Pronadjen poziv funkcije " + func.getName() + " na liniji " + funcCall.getLine(), null);
			funcCall.struct = func.getType();
    	}else{
			report_error("Greska na liniji " + funcCall.getLine()+" : ime " + func.getName() + " nije funkcija!", null);
			funcCall.struct = Symbol_Table.noType;
    	}
    }
    
    public void visit(Term term){
    	term.struct = term.getFactor().struct;
    }
    
    public void visit(TermExpr termExpr){
    	termExpr.struct = termExpr.getTerm().struct;
    }
    
    public void visit(AddExpr addExpr){
    	Struct te = addExpr.getExpr().struct;
    	Struct t = addExpr.getTerm().struct;
    	if(te.equals(t) && te == Symbol_Table.intType){
    		addExpr.struct = te;
    	}else{
			report_error("Greska na liniji "+ addExpr.getLine()+" : nekompatibilni tipovi u izrazu za sabiranje.", null);
			addExpr.struct = Symbol_Table.noType;
    	}
    }
    
    public void visit(Const cnst){
    	cnst.struct = Symbol_Table.intType;
    }
    
    public void visit(Var var){
    	var.struct = var.getDesignator().obj.getType();
    }
    
    public void visit(ReturnExpr returnExpr){
    	returnFound = true;
    	Struct currMethType = currentMethod.getType();
    	if(!currMethType.compatibleWith(returnExpr.getExpr().struct)){
			report_error("Greska na liniji " + returnExpr.getLine() + " : " + "tip izraza u return naredbi ne slaze se sa tipom povratne vrednosti funkcije " + currentMethod.getName(), null);
    	}
    }
    
    public void visit(Assignment assignment){
    	if(!assignment.getExpr().struct.assignableTo(assignment.getDesignator().obj.getType()))
    		report_error("Greska na liniji " + assignment.getLine() + " : " + "nekompatibilni tipovi u dodeli vrednosti! ", null);
    }
    
    */
    public boolean passed(){
    	return !errorDetected;
    }
    
}
