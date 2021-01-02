// generated with ast extension for cup
// version 0.8
// 2/0/2021 1:31:18


package rs.ac.bg.etf.pp1.ast;

public class DesignatorClass extends Factor {

    private Designator Designator;
    private FactorOptional FactorOptional;

    public DesignatorClass (Designator Designator, FactorOptional FactorOptional) {
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.FactorOptional=FactorOptional;
        if(FactorOptional!=null) FactorOptional.setParent(this);
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public FactorOptional getFactorOptional() {
        return FactorOptional;
    }

    public void setFactorOptional(FactorOptional FactorOptional) {
        this.FactorOptional=FactorOptional;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Designator!=null) Designator.accept(visitor);
        if(FactorOptional!=null) FactorOptional.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(FactorOptional!=null) FactorOptional.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(FactorOptional!=null) FactorOptional.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorClass(\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FactorOptional!=null)
            buffer.append(FactorOptional.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorClass]");
        return buffer.toString();
    }
}
