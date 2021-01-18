// generated with ast extension for cup
// version 0.8
// 18/0/2021 15:32:57


package rs.ac.bg.etf.pp1.ast;

public class CondFactClass extends CondFact {

    private ExprOne ExprOne;
    private CondFactOptional CondFactOptional;

    public CondFactClass (ExprOne ExprOne, CondFactOptional CondFactOptional) {
        this.ExprOne=ExprOne;
        if(ExprOne!=null) ExprOne.setParent(this);
        this.CondFactOptional=CondFactOptional;
        if(CondFactOptional!=null) CondFactOptional.setParent(this);
    }

    public ExprOne getExprOne() {
        return ExprOne;
    }

    public void setExprOne(ExprOne ExprOne) {
        this.ExprOne=ExprOne;
    }

    public CondFactOptional getCondFactOptional() {
        return CondFactOptional;
    }

    public void setCondFactOptional(CondFactOptional CondFactOptional) {
        this.CondFactOptional=CondFactOptional;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ExprOne!=null) ExprOne.accept(visitor);
        if(CondFactOptional!=null) CondFactOptional.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ExprOne!=null) ExprOne.traverseTopDown(visitor);
        if(CondFactOptional!=null) CondFactOptional.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ExprOne!=null) ExprOne.traverseBottomUp(visitor);
        if(CondFactOptional!=null) CondFactOptional.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("CondFactClass(\n");

        if(ExprOne!=null)
            buffer.append(ExprOne.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(CondFactOptional!=null)
            buffer.append(CondFactOptional.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [CondFactClass]");
        return buffer.toString();
    }
}
