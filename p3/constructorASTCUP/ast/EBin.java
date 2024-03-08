package ast;

public class EBin extends E {
    private KindE operation;
    private E opnd1;
    private E opnd2;
    public EBin(KindE operation, E opnd1, E opnd2) {
        this.operation = operation;
        this.opnd1 = opnd1;
        this.opnd2 = opnd2;
    }
    public E opnd1() {return opnd1;}
    public E opnd2() {return opnd2;}    

    public KindE kind() {
        return operation;
    }

    public String toString() {
        String operation_type;
        switch (operation) {
            case SUMA:
                operation_type = "sum";
                break;

            case MUL:
                operation_type = "mul";
                break;

            default:
            	operation_type = "error";
                break;
        }

        return operation_type+"("+opnd1().toString()+","+opnd2.toString()+")";
    }
}
