// generated with ast extension for cup
// version 0.8
// 18/0/2021 15:32:57


package rs.ac.bg.etf.pp1.ast;

public class OptionalDesignatorArray extends OptionalDesignator {

    private Helper Helper;
    private LSquareKeyWord LSquareKeyWord;
    private Expr Expr;

    public OptionalDesignatorArray (Helper Helper, LSquareKeyWord LSquareKeyWord, Expr Expr) {
        this.Helper=Helper;
        if(Helper!=null) Helper.setParent(this);
        this.LSquareKeyWord=LSquareKeyWord;
        if(LSquareKeyWord!=null) LSquareKeyWord.setParent(this);
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
    }

    public Helper getHelper() {
        return Helper;
    }

    public void setHelper(Helper Helper) {
        this.Helper=Helper;
    }

    public LSquareKeyWord getLSquareKeyWord() {
        return LSquareKeyWord;
    }

    public void setLSquareKeyWord(LSquareKeyWord LSquareKeyWord) {
        this.LSquareKeyWord=LSquareKeyWord;
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Helper!=null) Helper.accept(visitor);
        if(LSquareKeyWord!=null) LSquareKeyWord.accept(visitor);
        if(Expr!=null) Expr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Helper!=null) Helper.traverseTopDown(visitor);
        if(LSquareKeyWord!=null) LSquareKeyWord.traverseTopDown(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Helper!=null) Helper.traverseBottomUp(visitor);
        if(LSquareKeyWord!=null) LSquareKeyWord.traverseBottomUp(visitor);
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("OptionalDesignatorArray(\n");

        if(Helper!=null)
            buffer.append(Helper.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(LSquareKeyWord!=null)
            buffer.append(LSquareKeyWord.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [OptionalDesignatorArray]");
        return buffer.toString();
    }
}
