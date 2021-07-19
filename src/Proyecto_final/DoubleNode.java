
package Proyecto_final;

public class DoubleNode {
    
    private Object data;
    private DoubleNode previous;
    private DoubleNode next;

    public DoubleNode(Object data) {
        this.data = data;
    }
   
    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
    
    public DoubleNode getPrevious() {
        return previous;
    }

    public void setPrevious(DoubleNode previous) {
        this.previous = previous;
    }

    public DoubleNode getNext() {
        return next;
    }

    public void setNext(DoubleNode next) {
        this.next = next;
    }
    
    
}
