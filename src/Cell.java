import java.awt.Dimension;
import java.awt.Image;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Cell{

  private JButton button;
  private int xCoordinate;
  private char yCoordinate;

  public Cell(int xValue, char yValue){
    button = new JButton();
    button.setBorder(BorderFactory.createEmptyBorder());
    button.setPreferredSize(new Dimension(50, 50));
    xCoordinate = xValue;
    yCoordinate = yValue;

    try {
      Image img = ImageIO.read(getClass().getResource("./images/batt100.gif"));
      Image newimg = img.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH ) ;
      button.setIcon(new ImageIcon(newimg));
    }
    catch (Exception ex) {
      System.out.println(ex);
    }

  }

  public void setText(String newText){
    button.setText(newText);
  }

  public int getxCoordinates (){
    return xCoordinate;
  }

  public int getyCoordinates (){
    return yCoordinate;
  }

  public void getImagePath(String image){

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
