// generated with ast extension for cup
// version 0.8
// 12/0/2021 16:21:31


package rs.ac.bg.etf.pp1.ast;

public class ReturnValueClassVoid extends ReturnValue {

    private String methName;

    public ReturnValueClassVoid (String methName) {
        this.methName=methName;
    }

    public String getMethName() {
        return methName;
    }

    public void setMethName(String methName) {
        this.methName=methName;
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
        buffer.append("ReturnValueClassVoid(\n");

        buffer.append(" "+tab+methName);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ReturnValueClassVoid]");
        return buffer.toString();
    }
}
