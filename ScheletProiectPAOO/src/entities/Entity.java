package entities;

import java.awt.*;

public abstract class Entity {
    protected float x,y;
    protected int width, height;
    protected Rectangle hitBox; // coliziuni
    public Entity(float x, float y, int width, int height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        initHitbox();
    }

    protected void drawHitbox(Graphics g)
    {
        //pentru debugg
        g.setColor(Color.WHITE);
        g.drawRect(hitBox.x,hitBox.y,hitBox.width, hitBox.height);
    }

    private void initHitbox() {
        hitBox = new Rectangle((int) x,(int) y,width,height);
    }

    public void updateHitbox() {
        hitBox.x = (int) x; //pentru cand se misca playerul
        hitBox.y = (int) y;
    }

    public Rectangle getHitBox()
    {
        return hitBox;
    }
}

