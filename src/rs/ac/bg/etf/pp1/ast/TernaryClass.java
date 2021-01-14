// generated with ast extension for cup
// version 0.8
// 14/0/2021 22:20:26


package rs.ac.bg.etf.pp1.ast;

public class TernaryClass extends TernaryKeyWord {

    public TernaryClass () {
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
        buffer.append("TernaryClass(\n");

        buffer.append(tab);
        buffer.append(") [TernaryClass]");
        return buffer.toString();
    }
}
