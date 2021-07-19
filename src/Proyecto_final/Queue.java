
package Proyecto_final;

import javax.swing.JOptionPane;

public class Queue {
    
    private Node first; //primero
    private Node last; //ultimo

    public Queue() {  
        //first = null;
        //last = null;
    }
    
    public boolean isEmpty() //Esta vacia
    {

        if (first == null)

            return true;

        else

            return false;

    }
    
    public void Enqueue(Object data)  //guardar en la cola
    {
        if(isEmpty())
        {
            first = new Node(data);
            last = first;
        }
        else
        {
            Node n = new Node(data);
            last.setLink(n);
            last = n;
        }
    }
    
    public Object Dequeue() //Sacar de cola
    {
        Object data = null;
        if(!isEmpty())
        {
            data = first.getData();
            first = first.getLink();
            if(first==null)
                last = null;  //la lista queda vacia
        }
        return data;
    }
    
    public int Size(){
        
        int cont=0;
        Object data = null;
        
        while(!isEmpty())
        {
            data = first.getData();
            first = first.getLink();
            cont++;
            if(first==null)
                last = null;//la lista queda vacia
        }
        return cont;
    }
    
    public Object FirstQueue (){
        return first.getData();
    }
    
    public String Name_profiles() {
        Node aux=first;
        String text="";
        
        while (aux!=null) {
            text = text +"\n"+((Profiles)aux.getData()).getName();
            aux=aux.getLink();
        }
        return text;
    }
    
    public Profiles ExtraerProfile(String name){
        Node aux=first;
        Profiles p = new Profiles();
        
        while (aux!=null) {
            if(((Profiles)aux.getData()).getName().equalsIgnoreCase(name)){
                p = (Profiles) aux.getData();
                aux=aux.getLink();
            }
        }
        return p;
    }
    
   public Queue Remove_profile(String name, Queue q){
       
       Node aux=q.first;
       Profiles p= new Profiles();
       
       while(q!=null){
           p = (Profiles) q.Dequeue();
           if(p.getName().equals(name)){
           }
           else{
               q.Enqueue(p);
           }
       }
       return q;
   }
   
}
