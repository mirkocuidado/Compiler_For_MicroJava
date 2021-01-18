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
	
	
	public Obj getSthFromSymbolTable(String symbol_Name, String current_method_name) {
		Collection<Obj> program_list = Symbol_Table.find(nameOfProgram).getLocalSymbols();
		
		List<Obj> list_of_program_stuff = new ArrayList<>(program_list);
		
		List<Obj> list_of_vars_for_current_method = null;
		Obj retObj = null;
		
		for(int i = 0; i < list_of_program_stuff.size(); ++i) {
			if(list_of_program_stuff.get(i).getName().equals(current_method_name)) {
				list_of_vars_for_current_method = new ArrayList<>(list_of_program_stuff.get(i).getLocalSymbols());
				break;
			}
		}
		
		if(list_of_vars_for_current_method != null) {
			for(int i=0; i < list_of_vars_for_current_method.size(); ++i) {
				if(list_of_vars_for_current_method.get(i).getName().equals(symbol_Name)) {
					retObj = list_of_vars_for_current_method.get(i);
					break;
				}
			}
		}
		else {
			for(int i = 0; i < list_of_program_stuff.size(); ++i) {
				if(list_of_program_stuff.get(i).getName().equals(symbol_Name)) {
					retObj = list_of_program_stuff.get(i);
					break;
				}
			}
		}
		
		if(retObj == null) {
			for(int i = 0; i < list_of_program_stuff.size(); ++i) {
				if(list_of_program_stuff.get(i).getName().equals(symbol_Name)) {
					retObj = list_of_program_stuff.get(i);
					break;
				}
			}
		}
		
		if(retObj == null) {
			System.out.print("NO OBJECT FOUND!");
			System.exit(1);
		}
		
		return retObj;
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
	
	
	/********** MODIFICATION STUFF **********/
	
	Obj current_method_used = null;
	
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
			if(if_we_are_using_an_array_to_load !=0) {
				if_we_are_using_an_array_to_load--;
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
	
	/********** PRINT WIDTH **********/
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
		
		current_method_used = methodTypeName.obj;
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
		
		
		current_method_used = methodTypeName.obj;
		
	}
	
	public void visit(MethodDecl methodDecl){
		/*if(methodDecl.obj.getType() != Symbol_Table.noType) {
			Code.put(Code.const_m1);
			Code.put(Code.trap);
		}
		else {*/
			Code.put(Code.exit);
			Code.put(Code.return_);
			
		//}
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
	
	
	/********** BULLSHIT **********/
	
	int if_we_are_using_an_array_to_load = 0;
	int if_we_are_using_an_array_to_store = 0;
	//boolean just_set_an_array = false;
	
    public void visit(NoOptionalDesignatorClass param) {
    	/*if(if_we_are_using_an_array > 0)
    		if(just_set_an_array == false)
    			if_we_are_using_an_array--;
    		else just_set_an_array = false;*/
    }
    
    public void visit(OptionalDesignatorArray param) {
    	//if_we_are_using_an_array++;
    	//just_set_an_array = true;
    }
    
    //boolean lSquare = false;
    
    public void visit(LSquareClass lSquareParam) {
    	SyntaxNode pom = lSquareParam.getParent();
    	if(pom==null) return;
    	
    	while(pom!= null && pom.getClass() != DesignatorClass.class && pom.getClass() != DesignatorStatementOptionsClassAssignExpression.class)
    		pom = pom.getParent();
    	
    	if(pom == null) {
    		return;
    	}
    	if(pom.getClass() == DesignatorClass.class) {
    		if_we_are_using_an_array_to_load++;
    	}
    	else if(pom.getClass() == DesignatorStatementOptionsClassAssignExpression.class){
    		if_we_are_using_an_array_to_store++;
    	}
    	
    	
    	//lSquare = true;
    }
	
	/********** DESIGNATOR STATEMENT -> ASSIGNEMENT **********/
	public void visit(DesignatorStatementOptionsClassAssignExpression designatorWithAssignement) {
		//just_set_an_array = false;
		
		if(modification_two_boolean == true) {
			modification_two_boolean = false;
			return;
		}
		
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
			if(if_we_are_using_an_array_to_store !=0) {
				if_we_are_using_an_array_to_store--;
				//lSquare = false;
				if(designatorWithAssignement.getDesignator().obj.getType().getElemType().getKind() == Struct.Char) {
					Code.put(Code.bastore);
				}
				else {
					Code.put(Code.astore);
				}
			}
			else {
				Code.store(designatorWithAssignement.getDesignator().obj);
				/*if(lSquare == true) {
					if(designatorWithAssignement.getDesignator().obj.getType().getElemType().getKind() == Struct.Char) {
						Code.put(Code.bastore);
					}
					else {
						Code.put(Code.astore);
					}
				}
				else Code.store(designatorWithAssignement.getDesignator().obj);
				lSquare = false;*/
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
		if(plusplus.getDesignator().obj.getType().getKind()!=Struct.Array) {
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
		if(minusminus.getDesignator().obj.getType().getKind()!=Struct.Array) {
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

	
	boolean did_i_use_new = false;
	
	public void visit(NewFactorClass newArray) {
		
		Code.put(Code.newarray);
		did_i_use_new = true;
	}
	
	
	
	
	/********** DESIGNATOR **********/
	
	
	
	
	/********** DESIGNATOR SPURS **********/
	public void visit(Designator designator){
		if(designator.getOptionalDesignator() instanceof OptionalDesignatorArray) {
			/*
			 * 
			 * EMPTY AS TOTTENHAM'S TROPHY CABINET
			 * 
			 */
		}
	}
	
	/********** DESIGNATOR -> READ THE CURRENT ARRAY **********/
	public void visit(DesigIdent desigIdent) {
		
		// DEBILU, ako si zvao direktno ovo, verovatno ce array_we_are_using biti null -> trazi preko Symbol_Table
		array_we_are_using = desigIdent.obj;
		
		modification_two_array = desigIdent.obj;
		
		modification_three_variable_in_use = desigIdent.obj;
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
	
	
	
	
	/********** TERNARY **********/
	
	
	
	
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
	 * 5: jne (5 + mem[6]mem[7] )
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
		
		//Code.putJump(Code.pc+1); za while
		start_of_do_while_loops.add(Code.pc);
		//initial_jump_on_do.add(Code.pc-2); za while
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
	 *  do {       -> zapamtim u start_of_do_while_loops za povratak
	 *  			
	 *  }
	 *  while(...); -> uslov netacan => skocim ispod, nakon while
	 *  			-> uslov tacan => skocim na poslednju naredbu iz start_of_do_while_loops
	 ***************************************************************************************************************************/
	
	
	
	/********** <, <=, >, >=, ==, != **********/
	
	
	
	
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
	
	
	
	
	/********** && and || **********/
	

/* if(a>=b && c && d || k)
 * 
 * load a>=b
 * dup
 * load 0
 * je
 * prva instrukcija ispod load c <-------
 * pop                                   |
 * load c                                |
 * dup <---------------------------------|
 * load 0
 * je
 * prva instrukcija ispod load d
 * pop
 * load d
 * dup
 * load 1
 * je
 * prva instrukcija ispod load k
 * pop
 * load k
 * 
 */
	
	
	List<Integer> and_list_saver = new ArrayList<>();
	List<Integer> or_list_saver = new ArrayList<>();
	
	public void visit(FakeOrClass fakeOr) {
		Code.put(Code.dup);
		Code.put(Code.const_1);
		Code.putFalseJump(Code.ne, Code.pc+1);
		or_list_saver.add(Code.pc-2);
		Code.put(Code.pop);
	}
	
	public void visit(FakeAndClass fakeAnd) {
		Code.put(Code.dup);
		Code.put(Code.const_n);
		Code.putFalseJump(Code.ne, Code.pc+1); 
		// ako je na steku 0 => preskoci taj AND, jer je netacan
		// ako je na steku 1 => ne skace se, vec se uzima sledeci izraz
		and_list_saver.add(Code.pc-2);
		Code.put(Code.pop);
	}
	
	public void visit(CondTermOptionalClass allAroundAnd) {
		Code.fixup(and_list_saver.remove(and_list_saver.size()-1));
	}
	
	public void visit(ConditionOptionalClass allAroundOr) {
		Code.fixup(or_list_saver.remove(or_list_saver.size()-1));
	}

	 /********************************************************************************************************************
	 *  if(a < 2 && b > 3 || c < 4){ ... } 
	 *   
	 *   0: 1 ili 0 -> da li je a manje ili vece-jednako od 2
	 *   1: dupliram 1 ili 0 za if
	 *   2: fakeAndClass -> poredim sa 0 na ExprStack -> skacem ako je 0==0 tj. a<2 je netacno i preskacem sledeci
	 *   
	 *   ****** preskacem b > 3 ******
	 *   
	 *   3: 1 ili 0 -> da li je c manje ili vece-jednako od 4
	 * 	 4: dupliram 1 ili 0 za if
	 *   5: fakeorClass -> poredi sa 1 na ExprStack -> skacem ako je 1==1 tj. c<4 je tacno i preskacem sve uslove do kraja
	 *********************************************************************************************************************/
	

	
	/********** a = #3; 
	public void visit(ModificationOne modifParam) {
		Obj array_to_load_for_modification_one = getSthFromSymbolTable("niz", current_method_used.getName());
		Code.load(array_to_load_for_modification_one);
		
		int pom = modifParam.getVal();
		Code.loadConst(pom);
		
		if(array_to_load_for_modification_one.getType().getElemType().getKind() == Struct.Char) {
			Code.put(Code.baload);
		}
		else {
			Code.put(Code.aload);
		}
		
	}***********/
	
	
	Obj modification_two_array = null;
	boolean modification_two_boolean = false;
	
	/* array_of_char = #1667457891;
	 
			version_1
	
	public void visit(ModificationOne modifParamTwo) {
		
		int number_to_separate = modifParamTwo.getVal();
		
		int mask = 0x000000FF;
		
		for(int i=3; i>=0; i--) {
			Code.load(modification_two_array);
			Code.loadConst(i);
			int n_helper = number_to_separate & mask;
			Code.loadConst(n_helper);
			number_to_separate = number_to_separate>>8;
			Code.put(Code.bastore);
			modification_two_boolean = true;
		}
	}*/
	
	/* array_of_char = #1667457891;
	 
	 		version_2
	 		
	 public void visit(ModificationOne modifParamTwo) {
		
		int number_to_separate = modifParamTwo.getVal();
		
		Obj fake = new Obj(Obj.Var, modification_two_array.getName(), new Struct(Struct.Array, Symbol_Table.intType), modification_two_array.getAdr(), modification_two_array.getLevel());
		Code.load(fake);
		Code.loadConst(0);
		Code.loadConst(number_to_separate);
		Code.put(Code.astore);
		modification_two_boolean = true;
	}*/
	
	Obj modification_three_variable_in_use = null;
	
	/* char_var = #3; -> char_var = char_var + 3
	 
	 
	 public void visit(ModificationOne modifParamTwo) {
		if(modification_three_variable_in_use.getType().getKind() == Struct.Array) {
			Code.put(Code.dup2);
			if(modification_three_variable_in_use.getType().getElemType().getKind() == Struct.Char) {
				Code.put(Code.baload);
			}
			else {
				Code.put(Code.aload);
			}
		}
		else Code.load(modification_three_variable_in_use);
		
		Code.loadConst(modifParamTwo.getVal());
		Code.put(Code.add);
	}*/

	
	/* modification_4
	 
	 public void visit(RSquareClass endOfArrayUse) {
		
		Code.put(Code.dup2);
		Code.put(Code.dup2);
		Code.put(Code.pop);
		Code.put(Code.arraylength);
		Code.loadConst(2);
		Code.put(Code.div);
		Code.put(Code.add);
		Code.put(Code.dup2);
		if(array_we_are_using.getType().getElemType().getKind() == Struct.Char) {
			Code.put(Code.baload);
		}
		else {
			Code.put(Code.aload);
		}
		Code.loadConst(1);
		Code.put(Code.add);
		if(array_we_are_using.getType().getElemType().getKind() == Struct.Char) {
			Code.put(Code.bastore);
		}
		else {
			Code.put(Code.astore);
		}
	}
	
	public void visit(ModificationOne modificationFour) {
		Code.put(Code.dup2);
		Code.put(Code.pop);
		Code.put(Code.arraylength);
		Code.loadConst(2);
		Code.put(Code.div);
		Code.put(Code.add);
		if(array_we_are_using.getType().getElemType().getKind() == Struct.Char) {
			Code.put(Code.baload);
		}
		else {
			Code.put(Code.aload);
		}
	}*/
		
	Obj modification_five_left_part = null;
	
	/* sth = array # expr
	 
	 public void visit(AssignOpClass ass) {
		modification_five_left_part = array_we_are_using;
	}
	
	public void visit(HashClass hash) {
		Code.load(array_we_are_using);
	}
	
	public void visit(ModificationFive modif) {
		Code.put(Code.dup2);
		Code.put(Code.dup2);
		Code.put(Code.pop);
		Code.put(Code.arraylength);
		Code.loadConst(1);
		Code.put(Code.sub);
		Code.put(Code.dup_x1);
		Code.put(Code.pop);
		Code.put(Code.sub);
		Code.put(Code.aload);
		Code.put(Code.dup_x2);
		Code.put(Code.pop);
		Code.put(Code.aload);
		Code.put(Code.add);
		if(modification_five_left_part.getType().getKind()==Struct.Array) Code.put(Code.astore);
		else Code.store(modification_five_left_part);
	}*/
	
	int dup_place = 0;
	int addres_to_fill = 0;
	
	/*public void visit(ModificationFive modif) {
		Code.load(array_we_are_using);
		Code.loadConst(0);
		Code.put(Code.aload);
		
		// niz[0]
		
		Code.loadConst(0);
		
		// niz[0] 0
		
		dup_place = Code.pc;
		Code.put(Code.dup);
		
		// niz[0] 0 0
		
		Code.load(array_we_are_using);
		
		// niz[0] 0 0 niz
		
		Code.put(Code.arraylength);
		
		// niz[0] 0 0 array_length
		
		Code.loadConst(1);
		Code.put(Code.sub);
		
		// niz[0] 0 0 array_length-1
		
		Code.putFalseJump(Code.lt, Code.pc+1);
		addres_to_fill = Code.pc - 2;
		
		// niz[0] 0
		
		Code.loadConst(1);
		
		// niz[0] 0 1
		
		Code.put(Code.add);
		
		// niz[0] 1
		
		Code.put(Code.dup_x1);
		
		// 1 niz[0] 1
		
		Code.load(array_we_are_using);
		
		// 1 niz[0] 1 niz
		
		Code.put(Code.dup_x1);
		
		// 1 niz[0] niz 1 niz
		
		Code.put(Code.pop);
		
		// 1 niz[0] niz 1
		
		Code.put(Code.aload);
		
		// 1 niz[0] niz[1]
		
		Code.put(Code.add);
		
		// 1 niz[0]+niz[1]
		
		Code.put(Code.dup_x1);
		
		// suma 1 suma
		
		Code.put(Code.pop);
		
		// suma 1
		
		Code.putJump(dup_place);
		
		Code.fixup(addres_to_fill);

		Code.put(Code.pop);
		
		Code.store(modif.getDesignator().obj);
		
	}*/

	
	/* niz[2,3] -> temp = niz[2] niz[2] = niz[3] niz[3] = temp 
	  
	 public void visit(CommaClass commaArg) {
		// niz expr1
		
		Code.put(Code.dup_x1);
		
		// expr1 niz expr1
		
		Code.put(Code.pop);
		Code.put(Code.pop);
		
		// expr1
		
		Code.put(Code.dup);
		
		// expr1 expr1
		
		Code.load(array_we_are_using);
		
		// expr1 expr1 niz
		
		Code.put(Code.dup_x1);
		
		// expr1 niz expr1 niz
		
		Code.put(Code.pop);
		
		// expr1 niz expr1
		
		if(array_we_are_using.getType().getKind() == Struct.Char) {
			Code.put(Code.baload);
		}
		else {
			Code.put(Code.aload);
		}
		
		//expr1 niz[expr1]
	}
	
	
	public void visit(ModificationSeven modSeven) {
		// expr1 niz[expr1] expr2
		
		Code.put(Code.dup);
		
		// expr1 niz[expr1] expr2 expr2
		
		Code.load(array_we_are_using);
		
		// expr1 niz[expr1] expr2 expr2 niz
		
		Code.put(Code.dup_x1);
		
		// expr1 niz[expr1] expr2 niz expr2 niz
		
		Code.put(Code.pop);
		
		// expr1 niz[expr1] expr2 niz expr2
		
		if(array_we_are_using.getType().getKind() == Struct.Char) {
			Code.put(Code.baload);
		}
		else {
			Code.put(Code.aload);
		}
		
		// expr1 niz[expr1] expr2 niz[expr2]
		
		Code.put(Code.dup_x2);
		
 		//1 niz[2] niz[1] 2 niz[2]
		
		Code.put(Code.pop);
		
		// 1 niz[2] niz[1] 2
		
		Code.put(Code.dup_x1);
		
		// 1 niz[2] 2 niz[1] 2
		
		Code.put(Code.pop);
		
		// 1 niz[2] 2 niz[1]
		
		Code.load(array_we_are_using);
		
		// 1 niz[2] 2 niz[1] niz
		
		Code.put(Code.dup_x2);
		
		// 1 niz[2] niz 2 niz[1] niz
		
		Code.put(Code.pop);
		
		// 1 niz[2] niz 2 niz[1]
		if(array_we_are_using.getType().getKind() == Struct.Char) {
			Code.put(Code.bastore);
		}
		else {
			Code.put(Code.astore);
		}
		
		// 1 niz[2]
		
		Code.load(array_we_are_using);
		
		// 1 niz[2] niz
		
		Code.put(Code.dup_x2);
		
		// niz 1 niz[2] niz
		
		Code.put(Code.pop);
		
		// niz 1 niz[2]
		if(array_we_are_using.getType().getKind() == Struct.Char) {
			Code.put(Code.bastore);
		}
		else {
			Code.put(Code.astore);
		}
		
		// kraj
	}*/
	
    Map<String, Integer> labels = new HashMap<>();
    Map<String, List<Integer>> fixUps = new HashMap<>();
    
    /*public void visit(Ooof labelsParam) {
    	String n = current_method_used.getName() + "_" + labelsParam.getI1();
    	labels.put(n, Code.pc);
    	if (fixUps.containsKey(n)) {
    		for(int p : fixUps.get(n))
    			Code.fixup(p);
    		
    		fixUps.remove(n);
    	}
    }
	
    public void visit(GoToStmt gotoParam) {
    	String n = current_method_used.getName() + "_" + gotoParam.getI1();

    	
    	if(labels.containsKey(n)) {
    		Code.putJump(labels.get(n));
    	}
    	else {
    		Code.putJump(Code.pc+1);    	
        	if(fixUps.containsKey(n) == false) {
        		fixUps.put(n, new ArrayList<>());
        	}
    		fixUps.get(n).add(Code.pc-2);
    	}
    }*/
    
    int start = 0;
    int check_for_index_and_array_size = 0;
    int jump_if_you_have_a_new_max_value = 0;
    
    /*		get the maximum element of array
     
     public void visit(ModificationHardOne maxElem) {
    	Code.load(array_we_are_using);
    	Code.loadConst(0);
    	Code.put(Code.aload);
    	Code.loadConst(0);
    	
    	// niz[0] 0
    	
    	start = Code.pc;
    	Code.put(Code.dup);
    
    	// niz[0] 0 0
    	
    	Code.loadConst(1);
    	Code.put(Code.add);
    	
    	// niz[0] 0 1
    	
    	Code.load(array_we_are_using);
    	Code.put(Code.arraylength);
    	
    	// niz[0] 0 1 array_length
    	
    	Code.putFalseJump(Code.lt, Code.pc+1);
    	check_for_index_and_array_size = Code.pc - 2;
    	
    	// niz[0] 0
    	
    	Code.loadConst(1);
    	Code.put(Code.add);
    	
    	// niz[0] 1
    	
    	Code.put(Code.dup);
    	
    	// niz[0] 1 1
    	
    	Code.load(array_we_are_using);
    	
    	// niz[0] 1 1 niz
    	
    	Code.put(Code.dup_x1);
    	Code.put(Code.pop);
    	
    	// niz[0] 1 niz 1
    	
    	Code.put(Code.aload);
    	
    	// niz[0] 1 niz[1]
    	
    	Code.put(Code.dup_x1);
    	Code.put(Code.pop);
    	
    	// niz[0] niz[1] 1
    	
    	Code.put(Code.dup_x2);
    	Code.put(Code.pop);
    	
    	// 1 niz[0] niz[1]
    	
    	Code.put(Code.dup2);
    	
    	// 1 niz[0] niz[1] niz[0] niz[1]
    	
    	Code.putFalseJump(Code.ge, Code.pc+1);
    	jump_if_you_have_a_new_max_value = Code.pc - 2;
    	
    	// 1 niz[0] niz[1]
    	
    	Code.put(Code.pop);
    	
    	// 1 niz[0]
    	
    	Code.put(Code.dup_x1);
    	Code.put(Code.pop);
    	
    	// niz[0] 1
    	
    	Code.putJump(start);
    	
    	Code.fixup(jump_if_you_have_a_new_max_value);
    	
    	// 1 niz[0] niz[1]
    	
    	Code.put(Code.dup_x2);
    	
    	// niz[1] 1 niz[0] niz[1]
    	
    	Code.put(Code.pop);
    	Code.put(Code.pop);
    	
    	// niz[1] 1
    	
    	Code.putJump(start);
    	
    	// finish
    	Code.fixup(check_for_index_and_array_size);
    	
    	Code.put(Code.pop);
    	
    	//store to destination
    	
    	Code.store(maxElem.getDesignator().obj);
    	
    }*/
    
    /*		insert into sorted array
    
    int start_of_loop_one = 0;
    int check_if_greater_than_array_of_zero = 0; 
    int check_if_greater_than_array_of_len = 0;
    int address_if_we_reached_the_end = 0;
    int get_everything_ready_for_start_again = 0;
    
    public void visit(ModificationHardOne insertIntoSortArray) {
    	// 4
    	
    	Code.put(Code.dup); // 4 4 
    	
    	Code.load(array_we_are_using); // 4 4 niz
    	Code.loadConst(0); // 4 4 niz 0
    	Code.put(Code.aload); // 4 4 niz[0]
    	
    	Code.putFalseJump(Code.gt, Code.pc+1); //**********************************************************
    	check_if_greater_than_array_of_zero = Code.pc - 2;
    	
    	// 4
    	
    	Code.put(Code.dup); // 4 4 
    	
    	Code.load(array_we_are_using); // 4 4 niz
    	Code.load(array_we_are_using); // 4 4 niz niz
    	Code.put(Code.arraylength); // 4 4 niz array_len
    	
    	Code.loadConst(1);
    	Code.put(Code.sub); // 4 4 niz (array_len-1)
    	
    	Code.put(Code.aload); // 4 4 niz[array_len-1]
    	
    	Code.putFalseJump(Code.lt, Code.pc+1); //**********************************************************
    	check_if_greater_than_array_of_len = Code.pc - 2;
    	
    	// 4
    	
    	Code.loadConst(0);
    	
    	// START : 4 0
    	
    	start_of_loop_one = Code.pc;
    	Code.put(Code.dup); 
    	
    	// 4 0 0 
    	
    	Code.load(array_we_are_using);
    	Code.put(Code.arraylength);
    	
    	// 4 0 0 array_len
    	
    	Code.putFalseJump(Code.lt, Code.pc+1);
    	address_if_we_reached_the_end = Code.pc - 2;
    	
    	// 4 0
    	
    	Code.put(Code.dup);
    	
    	// 4 0 0
    	
    	Code.load(array_we_are_using);
    	
    	// 4 0 0 niz
    	
    	Code.put(Code.dup_x1);
    	Code.put(Code.pop);
    	
    	// 4 0 niz 0
    	
    	Code.put(Code.aload);
    	
    	// 4 0 niz[0]
    	
    	Code.put(Code.dup_x2);
    	Code.put(Code.pop);
    	
    	// niz[0] 4 0
    	
    	Code.loadConst(1);
    	Code.put(Code.add);
    	
    	// niz[0] 4 1
    	
    	Code.put(Code.dup_x2);
    	Code.put(Code.pop);
    	
    	// 1 niz[0] 4 
    	
    	Code.put(Code.dup_x2);
    	
    	// 4 1 niz[0] 4
    	
    	Code.put(Code.dup2);
    	
    	// 4 1 niz[0] 4 niz[0] 4
    	
    	Code.putFalseJump(Code.gt, Code.pc+1); //***********************************************************
    	get_everything_ready_for_start_again = Code.pc-2;
    	
    	// 4 1 niz[0] 4
    	
    	Code.put(Code.pop);
    	
    	// 4 1 niz[0]
    	
    	Code.put(Code.dup_x2);
    	Code.put(Code.pop);
    	
    	// niz[0] 4 1
    	
    	Code.put(Code.dup_x1);
    	
    	// niz[0] 1 4 1
    	
    	Code.loadConst(1);
    	Code.put(Code.sub);
    	
    	// niz[0] 1 4 0
    	
    	Code.put(Code.dup_x1);
    	Code.put(Code.pop);
    	
    	// niz[0] 1 0 4
    	
    	Code.load(array_we_are_using);
    	
    	// niz[0] 1 0 4 niz
    	
    	Code.put(Code.dup_x2);
    	Code.put(Code.pop);
    	
    	// niz[0] 1 niz 0 4 
    	
    	Code.put(Code.astore);
    	
    	// niz[0] 1
    	
    	Code.putJump(start_of_loop_one);
    	
    	Code.fixup(get_everything_ready_for_start_again);
    	
    	// 4 1 niz[0] 4 
    	
    	Code.put(Code.pop);
    	Code.put(Code.pop);

    	// 4 1 
    	
    	Code.putJump(start_of_loop_one);
    	
    	Code.fixup(address_if_we_reached_the_end);
    	
    	// 4 0
    	
    	Code.put(Code.pop);
    	
    	// 4 
    	
    	Code.fixup(check_if_greater_than_array_of_zero);
    	Code.fixup(check_if_greater_than_array_of_len);
    	
    	// 4
    	
    	Code.put(Code.pop);
    }
    */
}