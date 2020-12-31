// generated with ast extension for cup
// version 0.8
// 31/11/2020 1:5:7


package rs.ac.bg.etf.pp1.ast;

public class ConstListNoArray extends ConstVariable {

    private String constVarName;
    private ValueOptions ValueOptions;

    public ConstListNoArray (String constVarName, ValueOptions ValueOptions) {
        this.constVarName=constVarName;
        this.ValueOptions=ValueOptions;
        if(ValueOptions!=null) ValueOptions.setParent(this);
    }

    public String getConstVarName() {
        return constVarName;
    }

    public void setConstVarName(String constVarName) {
        this.constVarName=constVarName;
    }

    public ValueOptions getValueOptions() {
        return ValueOptions;
    }

    public void setValueOptions(ValueOptions ValueOptions) {
        this.ValueOptions=ValueOptions;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ValueOptions!=null) ValueOptions.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ValueOptions!=null) ValueOptions.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ValueOptions!=null) ValueOptions.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstListNoArray(\n");

        buffer.append(" "+tab+constVarName);
        buffer.append("\n");

        if(ValueOptions!=null)
            buffer.append(ValueOptions.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstListNoArray]");
        return buffer.toString();
    }
}
