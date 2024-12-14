package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Board extends JPanel {

    private Timer timer;
    private String message = "Game Over";
    private Ball ball;
    private Paddle paddle;
    private Brick[] bricks;
    private boolean inGame = true;

    // Static attributes for score and level
    private static int score = 0;
    private static int level = 1;

    public Board() {
        initBoard();
    }

    private void initBoard() {
        addKeyListener(new TAdapter());
        setFocusable(true);
        setPreferredSize(new Dimension(Commons.WIDTH, Commons.HEIGHT));
        gameInit();
    }

    private void gameInit() {
        bricks = new Brick[Commons.N_OF_BRICKS];
        ball = new Ball();
        paddle = new Paddle();

        int k = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 6; j++) {
                bricks[k] = new Brick(j * 40 + 30, i * 10 + 50);
                k++;
            }
        }

        timer = new Timer(Commons.PERIOD, new GameCycle());
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

            if (inGame) {
                drawObjects(g2d);
            } else {
                gameFinished(g2d);
            }

            Toolkit.getDefaultToolkit().sync();
        } catch (Exception e) {
            System.out.println("Error during rendering: " + e.getMessage());
        }
    }

    private void drawObjects(Graphics2D g2d) {
        try {
            g2d.drawImage(ball.getImage(), ball.getX(), ball.getY(),
                    ball.getImageWidth(), ball.getImageHeight(), this);
            g2d.drawImage(paddle.getImage(), paddle.getX(), paddle.getY(),
                    paddle.getImageWidth(), paddle.getImageHeight(), this);

            for (Brick brick : bricks) {
                if (!brick.isDestroyed()) {
                    g2d.drawImage(brick.getImage(), brick.getX(), brick.getY(),
                            brick.getImageWidth(), brick.getImageHeight(), this);
                }
            }

            // Draw score and level
            g2d.setColor(Color.BLACK);
            g2d.setFont(new Font("Verdana", Font.BOLD, 14));
            g2d.drawString("Score: " + score, 10, 20);
            g2d.drawString("Level: " + level, Commons.WIDTH - 80, 20);
        } catch (Exception e) {
            System.out.println("Error during object drawing: " + e.getMessage());
        }
    }

    private void gameFinished(Graphics2D g2d) {
        try {
            Font font = new Font("Verdana", Font.BOLD, 18);
            FontMetrics fontMetrics = this.getFontMetrics(font);

            g2d.setColor(Color.BLACK);
            g2d.setFont(font);
            g2d.drawString(message, (Commons.WIDTH - fontMetrics.stringWidth(message)) / 2, Commons.WIDTH / 2);

            // Display play again option
            JOptionPane.showMessageDialog(this, message + "\nScore: " + score + "\nLevel: " + level, "Game Over", JOptionPane.INFORMATION_MESSAGE);
            int response = JOptionPane.showConfirmDialog(this, "Do you want to play again?", "Play Again", JOptionPane.YES_NO_OPTION);

            if (response == JOptionPane.YES_OPTION) {
                resetGame();
            } else {
                System.exit(0);
            }
        } catch (Exception e) {
            System.out.println("Error during game over handling: " + e.getMessage());
        }
    }

    private void resetGame() {
        try {
            score = 0;
            level = 1;
            inGame = true;
            gameInit();
        } catch (Exception e) {
            System.out.println("Error resetting the game: " + e.getMessage());
        }
    }

    private class TAdapter extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            try {
                paddle.keyReleased(e);
            } catch (Exception ex) {
                System.out.println("Error during key release: " + ex.getMessage());
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {
            try {
                paddle.keyPressed(e);
            } catch (Exception ex) {
                System.out.println("Error during key press: " + ex.getMessage());
            }
        }
    }

    private class GameCycle implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                doGameCycle();
            } catch (Exception ex) {
                System.out.println("Error during game cycle: " + ex.getMessage());
            }
        }
    }

    private void doGameCycle() {
        try {
            ball.move();
            paddle.move();
            checkCollision();
            repaint();
        } catch (Exception e) {
            System.out.println("Error during game cycle execution: " + e.getMessage());
        }
    }

    private void stopGame() {
        try {
            inGame = false;
            timer.stop();
        } catch (Exception e) {
            System.out.println("Error stopping the game: " + e.getMessage());
        }
    }

    private void checkCollision() {
        try {
            if (ball.getRect().getMaxY() > Commons.BOTTOM_EDGE) {
                stopGame();
            }

            for (Brick brick : bricks) {
                if (brick.isDestroyed()) {
                    continue;
                }

                if ((ball.getRect()).intersects(brick.getRect())) {
                    int ballLeft = (int) ball.getRect().getMinX();
                    int ballHeight = (int) ball.getRect().getHeight();
                    int ballWidth = (int) ball.getRect().getWidth();
                    int ballTop = (int) ball.getRect().getMinY();

                    Point pointRight = new Point(ballLeft + ballWidth + 1, ballTop);
                    Point pointLeft = new Point(ballLeft - 1, ballTop);
                    Point pointTop = new Point(ballLeft, ballTop - 1);
                    Point pointBottom = new Point(ballLeft, ballTop + ballHeight + 1);

                    if (brick.getRect().contains(pointRight)) {
                        ball.setXDir(-1);
                    } else if (brick.getRect().contains(pointLeft)) {
                        ball.setXDir(1);
                    }

                    if (brick.getRect().contains(pointTop)) {
                        ball.setYDir(1);
                    } else if (brick.getRect().contains(pointBottom)) {
                        ball.setYDir(-1);
                    }

                    brick.setDestroyed(true);
                    score += 10; // Increase score when a brick is destroyed
                }
            }

            if ((ball.getRect()).intersects(paddle.getRect())) {
                int paddleLPos = (int) paddle.getRect().getMinX();
                int ballLPos = (int) ball.getRect().getMinX();

                int first = paddleLPos + 8;
                int second = paddleLPos + 16;
                int third = paddleLPos + 24;
                int fourth = paddleLPos + 32;

                if (ballLPos < first) {
                    ball.setXDir(-1);
                    ball.setYDir(-1);
                } else if (ballLPos >= first && ballLPos < second) {
                    ball.setXDir(-1);
                    ball.setYDir(-1 * ball.getYDir());
                } else if (ballLPos >= second && ballLPos < third) {
                    ball.setXDir(0);
                    ball.setYDir(-1);
                } else if (ballLPos >= third && ballLPos < fourth) {
                    ball.setXDir(1);
                    ball.setYDir(-1 * ball.getYDir());
                } else if (ballLPos > fourth) {
                    ball.setXDir(1);
                    ball.setYDir(-1);
                }
            }

            boolean allBricksDestroyed = true;
            for (Brick brick : bricks) {
                if (!brick.isDestroyed()) {
                    allBricksDestroyed = false;
                    break;
                }
            }

            if (allBricksDestroyed) {
                level++;
                gameInit();
            }
        } catch (Exception e) {
            System.out.println("Error during collision check: " + e.getMessage());
        }
    }
}
