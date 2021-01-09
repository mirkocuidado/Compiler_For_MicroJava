// generated with ast extension for cup
// version 0.8
// 9/0/2021 20:28:31


package rs.ac.bg.etf.pp1.ast;

public class IfStatement extends Statement {

    private PotentialError PotentialError;
    private Statement Statement;
    private OptionalElse OptionalElse;

    public IfStatement (PotentialError PotentialError, Statement Statement, OptionalElse OptionalElse) {
        this.PotentialError=PotentialError;
        if(PotentialError!=null) PotentialError.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
        this.OptionalElse=OptionalElse;
        if(OptionalElse!=null) OptionalElse.setParent(this);
    }

    public PotentialError getPotentialError() {
        return PotentialError;
    }

    public void setPotentialError(PotentialError PotentialError) {
        this.PotentialError=PotentialError;
    }

    public Statement getStatement() {
        return Statement;
    }

    public void setStatement(Statement Statement) {
        this.Statement=Statement;
    }

    public OptionalElse getOptionalElse() {
        return OptionalElse;
    }

    public void setOptionalElse(OptionalElse OptionalElse) {
        this.OptionalElse=OptionalElse;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(PotentialError!=null) PotentialError.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
        if(OptionalElse!=null) OptionalElse.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(PotentialError!=null) PotentialError.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
        if(OptionalElse!=null) OptionalElse.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(PotentialError!=null) PotentialError.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        if(OptionalElse!=null) OptionalElse.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("IfStatement(\n");

        if(PotentialError!=null)
            buffer.append(PotentialError.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(OptionalElse!=null)
            buffer.append(OptionalElse.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [IfStatement]");
        return buffer.toString();
    }
}
