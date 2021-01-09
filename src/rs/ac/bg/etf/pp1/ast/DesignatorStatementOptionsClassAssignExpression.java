// generated with ast extension for cup
// version 0.8
// 9/0/2021 20:28:31


package rs.ac.bg.etf.pp1.ast;

public class DesignatorStatementOptionsClassAssignExpression extends DesignatorStatementOptions {

    private AssignOpKeyWord AssignOpKeyWord;
    private Expr Expr;

    public DesignatorStatementOptionsClassAssignExpression (AssignOpKeyWord AssignOpKeyWord, Expr Expr) {
        this.AssignOpKeyWord=AssignOpKeyWord;
        if(AssignOpKeyWord!=null) AssignOpKeyWord.setParent(this);
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
    }

    public AssignOpKeyWord getAssignOpKeyWord() {
        return AssignOpKeyWord;
    }

    public void setAssignOpKeyWord(AssignOpKeyWord AssignOpKeyWord) {
        this.AssignOpKeyWord=AssignOpKeyWord;
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(AssignOpKeyWord!=null) AssignOpKeyWord.accept(visitor);
        if(Expr!=null) Expr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(AssignOpKeyWord!=null) AssignOpKeyWord.traverseTopDown(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(AssignOpKeyWord!=null) AssignOpKeyWord.traverseBottomUp(visitor);
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorStatementOptionsClassAssignExpression(\n");

        if(AssignOpKeyWord!=null)
            buffer.append(AssignOpKeyWord.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorStatementOptionsClassAssignExpression]");
        return buffer.toString();
    }
}
