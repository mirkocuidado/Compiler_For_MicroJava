// generated with ast extension for cup
// version 0.8
// 2/0/2021 1:31:18


package rs.ac.bg.etf.pp1.ast;

public class TermClass extends Term {

    private Factor Factor;
    private TermOptionalList TermOptionalList;

    public TermClass (Factor Factor, TermOptionalList TermOptionalList) {
        this.Factor=Factor;
        if(Factor!=null) Factor.setParent(this);
        this.TermOptionalList=TermOptionalList;
        if(TermOptionalList!=null) TermOptionalList.setParent(this);
    }

    public Factor getFactor() {
        return Factor;
    }

    public void setFactor(Factor Factor) {
        this.Factor=Factor;
    }

    public TermOptionalList getTermOptionalList() {
        return TermOptionalList;
    }

    public void setTermOptionalList(TermOptionalList TermOptionalList) {
        this.TermOptionalList=TermOptionalList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Factor!=null) Factor.accept(visitor);
        if(TermOptionalList!=null) TermOptionalList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Factor!=null) Factor.traverseTopDown(visitor);
        if(TermOptionalList!=null) TermOptionalList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Factor!=null) Factor.traverseBottomUp(visitor);
        if(TermOptionalList!=null) TermOptionalList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("TermClass(\n");

        if(Factor!=null)
            buffer.append(Factor.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(TermOptionalList!=null)
            buffer.append(TermOptionalList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [TermClass]");
        return buffer.toString();
    }
}
