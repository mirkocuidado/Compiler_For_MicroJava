// generated with ast extension for cup
// version 0.8
// 18/0/2021 15:32:57


package rs.ac.bg.etf.pp1.ast;

public class DoWhileStatement extends Statement {

    private DoKeyWord DoKeyWord;
    private Statement Statement;
    private LParenBrother LParenBrother;
    private Condition Condition;
    private RParenBrother RParenBrother;

    public DoWhileStatement (DoKeyWord DoKeyWord, Statement Statement, LParenBrother LParenBrother, Condition Condition, RParenBrother RParenBrother) {
        this.DoKeyWord=DoKeyWord;
        if(DoKeyWord!=null) DoKeyWord.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
        this.LParenBrother=LParenBrother;
        if(LParenBrother!=null) LParenBrother.setParent(this);
        this.Condition=Condition;
        if(Condition!=null) Condition.setParent(this);
        this.RParenBrother=RParenBrother;
        if(RParenBrother!=null) RParenBrother.setParent(this);
    }

    public DoKeyWord getDoKeyWord() {
        return DoKeyWord;
    }

    public void setDoKeyWord(DoKeyWord DoKeyWord) {
        this.DoKeyWord=DoKeyWord;
    }

    public Statement getStatement() {
        return Statement;
    }

    public void setStatement(Statement Statement) {
        this.Statement=Statement;
    }

    public LParenBrother getLParenBrother() {
        return LParenBrother;
    }

    public void setLParenBrother(LParenBrother LParenBrother) {
        this.LParenBrother=LParenBrother;
    }

    public Condition getCondition() {
        return Condition;
    }

    public void setCondition(Condition Condition) {
        this.Condition=Condition;
    }

    public RParenBrother getRParenBrother() {
        return RParenBrother;
    }

    public void setRParenBrother(RParenBrother RParenBrother) {
        this.RParenBrother=RParenBrother;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DoKeyWord!=null) DoKeyWord.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
        if(LParenBrother!=null) LParenBrother.accept(visitor);
        if(Condition!=null) Condition.accept(visitor);
        if(RParenBrother!=null) RParenBrother.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DoKeyWord!=null) DoKeyWord.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
        if(LParenBrother!=null) LParenBrother.traverseTopDown(visitor);
        if(Condition!=null) Condition.traverseTopDown(visitor);
        if(RParenBrother!=null) RParenBrother.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DoKeyWord!=null) DoKeyWord.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        if(LParenBrother!=null) LParenBrother.traverseBottomUp(visitor);
        if(Condition!=null) Condition.traverseBottomUp(visitor);
        if(RParenBrother!=null) RParenBrother.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DoWhileStatement(\n");

        if(DoKeyWord!=null)
            buffer.append(DoKeyWord.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(LParenBrother!=null)
            buffer.append(LParenBrother.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Condition!=null)
            buffer.append(Condition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(RParenBrother!=null)
            buffer.append(RParenBrother.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DoWhileStatement]");
        return buffer.toString();
    }
}
