// generated with ast extension for cup
// version 0.8
// 31/11/2020 1:5:7


package rs.ac.bg.etf.pp1.ast;

public class CondTermClass extends CondTerm {

    private CondFact CondFact;
    private CondTermOptional CondTermOptional;

    public CondTermClass (CondFact CondFact, CondTermOptional CondTermOptional) {
        this.CondFact=CondFact;
        if(CondFact!=null) CondFact.setParent(this);
        this.CondTermOptional=CondTermOptional;
        if(CondTermOptional!=null) CondTermOptional.setParent(this);
    }

    public CondFact getCondFact() {
        return CondFact;
    }

    public void setCondFact(CondFact CondFact) {
        this.CondFact=CondFact;
    }

    public CondTermOptional getCondTermOptional() {
        return CondTermOptional;
    }

    public void setCondTermOptional(CondTermOptional CondTermOptional) {
        this.CondTermOptional=CondTermOptional;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(CondFact!=null) CondFact.accept(visitor);
        if(CondTermOptional!=null) CondTermOptional.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(CondFact!=null) CondFact.traverseTopDown(visitor);
        if(CondTermOptional!=null) CondTermOptional.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(CondFact!=null) CondFact.traverseBottomUp(visitor);
        if(CondTermOptional!=null) CondTermOptional.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("CondTermClass(\n");

        if(CondFact!=null)
            buffer.append(CondFact.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(CondTermOptional!=null)
            buffer.append(CondTermOptional.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [CondTermClass]");
        return buffer.toString();
    }
}
