import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        JFrame jframe = new JFrame();
        GamePanel gamePanel = new GamePanel();
        jframe.addKeyListener(new KeyListener(gamePanel));
        jframe.add(gamePanel);
        jframe.setVisible(true);

        new Timer(
                10,
                e -> {
                    gamePanel.updateState();
                    gamePanel.repaint();
                }
        ).start();
    }
}
