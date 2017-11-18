/*Manuel Lema
 *Abdulaziz Malik
 * 
 *Client class for the client server component 
 * 
 */

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
  public BattleShipGrid playerGrid;
  public BattleShipGrid opponentPlayerGrid;


    // set up GUI
    public Client(BattleShipGrid playerGrid, BattleShipGrid opponentPlayerGrid){
      // create buttons
      this.playerGrid = playerGrid;
      this.opponentPlayerGrid = opponentPlayerGrid;
      connected = false;
      Frame.connected =true;
      Frame.statusBar.setText("CONNECTED...PLEASE CLICK ON A CELL TO START PLACING SHIPS");
      start();

    } // end CountDown constructor

  
    public void run()
    {
      if (connected == false)
      {
        try {
            echoSocket = new Socket("127.0.0.1", 10008);
            out = new PrintWriter(echoSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));

            connected = true;

        } catch (NumberFormatException e) {

        } catch (UnknownHostException e) {

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Couldn't connect to host");
 
        }

        while ( true ) {
          try{
 
              if((temp = in.readLine())!=null){
              if(temp.equals("sr")){
                opponentPlayerGrid.sendReady();
              }
              if(temp.equals("hit")){
            	  Frame.hits++;
            	  opponentPlayerGrid.updateOppBoard(true);

              }
              else if(temp.equals("miss")){

                opponentPlayerGrid.updateOppBoard(false);
              }

              else if(temp.equals("exit")){
                break;
              }

              else{
                int x = Character.getNumericValue(temp.charAt(0));
                int y = Character.getNumericValue(temp.charAt(2));

                if(playerGrid.checkPlayerBoard(x,y)==0){
                  Frame.lock = true;
                }
                else{
                Frame.lock = false;

                Frame.statusBar.setText("YOUR TURN");
                }


              }

              System.out.println("from server: " + temp);

            }
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

        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog(null, "couldn't close socket");

        }
      }

    }
 } // end class EchoServer3
