// generated with ast extension for cup
// version 0.8
// 14/0/2021 22:20:26


package rs.ac.bg.etf.pp1.ast;

public class ClassDeclClass extends ClassDecl {

    private String className;
    private ExtendsOption ExtendsOption;
    private VarDeclList VarDeclList;
    private MethodsOption MethodsOption;

    public ClassDeclClass (String className, ExtendsOption ExtendsOption, VarDeclList VarDeclList, MethodsOption MethodsOption) {
        this.className=className;
        this.ExtendsOption=ExtendsOption;
        if(ExtendsOption!=null) ExtendsOption.setParent(this);
        this.VarDeclList=VarDeclList;
        if(VarDeclList!=null) VarDeclList.setParent(this);
        this.MethodsOption=MethodsOption;
        if(MethodsOption!=null) MethodsOption.setParent(this);
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className=className;
    }

    public ExtendsOption getExtendsOption() {
        return ExtendsOption;
    }

    public void setExtendsOption(ExtendsOption ExtendsOption) {
        this.ExtendsOption=ExtendsOption;
    }

    public VarDeclList getVarDeclList() {
        return VarDeclList;
    }

    public void setVarDeclList(VarDeclList VarDeclList) {
        this.VarDeclList=VarDeclList;
    }

    public MethodsOption getMethodsOption() {
        return MethodsOption;
    }

    public void setMethodsOption(MethodsOption MethodsOption) {
        this.MethodsOption=MethodsOption;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ExtendsOption!=null) ExtendsOption.accept(visitor);
        if(VarDeclList!=null) VarDeclList.accept(visitor);
        if(MethodsOption!=null) MethodsOption.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ExtendsOption!=null) ExtendsOption.traverseTopDown(visitor);
        if(VarDeclList!=null) VarDeclList.traverseTopDown(visitor);
        if(MethodsOption!=null) MethodsOption.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ExtendsOption!=null) ExtendsOption.traverseBottomUp(visitor);
        if(VarDeclList!=null) VarDeclList.traverseBottomUp(visitor);
        if(MethodsOption!=null) MethodsOption.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassDeclClass(\n");

        buffer.append(" "+tab+className);
        buffer.append("\n");

        if(ExtendsOption!=null)
            buffer.append(ExtendsOption.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclList!=null)
            buffer.append(VarDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MethodsOption!=null)
            buffer.append(MethodsOption.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassDeclClass]");
        return buffer.toString();
    }
}
