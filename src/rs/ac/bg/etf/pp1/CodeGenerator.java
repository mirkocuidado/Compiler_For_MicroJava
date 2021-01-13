package rs.ac.bg.etf.pp1;

import java.util.*;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.*;
import rs.etf.pp1.symboltable.concepts.*;

public class CodeGenerator extends VisitorAdaptor{
	
	private int mainPc;
	
	public int getMainPc(){
		return mainPc;
	}
	
	int Prec(char ch){ 
		switch (ch) { 
			case '+': case '-':  return 1; 
			case '*': case '/': case '%': return 2; 
		} 
		return -1; 
	} 
	
	String infixToPostfix(String exp) 
	{ 
		String result = new String(""); 
		
		Stack<Character> stack = new Stack<>(); 
		
		for (int i = 0; i<exp.length(); ++i){ 
			char c = exp.charAt(i); 
			
			if (Character.isLetterOrDigit(c)) 
				result += c; 
			else if (c == '(') 
				stack.push(c);
			else if (c == ')'){ 
				while (!stack.isEmpty() && stack.peek() != '(') 
					result += stack.pop(); 
				
					stack.pop(); 
			} 
			else { 
				while (!stack.isEmpty() && Prec(c)<= Prec(stack.peek())){ 
					result += stack.pop(); 
			} 
				stack.push(c); 
			} 
	
		} 
	
		while (!stack.isEmpty()){ 
			if(stack.peek() == '(') 
				return "Invalid Expression"; 
			result += stack.pop(); 
		} 
		return result; 
	}
	
	String nameOfProgram;
	Obj table_symbol_object;
	
	public void visit(ProgramName programName) {
		nameOfProgram = programName.getProgramName();
		table_symbol_object = Symbol_Table.find(nameOfProgram);
	}
	
	int is_called = 0;
	
	int should_a_designator_be_loaded = 0;

	Obj array_we_are_using = null;
	
	
	
	
	/********** FACTOR **********/
	
	
	
	
	/* ~~~~~~~~~~ INTEGER CONST ~~~~~~~~~~ */
	public void visit(NumberConst numberConst) {
		Obj con = Symbol_Table.insert(Obj.Con, "$", numberConst.struct);
		con.setLevel(0);
		con.setAdr(numberConst.getVal());
		
		Code.load(con);
    }
	
	/* ~~~~~~~~~~ CHAR CONST ~~~~~~~~~~ */
	public void visit(CharConst charConst) {
		Obj con = Symbol_Table.insert(Obj.Con, "$", charConst.struct);
		con.setLevel(0);
		con.setAdr(charConst.getVal());
		
		Code.load(con);
    }
	
	/* ~~~~~~~~~~ BOOLEAN CONST ~~~~~~~~~~ */
	public void visit(BooleanClassConstFactor booleanConst) {
		Obj con = Symbol_Table.insert(Obj.Con, "$", booleanConst.struct);
		con.setLevel(0);
		if(booleanConst.getVal().equals("true")) {
			con.setAdr(1);
		}
		else if(booleanConst.getVal().equals("false")){
			con.setAdr(0);
		}
		Code.load(con);
    }
	
	/* ~~~~~~~~~~ LOAD for a = b; the b variable <- Factor = Expr; or for any other use of variable names ~~~~~~~~~~ */
	public void visit(DesignatorClass desig) {
		if(desig.getDesignator().obj.getType().getKind() == Struct.Array) {
			if(if_we_are_using_an_array !=0) {
				if(desig.getDesignator().obj.getType().getElemType().getKind() == Struct.Char) {
					Code.put(Code.baload);
				}
				else {
					Code.put(Code.aload);
				}
			}
			else {
				Code.load(desig.getDesignator().obj);
			}
		}
		else {
			Code.load(desig.getDesignator().obj);
		}
		
		/*
		 * problem 1 -> sta ako je ovde a[3] -> npr. x = 1 + 2 + a[3] + 4;
		 */
	}

	
	/********** PRINT **********/
	public void visit(PrintStmt printStmt){
		if(printStmt.getExpr().struct == Symbol_Table.intType){
			Code.loadConst(5);
			Code.put(Code.print);
		}
		else{
			Code.loadConst(1);
			Code.put(Code.bprint);
		}
	}
	
