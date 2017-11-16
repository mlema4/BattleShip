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

 public static String inputLine;



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
              if((inputLine = in.readLine()) != null){
                  Frame.lock = false;
              }
              System.out.println ("from Cilent: " + inputLine);
              //gui.history.insert (inputLine+"\n", 0);
              //out.println("inputLine");

              if (inputLine.equals("exit")){
                  server.serverContinue = false;
                  break;
              }

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
