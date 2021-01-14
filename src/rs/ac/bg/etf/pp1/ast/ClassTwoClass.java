// generated with ast extension for cup
// version 0.8
// 14/0/2021 0:52:53


package rs.ac.bg.etf.pp1.ast;

public class ClassTwoClass extends Expr {

    private ExprOne ExprOne;
    private TernaryKeyWord TernaryKeyWord;
    private ExprOne ExprOne1;
    private ColonKeyWord ColonKeyWord;
    private ExprOne ExprOne2;

    public ClassTwoClass (ExprOne ExprOne, TernaryKeyWord TernaryKeyWord, ExprOne ExprOne1, ColonKeyWord ColonKeyWord, ExprOne ExprOne2) {
        this.ExprOne=ExprOne;
        if(ExprOne!=null) ExprOne.setParent(this);
        this.TernaryKeyWord=TernaryKeyWord;
        if(TernaryKeyWord!=null) TernaryKeyWord.setParent(this);
        this.ExprOne1=ExprOne1;
        if(ExprOne1!=null) ExprOne1.setParent(this);
        this.ColonKeyWord=ColonKeyWord;
        if(ColonKeyWord!=null) ColonKeyWord.setParent(this);
        this.ExprOne2=ExprOne2;
        if(ExprOne2!=null) ExprOne2.setParent(this);
    }

    public ExprOne getExprOne() {
        return ExprOne;
    }

    public void setExprOne(ExprOne ExprOne) {
        this.ExprOne=ExprOne;
    }

    public TernaryKeyWord getTernaryKeyWord() {
        return TernaryKeyWord;
    }

    public void setTernaryKeyWord(TernaryKeyWord TernaryKeyWord) {
        this.TernaryKeyWord=TernaryKeyWord;
    }

    public ExprOne getExprOne1() {
        return ExprOne1;
    }

    public void setExprOne1(ExprOne ExprOne1) {
        this.ExprOne1=ExprOne1;
    }

    public ColonKeyWord getColonKeyWord() {
        return ColonKeyWord;
    }

    public void setColonKeyWord(ColonKeyWord ColonKeyWord) {
        this.ColonKeyWord=ColonKeyWord;
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
        if(TernaryKeyWord!=null) TernaryKeyWord.accept(visitor);
        if(ExprOne1!=null) ExprOne1.accept(visitor);
        if(ColonKeyWord!=null) ColonKeyWord.accept(visitor);
        if(ExprOne2!=null) ExprOne2.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ExprOne!=null) ExprOne.traverseTopDown(visitor);
        if(TernaryKeyWord!=null) TernaryKeyWord.traverseTopDown(visitor);
        if(ExprOne1!=null) ExprOne1.traverseTopDown(visitor);
        if(ColonKeyWord!=null) ColonKeyWord.traverseTopDown(visitor);
        if(ExprOne2!=null) ExprOne2.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ExprOne!=null) ExprOne.traverseBottomUp(visitor);
        if(TernaryKeyWord!=null) TernaryKeyWord.traverseBottomUp(visitor);
        if(ExprOne1!=null) ExprOne1.traverseBottomUp(visitor);
        if(ColonKeyWord!=null) ColonKeyWord.traverseBottomUp(visitor);
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

        if(TernaryKeyWord!=null)
            buffer.append(TernaryKeyWord.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ExprOne1!=null)
            buffer.append(ExprOne1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ColonKeyWord!=null)
            buffer.append(ColonKeyWord.toString("  "+tab));
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
