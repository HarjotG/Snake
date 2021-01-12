import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Snake {
    final int SNAKE_WIDTH = 4, SNAKE_HEIGHT = 4;
    final int START_SIZE = 20, START_X = 150, START_Y = 150;
    final int ELONGATE_SIZE = 10;

    List<Point> snakePoints;
    int xDir, yDir;
    boolean isMoving, elongate;

    public Snake(){
        snakePoints = new ArrayList<Point>();
        xDir = 0;
        yDir = 0;

        isMoving = false;
        elongate = false;
        snakePoints.add(new Point(START_X,  START_Y));

        for(int i = 1; i<START_SIZE; i++){
            snakePoints.add(new Point(START_X - i* SNAKE_WIDTH, START_Y));
        }
    }

    public void move(){
        if(isMoving) {
            Point temp = snakePoints.get(0);
            Point last = snakePoints.get(snakePoints.size() - 1);
            Point newStart = new Point(temp.getX() + xDir * SNAKE_WIDTH, temp.getY() + yDir * SNAKE_HEIGHT);

            for (int i = snakePoints.size() - 1; i >= 1; i--) {
                    snakePoints.set(i, snakePoints.get(i - 1));

            }

            snakePoints.set(0, newStart);
            if(elongate){
                for(int i = 0; i < ELONGATE_SIZE; i++) {
                    snakePoints.add(last);
                }
                elongate = false;
            }
        }
    }

    public void draw(Graphics g){
        g.setColor(Color.WHITE);
        for(Point p : snakePoints){
            g.fillRect(p.getX(), p.getY(), SNAKE_WIDTH, SNAKE_HEIGHT);
        }
    }

    public boolean isMoving(){
        return isMoving;
    }

    public void setisMooving(boolean isMoving){
        this.isMoving = isMoving;
    }

    public int getxDir() {
        return xDir;
    }

    public int getyDir() {
        return yDir;
    }

    public void setxDir(int xDir) {
        this.xDir = xDir;
    }

    public void setyDir(int yDir) {
        this.yDir = yDir;
    }

    // X position of head of snake
    public int getX(){
        return snakePoints.get(0).getX();
    }

    // Y position of head of snake
    public int getY(){
        return snakePoints.get(0).getY();
    }

    //checks if snake is colliding with itself
    public boolean snakeCollision(){
        int x = this.getX();
        int y = this.getY();
        for(int i = 1; i < snakePoints.size(); i++){
            if((snakePoints.get(i).getX() == x) && (snakePoints.get(i).getY() == y)){
                return true;
            }
        }
        return false;
    }

    public void setElongate(Boolean b){
        this.elongate = b;
    }
}
