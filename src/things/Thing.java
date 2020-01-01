package things;

import java.awt.*;

public abstract class Thing {
    final static float VELOCITY = 2f;
    final static float ROT_VELOCITY = 0.06f;

    private int x;    // px
    private int y;    // px

    private double direction;    // rad

    private float velocity; // px/step
    private float rotationVelocity; // rad/step

    Thing(int x, int y) {
        this(x, y, Thing.VELOCITY, Thing.ROT_VELOCITY);
    }

    Thing(int x, int y, float velocity, double direction) {
        this.x = x;
        this.y = y;
        this.velocity = velocity;
        this.direction = direction;
    }

    Thing(int x, int y, float velocity, float rotationVelocity) {
        this.x = x;
        this.y = y;
        this.velocity = velocity;
        this.rotationVelocity = rotationVelocity;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    double getDirection() {
        return direction;
    }

    public void turnLeft() {
        if (this.direction < 2 * Math.PI)
            this.direction += this.rotationVelocity;
        else
            this.direction = 0;
    }

    public void turnRight() {
        if (this.direction < 2 * Math.PI)
            this.direction -= this.rotationVelocity;
        else
            this.direction = 0;
    }

    public void step() {
        this.x += Math.round(this.velocity * Math.sin(this.direction));
        this.y += Math.round(this.velocity * Math.cos(this.direction));
    }

    public abstract void draw(Graphics2D graphics);
}
