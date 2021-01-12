import javax.swing.*;
import java.awt.*;

public class Renderer extends JPanel {

    Snake snake;
    Token token;

    public Renderer(Snake snake, Token token){
        this.snake = snake;
        this.token = token;
    }

    public void paint(Graphics g){
        paint((Graphics2D) g);
    }

    public void paint(Graphics2D g){
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, SnakeGame.WIDTH, SnakeGame.HEIGHT);
        if(!SnakeGame.gameOver) {
            snake.draw(g);
            token.draw(g);
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setColor(Color.red);
            g.setFont(new Font("Arial", 1, 14));
            g.drawString("Score: " + token.getScore(), 9, 16);
        }else{
            g.setColor(Color.red);
            g.setFont(new Font("Arial", 1, 50));
            g.drawString("Game Over", SnakeGame.WIDTH/2 - 150, 140);
            g.setFont(new Font("Arial", 1, 24));
            g.drawString("Score: " + token.getScore(), SnakeGame.WIDTH/2 - 75, 170);


        }

    }

}
