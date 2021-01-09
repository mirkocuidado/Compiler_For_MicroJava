// generated with ast extension for cup
// version 0.8
// 9/0/2021 20:28:31


package rs.ac.bg.etf.pp1.ast;

public class ClassOneClass extends Expr {

    private ExprOne ExprOne;

    public ClassOneClass (ExprOne ExprOne) {
        this.ExprOne=ExprOne;
        if(ExprOne!=null) ExprOne.setParent(this);
    }

    public ExprOne getExprOne() {
        return ExprOne;
    }

    public void setExprOne(ExprOne ExprOne) {
        this.ExprOne=ExprOne;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ExprOne!=null) ExprOne.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ExprOne!=null) ExprOne.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ExprOne!=null) ExprOne.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassOneClass(\n");

        if(ExprOne!=null)
            buffer.append(ExprOne.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassOneClass]");
        return buffer.toString();
    }
}
