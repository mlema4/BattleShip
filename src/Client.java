import java.net.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Client extends Thread{

  // Network Items
  public static boolean connected;
  Socket echoSocket;
  public static PrintWriter out;
  public static BufferedReader in;
  public static String temp;

    // set up GUI
    public Client(){
      // create buttons
      connected = false;
      start();

    } // end CountDown constructor

    /*
    public void sendCordinates(String coordinates){
      try
      {
        out.println(coordinates);
        out.flush();
        //history.insert ("From Server: " + in.readLine() + "\n" , 0);
      }
      catch (Exception e)
      {
        JOptionPane.showMessageDialog(null, "couldn't send cordinates");
      }
    }
    */
    public void run()
    {
      if (connected == false)
      {
        try {
            //machineName = machineInfo.getText();
            //portNum = Integer.parseInt(portInfo.getText());
            echoSocket = new Socket("127.0.0.1", 10008);
            out = new PrintWriter(echoSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
            //sendButton.setEnabled(true);
            connected = true;
            //connectButton.setText("Disconnect from Server");
        } catch (NumberFormatException e) {
            //history.insert ( "Server Port must be an integer\n", 0);
            //return 1;
        } catch (UnknownHostException e) {
            //history.insert("Don't know about host: " + machineName , 0);
            //return 1;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "IOException");
            //history.insert ("Couldn't get I/O for " + "the connection to: " + machineName , 0);
            //return 1;
        }

        while ( true ) {
          try{

            if((temp = in.readLine())!=null){
              Frame.lock = false;
            }

            if(temp.equals("exit")){
              break;
            }
            System.out.println("from server: " + temp);
          }
          catch(Exception e){

          }
        }


      }
      else
      {
        try
        {
          out.close();
          in.close();
          echoSocket.close();
          //sendButton.setEnabled(false);
          //connected = false;
          //connectButton.setText("Connect to Server");
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog(null, "couldn't close socket");
            //history.insert ("Error in closing down Socket ", 0);
            //return 1;
        }
      }
      //return 0;
    }
 } // end class EchoServer3
