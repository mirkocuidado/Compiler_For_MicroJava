// generated with ast extension for cup
// version 0.8
// 14/0/2021 0:52:53


package rs.ac.bg.etf.pp1.ast;

public class PrintStmt extends Statement {

    private Expr Expr;
    private PrintCommaNumber PrintCommaNumber;

    public PrintStmt (Expr Expr, PrintCommaNumber PrintCommaNumber) {
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.PrintCommaNumber=PrintCommaNumber;
        if(PrintCommaNumber!=null) PrintCommaNumber.setParent(this);
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public PrintCommaNumber getPrintCommaNumber() {
        return PrintCommaNumber;
    }

    public void setPrintCommaNumber(PrintCommaNumber PrintCommaNumber) {
        this.PrintCommaNumber=PrintCommaNumber;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Expr!=null) Expr.accept(visitor);
        if(PrintCommaNumber!=null) PrintCommaNumber.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(PrintCommaNumber!=null) PrintCommaNumber.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(PrintCommaNumber!=null) PrintCommaNumber.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("PrintStmt(\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(PrintCommaNumber!=null)
            buffer.append(PrintCommaNumber.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [PrintStmt]");
        return buffer.toString();
    }
}
