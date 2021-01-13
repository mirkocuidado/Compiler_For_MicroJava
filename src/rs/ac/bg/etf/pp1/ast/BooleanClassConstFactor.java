// generated with ast extension for cup
// version 0.8
// 12/0/2021 16:21:32


package rs.ac.bg.etf.pp1.ast;

public class BooleanClassConstFactor extends Factor {

    private String val;

    public BooleanClassConstFactor (String val) {
        this.val=val;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val=val;
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
        buffer.append("BooleanClassConstFactor(\n");

        buffer.append(" "+tab+val);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [BooleanClassConstFactor]");
        return buffer.toString();
    }
}
