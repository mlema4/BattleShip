import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;


public class BattleShipGrid{
  private Cell[][] cells;
  private JPanel grid;
  private JPanel battleGrid;
  private int[][] gridRepresentation;
  private JLabel xAxis, yAxis;
  private ArrayList<Coordinate> carrier = new ArrayList<>();;
  private ArrayList<Coordinate> battleship =new ArrayList<>();
  private ArrayList<Coordinate> patrolboat = new ArrayList<>();
  private ArrayList<Coordinate> submarine = new ArrayList<>();
  private ArrayList<Coordinate> destroyer = new ArrayList<>();
  private ArrayList<Integer> shipcordX= new ArrayList<>();
  private ArrayList<Integer> shipcordY= new ArrayList<>();
  public int positionsNeeded = -1;
  public int currentShip;
  public String currentShipName;

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
        cells[i][j] = tmp;
        tmp.addToGrid(battleGrid);
        gridRepresentation[i][j] = 0;
      }
    }

    gbc.gridx = 1;
    gbc.gridy = 1;
    gbc.gridwidth = 2;
    grid.add(battleGrid, gbc);

    //setlisteners();
  }


  public void setlisteners(JLabel statusBar){
    for(int i=0; i<10; i++ ){
      for(int j=0; j<10;j++){
        int ifinal = i;
        int jfinal = j;
        Cell tmp = cells[i][j];
        JButton buttontmp = tmp.getButton();
        buttontmp.addActionListener(new ActionListener(){
          @Override
          public void actionPerformed(ActionEvent actionEvent){


            Coordinate tmp = new Coordinate(ifinal, jfinal);
            if(checkValid(tmp))
              return;
              shipcordX.add(ifinal);
              shipcordY.add(jfinal);
            positionsNeeded--;
            statusBar.setText("PLEASE CLICK " + positionsNeeded + " MORE CELLS TO PLACE " + currentShipName);
          if(positionsNeeded == 0){
            if(check() == false)
                statusBar.setText("PLEASE TRY AGAIN. PLEASE CLICK " + positionsNeeded + " MORE CELLS TO PLACE " + currentShipName);

            else{
              changeship();
              statusBar.setText("PLEASE CLICK " + positionsNeeded + " MORE CELLS TO PLACE " + currentShipName);
              System.out.println("LINE 117");
            }
          }

          }
        });
      }
    }
  }

  public boolean checkValid(Coordinate tmp){
    if(currentShipName.equals("Aircraft Carrier")){
      System.out.println("LINE 128");
      return false;
    }
    else if(currentShipName.equals("Battleship"))
      return carrier.contains(tmp);
    else if(currentShipName.equals("Destroyer"))
      return (carrier.contains(tmp) || battleship.contains(tmp));
    else if(currentShipName.equals("Submarine"))
      return (carrier.contains(tmp) || battleship.contains(tmp) || destroyer.contains(tmp));
    else
      return (carrier.contains(tmp) || battleship.contains(tmp) || destroyer.contains(tmp) || submarine.contains(tmp));
  }

  public boolean checkHorizontal(){
    if(shipcordX.get(0) == shipcordX.get(currentShip-1)){ //Means that the ship might have been places HORIZONTAL
      if((shipcordY.get(0)+currentShip-1) == shipcordY.get(currentShip-1)){
        for(int i =1; i<currentShip-1; i++){
          String path = "./images/batt" + (i+1) + ".gif";
          System.out.println(path);
          cells[shipcordX.get(i)][shipcordY.get(i)].changeImage(path);
        }
        cells[shipcordX.get(0)][shipcordY.get(0)].changeImage("./images/batt1.gif");
        cells[shipcordX.get(currentShip-1)][shipcordY.get(currentShip-1)].changeImage("./images/batt5.gif");
        return true;
      }
    }
    return false;
  }

  public boolean checkVertical(){
    if(shipcordY.get(0) == shipcordY.get(currentShip-1)){ //Means that the ship might have been places Vertical
      if((shipcordX.get(0)+currentShip-1) == shipcordX.get(currentShip-1)){
        for(int i =1; i<currentShip-1; i++){
          String path = "./images/batt" + (i+6) + ".gif";
          System.out.println(path);
          cells[shipcordX.get(i)][shipcordY.get(i)].changeImage(path);
        }
        cells[shipcordX.get(0)][shipcordY.get(0)].changeImage("./images/batt6.gif");
        cells[shipcordX.get(currentShip-1)][shipcordY.get(currentShip-1)].changeImage("./images/batt10.gif");
        return true;
      }
    }
    return false;
  }



  public void changeship(){
    if(currentShipName.equals("Aircraft Carrier")){
      for(int i=0; i<currentShip; i++){
        carrier.add(new Coordinate(shipcordX.get(i), shipcordY.get(i)));
      }
        currentShipName = "Battleship";
        currentShip = 4;
        positionsNeeded =4;
      }
      else if(currentShipName.equals("Battleship")){
        for(int i=0; i<currentShip; i++){
          battleship.add(new Coordinate(shipcordX.get(i), shipcordY.get(i)));
        }
        currentShipName = "Destroyer";
        currentShip = 3;
        positionsNeeded = 3;
      }
      else if(currentShipName.equals("Destroyer")){
        for(int i=0; i<currentShip; i++){
          destroyer.add(new Coordinate(shipcordX.get(i), shipcordY.get(i)));
        }
        currentShipName = "Submarine";
        currentShip = 3;
        positionsNeeded =3;
      }
      else if(currentShipName.equals("Submarine")){
        for(int i=0; i<currentShip; i++){
          submarine.add(new Coordinate(shipcordX.get(i), shipcordY.get(i)));
        }
        currentShipName = "Patrol Boat";
        currentShip = 2;
        positionsNeeded =2;
      }
      else if(currentShipName.equals("Patrol Boat")){
        for(int i=0; i<currentShip; i++){
          patrolboat.add(new Coordinate(shipcordX.get(i), shipcordY.get(i)));
        }
        //changeActionListener;
      }
      shipcordX = new ArrayList<Integer>();
      shipcordY = new ArrayList<Integer>();

  }

  public boolean check(){
    Collections.sort(shipcordX);
    Collections.sort(shipcordY);
    if(checkHorizontal())
      return true;
    if(checkVertical())
      return true;

    positionsNeeded = currentShip;
    shipcordY = new ArrayList<Integer>();
    shipcordX = new ArrayList<Integer>();
    return false;

  }
  public int setShip(int sizeShip, String name){
    if(positionsNeeded == -1)
      positionsNeeded = sizeShip;
    currentShip = sizeShip;
    currentShipName = name;
    return positionsNeeded;

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

  public Cell [][] getCells(){
    return cells;
  }

}
