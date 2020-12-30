// generated with ast extension for cup
// version 0.8
// 30/11/2020 1:20:9


package rs.ac.bg.etf.pp1.ast;

public class OptionalDesignatorArray extends OptionalDesignator {

    private OptionalDesignator OptionalDesignator;
    private Expr Expr;

    public OptionalDesignatorArray (OptionalDesignator OptionalDesignator, Expr Expr) {
        this.OptionalDesignator=OptionalDesignator;
        if(OptionalDesignator!=null) OptionalDesignator.setParent(this);
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
    }

    public OptionalDesignator getOptionalDesignator() {
        return OptionalDesignator;
    }

    public void setOptionalDesignator(OptionalDesignator OptionalDesignator) {
        this.OptionalDesignator=OptionalDesignator;
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
        if(OptionalDesignator!=null) OptionalDesignator.accept(visitor);
        if(Expr!=null) Expr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(OptionalDesignator!=null) OptionalDesignator.traverseTopDown(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(OptionalDesignator!=null) OptionalDesignator.traverseBottomUp(visitor);
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("OptionalDesignatorArray(\n");

        if(OptionalDesignator!=null)
            buffer.append(OptionalDesignator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [OptionalDesignatorArray]");
        return buffer.toString();
    }
}
