// generated with ast extension for cup
// version 0.8
// 12/0/2021 16:21:31


package rs.ac.bg.etf.pp1.ast;

public class FormParYesError extends FormalParamDecl {

    public FormParYesError () {
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
        buffer.append("FormParYesError(\n");

        buffer.append(tab);
        buffer.append(") [FormParYesError]");
        return buffer.toString();
    }
}
