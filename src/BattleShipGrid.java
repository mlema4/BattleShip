import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import java.util.ArrayList;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JFrame;

public class BattleShipGrid{
  private Cell[][] cells;
  private JPanel grid;
  private JPanel battleGrid;
  private int[][] gridRepresentation;
  private JLabel xAxis, yAxis;
  private int[][] carrier;
  private int[][] battleship;
  private int[][] cruiser;
  private int[][] submarine;
  private int[][] destroyer;


  final static boolean shouldFill = true;
  final static boolean shouldWeightX = true;
  final static boolean RIGHT_TO_LEFT = false;

  public BattleShipGrid(){
    createGrid();
  }

  public void createGrid(){
    xAxis = new JLabel("A B C D E F G H I  J");
    xAxis.setFont(new Font("Serif", Font.BOLD, 45));
    yAxis = new JLabel("<html> 0<br>1<br>2<br>3<br>4<br>5<br>6<br>7<br>8<br>9</html>");
    yAxis.setFont(new Font("Serif", Font.BOLD, 42));
    gridRepresentation = new int[10][10];
    cells = new Cell[10][10];


    grid = new JPanel();
    grid.setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();

    if(shouldFill){
      gbc.fill = GridBagConstraints.HORIZONTAL;
    }

  //  gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.weightx = .2;
    JLabel tmpl = new JLabel();
    grid.add(tmpl, gbc);

    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.gridwidth = 2;
    gbc.weightx = .8;
    grid.add(xAxis, gbc);

  //  gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.gridx = 0 ;
    gbc.gridy = 1;
    //gbc.gridwidth = 2;
    grid.add(yAxis, gbc);


    battleGrid = new JPanel(new GridLayout(10,10,1,1));

    for(int i=0; i<10; i++ ){
      for(int j=0; j<10;j++){
        Cell tmp = new Cell((char)i ,getLetter(j));
        tmp.setXY(i,j);

        tmp.addToGrid(battleGrid);
        gridRepresentation[i][j] = 0;
      }
    }

    gbc.gridx = 1;
    gbc.gridy = 1;
    gbc.gridwidth = 2;
    grid.add(battleGrid, gbc);

  }


  public void setShips(){

  }

  public char getLetter(int val){
    switch (val){
      case 1: return 'A';
      case 2: return 'B';
      case 3: return 'C';
      case 4: return 'D';
      case 5: return 'E';
      case 6: return 'F';
      case 7: return 'G';
      case 8: return 'H';
      case 9: return 'I';
      case 10: return 'J';
      default:
        break;
    }
    return '0';
  }

  public void addToPanel(JPanel panel, GridBagConstraints gbc){
      panel.add(grid, gbc);
  }

  public JPanel getGrid(){
    return grid;
  }

}
