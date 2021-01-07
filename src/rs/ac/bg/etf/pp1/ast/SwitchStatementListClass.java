// generated with ast extension for cup
// version 0.8
// 7/0/2021 22:51:14


package rs.ac.bg.etf.pp1.ast;

public class SwitchStatementListClass extends SwitchStatementList {

    private SwitchStatementList SwitchStatementList;
    private Integer N2;
    private Statement Statement;

    public SwitchStatementListClass (SwitchStatementList SwitchStatementList, Integer N2, Statement Statement) {
        this.SwitchStatementList=SwitchStatementList;
        if(SwitchStatementList!=null) SwitchStatementList.setParent(this);
        this.N2=N2;
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
    }

    public SwitchStatementList getSwitchStatementList() {
        return SwitchStatementList;
    }

    public void setSwitchStatementList(SwitchStatementList SwitchStatementList) {
        this.SwitchStatementList=SwitchStatementList;
    }

    public Integer getN2() {
        return N2;
    }

    public void setN2(Integer N2) {
        this.N2=N2;
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
        if(SwitchStatementList!=null) SwitchStatementList.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(SwitchStatementList!=null) SwitchStatementList.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(SwitchStatementList!=null) SwitchStatementList.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SwitchStatementListClass(\n");

        if(SwitchStatementList!=null)
            buffer.append(SwitchStatementList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+N2);
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SwitchStatementListClass]");
        return buffer.toString();
    }
}
