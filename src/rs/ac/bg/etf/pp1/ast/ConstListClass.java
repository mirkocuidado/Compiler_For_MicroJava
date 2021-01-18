// generated with ast extension for cup
// version 0.8
// 18/0/2021 15:32:57


package rs.ac.bg.etf.pp1.ast;

public class ConstListClass extends ConstList {

    private ConstList ConstList;
    private ConstVariable ConstVariable;

    public ConstListClass (ConstList ConstList, ConstVariable ConstVariable) {
        this.ConstList=ConstList;
        if(ConstList!=null) ConstList.setParent(this);
        this.ConstVariable=ConstVariable;
        if(ConstVariable!=null) ConstVariable.setParent(this);
    }

    public ConstList getConstList() {
        return ConstList;
    }

    public void setConstList(ConstList ConstList) {
        this.ConstList=ConstList;
    }

    public ConstVariable getConstVariable() {
        return ConstVariable;
    }

    public void setConstVariable(ConstVariable ConstVariable) {
        this.ConstVariable=ConstVariable;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstList!=null) ConstList.accept(visitor);
        if(ConstVariable!=null) ConstVariable.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstList!=null) ConstList.traverseTopDown(visitor);
        if(ConstVariable!=null) ConstVariable.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstList!=null) ConstList.traverseBottomUp(visitor);
        if(ConstVariable!=null) ConstVariable.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstListClass(\n");

        if(ConstList!=null)
            buffer.append(ConstList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstVariable!=null)
            buffer.append(ConstVariable.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstListClass]");
        return buffer.toString();
    }
}
