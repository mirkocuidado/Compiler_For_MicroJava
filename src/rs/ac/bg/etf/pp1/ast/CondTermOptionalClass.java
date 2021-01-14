// generated with ast extension for cup
// version 0.8
// 14/0/2021 12:53:35


package rs.ac.bg.etf.pp1.ast;

public class CondTermOptionalClass extends CondTermOptional {

    private CondTermOptional CondTermOptional;
    private CondFact CondFact;

    public CondTermOptionalClass (CondTermOptional CondTermOptional, CondFact CondFact) {
        this.CondTermOptional=CondTermOptional;
        if(CondTermOptional!=null) CondTermOptional.setParent(this);
        this.CondFact=CondFact;
        if(CondFact!=null) CondFact.setParent(this);
    }

    public CondTermOptional getCondTermOptional() {
        return CondTermOptional;
    }

    public void setCondTermOptional(CondTermOptional CondTermOptional) {
        this.CondTermOptional=CondTermOptional;
    }

    public CondFact getCondFact() {
        return CondFact;
    }

    public void setCondFact(CondFact CondFact) {
        this.CondFact=CondFact;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(CondTermOptional!=null) CondTermOptional.accept(visitor);
        if(CondFact!=null) CondFact.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(CondTermOptional!=null) CondTermOptional.traverseTopDown(visitor);
        if(CondFact!=null) CondFact.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(CondTermOptional!=null) CondTermOptional.traverseBottomUp(visitor);
        if(CondFact!=null) CondFact.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("CondTermOptionalClass(\n");

        if(CondTermOptional!=null)
            buffer.append(CondTermOptional.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(CondFact!=null)
            buffer.append(CondFact.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [CondTermOptionalClass]");
        return buffer.toString();
    }
}
