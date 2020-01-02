package things;

import java.awt.*;

public class Tank extends MovingThing {
    private final static int RADIUS = 20;
    private final static int GUN_LENGTH = 30;

    private BombArena arena;

    private TankSize size;
    public Tank(int x, int y, TankSize size, BombArena arena) {
        super(x, y,
                Math.round(Tank.RADIUS * size.getFactor()),
                MovingThing.VELOCITY / size.getFactor(),
                MovingThing.ROT_VELOCITY / size.getFactor(),
                0
        );
        this.size = size;
        this.arena = arena;
    }

    private int getGunLength() {
        return Math.round(Tank.GUN_LENGTH * this.size.getFactor());
    }

    private int getGunPointX() {
        return (int) Math.round(this.getX() + (this.getGunLength() * Math.sin(this.getDirection())));
    }

    private int getGunPointY() {
        return (int) Math.round(this.getY() + (this.getGunLength() * Math.cos(this.getDirection())));
    }

    public void draw(Graphics2D graphics) {
        graphics.drawOval(
                this.getX() - this.getRadius(),
                this.getY() - this.getRadius(),
                this.getRadius() * 2,
                this.getRadius() * 2
        );
        graphics.drawLine(
                this.getX(),
                this.getY(),
                this.getGunPointX(),
                this.getGunPointY()
        );
    }

    public void bounce(Wall.Orientation ignore) {
        this.turnAround();
        this.step(10);
        this.turnAround();
    }

    public void fire() {
        this.arena.fired(new Bomb(this.getGunPointX(), this.getGunPointY(), this.getDirection()));
    }

    public enum TankSize {
        NORMAL(1),
        BIG(2f);

        private float factor;

        TankSize(float factor) {
            this.factor = factor;
        }

        private float getFactor() {
            return factor;
        }
    }
}
