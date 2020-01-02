package things;

import java.awt.*;

public abstract class MovingThing extends Thing {
    final static float VELOCITY = 2f;
    final static float ROT_VELOCITY = 0.06f;

    private int radius;  // px
    private double direction;    // rad

    private float velocity; // px/step
    private float rotationVelocity; // rad/step

    MovingThing(int x, int y, int radius) {
        this(x, y, radius, MovingThing.VELOCITY, MovingThing.ROT_VELOCITY, 0);
    }

    MovingThing(int x, int y, int radius, float velocity, float rotationVelocity, double direction) {
        super(x, y);
        this.radius = radius;
        this.velocity = velocity;
        this.rotationVelocity = rotationVelocity;
        this.direction = direction;
    }

    double getDirection() {
        return direction;
    }

    int getRadius() {
        return radius;
    }

    private void changeDirection(double amount) {
        this.direction = (this.direction + amount) % (2 * Math.PI);
    }


    public void turnLeft() {
        this.changeDirection(this.rotationVelocity);
    }

    public void turnRight() {
        this.changeDirection(- this.rotationVelocity);
    }

    public void step() {
        this.step(1);
    }

    void step(int number) {
        this.addX(Math.round(number * this.velocity * Math.sin(this.direction)));
        this.addY(Math.round(number * this.velocity * Math.cos(this.direction)));
    }

    void turnAround() {
        this.changeDirection(Math.PI);
    }

    void reflect(Wall.Orientation face) {
        switch (face) {
            case HORIZONTAL:
                this.direction = Math.PI - this.direction;
                break;
            case VERTICAL:
                this.direction = - this.direction;
                break;
        }
    }

    public abstract void draw(Graphics2D graphics);

    public abstract void bounce(Wall.Orientation face);
}
