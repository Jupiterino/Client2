package com.example; // CLIENT

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;
public class App {
    
    public static void main( String[] args ){
        int Number=0;
        int cont = 0;
        int risposta = 0;
        BufferedReader tastiera;
        try{
            System.out.println("Il client è partito");
            tastiera = new BufferedReader(new InputStreamReader(System.in));
            Socket miosocket = new Socket("localHost",3000);
            BufferedReader inDalServer = new BufferedReader(new InputStreamReader (miosocket.getInputStream()));//tubo nel quale ascolto
            DataOutputStream outVersoServer = new DataOutputStream(miosocket.getOutputStream());//tubo nel quale parlo

            outVersoServer = new DataOutputStream(miosocket.getOutputStream());
            inDalServer = new BufferedReader(new InputStreamReader(miosocket.getInputStream()));

            
            do{
            
            
            System.out.println("provo col numero: ");
            String StringaUtente = tastiera.readLine();
            outVersoServer.writeBytes(StringaUtente+'\n');


            Number = Integer.valueOf(StringaUtente);  
            //String stringaRicevuta = inDalServer.readLine();
            //System.out.println(StringaUtente);
            risposta = Integer.valueOf(inDalServer.readLine());
            
            if(risposta == 2){
                System.out.println("numero troppo grande");
                
            }else if(risposta == 1){
                System.out.println("numero troppo piccolo");
            }else if(risposta == 3){

                System.out.println("HAI INDOVINATO IN "+inDalServer.readLine()+" tentativi");  
            }

            }while(risposta != 3);   
            miosocket.close();     
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore durante la connessione!");
            System.exit(1);
        }
    }
}
