// generated with ast extension for cup
// version 0.8
// 18/0/2021 15:32:57


package rs.ac.bg.etf.pp1.ast;

public class YesOptionalStatement extends OptionalElse {

    private Statement Statement;

    public YesOptionalStatement (Statement Statement) {
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
    }

    public Statement getStatement() {
        return Statement;
    }

    public void setStatement(Statement Statement) {
        this.Statement=Statement;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Statement!=null) Statement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("YesOptionalStatement(\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [YesOptionalStatement]");
        return buffer.toString();
    }
}
