import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.*;
import java.awt.event.*;
import javax.swing.event.*;

public class Frame{
  public JPanel game;
  public BattleShipGrid playerGrid;
  private JMenuBar menubar;
  private JMenu Start, Help, Exit;
  private JMenuItem connect, about, help, statistics;
  private JPanel statusBar;

  final static boolean shouldFill = true;
  final static boolean shouldWeightX = true;
  final static boolean RIGHT_TO_LEFT = false;


  public Frame(){


    game = new JPanel(new BorderLayout());
    // GridBagConstraints gbc = new GridBagConstraints();
    // if(shouldFill){
    //   gbc.fill = GridBagConstraints.HORIZONTAL;
    // }
    //game.setBackground(Color.gray);

    //Add componenets to menubar
    menubar = new JMenuBar();
    Start = new JMenu("Start");
    connect = new JMenuItem("Connect");
    connect.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent actionEvent){
        //
      }
    });
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

    game.add(playerGrid.getGrid(), BorderLayout.CENTER);
    //playerGrid.addToPanel(game, BorderLayout.CENTER);

  }

  public void setShips(){

  }

  public void createFrame(){

    JFrame f = new JFrame("Demo");
    f.pack();
    f.setSize(700, 700);
    f.add(game);

    f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    f.setLocationByPlatform(true);

    f.pack();

    f.setVisible(true);

  }

}
