// generated with ast extension for cup
// version 0.8
// 31/11/2020 1:5:7


package rs.ac.bg.etf.pp1.ast;

public class ExprNegativeClass extends ExprNegative {

    private ExprPositive ExprPositive;

    public ExprNegativeClass (ExprPositive ExprPositive) {
        this.ExprPositive=ExprPositive;
        if(ExprPositive!=null) ExprPositive.setParent(this);
    }

    public ExprPositive getExprPositive() {
        return ExprPositive;
    }

    public void setExprPositive(ExprPositive ExprPositive) {
        this.ExprPositive=ExprPositive;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ExprPositive!=null) ExprPositive.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ExprPositive!=null) ExprPositive.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ExprPositive!=null) ExprPositive.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ExprNegativeClass(\n");

        if(ExprPositive!=null)
            buffer.append(ExprPositive.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ExprNegativeClass]");
        return buffer.toString();
    }
}
