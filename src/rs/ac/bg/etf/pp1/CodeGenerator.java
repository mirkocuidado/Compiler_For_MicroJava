package rs.ac.bg.etf.pp1;

import java.util.*;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.*;
import rs.etf.pp1.symboltable.concepts.*;

public class CodeGenerator extends VisitorAdaptor{
	
	public CodeGenerator() {
		
		/********** len **********/
		Obj obj = Symbol_Table.lenObj;
		obj.setAdr(Code.pc);
		Code.put(Code.enter);
		Code.put(obj.getLevel());
		Code.put(obj.getLocalSymbols().size());
		Code.put(Code.load_n);
		Code.put(Code.arraylength);
		Code.put(Code.exit);
		Code.put(Code.return_);
		
		/********** chr **********/
		obj = Symbol_Table.chrObj;
		obj.setAdr(Code.pc);
		Code.put(Code.enter);
		Code.put(obj.getLevel());
		Code.put(obj.getLocalSymbols().size());
		Code.put(Code.load_n);
		Code.put(Code.exit);
		Code.put(Code.return_);
		
		/********** ord **********/
		obj = Symbol_Table.ordObj;
		obj.setAdr(Code.pc);
		Code.put(Code.enter);
		Code.put(obj.getLevel());
		Code.put(obj.getLocalSymbols().size());
		Code.put(Code.load_n);
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	private int mainPc;
	
	public int getMainPc(){
		return mainPc;
	}
	
	String nameOfProgram;
	Obj table_symbol_object;
	
	int printWidth = -1;
	
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

	/********** READ **********/
	public void visit(ReadClass readStmt) {
		if(readStmt.getDesignator().obj.getType().getKind() == Struct.Array) {
			if(readStmt.getDesignator().obj.getType().getElemType().getKind() == Struct.Char) {
				Code.put(Code.bread);
				Code.put(Code.bastore);
			}
			else {
				Code.put(Code.read);
				Code.put(Code.astore);
			}
		}
		else {
			if(readStmt.getDesignator().obj.getType().getKind() == Struct.Char) {
				Code.put(Code.bread);
				Code.store(readStmt.getDesignator().obj);
			}
			else {
				Code.put(Code.read);
				Code.store(readStmt.getDesignator().obj);
			}
		}
	}
	
	/********** PRINT **********/
	public void visit(PrintStmt printStmt){
		if(printStmt.getExpr().struct == Symbol_Table.charType){
			if(printWidth==-1) {
				Code.loadConst(1);
			}
			else {
				Code.loadConst(printWidth);
			}
			Code.put(Code.bprint);
		}
		else{
			if(printWidth==-1) {
				Code.loadConst(5);
			}
			else {
				Code.loadConst(printWidth);
			}
			Code.put(Code.print);
		}
		
		printWidth = -1;
	}
	
	public void visit(CommaNumberClass printWidthParam) {
		printWidth = printWidthParam.getN1();
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
		just_set_an_array = false;
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
		if(funcCall.getDesignator().obj.getType() != Symbol_Table.noType) {
			Code.put(Code.pop);
		}
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
	boolean just_set_an_array = false;
	
    public void visit(NoOptionalDesignatorClass param) {
    	if(if_we_are_using_an_array > 0)
    		if(just_set_an_array == false)
    			if_we_are_using_an_array--;
    		else just_set_an_array = false;
    }
    
    public void visit(OptionalDesignatorArray param) {
    	if_we_are_using_an_array++;
    	just_set_an_array = true;
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
	
	public void visit(TermClass negative) {
		if(negative.getParent().getParent().getClass() == ExprNegativeClass.class) {
			Code.put(Code.const_m1);
			Code.put(Code.mul);
		}
	}
	
	List<Integer> ternaryList = new ArrayList<>();
	
	public void visit(TernaryClass ternary) {
		Code.put(Code.const_1); 
		ternaryList.add(Code.pc+1);
		Code.putFalseJump(Code.eq, Code.pc-1);
	}
	
	public void visit(ColonClass colon) {
		Code.putJump(Code.pc+1);
		Code.fixup(ternaryList.remove(ternaryList.size()-1));
		ternaryList.add(Code.pc-2);
	}
	
	
	public void visit(ClassTwoClass ternaryEnd) {
		Code.fixup(ternaryList.remove(ternaryList.size()-1));
	}
	
	 /* x = 1 ? 2 : 3;
	 * 0: enter
	 * 1: 0
	 * 2: 1
	 * 3: const_1
	 * 4: const_1
	 * 5: jne
	 * 6: xxxxxxxxxxxxx0
	 * 7: xxxxxxxxxxxxx7
	 * 8: const_2
	 * 9: jmp
	 * 10:yyyyyyyyyyyyy0
	 * 11:yyyyyyyyyyyyy4
	 * 12:const_3
	 * 13:store_0
	 * 14:
	 * */
	

	List<List<Integer>> ifElseListNextHop = new ArrayList<>();
	List<Integer> current_list_of_addresses_to_patch_next_hop = null;
	
	List<List<Integer>> ifElseListToEnd = new ArrayList<>();
	List<Integer> current_list_of_addresses_to_hop_to_end = null;
	
	public void visit(IfConditionNoError ifCondition) {
		/***** PLACE A CONSTANT FOR COMPARISON *****/
		Code.put(Code.const_1);
		/***** IF YOU CAME FROM ELSE YOU ARE NOT CHANGING THE CURRENT LIST OF PATCH ADDRESSES *****/
		if(ifCondition.getParent().getParent().getClass() == YesOptionalStatement.class) {
			
		}
		/***** BUT IF YOU DID NOT COME BACK FROM ELSE THEN YOU NEED TO CHANGE YOUR CURRENT LIST -> ADD NEW, SAVE OLD *****/
		else {
			ifElseListNextHop.add(new ArrayList<>());
			current_list_of_addresses_to_patch_next_hop = ifElseListNextHop.get(ifElseListNextHop.size()-1);
			ifElseListToEnd.add(new ArrayList<>());
			current_list_of_addresses_to_hop_to_end = ifElseListToEnd.get(ifElseListToEnd.size()-1);
		}
		/***** ANYWAYS YOU NEED TO MAKE A __________ WHERE IT NEEDS TO BE PATCHED LATER*****/
		current_list_of_addresses_to_patch_next_hop.add(Code.pc+1);
		Code.putFalseJump(Code.eq, Code.pc-1);
	}
	
	public void visit(Empty emptyHelper) {
		/***** PUT A JMP HERE, TO GO ALL THE WAY TO THE END OF THE IF-ELSE STRUCTURE *****/
		Code.putJump(Code.pc+1);
		/***** FIX THE PREVIOUS ELEMENT FOR NEXT HOP OF THE ***CURRENT*** SCOPE, SO IF IT'S CONDITION IS FALSE, IT COMES HERE *****/
		if(current_list_of_addresses_to_patch_next_hop.size()!=0)
			Code.fixup(current_list_of_addresses_to_patch_next_hop.remove(current_list_of_addresses_to_patch_next_hop.size()-1));
		/***** AT THE END OF THE IF-ELSE STRUCTURE, PATCH THE JMP ADDRESS *****/
		current_list_of_addresses_to_hop_to_end.add(Code.pc-2);
	}
	
	public void visit(ElseClassBaby elseExists) {
		/***** EMPTY *****/
	}
	
	public void visit(NoOptionalStatement noElse) {
		/***** WHEN WE REACH THE PLACE WHERE THERE IS NO MORE ELSE-s, WE PATCH ALL END ADDRESSES *****/
		for(int address_to_patch : current_list_of_addresses_to_hop_to_end) {
			Code.fixup(address_to_patch);
		}
		current_list_of_addresses_to_hop_to_end.removeAll(current_list_of_addresses_to_hop_to_end);
		
		/***** AND WE MOVE ON TO THE PREVIOUS SCOPE OF IF-ELSEs *****/
		ifElseListToEnd.remove(ifElseListToEnd.size()-1);
		ifElseListNextHop.remove(ifElseListNextHop.size()-1);
		if(ifElseListToEnd.size()!=0)
			current_list_of_addresses_to_hop_to_end = ifElseListToEnd.get(ifElseListToEnd.size()-1);
		if(ifElseListNextHop.size()!=0)
			current_list_of_addresses_to_patch_next_hop = ifElseListNextHop.get(ifElseListNextHop.size()-1);
		
	}
	
	public void visit(YesOptionalStatement elseWithNoIfAgain) {
		/***** IF WE ARE DID NOT GO TO ELSE IF(...) AGAIN, THEN THIS WAS THE FINAL PART OF THE IF-ELSE CONSTRUCTION *****/
		if(elseWithNoIfAgain.getStatement().getClass() != IfStatement.class) {
			/***** SINCE IT IS THE END, WE PATCH ALL THE ADDRESSES FOR END *****/
			for(int address_to_patch : current_list_of_addresses_to_hop_to_end) {
				Code.fixup(address_to_patch);
			}
			current_list_of_addresses_to_hop_to_end.removeAll(current_list_of_addresses_to_hop_to_end);
			
			/***** SINCE IT IS THE END, WE ALSO PATCH ALL ADDRESSES FOR NEXT HOP*****/
			for(int address_to_patch : current_list_of_addresses_to_patch_next_hop) {
				Code.fixup(address_to_patch);
			}
			current_list_of_addresses_to_patch_next_hop.removeAll(current_list_of_addresses_to_patch_next_hop);
			
			/***** AND IF THERE ARE MORE PREVIOUS SCOPES, WE CHANGE TO THEM *****/
			if(ifElseListToEnd.size()!=0) {
				ifElseListToEnd.remove(ifElseListToEnd.size()-1);
				if(ifElseListToEnd.size()!=0)
				current_list_of_addresses_to_hop_to_end = ifElseListToEnd.get(ifElseListToEnd.size()-1);
			}
			if(ifElseListNextHop.size()!=0) {
				ifElseListNextHop.remove(ifElseListNextHop.size()-1);
				if(ifElseListNextHop.size()!=0)
				current_list_of_addresses_to_patch_next_hop = ifElseListNextHop.get(ifElseListNextHop.size()-1);
			}
		}
	}
	
	 /************************************************************************************
	 *   key                | value (Lista)
	 *   if - spolja          x, y, z
	 *   if - unutra          ux, uy, uz
	 *************************************************************************************
	 * if(...) { -> ostavim tag da treba popuniti za netacan uslov
	 * 
	 * }
	 * 
	 ************************************************************************************* 
	 * if(...){ -> ostavim tag da treba popuniti za netacan uslov
	 * 
	 * } 
	 * else { -> ostavim tag da treba odavde skociti na kraj, ako je gore bilo tacno
	 * 
	 * }
	 * 
	 *************************************************************************************
	 *  if(...) {       -> ostavim tag da treba popuniti za netacan uslov
	 *  			
	 *  }		  	   -> ostavim tag da treba skociti na kraj, ako je gore bilo tacno
	 *  else if(...) { -> ostavim tag da treba popuniti za netacan uslov
	 *  --------
	 *  }			   -> ostavim tag da treba skociti na kraj, ako je gore bilo tacno
	 *  else if(...){  -> ostavim tag da treba popuniti za netacan uslov
	 *   --------      
	 *  }
	 *  else{		   -> ostavim tag da treba skociti na kraj, ako je gore bilo tacno
	 *  
	 *  }
	 *************************************************************************************/
	
	class ThreeListsClass{
		
		public ThreeListsClass() {
			start_of_do_while_loops_struct = new ArrayList<>();
			end_of_do_while_loops_struct = new ArrayList<>();
			initial_jump_on_do_struct = new ArrayList<>();
		}
		
		public List<Integer> start_of_do_while_loops_struct;
		public List<Integer> end_of_do_while_loops_struct;
		public List<Integer> initial_jump_on_do_struct;
	}
	
	List<ThreeListsClass> hash_map = new ArrayList<>();
	List<Integer> start_of_do_while_loops = null; // first statement of do...while
	List<Integer> end_of_do_while_loops = null;   // first statement after do...while
	List<Integer> initial_jump_on_do = null;      // check the condition
	
	public void visit(DoClass doKeyWord) {
		hash_map.add(new ThreeListsClass());
		start_of_do_while_loops = hash_map.get(hash_map.size()-1).start_of_do_while_loops_struct;
		end_of_do_while_loops = hash_map.get(hash_map.size()-1).end_of_do_while_loops_struct;
		initial_jump_on_do = hash_map.get(hash_map.size()-1).initial_jump_on_do_struct;
		
		Code.putJump(Code.pc+1);
		start_of_do_while_loops.add(Code.pc);
		initial_jump_on_do.add(Code.pc-2);
	}
	
	public void visit(LParenClass doWhileConditionBegin) {
		for(int address:initial_jump_on_do)
			Code.fixup(address);
		initial_jump_on_do.removeAll(initial_jump_on_do);
	}
	
	public void visit(RParenClass doWhileCloseParen) {
		Code.put(Code.const_1);
		end_of_do_while_loops.add(Code.pc+1);
		Code.putFalseJump(Code.eq, Code.pc-1);
		Code.putJump(start_of_do_while_loops.remove(start_of_do_while_loops.size()-1));
	}
	
	public void visit(DoWhileStatement instrAfterDoWhile) {
		for(int end_of_do_while:end_of_do_while_loops)
			Code.fixup(end_of_do_while);
		end_of_do_while_loops.removeAll(end_of_do_while_loops);
		
		if(hash_map.size()!=0) hash_map.remove(hash_map.size()-1);
		
		if(hash_map.size()!=0) {
			start_of_do_while_loops = hash_map.get(hash_map.size()-1).start_of_do_while_loops_struct;
			end_of_do_while_loops = hash_map.get(hash_map.size()-1).end_of_do_while_loops_struct;
			initial_jump_on_do = hash_map.get(hash_map.size()-1).initial_jump_on_do_struct;
		}
	}
	
	public void visit(BreakKeyWordClass breakKeyWord) {
		Code.putJump(Code.pc+1);
		end_of_do_while_loops.add(Code.pc-2);
	}
	
	public void visit(ContinueKeyWord continueKeyWord) {
		Code.putJump(Code.pc+1);
		initial_jump_on_do.add(Code.pc-2);
	}
	
	 /**************************************************************************************************************************
	 *  do {       -> skocim na uslov od while, zapamtim u start_of_do_while_loops za povratak i u initial_jump_on_do za patch
	 *  			
	 *  }
	 *  while(...); -> uslov netacan => skocim ispod, nakon while
	 *  			-> uslov tacan => skocim na poslednju naredbu iz start_of_do_while_loops
	 ***************************************************************************************************************************/
	
	public void visit(CondFactOptionalClass relOp) {
		if(relOp.getRelOp() instanceof RelationOperationClassEqualComparation) {
			Code.putFalseJump(Code.eq, Code.pc+7);
			Code.put(Code.const_1);
			Code.putJump(Code.pc+4);
			Code.loadConst(0);
		}
		else if(relOp.getRelOp() instanceof RelationOperationClassNotEqualComparation) {
			Code.putFalseJump(Code.ne, Code.pc+7);
			Code.put(Code.const_1);
			Code.putJump(Code.pc+4);
			Code.loadConst(0);
		}
		else if(relOp.getRelOp() instanceof GreaterOperationClass) {
			Code.putFalseJump(Code.gt, Code.pc+7);
			Code.put(Code.const_1);
			Code.putJump(Code.pc+4);
			Code.loadConst(0);
		}
		else if(relOp.getRelOp() instanceof LessOperationClass) {
			Code.putFalseJump(Code.lt, Code.pc+7);
			Code.put(Code.const_1);
			Code.putJump(Code.pc+4);
			Code.loadConst(0);
		}
		else if(relOp.getRelOp() instanceof GreaterOrEqualOperationClass) {
			Code.putFalseJump(Code.ge, Code.pc+7);
			Code.put(Code.const_1);
			Code.putJump(Code.pc+4);
			Code.loadConst(0);
		}
		else if(relOp.getRelOp() instanceof LessOrEqualOperationClass) {
			Code.putFalseJump(Code.le, Code.pc+7);
			Code.put(Code.const_1);
			Code.putJump(Code.pc+4);
			Code.loadConst(0);
		}
	}
	
	
	
	
	
	
	
	
	
}
