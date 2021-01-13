// generated with ast extension for cup
// version 0.8
// 12/0/2021 16:21:32


package rs.ac.bg.etf.pp1.ast;

public class ContinueClass extends Statement {

    private ContinueKeyWord ContinueKeyWord;

    public ContinueClass (ContinueKeyWord ContinueKeyWord) {
        this.ContinueKeyWord=ContinueKeyWord;
        if(ContinueKeyWord!=null) ContinueKeyWord.setParent(this);
    }

    public ContinueKeyWord getContinueKeyWord() {
        return ContinueKeyWord;
    }

    public void setContinueKeyWord(ContinueKeyWord ContinueKeyWord) {
        this.ContinueKeyWord=ContinueKeyWord;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ContinueKeyWord!=null) ContinueKeyWord.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ContinueKeyWord!=null) ContinueKeyWord.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ContinueKeyWord!=null) ContinueKeyWord.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ContinueClass(\n");

        if(ContinueKeyWord!=null)
            buffer.append(ContinueKeyWord.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ContinueClass]");
        return buffer.toString();
    }
}
