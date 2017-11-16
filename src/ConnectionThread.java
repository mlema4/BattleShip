import java.net.*; 
import java.io.*; 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


class ConnectionThread extends Thread
{
  Server server;
  
  public ConnectionThread (Server s)
  {
    server = s;
    start();
  }
  
  public void run()
  {
    server.serverContinue = true;
    
    try 
    { 
        server.serverSocket = new ServerSocket(10008); 
      //gui.portInfo.setText("Listening on Port: " + gui.serverSocket.getLocalPort());
      System.out.println ("Connection Socket Created");
      try { 
        while (server.serverContinue)
        {
          System.out.println ("Waiting for Connection");
          //gui.ssButton.setText("Stop Listening");
          new CommunicationThread (server.serverSocket.accept(), server); 
        }
      } 
      catch (IOException e) 
      { 
        System.err.println("Accept failed."); 
        System.exit(1); 
      } 
    } 
    catch (IOException e) 
    { 
      System.err.println("Could not listen on port: 10008."); 
      System.exit(1); 
    } 
    finally
    {
      try {
        server.serverSocket.close(); 
      }
      catch (IOException e)
      { 
        System.err.println("Could not close port: 10008."); 
        System.exit(1); 
      } 
    }
  }
}