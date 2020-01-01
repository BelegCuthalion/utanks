import things.Tank;
import things.Thing;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel {
    private Tank player1;
    private Tank player2;
    private List<Thing> things = new ArrayList<>(10);

    private boolean p1Left = false;
    private boolean p1Right = false;
    private boolean p1Up = false;
    private boolean p2Left = false;
    private boolean p2Right = false;
    private boolean p2Up = false;
    private boolean quitPressed = false;

    GamePanel() {
        this.player1 = new Tank(100, 100, Tank.TankSize.NORMAL);
        this.player2 = new Tank(400, 400, Tank.TankSize.BIG);

        this.things.add(this.player1);
        this.things.add(this.player2);
    }

    void updateState() {
        if (this.p1Left) {
            this.player1.turnLeft();
        }
        if (this.p1Right) {
            this.player1.turnRight();
        }
        if (this.p1Up) {
            this.player1.step();
        }
        if (this.p2Left) {
            this.player2.turnLeft();
        }
        if (this.p2Right) {
            this.player2.turnRight();
        }
        if (this.p2Up) {
            this.player2.step();
        }
    }


    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        for (Thing thingy : this.things)
            thingy.draw((Graphics2D) graphics);
    }

    void setP1Left(boolean p1Left) {
        this.p1Left = p1Left;
    }

    void setP1Right(boolean p1Right) {
        this.p1Right = p1Right;
    }

    void setP1Up(boolean p1Up) {
        this.p1Up = p1Up;
    }

    void p1Fire() {
        this.player1.fire();
    }

    void setP2Left(boolean p2Left) {
        this.p2Left = p2Left;
    }

    void setP2Right(boolean p2Right) {
        this.p2Right = p2Right;
    }

    void setP2Up(boolean p2Up) {
        this.p2Up = p2Up;
    }

    void p2Fire() {
        this.player2.fire();
    }

    void setQuitPressed(boolean quitPressed) {
        this.quitPressed = quitPressed;
    }
}
