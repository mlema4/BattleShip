import java.net.*; 
import java.io.*; 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Server{
  

  private boolean running;

  // Network Items
  boolean serverContinue;
  ServerSocket serverSocket;

   // set up GUI
   public Server()
   {
    // create buttons
    running = false;

   } // end CountDown constructor
   
    // handle button event
    public void doManageHost()
    {
       if (running == false)
       {
         new ConnectionThread (this);
       }
       else
       {
         serverContinue = false;
       }
    }
 } // end class EchoServer3



 







 

