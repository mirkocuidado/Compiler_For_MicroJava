// generated with ast extension for cup
// version 0.8
// 31/11/2020 1:5:7


package rs.ac.bg.etf.pp1.ast;

public class IfConditionYesError extends PotentialError {

    public IfConditionYesError () {
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("IfConditionYesError(\n");

        buffer.append(tab);
        buffer.append(") [IfConditionYesError]");
        return buffer.toString();
    }
}
