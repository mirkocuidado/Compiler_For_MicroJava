// generated with ast extension for cup
// version 0.8
// 12/0/2021 16:21:32


package rs.ac.bg.etf.pp1.ast;

public class MinusOperation extends AddOp {

    private String M1;

    public MinusOperation (String M1) {
        this.M1=M1;
    }

    public String getM1() {
        return M1;
    }

    public void setM1(String M1) {
        this.M1=M1;
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
        buffer.append("MinusOperation(\n");

        buffer.append(" "+tab+M1);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MinusOperation]");
        return buffer.toString();
    }
}
