package things;

import java.awt.*;

public class Bomb extends Thing {
    final static int RADIUS = 10;
    private final static float VELOCITY = 15;
    private final static int LIFE = 30;   // ms

    int age = Bomb.LIFE;

    Bomb(int x, int y, double direction) {
        super(x, y, Bomb.VELOCITY, direction);
    }

    public void draw(Graphics2D graphics) {
        this.step();
        graphics.fillOval(
                this.getX() - Bomb.RADIUS,
                this.getY() - Bomb.RADIUS,
                Bomb.RADIUS * 2,
                Bomb.RADIUS * 2
        );
    }
}
