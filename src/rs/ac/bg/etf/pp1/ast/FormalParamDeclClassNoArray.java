// generated with ast extension for cup
// version 0.8
// 31/11/2020 1:5:7


package rs.ac.bg.etf.pp1.ast;

public class FormalParamDeclClassNoArray extends FormalParamDecl {

    private Type Type;
    private String formalParamName;

    public FormalParamDeclClassNoArray (Type Type, String formalParamName) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.formalParamName=formalParamName;
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public String getFormalParamName() {
        return formalParamName;
    }

    public void setFormalParamName(String formalParamName) {
        this.formalParamName=formalParamName;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FormalParamDeclClassNoArray(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+formalParamName);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FormalParamDeclClassNoArray]");
        return buffer.toString();
    }
}
