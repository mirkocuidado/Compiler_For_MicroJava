// generated with ast extension for cup
// version 0.8
// 18/0/2021 15:32:58


package rs.ac.bg.etf.pp1.ast;

public class MultiplyOperationClass extends MulOp {

    private String val;

    public MultiplyOperationClass (String val) {
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
        buffer.append("MultiplyOperationClass(\n");

        buffer.append(" "+tab+val);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MultiplyOperationClass]");
        return buffer.toString();
    }
}
