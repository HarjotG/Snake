import java.awt.*;
import java.util.Random;

public class Token {
    final int TOKEN_WIDTH = 6;
    final int TOKEN_HEIGHT = 6;

    private int x, y, score;
    private Snake snake;

    public Token (Snake snake){
        changePosition();
        score = 0;
        this.snake = snake;
    }

    public void changePosition(){
        Random random = new Random();
        x = random.nextInt(SnakeGame.WIDTH - 22 - TOKEN_WIDTH) + 7;
        y = random.nextInt(SnakeGame.HEIGHT - 37 - TOKEN_HEIGHT) + 7;
//        x = (int) (Math.random() * (SnakeGame.WIDTH - 12) - TOKEN_WIDTH);
//        y = (int) (Math.random() * (SnakeGame.HEIGHT - 30) - TOKEN_HEIGHT);

    }

    public int getScore(){
        return score;
    }

    public void draw(Graphics g){
        g.setColor(Color.green);
        g.fillRect(x, y, TOKEN_WIDTH, TOKEN_HEIGHT);
    }

    public boolean snakeScored(){
        int snakeX = snake.getX() + snake.SNAKE_WIDTH / 2;
        int snakeY = snake.getY() + snake.SNAKE_HEIGHT / 2;

        if(snakeX >= x - 1 && snakeX <= (x + TOKEN_WIDTH + 1)){
            if(snakeY >= y - 1 && snakeY <= (y + TOKEN_HEIGHT + 1)){
                changePosition();
                score++;
                snake.setElongate(true);
                return true;
            }
        }
        return false;

    }
}
