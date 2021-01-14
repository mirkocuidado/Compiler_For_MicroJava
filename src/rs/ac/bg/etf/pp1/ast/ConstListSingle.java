// generated with ast extension for cup
// version 0.8
// 14/0/2021 22:20:26


package rs.ac.bg.etf.pp1.ast;

public class ConstListSingle extends ConstList {

    private ConstVariable ConstVariable;

    public ConstListSingle (ConstVariable ConstVariable) {
        this.ConstVariable=ConstVariable;
        if(ConstVariable!=null) ConstVariable.setParent(this);
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
        if(ConstVariable!=null) ConstVariable.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstVariable!=null) ConstVariable.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstVariable!=null) ConstVariable.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstListSingle(\n");

        if(ConstVariable!=null)
            buffer.append(ConstVariable.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstListSingle]");
        return buffer.toString();
    }
}
