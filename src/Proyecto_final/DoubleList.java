
package Proyecto_final;

import static Proyecto_final.Main.queue_profiles;
import static Proyecto_final.Main.queue_profiles2;

import javax.swing.JOptionPane;

public class DoubleList {
    
    private DoubleNode first;
    private DoubleNode last;

    public DoubleList() {
    }
    
    public boolean isEmpty()
    {
        return first==null && last == null;
    }
    
    public DoubleNode getFirst() {
        return first;
    }
    
    public DoubleNode getLast() {
        return last;
    }
    
    public void AddFirst(Object data)
    {
        if(isEmpty())
        {
            first = new DoubleNode(data);
            last = first;
        }
        else
        {
            DoubleNode n = new DoubleNode(data);
            first.setPrevious(n);
            n.setNext(first);
            first = n;
        }
    }
    
    @Override
    public String toString()
    {
        String text="";
        DoubleNode aux = first;
        while(aux != null)
        {
            text = text + aux.getData() + "\n";
            aux = aux.getNext();
        }
        return text;
    }
    
    public void AddLast(Object data)
    {
        if(isEmpty())
            AddFirst(data);
        else
        {
            DoubleNode n = new DoubleNode(data);
            last.setNext(n);
            n.setPrevious(last);
            last=n;
        }
    }
    
    public int Size()
    {
        int counter = 0;
        DoubleNode aux = last;  //DoubleNode aux = first
        while(aux!=null)
        {
            counter++;
            aux = aux.getPrevious(); //aux = aux.getNext();
        }
        return counter;
    }

    //insertar en una posición determinada, garantizando que posicion sea valida
    public void Add(Object data, int pos)
    {
        if(pos == 1)
            AddFirst(data);
        else
        {
            if(pos == Size()+1)
                AddLast(data);
            else
            {
                DoubleNode aux = first;
                int counter=1;
                //recorremos buscando la posicion en la que vamos a insertar
                while(aux!=null && counter<pos)
                {
                    aux = aux.getNext();
                    counter++;
                }
                DoubleNode n = new DoubleNode(data);
                n.setNext(aux);
                n.setPrevious(aux.getPrevious());
                aux.getPrevious().setNext(n);
                aux.setPrevious(n);
            }
        }
    }
    
    public void Add(Object data)
    {
        DoubleNode aux = first;
        while(aux!=null  && ((String)data).compareTo((String)aux.getData())>0)
            aux = aux.getNext();
        
        if(aux!=null)
        {
            if(aux.getPrevious()!=null)
            {
                DoubleNode n = new DoubleNode(data);
                n.setNext(aux);
                n.setPrevious(aux.getPrevious());
                aux.getPrevious().setNext(n);
                aux.setPrevious(n);
            }
            else
                AddFirst(data);
        }
        else
            AddLast(data);
    }
    
    public boolean RemoveFirst()
    {
        if(!isEmpty())
        {
            first = first.getNext();
            if(first == null)
                last = null;
            else
                first.setPrevious(null);
            
            return true;
        }
        return false;
    }
    
    public boolean RemoveLast()
    {
        if(!isEmpty())
        {
            last = last.getPrevious();
            if(last == null)
                first = null;
            else
                last.setNext(null);
            
            return true;
        }
        return false;
    }

    public boolean Remove(int pos)
    {
        if(!isEmpty())
        {
            if(pos==1)
                RemoveFirst();
            else
            {
                if(pos==Size())
                    RemoveLast();
                else
                {
                    int cont=1;
                    DoubleNode aux = first;
                    while(cont<pos){
                        aux=aux.getNext();
                        cont++;
                    }
                    aux.getPrevious().setNext(aux.getNext());
                    aux.getNext().setPrevious(aux.getPrevious());
                }
            }
            return true;
        }
        
        return false;
    }
    
