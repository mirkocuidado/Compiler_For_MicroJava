// generated with ast extension for cup
// version 0.8
// 18/0/2021 15:32:57


package rs.ac.bg.etf.pp1.ast;

public class IfStatement extends Statement {

    private PotentialError PotentialError;
    private Statement Statement;
    private EmptyHelper EmptyHelper;
    private OptionalElse OptionalElse;

    public IfStatement (PotentialError PotentialError, Statement Statement, EmptyHelper EmptyHelper, OptionalElse OptionalElse) {
        this.PotentialError=PotentialError;
        if(PotentialError!=null) PotentialError.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
        this.EmptyHelper=EmptyHelper;
        if(EmptyHelper!=null) EmptyHelper.setParent(this);
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

    public EmptyHelper getEmptyHelper() {
        return EmptyHelper;
    }

    public void setEmptyHelper(EmptyHelper EmptyHelper) {
        this.EmptyHelper=EmptyHelper;
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
        if(EmptyHelper!=null) EmptyHelper.accept(visitor);
        if(OptionalElse!=null) OptionalElse.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(PotentialError!=null) PotentialError.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
        if(EmptyHelper!=null) EmptyHelper.traverseTopDown(visitor);
        if(OptionalElse!=null) OptionalElse.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(PotentialError!=null) PotentialError.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        if(EmptyHelper!=null) EmptyHelper.traverseBottomUp(visitor);
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

        if(EmptyHelper!=null)
            buffer.append(EmptyHelper.toString("  "+tab));
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
