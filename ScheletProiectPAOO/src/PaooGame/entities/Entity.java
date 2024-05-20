package PaooGame.entities;

import PaooGame.Tiles.LevelManager;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import static PaooGame.utils.Camera.xCamera;
import static PaooGame.utils.Constants.*;
import static PaooGame.utils.GravityCollisionMethods.*;

/*
Abstractizarea conceptului de entitate
 */

public abstract class Entity {

    protected float x,y;
    protected int width, height;
    public Rectangle2D.Float hitBox; // dreptunghiul pentru coliziuni

    protected static int[][] levelData; // matricea nivelului curent, rol in testarea coliziunilor

    // variabilele ce tin de gravitatie si jump
    protected float airSpeed = 0f; // viteza in aer
    protected float gravity = 0.1f; // gravitatia
    protected float jumpSpeed = -4f; // viteza sariturii
    protected float fallSpeedAfterCollision = 1f; // viteza cu care se deplaseaza in jos atunci cand loveste tavanul/o platforma
    protected boolean inAir = true;

    // variabile ce tin de animatiile actiunilor
    protected int aniIndex; // indexul animatiei
    protected int aniTick; // tick-ul animatiei
    protected int aniSpeed = 4; // viteza animatiei
    protected int action; // actiunea entitatii
    protected boolean attacking = false;
    public boolean died = false;

    public HealthBar health; // viata entitatii


    public Entity(float x, float y, int width, int height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        health = new HealthBar();
        loadLvlData();
    }

    // functie pentru debug coliziuni
    protected void drawHitbox(Graphics g)
    {
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

    /*
    Functia de update a animatiei
     */
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
    
    /*
    Functia ce testeaza gravitatia in joc
    Metodele apelate sunt in clasa GravityCollisionMethods din pachetul utils
     */
    protected void testGravity(int xSpeed)
    {
        /*
        daca entitatea initial nu se afla in aer si nici pe podea, atunci este in aer
         */
        if(!inAir)
        {
            if(!IsEntityOnFloor(hitBox,levelData))
            {
                inAir = true;
            }
        }

        /*
        daca se afla in aer, testam daca se poate misca unde se afla, actualizand pozitia si gravitatia
         */
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
                if(airSpeed > 0) // mergem in jos sau lovim ceva
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

    protected void updateXPos(float xSpeed) { // actualizam pozitia X
        if(CanMoveHere(hitBox.x+xSpeed,hitBox.y,hitBox.width,hitBox.height,levelData)) // daca se poate misca
        {
            hitBox.x += xSpeed;
        }
        else // daca nu se poate misca, sta langa un perete
        {
            hitBox.x = GetEntityXPosNextToWall(hitBox,xSpeed);
        }
    }

    protected void loadLvlData()
    {
        levelData = LevelManager.getData();
    }

    public void update() {}

    protected void setAnimation() {}

    protected void updatePos() {}

    public void takeDamage(){}

    public void render(Graphics g) {}
}