    public boolean Remove(String name)
    {
        DoubleNode aux = first;
        
        while(aux!=null && !((String)aux.getData()).equalsIgnoreCase(name))
            aux=aux.getNext();
        
        if(aux!=null)  //se encontró el elemento
        {
            if(aux.getPrevious()==null) //preguntamos si es el primero
                RemoveFirst();
            else
            {
                if(aux.getNext()==null) //preguntamos si es el ultimo
                    RemoveLast();
                else
                {
                    aux.getPrevious().setNext(aux.getNext());
                    aux.getNext().setPrevious(aux.getPrevious());
                }
            }
            return true;
        }
        return false;
    }
   
    //Validar usuario y contraseña, devuelve cola
    public void SearchAccount_RegistrerProfile (int code, int password){
        
        Queue q = new Queue();
        Queue q_aux = new Queue();
        Profiles pro;
        
        String name,language, configuration;
        
        String type_language[]={"Spanish", "English", "Portuguese"};
        String type_configuration[]={"Kids", "Adult"};
        
        
        DoubleNode aux = last;  //first
        
        while(aux!=null)
        {
            if(((Account)aux.getData()).getCode()==code && ((Account)aux.getData()).getPassword()==password){
                    //Insertando una cola dentro de otra cola
                    //q.Enqueue((Account)aux.getData()).getProfiles());
                    q =(((Account)aux.getData()).getProfiles());
                    
                    while(!q.isEmpty())
                    {
                        pro = (Profiles)q.Dequeue();

                        name=(String)JOptionPane.showInputDialog("Enter profile name");
                        language = (String)JOptionPane.showInputDialog(null,"Register profile language", "Language",
                                    1,null,type_language, type_language[0]);
                        configuration = (String)JOptionPane.showInputDialog(null,"Register profile configuration", "Configuration",
                                    1,null, type_configuration, type_configuration[0]);
           
                        Stack movies_seen = new Stack();
           
                        pro.setName(name);
                        pro.setLanguage(language);
                        pro.setConfiguration(configuration);
                        pro.setMovies_seen(movies_seen);
           
                        JOptionPane.showMessageDialog(null,"Register profile");
           
                        q_aux.Enqueue(pro);
                    } //retomamos
                    while(!q_aux.isEmpty())
                        q.Enqueue(q_aux.Dequeue());
                }
            else{
                JOptionPane.showMessageDialog(null,"Code or password are incorrect");
            }
            aux=aux.getPrevious();  //aux = aux.getNext();
            }
           
    }
    
    //Validar usuario, contraseña y estado en activo, devuelve cola 
    public void SearchAccount_active_RegisterPlayback (int code, int password, List list_playback){
        
        Queue q = new Queue();
        Queue aux_q = new Queue();
        Stack aux_play = new Stack();
        Profiles aux_pro = new Profiles();
        
        DoubleNode aux = last;  //first
        
        Profiles pro = null;
        Stack play = null;
        Playback p;
        
        String r1[]={"Yes", "No"};
        String r="Yes", r2;
        
        while(aux!=null)
        {
            if(((Account)aux.getData()).getCode()==code && ((Account)aux.getData()).getPassword()==password &&
               ((Account)aux.getData()).getState().equals("Active")){
                    
                q =(((Account)aux.getData()).getProfiles());
                        
                String text = q.Name_profiles();
        
                String name_profile = JOptionPane.showInputDialog(null, "Type name of the profile to select,"
                                      + " registered names: "+text); //escriba el nombre del perfil para seleccionar
                
                while(!q.isEmpty())
                {
                    pro = (Profiles)q.Dequeue();
                    
                    if(pro.getName().equals(name_profile)){
                   
                        while(r.equals("Yes")){ 
                            
                            play = pro.getMovies_seen();
                            //p = (Playback)play.Pop();
                        
                            String title_playback = JOptionPane.showInputDialog(null,"Select PLAYBACK"
                                +" for the profile of "+pro.getName()+", the PLAYBACK available are: "
                                +"\n"+list_playback.toString());
                            
                            //Playback
                            p = list_playback.Search_NamePlayback(title_playback);
               
                            aux_play.Push(p);
                            
                            JOptionPane.showMessageDialog(null,"PLAYBACK successfully registered");
                        
                            r = (String)JOptionPane.showInputDialog(null,"Registrer new PLAYBACK", "Type Response",1,null,r1, r1[0]);
               
                        }
                        aux_pro.setMovies_seen(aux_play);
                    }
                
                aux_q.Enqueue(aux_pro);
                }
                
            while(!aux_q.isEmpty())
                q.Enqueue(aux_q.Dequeue());
            
            }else{
                JOptionPane.showMessageDialog(null, "Password and code incorrets");
            }
                
            aux=aux.getPrevious();  //aux = aux.getNext();
        }
        
    }
    
