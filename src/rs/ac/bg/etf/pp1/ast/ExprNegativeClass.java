// generated with ast extension for cup
// version 0.8
// 14/0/2021 22:20:26


package rs.ac.bg.etf.pp1.ast;

public class ExprNegativeClass extends ExprNegative {

    private String M1;
    private ExprPositive ExprPositive;

    public ExprNegativeClass (String M1, ExprPositive ExprPositive) {
        this.M1=M1;
        this.ExprPositive=ExprPositive;
        if(ExprPositive!=null) ExprPositive.setParent(this);
    }

    public String getM1() {
        return M1;
    }

    public void setM1(String M1) {
        this.M1=M1;
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

        buffer.append(" "+tab+M1);
        buffer.append("\n");

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
