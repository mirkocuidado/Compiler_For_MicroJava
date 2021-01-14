// generated with ast extension for cup
// version 0.8
// 14/0/2021 12:53:35


package rs.ac.bg.etf.pp1.ast;

public class CondFactOptionalClass extends CondFactOptional {

    private RelOp RelOp;
    private ExprOne ExprOne;

    public CondFactOptionalClass (RelOp RelOp, ExprOne ExprOne) {
        this.RelOp=RelOp;
        if(RelOp!=null) RelOp.setParent(this);
        this.ExprOne=ExprOne;
        if(ExprOne!=null) ExprOne.setParent(this);
    }

    public RelOp getRelOp() {
        return RelOp;
    }

    public void setRelOp(RelOp RelOp) {
        this.RelOp=RelOp;
    }

    public ExprOne getExprOne() {
        return ExprOne;
    }

    public void setExprOne(ExprOne ExprOne) {
        this.ExprOne=ExprOne;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(RelOp!=null) RelOp.accept(visitor);
        if(ExprOne!=null) ExprOne.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(RelOp!=null) RelOp.traverseTopDown(visitor);
        if(ExprOne!=null) ExprOne.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(RelOp!=null) RelOp.traverseBottomUp(visitor);
        if(ExprOne!=null) ExprOne.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("CondFactOptionalClass(\n");

        if(RelOp!=null)
            buffer.append(RelOp.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ExprOne!=null)
            buffer.append(ExprOne.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [CondFactOptionalClass]");
        return buffer.toString();
    }
}
