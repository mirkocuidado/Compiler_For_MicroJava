// generated with ast extension for cup
// version 0.8
// 14/0/2021 12:53:35


package rs.ac.bg.etf.pp1.ast;

public class ConditionClass extends Condition {

    private CondTerm CondTerm;
    private ConditionOptional ConditionOptional;

    public ConditionClass (CondTerm CondTerm, ConditionOptional ConditionOptional) {
        this.CondTerm=CondTerm;
        if(CondTerm!=null) CondTerm.setParent(this);
        this.ConditionOptional=ConditionOptional;
        if(ConditionOptional!=null) ConditionOptional.setParent(this);
    }

    public CondTerm getCondTerm() {
        return CondTerm;
    }

    public void setCondTerm(CondTerm CondTerm) {
        this.CondTerm=CondTerm;
    }

    public ConditionOptional getConditionOptional() {
        return ConditionOptional;
    }

    public void setConditionOptional(ConditionOptional ConditionOptional) {
        this.ConditionOptional=ConditionOptional;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(CondTerm!=null) CondTerm.accept(visitor);
        if(ConditionOptional!=null) ConditionOptional.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(CondTerm!=null) CondTerm.traverseTopDown(visitor);
        if(ConditionOptional!=null) ConditionOptional.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(CondTerm!=null) CondTerm.traverseBottomUp(visitor);
        if(ConditionOptional!=null) ConditionOptional.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConditionClass(\n");

        if(CondTerm!=null)
            buffer.append(CondTerm.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConditionOptional!=null)
            buffer.append(ConditionOptional.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConditionClass]");
        return buffer.toString();
    }
}
