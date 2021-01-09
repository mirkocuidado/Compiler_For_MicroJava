// generated with ast extension for cup
// version 0.8
// 9/0/2021 20:28:31


package rs.ac.bg.etf.pp1.ast;

public class SwitchStatement extends Statement {

    private Expr Expr;
    private SwitchStatementList SwitchStatementList;

    public SwitchStatement (Expr Expr, SwitchStatementList SwitchStatementList) {
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.SwitchStatementList=SwitchStatementList;
        if(SwitchStatementList!=null) SwitchStatementList.setParent(this);
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public SwitchStatementList getSwitchStatementList() {
        return SwitchStatementList;
    }

    public void setSwitchStatementList(SwitchStatementList SwitchStatementList) {
        this.SwitchStatementList=SwitchStatementList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Expr!=null) Expr.accept(visitor);
        if(SwitchStatementList!=null) SwitchStatementList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(SwitchStatementList!=null) SwitchStatementList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(SwitchStatementList!=null) SwitchStatementList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SwitchStatement(\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(SwitchStatementList!=null)
            buffer.append(SwitchStatementList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SwitchStatement]");
        return buffer.toString();
    }
}