    public List Generate_Payment(){
        
        DoubleNode aux = last;
        
        List list_payment = new List();
        Queue aux_q = new Queue();
        Profiles p = new Profiles();
        Stack aux_s = new Stack();
        Playback aux_play = new Playback();
        
        String name;
        int cant_movies=0, cant_series=0, cant_others=0, price=0;
        
        while(aux!=null)
        {
            name = ((Account)aux.getData()).getHolder();
        
            aux_q = ((Account)aux.getData()).getProfiles();
        
            if(((Account)aux.getData()).getType().equals("Basic"))
                price=21900;
            if(((Account)aux.getData()).getType().equals("Plus"))
                price=31900;
            if(((Account)aux.getData()).getType().equals("Premiun"))
                price=38900;

            while(!aux_q.isEmpty()){
                
                p = (Profiles) aux_q.Dequeue();
                aux_s = p.getMovies_seen();
                
                JOptionPane.showMessageDialog(null,"Fuera del if "+p.getName()+"---"+aux_s.toString());
                
                if(!aux_s.isEmpty()){
                    
                    JOptionPane.showMessageDialog(null,"Dentro del if "+p.getName()+"---"+aux_s.toString());
                    
                    while(!aux_s.isEmpty()){
                        aux_play = new Playback();
                    
                        aux_play = (Playback)aux_s.Pop();
                    
                        if(aux_play.getType().equals("Movie"))
                            cant_movies=cant_movies+1;
                        if(aux_play.getType().equals("Serie"))
                            cant_series=cant_series+1;
                        if(aux_play.getType().equals("Others"))
                            cant_others=cant_others+1;
                    }
                }
            }
            
            list_payment.AddLast(new Payment_account(name,cant_movies,cant_series,cant_others,price));
            
            aux=aux.getPrevious();
        }
        return list_payment;
    }
    
    public Queue List_suspended_inactive(){
        
        DoubleNode aux = getFirst();
        Account a = new Account();
        Queue q = new Queue();
        
        while(aux!=null)
        {
            a = (Account)aux.getData();
            
            if(a.getState().equals("Suspended") || a.getState().equals("Inactive")){
                q.Enqueue(a);
            }
            
        }
        return q;
    }
    
    public Queue Search_Profiles (int code, int password){
        
        Queue q = new Queue();
        DoubleNode aux = last;  //first
        
        while(aux!=null)
        {
            if(((Account)aux.getData()).getCode()==code && ((Account)aux.getData()).getPassword()==password)
                q =(((Account)aux.getData()).getProfiles());
                
            aux=aux.getPrevious();  //aux = aux.getNext();
        }
        return q;
    }
    
    public void Update_status (int code, int password){
        
        DoubleNode aux = last;  //first
        String s, state;
        
        String state_account[]={"Active", "Suspended", "Inactive"};
        
        while(aux!=null)
        {
            if(((Account)aux.getData()).getCode()==code && ((Account)aux.getData()).getPassword()==password){
                s =((Account)aux.getData()).getState();
                
                state = (String)JOptionPane.showInputDialog(null,"Selected option", "State account",
                        1,null,state_account, state_account[0]);
                
                ((Account)aux.getData()).setState(state);
            }
            aux=aux.getPrevious();  //aux = aux.getNext();
        }
    }
    
