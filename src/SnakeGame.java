import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SnakeGame extends JFrame implements Runnable, KeyListener {
    public static SnakeGame snakeGame;
    public static final int WIDTH = 400, HEIGHT = 400;
    public static boolean gameOver;
    public Renderer renderer;
    Image img;
    Graphics gfx;
    Snake snake;
    Thread thread;
    Token token;
    boolean canMoveHoriz;
    boolean canMoveVert;

    public SnakeGame(){
        gameOver = false;
        canMoveHoriz = true;
        canMoveVert = true;
        snake = new Snake();
        token = new Token(snake);
        renderer = new Renderer(snake, token);
        JFrame jframe = new JFrame("Snake Game");
        jframe.setSize(WIDTH, HEIGHT);
        jframe.setResizable(false);
        jframe.setVisible(true);
        jframe.setDefaultCloseOperation(jframe.EXIT_ON_CLOSE);

        jframe.add(renderer);
        jframe.addKeyListener(this);
        thread = new Thread(this);
        thread.start();
    }

    public static void main(String args[]){
        snakeGame = new SnakeGame();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if (!snake.isMoving()) {
                if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN ||
                        key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) {

                    snake.setisMooving(true);
                }
            }
            if (key == KeyEvent.VK_UP && canMoveVert == true) {
                if (snake.getyDir() != 1) {
                    snake.setyDir(-1);
                    snake.setxDir(0);
                    canMoveHoriz = false;
                }
            }
            if (key == KeyEvent.VK_DOWN && canMoveVert == true) {
                if (snake.getyDir() != -1) {
                    snake.setyDir(1);
                    snake.setxDir(0);
                    canMoveHoriz = false;
                }
            }
            if (key == KeyEvent.VK_LEFT && canMoveHoriz == true) {
                if (snake.getxDir() != 1) {
                    snake.setyDir(0);
                    snake.setxDir(-1);
                    canMoveVert = false;
                }
            }
            if (key == KeyEvent.VK_RIGHT && canMoveHoriz == true) {
                if (snake.getxDir() != -1) {
                    snake.setyDir(0);
                    snake.setxDir(1);
                    canMoveVert = false;
                }
            }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        for(;;){
            if(!gameOver) {
                snake.move();
                checkGameOver();
                token.snakeScored();
                canMoveVert = true;
                canMoveHoriz = true;
            }

            renderer.repaint();

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void checkGameOver(){
        // checks if snake x position is off the screen
        if(snake.getX() < 0 || snake.getX() > WIDTH - snake.SNAKE_WIDTH - 12){
            gameOver = true;

        }
        // checks is snake y position is off the screen
        if(snake.getY() < 0 || snake.getY() > HEIGHT - snake.SNAKE_HEIGHT - 35){
            gameOver = true;

        }

        // checks if snake is colliding with itself
        if(snake.snakeCollision()){
            gameOver = true;
        }
    }


}

