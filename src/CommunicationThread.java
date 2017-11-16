import java.net.*; 
import java.io.*; 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


class CommunicationThread extends Thread
{ 
 //private boolean serverContinue = true;
 public static PrintWriter out;
 private Socket clientSocket;
 private Server server;



 public CommunicationThread (Socket clientSoc, Server s)
   {
    clientSocket = clientSoc;
    server = s;
    //gui.history.insert ("Comminucating with Port" + clientSocket.getLocalPort()+"\n", 0);
    start();
   }

 public void run()
   {
    System.out.println ("New Communication Thread Started");

    try { 
         out = new PrintWriter(clientSocket.getOutputStream(), 
                                      true); 
         BufferedReader in = new BufferedReader( 
                 new InputStreamReader( clientSocket.getInputStream())); 

         String inputLine; 

         while (true)
             {
              inputLine = in.readLine();  
              System.out.println ("Server: " + inputLine); 
              //gui.history.insert (inputLine+"\n", 0);
              //out.println("inputLine"); 

              if (inputLine.equals("Bye.")) 
                  break; 

              if (inputLine.equals("End Server.")) 
                server.serverContinue = false; 
             } 

         out.close(); 
         in.close(); 
         clientSocket.close(); 
        } 
    catch (IOException e) 
        { 
         System.err.println("Problem with Communication Server");
         //System.exit(1); 
        } 
    }
} 