package entities;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public abstract class EntityFactory {
    protected float x,y;
    protected int width, height;
    protected Rectangle2D.Float hitBox; // coliziuni

    public EntityFactory(float x, float y, int width, int height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    protected void drawHitbox(Graphics g)
    {
        //pentru debugg
        g.setColor(Color.WHITE);
        g.drawRect((int)hitBox.x,(int)hitBox.y,(int)hitBox.width,(int)hitBox.height);
    }

    protected void initHitbox(float x, float y, float width, float height) {
        hitBox = new Rectangle2D.Float(x,y,width,height);
    }

    public Rectangle2D.Float getHitBox()
    {
        return hitBox;
    }


}

