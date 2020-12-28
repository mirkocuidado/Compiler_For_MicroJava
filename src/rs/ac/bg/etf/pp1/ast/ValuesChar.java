// generated with ast extension for cup
// version 0.8
// 29/11/2020 0:2:0


package rs.ac.bg.etf.pp1.ast;

public class ValuesChar extends ValueOptions {

    public ValuesChar () {
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
        buffer.append("ValuesChar(\n");

        buffer.append(tab);
        buffer.append(") [ValuesChar]");
        return buffer.toString();
    }
}
