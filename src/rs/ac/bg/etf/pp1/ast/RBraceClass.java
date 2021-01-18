// generated with ast extension for cup
// version 0.8
// 18/0/2021 15:32:57


package rs.ac.bg.etf.pp1.ast;

public class RBraceClass extends RBraceKeyWord {

    public RBraceClass () {
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
        buffer.append("RBraceClass(\n");

        buffer.append(tab);
        buffer.append(") [RBraceClass]");
        return buffer.toString();
    }
}