	/********** RETURN EXPRESSION 1 **********/
	public void visit(ReturnExpr returnExpr){
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	/********** RETURN EXPRESSION 2 **********/
	public void visit(ReturnNoExpr returnNoExpr){
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	
	
	
	/********** FUNCTIONS **********/
	

	
	
	public void visit(ReturnValueClassNoVoid methodTypeName){
		if("main".equalsIgnoreCase(methodTypeName.getMethName())){
			mainPc = Code.pc;
		}
		methodTypeName.obj.setAdr(Code.pc);
	
		int number_of_formal_params = methodTypeName.obj.getLevel();
		int number_of_formal_params_plus_local_variables = methodTypeName.obj.getLocalSymbols().size();
		
		// Generate the entry
		Code.put(Code.enter);
		Code.put(number_of_formal_params);
		Code.put(number_of_formal_params_plus_local_variables);
	}
	
	public void visit(ReturnValueClassVoid methodTypeName){
		if("main".equalsIgnoreCase(methodTypeName.getMethName())){
			mainPc = Code.pc;
		}
		methodTypeName.obj.setAdr(Code.pc);
	
		int number_of_formal_params = methodTypeName.obj.getLevel();
		int number_of_formal_params_plus_local_variables = methodTypeName.obj.getLocalSymbols().size();
		
		// Generate the entry
		Code.put(Code.enter);
		Code.put(number_of_formal_params);
		Code.put(number_of_formal_params_plus_local_variables);
	}
	
	public void visit(MethodDecl methodDecl){
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	
	
	
	/********** FUNCTION CALL TO BE DETECTED **********/
	public void visit(FactorOptionalParams actuals) {
		Obj functionObj = actuals.getDesignator().obj;
		int offset = functionObj.getAdr() - Code.pc;
		Code.put(Code.call);
		Code.put2(offset);
	}
	
	public void visit(HelperClass h) { 
		Code.load(array_we_are_using);
	}

	

	
	/********** DESIGNATOR STATEMENT **********/
	
	
	
	
	/********** DESIGNATOR STATEMENT -> ASSIGNEMENT **********/
	public void visit(DesignatorStatementOptionsClassAssignExpression designatorWithAssignement) {
		// STORE U NIZ u zavisnosti od tipa
		
		if(did_i_use_new == true) { 
			if(designatorWithAssignement.getDesignator().obj.getType().getElemType().getKind() == Struct.Char) {
				Obj o = Symbol_Table.insert(Obj.Con, "0", new Struct(Struct.Int));
				Code.load(o);
			}
			else {
				Code.put(Code.const_1);
			}
			did_i_use_new = false;
			Code.store(designatorWithAssignement.getDesignator().obj);
			return; //!!!!!
		}
		if(designatorWithAssignement.getDesignator().obj.getType().getKind() == Struct.Array) {
			if(if_we_are_using_an_array !=0) {
				if(designatorWithAssignement.getDesignator().obj.getType().getElemType().getKind() == Struct.Char) {
					Code.put(Code.bastore);
				}
				else {
					Code.put(Code.astore);
				}
			}
			else {
				Code.store(designatorWithAssignement.getDesignator().obj);
			}
		}
		else {
			Code.store(designatorWithAssignement.getDesignator().obj);
		}
	}
	
	/********** DESIGNATOR STATEMENT -> FUNCTION CALL **********/
	public void visit(DesignatorStatementActualParameters funcCall) {
		Obj functionObj = funcCall.getDesignator().obj;
		int offset = functionObj.getAdr() - Code.pc;
		Code.put(Code.call);
		Code.put2(offset);
	}
	
	/********** DESIGNATOR STATEMENT -> PLUSPLUS **********/
	public void visit(DesignatorStatementPlusPlus plusplus) {
		if(plusplus.getDesignator().obj.getType().getKind()!=Struct.Array) { // mozda if_we_are_using_an_array == 0?!
			// if we are using a simple variable -> a++;
			Code.load(plusplus.getDesignator().obj);
			Code.put(Code.const_1);
			Code.put(Code.add);
			Code.store(plusplus.getDesignator().obj);
		}
		else {
			//if we are using an array variable -> a[i]++;
			Code.put(Code.dup2);
			if(plusplus.getDesignator().obj.getType().getKind() == Struct.Char) {
				Code.put(Code.baload);
			}
			else {
				Code.put(Code.aload);
			}
			Code.put(Code.const_1);
			Code.put(Code.add);
			if(plusplus.getDesignator().obj.getType().getKind() == Struct.Char) {
				Code.put(Code.bastore);
			}
			else {
				Code.put(Code.astore);
			}
		}
	}
	
	/********** DESIGNATOR STATEMENT -> MINUSMINUS **********/
	public void visit(DesignatorStatementMinusMinus minusminus) {
		// if we are using a simple variable -> a--;
		if(minusminus.getDesignator().obj.getType().getKind()!=Struct.Array) { // mozda if_we_are_using_an_array == 0?!
			Code.load(minusminus.getDesignator().obj);
			Code.put(Code.const_1);
			Code.put(Code.sub);
			Code.store(minusminus.getDesignator().obj);
		}
		else {
			//if we are using an array variable -> a[i]--;
			Code.put(Code.dup2);
			if(minusminus.getDesignator().obj.getType().getKind() == Struct.Char) {
				Code.put(Code.baload);
			}
			else {
				Code.put(Code.aload);
			}
			Code.put(Code.const_1);
			Code.put(Code.sub);
			if(minusminus.getDesignator().obj.getType().getKind() == Struct.Char) {
				Code.put(Code.bastore);
			}
			else {
				Code.put(Code.astore);
			}
		}
	}
	
	/*boolean should_i_pop = false;
	
	public void visit(AssignOpClass assign) {
		if(should_i_pop == true) {
			Code.put(Code.pop);
			Code.put(Code.pop);
			Code.put(Code.pop);
		}
		should_i_pop = false;
	}*/
	
	boolean did_i_use_new = false;
	
	public void visit(NewFactorClass newArray) {
		Code.put(Code.newarray);
		did_i_use_new = true;
	}
	
	
	/********** DESIGNATOR **********/
	
	// leva strana sa ucitavanjem ako je levo a[3];
	
	
	/********** DESIGNATOR -> ONLY LOAD IF IT HAS [ ] **********/
	public void visit(Designator designator){
		if(designator.getOptionalDesignator() instanceof OptionalDesignatorArray) {
			//Code.load(designator.obj);
			//Code.put(Code.aload); ovu liniju bih u OptionalDesignatorArray
			//should_i_pop = true;
		}
	}
	
	/********** DESIGNATOR -> READ THE CURRENT ARRAY **********/
	public void visit(DesigIdent desigIdent) {
		array_we_are_using = desigIdent.obj;
	}
	
	/********** BULLSHIT **********/
	
	int if_we_are_using_an_array = 0;
	
    public void visit(NoOptionalDesignatorClass param) {
    	if(if_we_are_using_an_array > 0)
    		if_we_are_using_an_array--;
    }
    
    public void visit(OptionalDesignatorArray param) {
    	if_we_are_using_an_array++;
    }
	

    
    
    /********** OPERATIONS FOR CALCULATING EXPRESSIONS **********/
	
	public void visit(TermOptionalListClass termForMul) {
		
		if (termForMul.getMulOp() instanceof MultiplyOperationClass) {
			Code.put(Code.mul);
		}
		else if(termForMul.getMulOp() instanceof DivideOperationClass) {
			Code.put(Code.div);
		}
		else if(termForMul.getMulOp() instanceof ModOperationClass) {
			Code.put(Code.rem);
		}
	}
	
	public void visit(ExprOptionalListClass exprForAdd) {
		if(exprForAdd.getAddOp() instanceof PlusOperation) {
			Code.put(Code.add);
		}
		else if(exprForAdd.getAddOp() instanceof MinusOperation) {
			Code.put(Code.sub);
		}
	}
}
