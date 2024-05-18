package PaooGame.Inventory;

import PaooGame.entities.Player;
import PaooGame.entities.SimpleEnemy;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import static PaooGame.utils.Camera.xCamera;

public abstract class ItemAbstractClass {
    protected int x,y;
    protected Rectangle2D.Float hitBox;
    public boolean collected = false;
    public boolean used = false;

    protected void initHitbox(float x, float y, float width, float height) {
        hitBox = new Rectangle2D.Float(x,y,width,height);
    }

    protected void drawHitbox(Graphics g)
    {
        g.setColor(Color.WHITE);
        g.drawRect((int)hitBox.x - xCamera,(int)hitBox.y,(int)hitBox.width,(int)hitBox.height);
    }

    public void update(){};
    public void render(Graphics g){};
    public void pickItem(Player p){};
    public void useItem(Player p){};
}

