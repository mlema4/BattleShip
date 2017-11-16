import java.awt.Dimension;
import java.awt.Image;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Cell{

  private JButton button;
  private char xCoordinate;
  private char yCoordinate;
  private int x,y;


  public Cell(char xValue, char yValue){
    button = new JButton();
    button.setBorder(BorderFactory.createEmptyBorder());
    button.setPreferredSize(new Dimension(50, 50));
    xCoordinate = xValue;
    yCoordinate = yValue;
    //button.setBackground(Color.blue);
    try {
      Image img = ImageIO.read(getClass().getResource("./images/batt100.gif"));
      Image newimg = img.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH ) ;
      button.setIcon(new ImageIcon(newimg));
    }
    catch (Exception ex) {
      System.out.println(ex);
    }
    try {
      Image img = ImageIO.read(getClass().getResource("./images/batt101.gif"));
      Image newimg = img.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH ) ;
      button.setRolloverIcon(new ImageIcon(newimg));
    }
    catch (Exception ex) {
      System.out.println(ex);
    }

  }

  public void setXY(int x, int y){
    this.x = x;
    this.y = y;
  }

  public void setText(String newText){
    button.setText(newText);
  }

  public char getxCoordinates (){
    return xCoordinate;
  }

  public char getyCoordinates (){
    return yCoordinate;
  }

  public void getImagePath(String image){

  }
  public JButton getButton(){
    return button;
  }
  public void addToGrid(JPanel panel){
    panel.add(button);
  }

  public void changeImage(String newImage){
    String imagePath = newImage;
    try {

      Image img = ImageIO.read(getClass().getResource(imagePath));
      Image newimg = img.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH ) ;
      button.setIcon(new ImageIcon(newimg));
    }
    catch (Exception ex) {
      System.out.println(ex);
    }


  }

}
