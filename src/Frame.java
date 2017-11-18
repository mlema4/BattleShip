import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.*;
import java.awt.event.*;
import javax.swing.event.*;

public class Frame{
  private JFrame f;
  public JPanel game;
  public JPanel opponentPannel;
  public static BattleShipGrid playerGrid;
  public static BattleShipGrid opponentPlayerGrid;
  private JMenuBar menubar;
  private JMenu Start, Help, Exit;
  private JMenuItem connect, about, help, statistics, host;
  public static JLabel statusBar;
  public static int hits = 0;
  //pricate Cells[][] tmpcells;
  private boolean listenersSet = false;

  final static boolean shouldFill = true;
  final static boolean shouldWeightX = true;
  final static boolean RIGHT_TO_LEFT = false;
  public boolean connection = false;
  public static boolean lock;

  public static String coordinates;
  public static int type;
  private WindowListener threadExiter;
  public static boolean serverReady = false;
  public static boolean cf = false;
  public static int lastXclicked;
  public static int lastYclicked;
  public static boolean connected = false;


  public Frame(){


    game = new JPanel(new BorderLayout());

    statusBar = new JLabel();

    menubar = new JMenuBar();
    Start = new JMenu("Start");
    connect = new JMenuItem("Connect");
    opponentPlayerGrid = new BattleShipGrid();
    connect.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent actionEvent){
        Client newConnection = new Client(playerGrid, opponentPlayerGrid);
        //if(newConnection.doManageConnection() == 0){
          type = 0;
          //kilstatusBar.setText("PLEASE CLICK 5 CELLS TO PLACE AIRCRAFT CARRIER");
          setShips(5, "Aircraft Carrier");
          //opponentPlayerGrid = new BattleShipGrid();
          //opponentPlayerGrid.isShipsSet = playerGrid.isShipsSet;
          opponentPlayerGrid.setOpponentBoardlisteners();
          game.add(opponentPlayerGrid.getGrid(), BorderLayout.EAST);
          f.setSize(1200,700);
          Frame.lock = true;

        //}
        //else{
          //JOptionPane.showMessageDialog(null, "couldn't connect to host");
        //}

      }
    });

    host = new JMenuItem("Host");
    host.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent actionEvent){
        type = 1;
        //statusBar.setText("Connecting...");
        Server server = new Server(playerGrid, opponentPlayerGrid);
        server.doManageHost();
        statusBar.setText("Waiting for Connection...");
        setShips(5, "Aircraft Carrier");
        //opponentPlayerGrid = new BattleShipGrid();
        //opponentPlayerGrid.isShipsSet = playerGrid.isShipsSet;
        opponentPlayerGrid.setOpponentBoardlisteners();
        game.add(opponentPlayerGrid.getGrid(), BorderLayout.EAST);
        f.setSize(1200,700);
        Frame.lock = true;
      }
    });
    Start.add(host);
    Start.add(connect);

    Help = new JMenu("Help");
    help = new JMenuItem("Help");

    help.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent actionEvent){
        JOptionPane.showMessageDialog(null, "connection help:\n   "+
        "To be able to play. One user has to host the game and another has to connect.\n   "+
        "If a host if not found a message will aper that will display a message. You can\n   " +
        "try again or host the game and wait for a player. If no message aper then that\n   " +
        "means a connection was made.\n\n   " +
        "To connect, go to the start menu item and click on connect.\n   " +
        "To Host, go to start menu item and click host then wait for someone.\n\ngame help:\n   " +
       "Once a host and a client are matched. Then both players set their board.\n   " +
       "You can't place ships on your opponent's board. Each player gets 5 different\n   " +
       "ships. The ship vary in size. A Aircraft Carriers is size 5. This means that\n   " +
       "it takes 5 cells on your board, Battleship is size 4, Destroyer is size 3,\n   " +
       "Submarine is size 3, and Patrol Boat is size 2. You can't place your ships in\n   " +
       "diagonals, either vertical or horizontal. Once both players set the board if a\n   " +
       "player is host they get to go first. The goal of the game is sink all of your\n   " +
       "opponent's ships. Plays go on a turn by turn bases. Each click on your opponent's board\n   " +
       "will try to destroy a part of one of their ship. Your board and the opponent board will update\n   " +
       "based on what parts of your ship are destroy and where you hit and missed.\n   " +
       "Whoever destroys all of their opponent ship first wins.");
      }
    });
    about = new JMenuItem("About");
    about.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent actionEvent){
        JOptionPane.showMessageDialog(null, "Author: Abdulaziz Malik - amalik11\n Manuel Lema - mlema4");
      }
    });
    statistics = new JMenuItem("Statistics");
    statistics.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent actionEvent){
        //
      }
    });

    Help.add(help);
    Help.add(about);
    Help.add(statistics);

    Exit = new JMenu("Exit");
    Exit.addMenuListener(new MenuListener(){
      @Override
      public void menuSelected(MenuEvent e){
        System.exit(0);
      }
      @Override
      public void menuDeselected(MenuEvent e) {
      }

      @Override
      public void menuCanceled(MenuEvent e) {
      }
    });

    menubar.add(Start);
    menubar.add(Help);
    menubar.add(Exit);

    game.setBorder(new EmptyBorder(2,3,2,3));


    game.add(menubar, BorderLayout.NORTH);

    playerGrid = new BattleShipGrid();



    game.add(playerGrid.getGrid(), BorderLayout.WEST);
    //playerGrid.addToPanel(game, BorderLayout.CENTER);


    game.add(statusBar, BorderLayout.SOUTH);
  //  tmpcells = playerGrid.getCells();


  //exitlistener
  threadExiter = new WindowAdapter() {

        @Override
        public void windowClosing(WindowEvent e) {
          if(type == 0){
            Client.connected = true;
            Client.temp = "exit";
          }
          else{
            CommunicationThread.inputLine = "exit";
          }
          System.exit(0);
        }
    };


  }

  public static void checkPlayerBoard(int x,int y){
    playerGrid.checkPlayerBoard(x, y);
  }
  // public static void updateOppBoard(boolean hit){
  //   opponentPlayerGrid.ifhit(lastXclicked, lastYclicked, hit);
  // }
  public void setShips(int size, String ship){
    if(!listenersSet){
      playerGrid.setlisteners(statusBar);
    }
      int selectedPosLeft = playerGrid.setShip(size, ship);
//      selectedPosLeft = playerGrid.setShip(size);
  //  }

  }

  public void opponentsPannel(){
    game = new JPanel(new BorderLayout());
    opponentPlayerGrid = new BattleShipGrid();
  }

  public void createFrame(){

    f = new JFrame("Demo");
    f.pack();
    f.setSize(700, 700);
    f.add(game);
    f.addWindowListener(threadExiter);


    f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    f.setLocationByPlatform(true);

    f.pack();

    f.setVisible(true);

  }

}