    public List List_Suspended_Inactive(){
        DoubleNode aux = first;
        List list_aux = new List();
        
        while(aux != null){
            if(((Account)aux.getData()).getState().equals("Suspended") || ((Account)aux.getData()).getState().equals("Inactive")){
                list_aux.AddLast(aux);
            }
        aux = aux.getNext();
        }
        return list_aux;
    }
    
    public void Porcentage_type(){
        
        DoubleNode aux = first;
        List list_aux = new List();
        double porc_basic, porc_plus, porc_premiun;
        int total=0, cont_basic=0, cont_plus=0, cont_premiun=0;
        
        while(aux != null){
            if(((Account)aux.getData()).getType().equals("Basic"))
                cont_basic++;
                total++;
                
            if(((Account)aux.getData()).getType().equals("Plus"))
                cont_plus++;
                total++;
                
            if(((Account)aux.getData()).getType().equals("Premiun"))
                cont_premiun++;
                total++;
            
        aux = aux.getNext();
        }
        
        porc_basic=(cont_basic*100)/total;
        
        porc_plus=(cont_plus*100)/total;
        
        porc_premiun = (cont_premiun*100)/total;
        
        JOptionPane.showInputDialog(null,"The percentage is:\nBasic: "+porc_basic+"\nPlus: "+porc_plus+"\nPremium: "+porc_premiun);
    }
    
    public int Raised_money(){
   
        DoubleNode aux = getFirst();
        
        int total=0;
          
        while(aux!=null)
        { 
            Account a = (Account)aux.getData();
            if(((Account)aux.getData()).getState().equals("Active")){
                if(a.getType().equals("Basic"))
                    total = total + 21900;
                if(a.getType().equals("Plus"))
                    total = total + 31900;
                if(a.getType().equals("Premium"))
                    total = total + 38900;
            }
        aux = aux.getNext();   
        }
        return total;
    }
    
    public void Cant_Profiles_Disp(){
        
        DoubleNode aux = first;
        List list_aux = new List();
        int disponible=0;
        
        while(aux != null){
            Queue q =((Account)aux.getData()).getProfiles();
            
            while(!q.isEmpty()){
                Profiles p = (Profiles)q.Dequeue();
                if(p==null){
                 disponible++;
                }
            }
            
            JOptionPane.showMessageDialog(null, (aux.getData()).toString() +"\nAvailable profiles: "+disponible);
        }
        aux = aux.getNext();
    }
    
    public void Account_usage(){
        
        DoubleNode aux = first;
        List list_aux = new List();
        int cont=0;
        
        while(aux != null){
            Queue q =((Account)aux.getData()).getProfiles();
            
            while(!q.isEmpty()){
                Profiles p = (Profiles)q.Dequeue();
                Stack s = p.getMovies_seen();
                
                while(!s.isEmpty()){
                    s.Pop();
                    cont++;
                }
                if(cont<=10){
                    JOptionPane.showMessageDialog(null,"Low usage");
                }
                if(11<=cont && cont<=50){
                    JOptionPane.showMessageDialog(null,"Medium use");
                }
                if(cont>50){
                   JOptionPane.showMessageDialog(null,"High usage"); 
                } 
            }
            
        }
        aux = aux.getNext();
    }
    
    public void Reproducciones_mas_vistas(){
        
        DoubleNode aux = first;
        List list_aux = new List();
        Queue queue_title = new Queue();
        
        while(aux != null){
            Queue q =((Account)aux.getData()).getProfiles();
            
            while(!q.isEmpty()){
                Profiles p = (Profiles)q.Dequeue();
                Stack s = p.getMovies_seen();
                
                while(!s.isEmpty()){
                    Playback rep = (Playback)s.Pop();
                    String title = rep.getTitle();
                    
                    queue_title.Enqueue(title);
                }
            }
        aux = aux.getNext();    
        }
    
        
    }
}
    