package entities;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import static PaooGame.Graphics.Assets.map_lvl1;
import static utils.Camera.xCamera;
import static utils.Constants.*;
import static utils.GravityColisionMethods.*;

public abstract class Entity {
    protected float x,y;
    protected int width, height;
    protected Rectangle2D.Float hitBox; // coliziuni


    protected int[][] levelData = map_lvl1;
    protected float airSpeed = 0f;
    protected float gravity = 0.1f;
    protected float jumpSpeed = -5f;
    protected float fallSpeedAfterCollision = 1f;
    protected boolean inAir = false;
    protected int aniIndex;
    protected int aniTick;
    protected int aniSpeed = 4;
    protected int action;
    protected boolean attacking = false;


    public Entity(float x, float y, int width, int height)
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
        g.drawRect((int)hitBox.x -xCamera,(int)hitBox.y,(int)hitBox.width,(int)hitBox.height);
    }

    protected void initHitbox(float x, float y, float width, float height) {
        hitBox = new Rectangle2D.Float(x,y,width,height);
    }

    public Rectangle2D.Float getHitBox()
    {
        return hitBox;
    }

    protected void updateAnimation(int action, String enemyType)
    {
        aniTick++;
        if(aniTick >= aniSpeed)
        {
            aniTick = 0;
            aniIndex++;
            if(aniIndex >= GetSpriteAmount(action, enemyType)) {
                aniIndex = 0;
                attacking = false;
            }
        }
    }
    
    
    protected void testGravity(int xSpeed)
    {
        if(!inAir)
        {
            if(!IsEntityOnFloor(hitBox,levelData))
            {
                inAir = true;
            }
        }

        if(inAir)
        {
            if(CanMoveHere(hitBox.x,hitBox.y + airSpeed, hitBox.width,hitBox.height,levelData))
            {
                hitBox.y += airSpeed;
                airSpeed += gravity;
                updateXPos(xSpeed);
            }
            else
            {
                hitBox.y = GetEntityYPosUnderRoofFloor(hitBox,airSpeed);
                if(airSpeed > 0) // mergem in jos, lovim ceva
                    resetInAir();
                else
                    airSpeed = fallSpeedAfterCollision;
                updateXPos(xSpeed);
            }
        }
        else
        {
            updateXPos(xSpeed);
        }
    }

    protected void resetInAir() {
        inAir = false;
        airSpeed = 0;
    }

    protected void updateXPos(float xSpeed) {
        if(CanMoveHere(hitBox.x+xSpeed,hitBox.y,hitBox.width,hitBox.height,levelData))
        {
            hitBox.x += xSpeed;
        }
        else
        {
            hitBox.x = GetEntityXPosNextToWall(hitBox,xSpeed);
        }
    }

    public void update() {}

    public void render(Graphics g) {}
}

