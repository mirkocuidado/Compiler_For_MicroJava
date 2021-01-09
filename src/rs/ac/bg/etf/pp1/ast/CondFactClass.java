// generated with ast extension for cup
// version 0.8
// 8/0/2021 22:56:20


package rs.ac.bg.etf.pp1.ast;

public class CondFactClass extends CondFact {

    private Expr Expr;
    private CondFactOptional CondFactOptional;

    public CondFactClass (Expr Expr, CondFactOptional CondFactOptional) {
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.CondFactOptional=CondFactOptional;
        if(CondFactOptional!=null) CondFactOptional.setParent(this);
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public CondFactOptional getCondFactOptional() {
        return CondFactOptional;
    }

    public void setCondFactOptional(CondFactOptional CondFactOptional) {
        this.CondFactOptional=CondFactOptional;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Expr!=null) Expr.accept(visitor);
        if(CondFactOptional!=null) CondFactOptional.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(CondFactOptional!=null) CondFactOptional.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(CondFactOptional!=null) CondFactOptional.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("CondFactClass(\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(CondFactOptional!=null)
            buffer.append(CondFactOptional.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [CondFactClass]");
        return buffer.toString();
    }
}
