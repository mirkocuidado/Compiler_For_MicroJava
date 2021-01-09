// generated with ast extension for cup
// version 0.8
// 9/0/2021 20:28:31


package rs.ac.bg.etf.pp1.ast;

public class NewFactorClass extends Factor {

    private Type Type;
    private FactorOptionalSecond FactorOptionalSecond;

    public NewFactorClass (Type Type, FactorOptionalSecond FactorOptionalSecond) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.FactorOptionalSecond=FactorOptionalSecond;
        if(FactorOptionalSecond!=null) FactorOptionalSecond.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
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
        if(Type!=null) Type.accept(visitor);
        if(FactorOptionalSecond!=null) FactorOptionalSecond.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(FactorOptionalSecond!=null) FactorOptionalSecond.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(FactorOptionalSecond!=null) FactorOptionalSecond.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("NewFactorClass(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
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
