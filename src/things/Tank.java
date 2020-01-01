package things;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Tank extends Thing {
    private final static int RADIUS = 20;
    private final static int GUN_LENGTH = 30;

    private List<Bomb> onTheAir = new ArrayList<>(10);

    private TankSize size;
    public Tank(int x, int y, TankSize size) {
        super(x, y, Thing.VELOCITY / size.getFactor(), Thing.ROT_VELOCITY / size.getFactor());
        this.size = size;
    }

    int getRadius() {
        return Math.round(Tank.RADIUS * this.size.getFactor());
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
        for (Bomb littleBoy : this.onTheAir) {
            littleBoy.draw(graphics);
        }
        this.onTheAir.removeIf(bomb -> bomb.age-- == 0);
    }

    public void fire() {
        this.onTheAir.add(new Bomb(this.getGunPointX(), this.getGunPointY(), this.getDirection()));
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
