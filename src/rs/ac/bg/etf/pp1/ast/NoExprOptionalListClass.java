// generated with ast extension for cup
// version 0.8
// 8/0/2021 22:56:20


package rs.ac.bg.etf.pp1.ast;

public class NoExprOptionalListClass extends ExprOptionalList {

    public NoExprOptionalListClass () {
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
        buffer.append("NoExprOptionalListClass(\n");

        buffer.append(tab);
        buffer.append(") [NoExprOptionalListClass]");
        return buffer.toString();
    }
}
