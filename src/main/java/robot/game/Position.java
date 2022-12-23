package robot.game;

public class Position {
    private final int X;
    private final int Y;

    public Position(int newX, int newY){
        this.X = newX;
        this.Y = newY;
    }

    public int getX(){
        return this.X;
    }

    public int getY(){
        return this.Y;
    }
}
