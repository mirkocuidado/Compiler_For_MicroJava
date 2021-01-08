// generated with ast extension for cup
// version 0.8
// 8/0/2021 16:5:42


package rs.ac.bg.etf.pp1.ast;

public class ClassTwoClass extends Expr {

    private ExprOne ExprOne;
    private ExprOne ExprOne1;
    private ExprOne ExprOne2;

    public ClassTwoClass (ExprOne ExprOne, ExprOne ExprOne1, ExprOne ExprOne2) {
        this.ExprOne=ExprOne;
        if(ExprOne!=null) ExprOne.setParent(this);
        this.ExprOne1=ExprOne1;
        if(ExprOne1!=null) ExprOne1.setParent(this);
        this.ExprOne2=ExprOne2;
        if(ExprOne2!=null) ExprOne2.setParent(this);
    }

    public ExprOne getExprOne() {
        return ExprOne;
    }

    public void setExprOne(ExprOne ExprOne) {
        this.ExprOne=ExprOne;
    }

    public ExprOne getExprOne1() {
        return ExprOne1;
    }

    public void setExprOne1(ExprOne ExprOne1) {
        this.ExprOne1=ExprOne1;
    }

    public ExprOne getExprOne2() {
        return ExprOne2;
    }

    public void setExprOne2(ExprOne ExprOne2) {
        this.ExprOne2=ExprOne2;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ExprOne!=null) ExprOne.accept(visitor);
        if(ExprOne1!=null) ExprOne1.accept(visitor);
        if(ExprOne2!=null) ExprOne2.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ExprOne!=null) ExprOne.traverseTopDown(visitor);
        if(ExprOne1!=null) ExprOne1.traverseTopDown(visitor);
        if(ExprOne2!=null) ExprOne2.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ExprOne!=null) ExprOne.traverseBottomUp(visitor);
        if(ExprOne1!=null) ExprOne1.traverseBottomUp(visitor);
        if(ExprOne2!=null) ExprOne2.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassTwoClass(\n");

        if(ExprOne!=null)
            buffer.append(ExprOne.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ExprOne1!=null)
            buffer.append(ExprOne1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ExprOne2!=null)
            buffer.append(ExprOne2.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassTwoClass]");
        return buffer.toString();
    }
}
