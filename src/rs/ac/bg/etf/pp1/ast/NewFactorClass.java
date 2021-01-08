// generated with ast extension for cup
// version 0.8
// 8/0/2021 16:5:42


package rs.ac.bg.etf.pp1.ast;

public class NewFactorClass extends Factor {

    private String varType;
    private FactorOptionalSecond FactorOptionalSecond;

    public NewFactorClass (String varType, FactorOptionalSecond FactorOptionalSecond) {
        this.varType=varType;
        this.FactorOptionalSecond=FactorOptionalSecond;
        if(FactorOptionalSecond!=null) FactorOptionalSecond.setParent(this);
    }

    public String getVarType() {
        return varType;
    }

    public void setVarType(String varType) {
        this.varType=varType;
    }

    public FactorOptionalSecond getFactorOptionalSecond() {
        return FactorOptionalSecond;
    }

    public void setFactorOptionalSecond(FactorOptionalSecond FactorOptionalSecond) {
        this.FactorOptionalSecond=FactorOptionalSecond;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(FactorOptionalSecond!=null) FactorOptionalSecond.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(FactorOptionalSecond!=null) FactorOptionalSecond.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(FactorOptionalSecond!=null) FactorOptionalSecond.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("NewFactorClass(\n");

        buffer.append(" "+tab+varType);
        buffer.append("\n");

        if(FactorOptionalSecond!=null)
            buffer.append(FactorOptionalSecond.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [NewFactorClass]");
        return buffer.toString();
    }
}
