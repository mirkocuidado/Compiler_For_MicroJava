// generated with ast extension for cup
// version 0.8
// 18/0/2021 15:32:57


package rs.ac.bg.etf.pp1.ast;

public class ReturnExpr extends Statement {

    private ReturnNonTerminal ReturnNonTerminal;
    private Expr Expr;

    public ReturnExpr (ReturnNonTerminal ReturnNonTerminal, Expr Expr) {
        this.ReturnNonTerminal=ReturnNonTerminal;
        if(ReturnNonTerminal!=null) ReturnNonTerminal.setParent(this);
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
    }

    public ReturnNonTerminal getReturnNonTerminal() {
        return ReturnNonTerminal;
    }

    public void setReturnNonTerminal(ReturnNonTerminal ReturnNonTerminal) {
        this.ReturnNonTerminal=ReturnNonTerminal;
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
        if(ReturnNonTerminal!=null) ReturnNonTerminal.accept(visitor);
        if(Expr!=null) Expr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ReturnNonTerminal!=null) ReturnNonTerminal.traverseTopDown(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ReturnNonTerminal!=null) ReturnNonTerminal.traverseBottomUp(visitor);
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ReturnExpr(\n");

        if(ReturnNonTerminal!=null)
            buffer.append(ReturnNonTerminal.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ReturnExpr]");
        return buffer.toString();
    }
}
