// generated with ast extension for cup
// version 0.8
// 5/0/2021 1:32:46


package rs.ac.bg.etf.pp1.ast;

public class VarDeclYesError extends VarDecl {

    public VarDeclYesError () {
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
        buffer.append("VarDeclYesError(\n");

        buffer.append(tab);
        buffer.append(") [VarDeclYesError]");
        return buffer.toString();
    }
}
