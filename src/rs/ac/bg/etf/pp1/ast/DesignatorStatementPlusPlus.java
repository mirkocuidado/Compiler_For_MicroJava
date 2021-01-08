// generated with ast extension for cup
// version 0.8
// 8/0/2021 16:5:42


package rs.ac.bg.etf.pp1.ast;

public class DesignatorStatementPlusPlus extends DesignatorStatementOptions {

    public DesignatorStatementPlusPlus () {
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
        buffer.append("DesignatorStatementPlusPlus(\n");

        buffer.append(tab);
        buffer.append(") [DesignatorStatementPlusPlus]");
        return buffer.toString();
    }
}
