
package Proyecto_final;

import javax.swing.*;

public class Main {
    
    static List list_playback = new List();
    static DoubleList list_account = new DoubleList(); 
    static List aux_list = new List();
    static Queue aux = new Queue();
    static Queue queue_profiles = new Queue(); //Guardar perfiles vacios
    static Queue queue_profiles1 = new Queue(); //Guardar para registrar perfiles
    static Queue queue_profiles2 = new Queue(); //Guardar para registrar playback
    static Archivo ar = new Archivo(); 
    static BinaryTree bt = new BinaryTree();
    
    public static void main(String[] args) {
        
        //Register Playback. Point #1
        String type_p, title, genre;
        double duration; 
        int year;
        
        String type_playback[]={"Movie", "Serie", "Others"};
        String genre_playback[]={"Action", "Adventure", "Science fiction", "Drama", "Terror"}; 
        JOptionPane.showMessageDialog(null, "***REGISTER PLAYBACK***");
        
        String r1[]={"Yes", "No"};
        String rsp1 = "Yes";
        
        while(rsp1.equals("Yes")){    
            type_p = (String)JOptionPane.showInputDialog(null,"Selected option", "Type Playback",
                        1,null,type_playback, type_playback[0]);
            title = (String)JOptionPane.showInputDialog("Enter title");
            genre = (String)JOptionPane.showInputDialog(null,"Selected option", "Type genre",
                        1,null,genre_playback, genre_playback[0]);
            duration = Double.parseDouble(JOptionPane.showInputDialog("Enter duration"));
            year = Integer.parseInt(JOptionPane.showInputDialog("Enter year"));
        
            list_playback.Add(new Playback(type_p, title, genre, duration, year));
            
            JOptionPane.showMessageDialog(null, "Item created and saved");
            
            rsp1 = (String)JOptionPane.showInputDialog(null,"Register new PLAYBACK?", "Type Response",
                        1,null,r1, r1[0]);
            
            //JOptionPane.showInputDialog("Play list"+list_playback.toString());
        }    
        //Register Account. Point #2
        
        int code, password;
        String holder, type_a, state;
        
        String type_account[]={"Basic", "Plus", "Premiun"};
        String state_account[]={"Active", "Suspended", "Inactive"};
        
        JOptionPane.showMessageDialog(null, "***REGISTER ACCOUNT***");
       
        String rsp2 = "Yes";
        
        while(rsp2.equals("Yes")){    
            code = Integer.parseInt(JOptionPane.showInputDialog("Enter code account"));
            holder = (String)JOptionPane.showInputDialog("Enter name account");
            password = Integer.parseInt(JOptionPane.showInputDialog("Enter password account"));
            type_a = (String)JOptionPane.showInputDialog(null,"Selected option", "Type Account",
                        1,null,type_account, type_account[0]);
            state = (String)JOptionPane.showInputDialog(null,"Selected option", "State account",
                        1,null,state_account, state_account[0]);
            
            queue_profiles = new Queue();
            
            //Guardar los perfiles en una cola
            if(type_a.equals("Basic")){
                Profiles p1 = new Profiles();
                Profiles p2 = new Profiles();
                
                queue_profiles.Enqueue(p1);
                queue_profiles.Enqueue(p2);
            }
            if(type_a.equals("Plus")){
                Profiles p1 = new Profiles();
                Profiles p2 = new Profiles();
                Profiles p3 = new Profiles();
                
                queue_profiles.Enqueue(p1);
                queue_profiles.Enqueue(p2);
                queue_profiles.Enqueue(p3);
                
            }
            if(type_a.equals("Premiun")){
                Profiles p1 = new Profiles();
                Profiles p2 = new Profiles();
                Profiles p3 = new Profiles();
                Profiles p4 = new Profiles();
                
                queue_profiles.Enqueue(p1);
                queue_profiles.Enqueue(p2);
                queue_profiles.Enqueue(p3);
                queue_profiles.Enqueue(p4);
            
            }
            
            Account a = new Account(code, holder, password, type_a, state, queue_profiles);
            
            //Guardar la cuenta en una lista double
            list_account.AddLast(a);
            
            JOptionPane.showMessageDialog(null, "Item created and saved");
            
            rsp2 = (String)JOptionPane.showInputDialog(null,"Register new ACCOUNT?", "Type Response",
                        1,null,r1, r1[0]);
            
        }
        
        //Register profiles. Point #3
        
        JOptionPane.showMessageDialog(null, "Verify account to register profiles"); //Verificar cuenta para registrar perfiles
        
        String rsp3 = "Yes";
        
        while(rsp3.equals("Yes")){    
        
            code = Integer.parseInt(JOptionPane.showInputDialog("Enter code account"));
            password = Integer.parseInt(JOptionPane.showInputDialog("Enter password accunt"));
        
            list_account.SearchAccount_RegistrerProfile(code, password);
            
            rsp3 = (String)JOptionPane.showInputDialog(null,"Check again?", "Type Response",
                        1,null,r1, r1[0]);
        }
        
        //Point #4. Validar contraseña y usuario de la cuenta, validar estado cuenta. 
        
        JOptionPane.showMessageDialog(null, "Verify account to register PLAYBACK"); //Verificar cuenta para registrar PLAYBACK
        
        code = Integer.parseInt(JOptionPane.showInputDialog("Enter code account"));
        password = Integer.parseInt(JOptionPane.showInputDialog("Enter password account"));
        
        list_account.SearchAccount_active_RegisterPlayback(code, password, list_playback);
        
        //JOptionPane.showMessageDialog(null,Print(),"Print", 2);

        //Point #5.
        
        List l = list_account.Generate_Payment();
        ar.Write_PaymentAccount(l, "Cuenta de cobro.txt");
        
        //Point #6.
        
        
        //Point #7.
        Queue q= list_account.List_suspended_inactive();
        while(!q.isEmpty()){
           Account a = (Account)q.Dequeue();
           bt.Add(a, bt.getRoot());
        }    
        JOptionPane.showMessageDialog(null,"created tree");
        
        //Point #8.
        
        JOptionPane.showMessageDialog(null, "Eliminar perfil"); //Verificar cuenta para registrar PLAYBACK
        
        code = Integer.parseInt(JOptionPane.showInputDialog("Enter code account"));
        password = Integer.parseInt(JOptionPane.showInputDialog("Enter password account"));
        
        Queue q_aux = list_account.Search_Profiles(code, password);
        
        String name = JOptionPane.showInputDialog("Selecciones perfil a elimanar: "+q_aux.Name_profiles());
        
        Queue queue_actualizada = q_aux.Remove_profile(name, q_aux);
        
        JOptionPane.showMessageDialog(null, "Se actualizo: "+queue_actualizada.Name_profiles());
        
        //Point #9.
        
        JOptionPane.showMessageDialog(null, "Actualizar estado de la cuenta"); //Verificar cuenta para registrar PLAYBACK
        
        code = Integer.parseInt(JOptionPane.showInputDialog("Enter code account"));
        password = Integer.parseInt(JOptionPane.showInputDialog("Enter password account"));
        
        list_account.Update_status(code, password);
        
        //Point #10.
        //(a)
        List list_supended_inactive = list_account.List_Suspended_Inactive();
        
        JOptionPane.showMessageDialog(null, "Las cuentas supendidas o inactivas son: "+list_supended_inactive.toString());
        
        //(b)
        
        list_account.Porcentage_type();
        
        //(c)
        
        int t = list_account.Raised_money();
        
        JOptionPane.showMessageDialog(null, "Total del dinero recaudado por las cuentas que están activas: "+t);
        
        //(d)
        
        list_account.Cant_Profiles_Disp();
        
        //(e)
        
        list_account.Account_usage();

    }
//*********************************************************************************************************    
    
    public static String Print()
    {
        String text="";
        Playback po;
        
        while(!queue_profiles2.isEmpty())
        {
            po=(Playback)queue_profiles2.Dequeue();
            text = text + po.toString() + "\n";
            aux.Enqueue(po);
        }
        while(!aux.isEmpty())
            queue_profiles2.Enqueue(aux.Dequeue());
        
        return text;
    }

} 
