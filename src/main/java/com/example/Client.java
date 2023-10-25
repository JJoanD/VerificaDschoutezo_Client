package com.example;

import java.io.*;
import java.net.*;

public class Client {

     String nomeServer = "localhost";
    int portaServer = 6789;
    Socket mySocket;
    DataOutputStream outVersoServer;
    BufferedReader inDalServer;
    BufferedReader tastiera;
    String stringaUtenteOperando1;
    String stringaUtenteOperando2;
    String stringaRicevutaDalServer;
    String stringaOperazione;
    String stringaScelta;
    
     public Socket connetti(){
      System.out.println("2 CLIENT partito in esecuzione");
    try{
        //per l'input da tastiera
        tastiera = new BufferedReader(new InputStreamReader(System.in));
        //creazione socket
        mySocket = new Socket(nomeServer, portaServer); //possibile utilizzare anche 'InetAddress.getLocalHost' al posto di 'nomeServer'

        outVersoServer = new DataOutputStream(mySocket.getOutputStream());
        inDalServer = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));

    }catch(UnknownHostException e){
        System.err.println("Host sconosciuto");
    }catch(Exception e){
        System.out.println(e.getMessage());
        System.out.println("Errore durante la connessione");
        System.exit(1);
    }
    return mySocket;
    }

    public void comunica(){
        
        for(;;){
            try{
           System.out.println("Inserisci i 2 operendai di cui fare una delle operazioni di base");
            stringaUtenteOperando1 = tastiera.readLine();
            outVersoServer.writeBytes(stringaUtenteOperando1 + "\n");

            stringaRicevutaDalServer = inDalServer.readLine();
            if(stringaRicevutaDalServer.equals("ricevuto primo")){
                System.out.println("primo inviato corretamente");
            
            }
            stringaUtenteOperando2 = tastiera.readLine();
            outVersoServer.writeBytes(stringaUtenteOperando2 + "\n");
            
            if(stringaRicevutaDalServer.equals("ricevuto secondo")){
                System.out.println("secondo inviato corretamente");
            
            }

            System.out.println("Scegliere uan tra le seguenti operazioni");
            System.out.println("+ = addizione , - = sottrazione , / = divisione , * = moltiplicazione ");
            stringaOperazione = tastiera.readLine();
            outVersoServer.writeBytes(stringaOperazione + "\n");

            stringaRicevutaDalServer = inDalServer.readLine();
            if(!stringaOperazione.equals("operazione non esistente")){
                System.out.println("Risultato = " + stringaRicevutaDalServer);

                System.out.println("Scegliere se desidera smettere inserisca 'BYE' , altrimenti continuerà");
                stringaScelta = tastiera.readLine();
                outVersoServer.writeBytes(stringaScelta + "\n");

                if(stringaScelta.equals("BYE")){
                    System.out.println("Chiusura socket");
                    mySocket.close();
                    break;
                }
            }
            
         }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore durante la connessione col server");
            System.exit(1);
         }
        }
        
    }

}
