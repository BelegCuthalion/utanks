package things;

import java.awt.*;

/**
 *       HORIZONTAL         VERTICAL
 *
 *    i1           i2          j
 *  j  -------------           | i1
 *                             |
 *                             |
 *                             |
 *                             | i2
 */
public class Wall extends Thing {
    final private static int PROXIMITY = 5;

    private int i1;
    private int i2;
    private int j;
    private Orientation orientation;



    /**
     * Build a wall with a point, length and orientation
     */
    public Wall(int x, int y, int length, Orientation orientation) {
        super(x, y);
        this.orientation = orientation;
        switch (orientation) {
            case HORIZONTAL:
                this.i1 = x;
                this.i2 = x + length;
                this.j = y;
                break;
            case VERTICAL:
                this.i1 = y;
                this.i2 = y + length;
                this.j = x;
                break;
        }
    }

    private int getLength() { return this.i2 - this.i1; }

    public Orientation getOrientation() {
        return this.orientation;
    }

    public int getJ() {
        return this.j;
    }

    public boolean hit(MovingThing movingThing) {
        int thingI, thingJ, start, end;
        switch (this.orientation) {
            case HORIZONTAL:
                thingI = movingThing.getX();
                thingJ = movingThing.getY();
                break;
            case VERTICAL: default:
                thingI = movingThing.getY();
                thingJ = movingThing.getX();
                break;
        }
        if (this.i1 <= this.i2) {
            start = this.i1;
            end = this.i2;
        } else {
            start = this.i2;
            end = this.i1;
        }
        int contactJ = thingJ + ((this.j <= thingJ) ? - movingThing.getRadius() : movingThing.getRadius());
        return (
                (start <= thingI + movingThing.getRadius() && thingI - movingThing.getRadius() <= end) &&
                (this.j - Wall.PROXIMITY <= contactJ && contactJ <= this.j + Wall.PROXIMITY)
        );
    }

    public void draw(Graphics2D graphics) {
        int width, height;
        switch (this.orientation) {
            case HORIZONTAL:
                width = this.getLength();
                height = 2 * Wall.PROXIMITY;
                break;
            case VERTICAL: default:
                width = 2 * Wall.PROXIMITY;
                height = this.getLength();
                break;
        }
        graphics.fillRect(
                this.getX(), this.getY(), width, height
        );
    }

    public enum Orientation {
        HORIZONTAL,
        VERTICAL
    }
}
