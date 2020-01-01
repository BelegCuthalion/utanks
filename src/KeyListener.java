import java.awt.event.KeyEvent;

public class KeyListener implements java.awt.event.KeyListener {
    private GamePanel gamePanel;

    KeyListener(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void keyTyped(KeyEvent e) { e.consume(); }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                this.gamePanel.setP1Left(true);
                break;
            case KeyEvent.VK_RIGHT:
                this.gamePanel.setP1Right(true);
                break;
            case KeyEvent.VK_UP:
                this.gamePanel.setP1Up(true);
                break;
            case KeyEvent.VK_A:
                this.gamePanel.setP2Left(true);
                break;
            case KeyEvent.VK_D:
                this.gamePanel.setP2Right(true);
                break;
            case KeyEvent.VK_W:
                this.gamePanel.setP2Up(true);
                break;
            case KeyEvent.VK_ENTER:
                this.gamePanel.p1Fire();
                break;
            case KeyEvent.VK_SPACE:
                this.gamePanel.p2Fire();
                break;
            case KeyEvent.VK_ESCAPE:
                this.gamePanel.setQuitPressed(true);
                break;
        }
        e.consume();
    }
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                this.gamePanel.setP1Left(false);
                break;
            case KeyEvent.VK_RIGHT:
                this.gamePanel.setP1Right(false);
                break;
            case KeyEvent.VK_UP:
                this.gamePanel.setP1Up(false);
                break;
            case KeyEvent.VK_A:
                this.gamePanel.setP2Left(false);
                break;
            case KeyEvent.VK_D:
                this.gamePanel.setP2Right(false);
                break;
            case KeyEvent.VK_W:
                this.gamePanel.setP2Up(false);
                break;
        }
        e.consume();
    }
}