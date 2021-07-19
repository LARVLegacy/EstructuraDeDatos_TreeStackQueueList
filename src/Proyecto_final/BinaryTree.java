
package Proyecto_final;

import javax.swing.JOptionPane;

public class BinaryTree {
    
    private BinaryNode root;

    public BinaryTree() {
        root=null;
    }
    
    public boolean isEmpty()
    {
        return root == null;
    }
    
    public void Add(Object data, BinaryNode aux)
    {
        if(isEmpty())
            root = new BinaryNode(data);
        else
        {
           double number = Math.random();
            System.out.println("side " + number);
//            String side; 
//            side = JOptionPane.showInputDialog("Left o Rigth of: " + aux.getData());
//            if(side.equalsIgnoreCase("left"))  //
            if(number<0.5)   // if(data<aux.getData()) -> AVL -> arbol binario de busqueda
            {
                if(aux.getLeft()==null)   //no tiene hijo
                    aux.setLeft(new BinaryNode(data));
                else   //ya esta ocupado...
                    Add(data, aux.getLeft());
            }
            else
            {
                if(aux.getRigth() == null)
                    aux.setRigth(new BinaryNode(data));
                else
                    Add(data, aux.getRigth());
            }
        }
    }
    
    public BinaryNode getRoot() {
        return root;
    }
    
    public String PreOrder(BinaryNode aux)
    {
        if(aux!=null)
            return aux.getData() + "  "+ PreOrder(aux.getLeft()) + PreOrder(aux.getRigth());
               //      raiz                     izquierdo                  derecho
        else
            return "";
    }
    
    public String InOrder(BinaryNode aux)
    {
        if(aux!=null)
            return InOrder(aux.getLeft()) + aux.getData() + "  "+ InOrder(aux.getRigth());
               //      izquierdo                     raiz                   derecho
        else
            return "";
    }
    
    public String PostOrder(BinaryNode aux)
    {
        if(aux!=null)
            return PostOrder(aux.getLeft()) + PostOrder(aux.getRigth())+ aux.getData() + "  ";
               //      izquierdo                     derecho                raiz
        else
            return "";
    }
    
    public int Size(BinaryNode aux)
    {
        if(aux!=null)
//            return 1 + Size(aux.getLeft()) + Size(aux.getRigth());  //PreOrder
//            return Size(aux.getLeft()) + 1 + Size(aux.getRigth());   //InOrder
            return Size(aux.getLeft()) + Size(aux.getRigth()) + 1;  //PostOrder
        else
            return 0;
    }
    
    public boolean Search(int x, BinaryNode aux) //x->lo que se va a buscar en el arbol
    {
        if(aux!=null)
        {
            if(((int)aux.getData()) == x)  
            //compara el nodo que hace el papel de raiz con el dato
                return true;
            else
                return Search(x, aux.getLeft()) || Search(x,aux.getRigth());
        }
        else
            return false;
    }
    
    public int Heigth(BinaryNode aux)
    {
        if(aux!=null)
            return 1 + Math.max(Heigth(aux.getLeft()), Heigth(aux.getRigth()));
        else
            return 0;
    }
    
    public BinaryNode SearchNode(int x, BinaryNode aux)
    {
        BinaryNode res;
        if(aux!=null)
        {
            if((int)aux.getData() == x)
                return aux;
            else
            {
                //buscar por el izquierdo del aux
                res = SearchNode(x, aux.getLeft());
                if(res == null)   //No lo encontro por la izquierda
                    res = SearchNode(x, aux.getRigth());  //busca por la derecha
                
                return res;
            }
        }
        return null;
    }
    
    public BinaryNode getFather(int x, BinaryNode aux)
    {
        BinaryNode father=null;
        if(root!=null && (int)root.getData() == x)
            return null;
        else
        {
            if(aux!=null)
            {
                if(aux.getLeft()!= null  && (int)aux.getLeft().getData() == x  || 
                        aux.getRigth()!=null  && (int)aux.getRigth().getData() == x)
                    return aux;
                else
                {
                    //hacemos la recursividad por izquierda
                    father = getFather(x, aux.getLeft());
                    if(father == null)  // no lo encontro por la izq
                        father = getFather(x, aux.getRigth()); //lo buscamos por la derecha
                }
                return father;
            }
        }
        return null;
    }
    
    public List Predeccessor(BinaryNode aux, List prede)
    {
        
        if(aux!= null)
        {
            prede.AddFirst(aux.getData());
            Predeccessor(getFather((int)aux.getData(), getRoot()), prede);
        }
        return prede;
    }
    
    public boolean Delete(int x)
    {
        BinaryNode delete = SearchNode(x, getRoot());
        if(delete != null)  //enocntro el nodo a eliminar
        {
            if(delete.getLeft() == null  && delete.getRigth()==null)
                return DeleteLeaf(delete);
            else
            {
                if(delete.getLeft()== null || delete.getRigth()==null)
                    return DeleteBranch(delete);
                else
                    return Delete2Branch(delete);
            }
        }
        return false;
    }
    
    private boolean DeleteLeaf(BinaryNode delete)
    {
        BinaryNode father = getFather((int)delete.getData(), getRoot());
        if(father == null)
            root = null;
        else
        {
            //preguntamos si el hijo izq del padre es el nodo a eliminar
            if(father.getLeft()== delete)              
                father.setLeft(null);
            else
                father.setRigth(null);
        }
        return true;
    }
    
    private boolean DeleteBranch(BinaryNode delete)
    {
        BinaryNode father = getFather((int)delete.getData(), getRoot());
        if(father == null)
        {
            if(delete.getLeft()!= null)
                root = delete.getLeft();
            else
                root = delete.getRigth();
        }
        else
        {
            if(father.getLeft() == delete)
            {
                if(delete.getLeft()!=null)
                    father.setLeft(delete.getLeft());
                else
                    father.setLeft(delete.getRigth());
            }
            else
            {
                if(delete.getLeft() != null)
                    father.setRigth(delete.getLeft());
                else
                    father.setRigth(delete.getRigth());
            }
        }        
        return true;
    }
    
    private boolean Delete2Branch(BinaryNode delete)
    {
        int left = MoreLeft(delete);
        Delete(left);
        delete.setData(left);
        return true;
    }
    
    private int MoreLeft(BinaryNode aux)
    {
        if(aux.getLeft() != null)
            return MoreLeft(aux.getLeft());
        else
            return (int)aux.getData();
    }
    
}
