// generated with ast extension for cup
// version 0.8
// 2/0/2021 1:31:18


package rs.ac.bg.etf.pp1.ast;

public class TermOptionalListClass extends TermOptionalList {

    private TermOptionalList TermOptionalList;
    private MulOp MulOp;
    private Factor Factor;

    public TermOptionalListClass (TermOptionalList TermOptionalList, MulOp MulOp, Factor Factor) {
        this.TermOptionalList=TermOptionalList;
        if(TermOptionalList!=null) TermOptionalList.setParent(this);
        this.MulOp=MulOp;
        if(MulOp!=null) MulOp.setParent(this);
        this.Factor=Factor;
        if(Factor!=null) Factor.setParent(this);
    }

    public TermOptionalList getTermOptionalList() {
        return TermOptionalList;
    }

    public void setTermOptionalList(TermOptionalList TermOptionalList) {
        this.TermOptionalList=TermOptionalList;
    }

    public MulOp getMulOp() {
        return MulOp;
    }

    public void setMulOp(MulOp MulOp) {
        this.MulOp=MulOp;
    }

    public Factor getFactor() {
        return Factor;
    }

    public void setFactor(Factor Factor) {
        this.Factor=Factor;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(TermOptionalList!=null) TermOptionalList.accept(visitor);
        if(MulOp!=null) MulOp.accept(visitor);
        if(Factor!=null) Factor.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(TermOptionalList!=null) TermOptionalList.traverseTopDown(visitor);
        if(MulOp!=null) MulOp.traverseTopDown(visitor);
        if(Factor!=null) Factor.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(TermOptionalList!=null) TermOptionalList.traverseBottomUp(visitor);
        if(MulOp!=null) MulOp.traverseBottomUp(visitor);
        if(Factor!=null) Factor.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("TermOptionalListClass(\n");

        if(TermOptionalList!=null)
            buffer.append(TermOptionalList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MulOp!=null)
            buffer.append(MulOp.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Factor!=null)
            buffer.append(Factor.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [TermOptionalListClass]");
        return buffer.toString();
    }
}
