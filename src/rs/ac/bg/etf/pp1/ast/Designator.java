// generated with ast extension for cup
// version 0.8
// 14/0/2021 22:20:26


package rs.ac.bg.etf.pp1.ast;

public class Designator implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Obj obj = null;

    private DesigIdent DesigIdent;
    private OptionalDesignator OptionalDesignator;

    public Designator (DesigIdent DesigIdent, OptionalDesignator OptionalDesignator) {
        this.DesigIdent=DesigIdent;
        if(DesigIdent!=null) DesigIdent.setParent(this);
        this.OptionalDesignator=OptionalDesignator;
        if(OptionalDesignator!=null) OptionalDesignator.setParent(this);
    }

    public DesigIdent getDesigIdent() {
        return DesigIdent;
    }

    public void setDesigIdent(DesigIdent DesigIdent) {
        this.DesigIdent=DesigIdent;
    }

    public OptionalDesignator getOptionalDesignator() {
        return OptionalDesignator;
    }

    public void setOptionalDesignator(OptionalDesignator OptionalDesignator) {
        this.OptionalDesignator=OptionalDesignator;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesigIdent!=null) DesigIdent.accept(visitor);
        if(OptionalDesignator!=null) OptionalDesignator.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesigIdent!=null) DesigIdent.traverseTopDown(visitor);
        if(OptionalDesignator!=null) OptionalDesignator.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesigIdent!=null) DesigIdent.traverseBottomUp(visitor);
        if(OptionalDesignator!=null) OptionalDesignator.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Designator(\n");

        if(DesigIdent!=null)
            buffer.append(DesigIdent.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(OptionalDesignator!=null)
            buffer.append(OptionalDesignator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Designator]");
        return buffer.toString();
    }
}
