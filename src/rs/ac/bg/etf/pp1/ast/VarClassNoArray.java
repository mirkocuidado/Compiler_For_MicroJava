// generated with ast extension for cup
// version 0.8
// 12/0/2021 16:21:31


package rs.ac.bg.etf.pp1.ast;

public class VarClassNoArray extends Variable {

    private String varName;

    public VarClassNoArray (String varName) {
        this.varName=varName;
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName=varName;
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
        buffer.append("VarClassNoArray(\n");

        buffer.append(" "+tab+varName);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarClassNoArray]");
        return buffer.toString();
    }
}
