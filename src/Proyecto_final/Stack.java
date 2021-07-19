
package Proyecto_final;

public class Stack {
    
    private Node top;

    public Stack() {
    }
    
    public boolean isEmpty()
    {
        return top==null;
    }
    
    //Adicionar, meter, guardar datos en la pila
    public void Push(Object data)
    {
        if(isEmpty())
            top = new Node(data);
        else
        {
            Node n = new Node(data, top);
            top = n;
        }
    }
    
    //eliminar, sacar de la pila
    public Object Pop()
    {
        Object obj = null;
        if(!isEmpty())
        {
            Node aux = top;  //es opcional
            obj = top.getData();
            top = top.getLink();
            aux=null;        //es opcional
        }
        return obj;
    }    
}
