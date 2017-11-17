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
  private boolean start = false;
  private String clientStarter;
  public static boolean serverStarter = false;

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
          System.out.println("before start"+ start +" " + inputLine);
          if(!start){
            if(inputLine.equals("cf")){
              //clientStarter = "cf";
              System.out.println("cf print: " + inputLine);
              Frame.cf = true;
            }
          }
          if(!start){
            if(Frame.serverReady && Frame.cf){
              System.out.println("server is ready");
              Frame.lock = false;
              Frame.statusBar.setText("YOUR TURN");
              start=true;
            }
          }


          else{
            System.out.println ("from Cilent: " + inputLine);
            if(inputLine.equals("hit")){
              //server.opponentPlayerGrid.updateOppBoard(true);
              //Frame.lock = false;
            }

            else if(inputLine.equals("miss")){
              //server.opponentPlayerGrid.updateOppBoard(false);
            }

            else if (inputLine.equals("exit")){
              server.serverContinue = false;
              break;
            }

            else{
              int x = Character.getNumericValue(inputLine.charAt(0));
              int y = Character.getNumericValue(inputLine.charAt(2));
              System.out.println(x + " " + y);
              server.playerGrid.checkPlayerBoard(x,y);
              Frame.lock = false;
              Frame.statusBar.setText("YOUR TURN");

            }

          }



        }
      }
      out.close();
      in.close();
      clientSocket.close();

    }
    catch (IOException e)
    {
      System.err.println("Problem with Communication Server");
      System.err.println(e);
      //System.exit(1);
    }
  }
}
