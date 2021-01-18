// generated with ast extension for cup
// version 0.8
// 18/0/2021 15:32:58


package rs.ac.bg.etf.pp1.ast;

public interface Visitor { 

    public void visit(FormPars FormPars);
    public void visit(AssignOpKeyWord AssignOpKeyWord);
    public void visit(FakeAND FakeAND);
    public void visit(Factor Factor);
    public void visit(Statement Statement);
    public void visit(GroupDeclList GroupDeclList);
    public void visit(TernaryKeyWord TernaryKeyWord);
    public void visit(FakeOR FakeOR);
    public void visit(ConstList ConstList);
    public void visit(ConstVariable ConstVariable);
    public void visit(RParenBrother RParenBrother);
    public void visit(CondTermOptional CondTermOptional);
    public void visit(FlagSet FlagSet);
    public void visit(FormalParamList FormalParamList);
    public void visit(Variable Variable);
    public void visit(ExprOptionalList ExprOptionalList);
    public void visit(PotentialError PotentialError);
    public void visit(LSquareKeyWord LSquareKeyWord);
    public void visit(Expr Expr);
    public void visit(ValueOptions ValueOptions);
    public void visit(VarDecl VarDecl);
    public void visit(ExprPositive ExprPositive);
    public void visit(MethodsOption MethodsOption);
    public void visit(PrintCommaNumber PrintCommaNumber);
    public void visit(Unmatched Unmatched);
    public void visit(ExprOne ExprOne);
    public void visit(LBraceKeyWord LBraceKeyWord);
    public void visit(RelOp RelOp);
    public void visit(OptionalDesignator OptionalDesignator);
    public void visit(EmptyHelper EmptyHelper);
    public void visit(ActualParamList ActualParamList);
    public void visit(FactorOptionalSecond FactorOptionalSecond);
    public void visit(Helper Helper);
    public void visit(TermOptionalList TermOptionalList);
    public void visit(Condition Condition);
    public void visit(ExtendsOption ExtendsOption);
    public void visit(CondFactOptional CondFactOptional);
    public void visit(DesignatorStatement DesignatorStatement);
    public void visit(FactorOptional FactorOptional);
    public void visit(StatementList StatementList);
    public void visit(ConstDecl ConstDecl);
    public void visit(ColonKeyWord ColonKeyWord);
    public void visit(LParenBrother LParenBrother);
    public void visit(MulOp MulOp);
    public void visit(CondTerm CondTerm);
    public void visit(GroupDecl GroupDecl);
    public void visit(ConditionOptional ConditionOptional);
    public void visit(FormalParamDecl FormalParamDecl);
    public void visit(ActualPars ActualPars);
    public void visit(VarList VarList);
    public void visit(ReturnNonTerminal ReturnNonTerminal);
    public void visit(ClassDecl ClassDecl);
    public void visit(ElseKeyWord ElseKeyWord);
    public void visit(SwitchStatementList SwitchStatementList);
    public void visit(MethodDeclList MethodDeclList);
    public void visit(AddOp AddOp);
    public void visit(ExprNegative ExprNegative);
    public void visit(AssignOp AssignOp);
    public void visit(RBraceKeyWord RBraceKeyWord);
    public void visit(Fake Fake);
    public void visit(OptionalElse OptionalElse);
    public void visit(Matched Matched);
    public void visit(VarDeclList VarDeclList);
    public void visit(CondFact CondFact);
    public void visit(ReturnValue ReturnValue);
    public void visit(Term Term);
    public void visit(LParenFactor LParenFactor);
    public void visit(DoKeyWord DoKeyWord);
    public void visit(BreakKeyWord BreakKeyWord);
    public void visit(ModOperationClass ModOperationClass);
    public void visit(DivideOperationClass DivideOperationClass);
    public void visit(MultiplyOperationClass MultiplyOperationClass);
    public void visit(LessOrEqualOperationClass LessOrEqualOperationClass);
    public void visit(GreaterOrEqualOperationClass GreaterOrEqualOperationClass);
    public void visit(LessOperationClass LessOperationClass);
    public void visit(GreaterOperationClass GreaterOperationClass);
    public void visit(RelationOperationClassNotEqualComparation RelationOperationClassNotEqualComparation);
    public void visit(RelationOperationClassEqualComparation RelationOperationClassEqualComparation);
    public void visit(AssignOperationClass AssignOperationClass);
    public void visit(MinusOperation MinusOperation);
    public void visit(PlusOperation PlusOperation);
    public void visit(NoFactorOptionalSecondClass NoFactorOptionalSecondClass);
    public void visit(FactorOptionalSecondClass FactorOptionalSecondClass);
    public void visit(LParenFactorClass LParenFactorClass);
    public void visit(ParenFactorClass ParenFactorClass);
    public void visit(NewFactorClass NewFactorClass);
    public void visit(BooleanClassConstFactor BooleanClassConstFactor);
    public void visit(CharConst CharConst);
    public void visit(NumberConst NumberConst);
    public void visit(FactorOptionalParams FactorOptionalParams);
    public void visit(DesignatorClass DesignatorClass);
    public void visit(NoTermOptionalListClass NoTermOptionalListClass);
    public void visit(TermOptionalListClass TermOptionalListClass);
    public void visit(TermClass TermClass);
    public void visit(ColonClass ColonClass);
    public void visit(TernaryClass TernaryClass);
    public void visit(NoExprOptionalListClass NoExprOptionalListClass);
    public void visit(ExprOptionalListClass ExprOptionalListClass);
    public void visit(ClassTwoClass ClassTwoClass);
    public void visit(ClassOneClass ClassOneClass);
    public void visit(ExprSecondOptionClassMinus ExprSecondOptionClassMinus);
    public void visit(NormalExpressionClass NormalExpressionClass);
    public void visit(ExprFirstOptionClassWithMinus ExprFirstOptionClassWithMinus);
    public void visit(ExprNegativeClass ExprNegativeClass);
    public void visit(NoCondFactOptionalClass NoCondFactOptionalClass);
    public void visit(CondFactOptionalClass CondFactOptionalClass);
    public void visit(CondFactClass CondFactClass);
    public void visit(FakeAndClass FakeAndClass);
    public void visit(NoCondTermOptionalClass NoCondTermOptionalClass);
    public void visit(CondTermOptionalClass CondTermOptionalClass);
    public void visit(CondTermClass CondTermClass);
    public void visit(FakeOrClass FakeOrClass);
    public void visit(NoConditionOptionalClass NoConditionOptionalClass);
    public void visit(ConditionOptionalClass ConditionOptionalClass);
    public void visit(ConditionError ConditionError);
    public void visit(ConditionClass ConditionClass);
    public void visit(ActualParam ActualParam);
    public void visit(ActualParams ActualParams);
    public void visit(NoActuals NoActuals);
    public void visit(Actuals Actuals);
    public void visit(NoSwitchStatementList NoSwitchStatementList);
    public void visit(SwitchStatementListClass SwitchStatementListClass);
    public void visit(NoCommaNumberClass NoCommaNumberClass);
    public void visit(CommaNumberClass CommaNumberClass);
    public void visit(IfConditionNoError IfConditionNoError);
    public void visit(NoStmt NoStmt);
    public void visit(Statements Statements);
    public void visit(ReturnNonTerminalClass ReturnNonTerminalClass);
    public void visit(ContinueKeyWord ContinueKeyWord);
    public void visit(BreakKeyWordClass BreakKeyWordClass);
    public void visit(DoClass DoClass);
    public void visit(ElseClassBaby ElseClassBaby);
    public void visit(NoOptionalStatement NoOptionalStatement);
    public void visit(YesOptionalStatement YesOptionalStatement);
    public void visit(Empty Empty);
    public void visit(RParenClass RParenClass);
    public void visit(LParenClass LParenClass);
    public void visit(StatementStatement StatementStatement);
    public void visit(SwitchStatement SwitchStatement);
    public void visit(DoWhileStatement DoWhileStatement);
    public void visit(IfStatement IfStatement);
    public void visit(ReadClass ReadClass);
    public void visit(ContinueClass ContinueClass);
    public void visit(BreakClass BreakClass);
    public void visit(ReturnNoExpr ReturnNoExpr);
    public void visit(ReturnExpr ReturnExpr);
    public void visit(PrintStmt PrintStmt);
    public void visit(ErrorStmt ErrorStmt);
    public void visit(Assignment Assignment);
    public void visit(AssignOpClass AssignOpClass);
    public void visit(ModificationHardOne ModificationHardOne);
    public void visit(DesignatorStatementMinusMinus DesignatorStatementMinusMinus);
    public void visit(DesignatorStatementPlusPlus DesignatorStatementPlusPlus);
    public void visit(DesignatorStatementActualParameters DesignatorStatementActualParameters);
    public void visit(DesignatorStatementOptionsClassAssignExpression DesignatorStatementOptionsClassAssignExpression);
    public void visit(HelperClass HelperClass);
    public void visit(LSquareClass LSquareClass);
    public void visit(NoOptionalDesignatorClass NoOptionalDesignatorClass);
    public void visit(OptionalDesignatorArray OptionalDesignatorArray);
    public void visit(OptionalDesignatorClass OptionalDesignatorClass);
    public void visit(Designator Designator);
    public void visit(DesigIdent DesigIdent);
    public void visit(FormParYesError FormParYesError);
    public void visit(FormalParamDeclClassArray FormalParamDeclClassArray);
    public void visit(FormalParamDeclClassNoArray FormalParamDeclClassNoArray);
    public void visit(SingleFormalParamDecl SingleFormalParamDecl);
    public void visit(FormalParamDecls FormalParamDecls);
    public void visit(NoFormParam NoFormParam);
    public void visit(FormParams FormParams);
    public void visit(ReturnValueClassVoid ReturnValueClassVoid);
    public void visit(ReturnValueClassNoVoid ReturnValueClassNoVoid);
    public void visit(RBraceClass RBraceClass);
    public void visit(LBraceClass LBraceClass);
    public void visit(MethodDecl MethodDecl);
    public void visit(NoMethodDecl NoMethodDecl);
    public void visit(MethodDeclarations MethodDeclarations);
    public void visit(Type Type);
    public void visit(ErrorVariable ErrorVariable);
    public void visit(VarClassArray VarClassArray);
    public void visit(VarClassNoArray VarClassNoArray);
    public void visit(VarListSingle VarListSingle);
    public void visit(VarListClass VarListClass);
    public void visit(VarDeclYesError VarDeclYesError);
    public void visit(VarDeclClass VarDeclClass);
    public void visit(NoVarDeclList NoVarDeclList);
    public void visit(VarDeclListClass VarDeclListClass);
    public void visit(ValuesChar ValuesChar);
    public void visit(ValuesBoolean ValuesBoolean);
    public void visit(ValuesNumber ValuesNumber);
    public void visit(ErrorConstVariableList ErrorConstVariableList);
    public void visit(ConstListNoArray ConstListNoArray);
    public void visit(ConstListSingle ConstListSingle);
    public void visit(ConstListClass ConstListClass);
    public void visit(ConstDeclYesError ConstDeclYesError);
    public void visit(ConstDeclClass ConstDeclClass);
    public void visit(NoMethodsOption NoMethodsOption);
    public void visit(MethodsClassName MethodsClassName);
    public void visit(NoExtendsOption NoExtendsOption);
    public void visit(ExtendsClassName ExtendsClassName);
    public void visit(ClassDeclClass ClassDeclClass);
    public void visit(GroupDeclarationsConsts GroupDeclarationsConsts);
    public void visit(GroupDeclarationClasses GroupDeclarationClasses);
    public void visit(GroupDeclarationVars GroupDeclarationVars);
    public void visit(NoGroupDecl NoGroupDecl);
    public void visit(GroupDeclarations GroupDeclarations);
    public void visit(ProgramName ProgramName);
    public void visit(Program Program);

}
