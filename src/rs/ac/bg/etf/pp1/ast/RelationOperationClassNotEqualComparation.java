// generated with ast extension for cup
// version 0.8
// 2/0/2021 1:31:18


package rs.ac.bg.etf.pp1.ast;

public class RelationOperationClassNotEqualComparation extends RelOp {

    public RelationOperationClassNotEqualComparation () {
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("RelationOperationClassNotEqualComparation(\n");

        buffer.append(tab);
        buffer.append(") [RelationOperationClassNotEqualComparation]");
        return buffer.toString();
    }
}
