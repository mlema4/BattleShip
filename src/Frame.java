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
  public BattleShipGrid playerGrid;
  public BattleShipGrid opponentPlayerGrid;
  private JMenuBar menubar;
  private JMenu Start, Help, Exit;
  private JMenuItem connect, about, help, statistics, host;
  private JLabel statusBar;
  //pricate Cells[][] tmpcells;
  private boolean listenersSet = false;

  final static boolean shouldFill = true;
  final static boolean shouldWeightX = true;
  final static boolean RIGHT_TO_LEFT = false;
  public boolean connection = false;
  public static boolean lock;

  public static String coordinates;
  public static int type;


  public Frame(){


    game = new JPanel(new BorderLayout());

    statusBar = new JLabel();

    menubar = new JMenuBar();
    Start = new JMenu("Start");
    connect = new JMenuItem("Connect");
    connect.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent actionEvent){
        Client newConnection = new Client();
        //if(newConnection.doManageConnection() == 0){
          type = 0;
          statusBar.setText("Connecting..." + "PLEASE CLICK 5 CELLS TO PLACE AIRCRAFT CARRIER");
          setShips(5, "Aircraft Carrier");
          opponentPlayerGrid = new BattleShipGrid();
          opponentPlayerGrid.setOpponentBoardlisteners();
          game.add(opponentPlayerGrid.getGrid(), BorderLayout.EAST);
          f.setSize(1200,700);
          Frame.lock = false;

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
        Server server = new Server();
        server.doManageHost();
        statusBar.setText("Connecting..." + "PLEASE CLICK 5 CELLS TO PLACE AIRCRAFT CARRIER");
        setShips(5, "Aircraft Carrier");
        opponentPlayerGrid = new BattleShipGrid();
        opponentPlayerGrid.setOpponentBoardlisteners();
        game.add(opponentPlayerGrid.getGrid(), BorderLayout.EAST);
        f.setSize(1200,700);
        Frame.lock = false;
      }
    });
    Start.add(host);
    Start.add(connect);

    Help = new JMenu("Help");
    help = new JMenuItem("Help");
    help.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent actionEvent){

      }
    });
    about = new JMenuItem("About");
    about.addActionListener(new ActionListener(){
      @Override
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

  }

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

    f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    f.setLocationByPlatform(true);

    f.pack();

    f.setVisible(true);

  }

}