// generated with ast extension for cup
// version 0.8
// 12/0/2021 16:21:31


package rs.ac.bg.etf.pp1.ast;

public class MethodDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Obj obj = null;

    private ReturnValue ReturnValue;
    private FormPars FormPars;
    private VarDeclList VarDeclList;
    private LBraceKeyWord LBraceKeyWord;
    private StatementList StatementList;
    private RBraceKeyWord RBraceKeyWord;

    public MethodDecl (ReturnValue ReturnValue, FormPars FormPars, VarDeclList VarDeclList, LBraceKeyWord LBraceKeyWord, StatementList StatementList, RBraceKeyWord RBraceKeyWord) {
        this.ReturnValue=ReturnValue;
        if(ReturnValue!=null) ReturnValue.setParent(this);
        this.FormPars=FormPars;
        if(FormPars!=null) FormPars.setParent(this);
        this.VarDeclList=VarDeclList;
        if(VarDeclList!=null) VarDeclList.setParent(this);
        this.LBraceKeyWord=LBraceKeyWord;
        if(LBraceKeyWord!=null) LBraceKeyWord.setParent(this);
        this.StatementList=StatementList;
        if(StatementList!=null) StatementList.setParent(this);
        this.RBraceKeyWord=RBraceKeyWord;
        if(RBraceKeyWord!=null) RBraceKeyWord.setParent(this);
    }

    public ReturnValue getReturnValue() {
        return ReturnValue;
    }

    public void setReturnValue(ReturnValue ReturnValue) {
        this.ReturnValue=ReturnValue;
    }

    public FormPars getFormPars() {
        return FormPars;
    }

    public void setFormPars(FormPars FormPars) {
        this.FormPars=FormPars;
    }

    public VarDeclList getVarDeclList() {
        return VarDeclList;
    }

    public void setVarDeclList(VarDeclList VarDeclList) {
        this.VarDeclList=VarDeclList;
    }

    public LBraceKeyWord getLBraceKeyWord() {
        return LBraceKeyWord;
    }

    public void setLBraceKeyWord(LBraceKeyWord LBraceKeyWord) {
        this.LBraceKeyWord=LBraceKeyWord;
    }

    public StatementList getStatementList() {
        return StatementList;
    }

    public void setStatementList(StatementList StatementList) {
        this.StatementList=StatementList;
    }

    public RBraceKeyWord getRBraceKeyWord() {
        return RBraceKeyWord;
    }

    public void setRBraceKeyWord(RBraceKeyWord RBraceKeyWord) {
        this.RBraceKeyWord=RBraceKeyWord;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ReturnValue!=null) ReturnValue.accept(visitor);
        if(FormPars!=null) FormPars.accept(visitor);
        if(VarDeclList!=null) VarDeclList.accept(visitor);
        if(LBraceKeyWord!=null) LBraceKeyWord.accept(visitor);
        if(StatementList!=null) StatementList.accept(visitor);
        if(RBraceKeyWord!=null) RBraceKeyWord.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ReturnValue!=null) ReturnValue.traverseTopDown(visitor);
        if(FormPars!=null) FormPars.traverseTopDown(visitor);
        if(VarDeclList!=null) VarDeclList.traverseTopDown(visitor);
        if(LBraceKeyWord!=null) LBraceKeyWord.traverseTopDown(visitor);
        if(StatementList!=null) StatementList.traverseTopDown(visitor);
        if(RBraceKeyWord!=null) RBraceKeyWord.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ReturnValue!=null) ReturnValue.traverseBottomUp(visitor);
        if(FormPars!=null) FormPars.traverseBottomUp(visitor);
        if(VarDeclList!=null) VarDeclList.traverseBottomUp(visitor);
        if(LBraceKeyWord!=null) LBraceKeyWord.traverseBottomUp(visitor);
        if(StatementList!=null) StatementList.traverseBottomUp(visitor);
        if(RBraceKeyWord!=null) RBraceKeyWord.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethodDecl(\n");

        if(ReturnValue!=null)
            buffer.append(ReturnValue.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FormPars!=null)
            buffer.append(FormPars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclList!=null)
            buffer.append(VarDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(LBraceKeyWord!=null)
            buffer.append(LBraceKeyWord.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(StatementList!=null)
            buffer.append(StatementList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(RBraceKeyWord!=null)
            buffer.append(RBraceKeyWord.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethodDecl]");
        return buffer.toString();
    }
}
