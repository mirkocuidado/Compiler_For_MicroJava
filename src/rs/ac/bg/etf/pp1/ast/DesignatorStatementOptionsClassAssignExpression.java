// generated with ast extension for cup
// version 0.8
// 12/0/2021 16:21:32


package rs.ac.bg.etf.pp1.ast;

public class DesignatorStatementOptionsClassAssignExpression extends DesignatorStatement {

    private Designator Designator;
    private AssignOpKeyWord AssignOpKeyWord;
    private Expr Expr;

    public DesignatorStatementOptionsClassAssignExpression (Designator Designator, AssignOpKeyWord AssignOpKeyWord, Expr Expr) {
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.AssignOpKeyWord=AssignOpKeyWord;
        if(AssignOpKeyWord!=null) AssignOpKeyWord.setParent(this);
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
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
        if(Designator!=null) Designator.accept(visitor);
        if(AssignOpKeyWord!=null) AssignOpKeyWord.accept(visitor);
        if(Expr!=null) Expr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(AssignOpKeyWord!=null) AssignOpKeyWord.traverseTopDown(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(AssignOpKeyWord!=null) AssignOpKeyWord.traverseBottomUp(visitor);
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorStatementOptionsClassAssignExpression(\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

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
