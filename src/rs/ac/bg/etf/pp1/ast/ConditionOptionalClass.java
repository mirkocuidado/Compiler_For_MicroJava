// generated with ast extension for cup
// version 0.8
// 30/11/2020 11:33:53


package rs.ac.bg.etf.pp1.ast;

public class ConditionOptionalClass extends ConditionOptional {

    private ConditionOptional ConditionOptional;
    private CondTerm CondTerm;

    public ConditionOptionalClass (ConditionOptional ConditionOptional, CondTerm CondTerm) {
        this.ConditionOptional=ConditionOptional;
        if(ConditionOptional!=null) ConditionOptional.setParent(this);
        this.CondTerm=CondTerm;
        if(CondTerm!=null) CondTerm.setParent(this);
    }

    public ConditionOptional getConditionOptional() {
        return ConditionOptional;
    }

    public void setConditionOptional(ConditionOptional ConditionOptional) {
        this.ConditionOptional=ConditionOptional;
    }

    public CondTerm getCondTerm() {
        return CondTerm;
    }

    public void setCondTerm(CondTerm CondTerm) {
        this.CondTerm=CondTerm;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConditionOptional!=null) ConditionOptional.accept(visitor);
        if(CondTerm!=null) CondTerm.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConditionOptional!=null) ConditionOptional.traverseTopDown(visitor);
        if(CondTerm!=null) CondTerm.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConditionOptional!=null) ConditionOptional.traverseBottomUp(visitor);
        if(CondTerm!=null) CondTerm.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConditionOptionalClass(\n");

        if(ConditionOptional!=null)
            buffer.append(ConditionOptional.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(CondTerm!=null)
            buffer.append(CondTerm.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConditionOptionalClass]");
        return buffer.toString();
    }
}
