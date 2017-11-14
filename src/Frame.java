import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JFrame;


public class Frame{
  public JPanel game;
  public BattleShipGrid playerGrid;


  final static boolean shouldFill = true;
  final static boolean shouldWeightX = true;
  final static boolean RIGHT_TO_LEFT = false;


  public Frame(){
    game = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    if(shouldFill){
      gbc.fill = GridBagConstraints.HORIZONTAL;
    }
    game.setBackground(Color.gray);


    game.setBorder(new EmptyBorder(2,3,2,3));
    gbc.gridx = 0;
    gbc.gridy = 0;
    JButton start = new JButton("START");
    start.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent actionEvent){
          game.removeAll();
          game.revalidate();
          game.repaint();
          playerGrid.addToPanel(game, gbc);


      }
    });
    game.add(start, gbc);

    playerGrid = new BattleShipGrid();

    gbc.gridx =1;
    gbc.gridy =1;

    playerGrid.addToPanel(game, gbc);

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
