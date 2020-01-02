package things;

import java.awt.*;

public class Bomb extends MovingThing {
    private final static int RADIUS = 10;
    private final static float VELOCITY = 8;
    private final static int LIFE = 100;   // step

    private int age = Bomb.LIFE;

    Bomb(int x, int y, double direction) {
        super(x, y, Bomb.RADIUS, Bomb.VELOCITY, 0, direction);
    }

    public void draw(Graphics2D graphics) {
        graphics.fillOval(
                this.getX() - Bomb.RADIUS,
                this.getY() - Bomb.RADIUS,
                Bomb.RADIUS * 2,
                Bomb.RADIUS * 2
        );
    }

    public void growOld() {
        this.age--;
    }

    public boolean isDead() {
        return this.age <= 0;
    }

    public void bounce(Wall.Orientation face) {
        this.reflect(face);
    }
}
