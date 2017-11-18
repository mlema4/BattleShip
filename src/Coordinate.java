/*Manuel Lema
 *Abdulaziz Malik
 *Coordinate class to help with checking coordinates from buttons
 *https://stackoverflow.com/questions/5303539/didnt-java-once-have-a-pair-class 
 * 
 * 
 */

public class Coordinate{

    private int x,y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinate Coordinate = (Coordinate) o;

        if (x != Coordinate.x) return false;
        if (y != Coordinate.y) return false;

        return true;
    }
}
