import things.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel implements BombArena {
    private Tank player1;
    private Tank player2;
    private List<Thing> thingsToDraw = new ArrayList<>(10);
    private List<Wall> walls = new ArrayList<>(10);
    private List<Bomb> onTheAir = new ArrayList<>(10);

    private boolean p1Left = false;
    private boolean p1Right = false;
    private boolean p1Up = false;
    private boolean p2Left = false;
    private boolean p2Right = false;
    private boolean p2Up = false;
    private boolean quitPressed = false;

    GamePanel() {
        this.player1 = new Tank(100, 100, Tank.TankSize.NORMAL, this);
        this.player2 = new Tank(400, 400, Tank.TankSize.BIG, this);

        this.thingsToDraw.add(this.player1);
        this.thingsToDraw.add(this.player2);

        Wall leftEdge = new Wall(20, 20, 600, Wall.Orientation.VERTICAL);
        Wall topEdge = new Wall(20, 20, 600, Wall.Orientation.HORIZONTAL);
        Wall rightEdge = new Wall(620, 20, 600, Wall.Orientation.VERTICAL);
        Wall bottomEdge = new Wall(20, 620, 600, Wall.Orientation.HORIZONTAL);
        Wall center = new Wall(320, 220, 200, Wall.Orientation.VERTICAL);
        this.walls.add(leftEdge);
        this.walls.add(topEdge);
        this.walls.add(rightEdge);
        this.walls.add(bottomEdge);
        this.walls.add(center);
        this.thingsToDraw.add(leftEdge);
        this.thingsToDraw.add(topEdge);
        this.thingsToDraw.add(rightEdge);
        this.thingsToDraw.add(bottomEdge);
        this.thingsToDraw.add(center);
    }

    /**
     * State of the affairs.
     * This could be a function of time.
     */
    void updateState() {
        this.onTheAir.forEach(Bomb::step);
        this.onTheAir.forEach(Bomb::growOld);
        this.onTheAir.removeIf(Bomb::isDead);
        this.onTheAir.forEach(bomb -> this.walls.forEach(wall -> {
            if (wall.hit(bomb)) bomb.bounce(wall.getOrientation());
        }));
        if (this.p1Left) {
            this.player1.turnLeft();
        }
        if (this.p1Right) {
            this.player1.turnRight();
        }
        if (this.p1Up) {
            if (this.walls.stream().anyMatch(wall -> wall.hit(this.player1))) {
                this.player1.bounce(null);
            } else {
                this.player1.step();
            }
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
        for (Thing thingy : this.thingsToDraw)
            thingy.draw((Graphics2D) graphics);
        for (Bomb fatMan : this.onTheAir)
            fatMan.draw((Graphics2D) graphics);
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

    public void fired(Bomb bomb) {
        this.onTheAir.add(bomb);
    }
}
