// generated with ast extension for cup
// version 0.8
// 7/0/2021 22:51:14


package rs.ac.bg.etf.pp1.ast;

public class ExprFirstOptionClassWithMinus extends ExprPositive {

    private Term Term;
    private ExprOptionalList ExprOptionalList;

    public ExprFirstOptionClassWithMinus (Term Term, ExprOptionalList ExprOptionalList) {
        this.Term=Term;
        if(Term!=null) Term.setParent(this);
        this.ExprOptionalList=ExprOptionalList;
        if(ExprOptionalList!=null) ExprOptionalList.setParent(this);
    }

    public Term getTerm() {
        return Term;
    }

    public void setTerm(Term Term) {
        this.Term=Term;
    }

    public ExprOptionalList getExprOptionalList() {
        return ExprOptionalList;
    }

    public void setExprOptionalList(ExprOptionalList ExprOptionalList) {
        this.ExprOptionalList=ExprOptionalList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Term!=null) Term.accept(visitor);
        if(ExprOptionalList!=null) ExprOptionalList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Term!=null) Term.traverseTopDown(visitor);
        if(ExprOptionalList!=null) ExprOptionalList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Term!=null) Term.traverseBottomUp(visitor);
        if(ExprOptionalList!=null) ExprOptionalList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ExprFirstOptionClassWithMinus(\n");

        if(Term!=null)
            buffer.append(Term.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ExprOptionalList!=null)
            buffer.append(ExprOptionalList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ExprFirstOptionClassWithMinus]");
        return buffer.toString();
    }
}
