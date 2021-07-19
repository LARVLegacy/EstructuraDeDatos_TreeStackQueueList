
package Proyecto_final;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Archivo {
    
    private File f;
    private FileReader fr;
    private FileWriter fw;
    private BufferedReader br;
    private BufferedWriter bw;
    
   public int Cantidad(String ruta)
    {
        int pos = 0;
        //paso 1
        f = new File(ruta);
        try
        {
            fr = new FileReader(f);   //paso 2
            br = new BufferedReader(fr);  //paso 3
            //paso 4
            while((br.readLine())!=null)
            {
                pos++;
            }
            br.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return pos;
    }
    
    public List Read_PaymentAccount(String ruta)
    {
        List list=new List();
        String registro, campos[];
        
        f = new File(ruta);
        try
        {
            fr = new FileReader(f);
            br = new BufferedReader(fr);
            while((registro=br.readLine())!=null)
            {
                campos=registro.split(" ");
                list.AddLast( new Payment_account(campos[0],
                                      Integer.parseInt(campos[1]),
                                      Integer.parseInt(campos[2]),
                                      Integer.parseInt(campos[3]),
                                      Integer.parseInt(campos[4])));
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return list;
    }
    
public void Write_PaymentAccount (List lista, String ruta)
    {
        //paso 1
        f = new File(ruta);
        try{
            fw = new FileWriter(f); //paso 2            
            bw = new BufferedWriter(fw); //paso 3
            //paso 4
            Node aux = lista.getFirst();
            while(aux!=null)
            {
                    
                Payment_account pay = (Payment_account)aux.getData();
                    bw.write("Mister "+pay.getName() + ":\nYou have enjoyed " + pay.getMovies() + " movies, " + pay.getSeries()
                            + " series and " + pay.getOthers() + " others playback.\nDo not forget to make your payment for the value of "
                            + pay.getPrice() + " pesos\n");
                    
                aux = aux.getLink();
            }
            bw.close();  //paso 5
        }       catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    
}

