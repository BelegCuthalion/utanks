package things;

import java.awt.*;

public abstract class Thing {
    private int x;    // px
    private int y;    // px

    Thing(int x, int y) {
        this.x = x;
        this.y = y;
    }

    int getX() {
        return this.x;
    }

    int getY() {
        return this.y;
    }

    void addX(long x) {
        this.x += x;
    }

    void addY(long y) {
        this.y += y;
    }

    public abstract void draw(Graphics2D graphics);
}
