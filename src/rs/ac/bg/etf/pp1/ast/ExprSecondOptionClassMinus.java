// generated with ast extension for cup
// version 0.8
// 9/0/2021 20:28:31


package rs.ac.bg.etf.pp1.ast;

public class ExprSecondOptionClassMinus extends ExprOne {

    private ExprNegative ExprNegative;

    public ExprSecondOptionClassMinus (ExprNegative ExprNegative) {
        this.ExprNegative=ExprNegative;
        if(ExprNegative!=null) ExprNegative.setParent(this);
    }

    public ExprNegative getExprNegative() {
        return ExprNegative;
    }

    public void setExprNegative(ExprNegative ExprNegative) {
        this.ExprNegative=ExprNegative;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ExprNegative!=null) ExprNegative.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ExprNegative!=null) ExprNegative.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ExprNegative!=null) ExprNegative.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ExprSecondOptionClassMinus(\n");

        if(ExprNegative!=null)
            buffer.append(ExprNegative.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ExprSecondOptionClassMinus]");
        return buffer.toString();
    }
}
