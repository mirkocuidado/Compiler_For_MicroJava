// generated with ast extension for cup
// version 0.8
// 12/0/2021 16:21:32


package rs.ac.bg.etf.pp1.ast;

public class ClassTwoClass extends Expr {

    private CondFact CondFact;
    private ExprOne ExprOne;
    private ExprOne ExprOne1;

    public ClassTwoClass (CondFact CondFact, ExprOne ExprOne, ExprOne ExprOne1) {
        this.CondFact=CondFact;
        if(CondFact!=null) CondFact.setParent(this);
        this.ExprOne=ExprOne;
        if(ExprOne!=null) ExprOne.setParent(this);
        this.ExprOne1=ExprOne1;
        if(ExprOne1!=null) ExprOne1.setParent(this);
    }

    public CondFact getCondFact() {
        return CondFact;
    }

    public void setCondFact(CondFact CondFact) {
        this.CondFact=CondFact;
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

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(CondFact!=null) CondFact.accept(visitor);
        if(ExprOne!=null) ExprOne.accept(visitor);
        if(ExprOne1!=null) ExprOne1.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(CondFact!=null) CondFact.traverseTopDown(visitor);
        if(ExprOne!=null) ExprOne.traverseTopDown(visitor);
        if(ExprOne1!=null) ExprOne1.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(CondFact!=null) CondFact.traverseBottomUp(visitor);
        if(ExprOne!=null) ExprOne.traverseBottomUp(visitor);
        if(ExprOne1!=null) ExprOne1.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassTwoClass(\n");

        if(CondFact!=null)
            buffer.append(CondFact.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

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

        buffer.append(tab);
        buffer.append(") [ClassTwoClass]");
        return buffer.toString();
    }
}
