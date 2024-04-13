package entities;

import PaooGame.Graphics.Assets;
import PaooGame.Graphics.Assets.*;

import java.awt.*;

import static PaooGame.Tiles.LevelConstructor.map;
import static PaooGame.Tiles.Tile.TILE_SIZE;
import static utils.Camera.xCamera;
import static utils.Constants.PLayerConstants.GetSpriteAmount;
import static utils.HelpMethods.*;

public class Enemy extends EntityFactory {

    private static int[][] levelData;
    private float xDrawOffset = 30;
    private float yDrawOffset = 60;
    private int aniIndex, aniTick, aniSpeed = 7;

    private float airSpeed = 0f;
    private float gravity = 0.1f;
    private float jumpSpeed = -5f;
    private float fallSpeedAfterCollision = 1f;
    private boolean inAir = false;

    public static float xSpeed = 1;

    public Enemy(float x,float y,int width, int height)
    {
        super(x,y,width,height);
        initHitbox(x,y,28,28);
        loadLvlData();
    }

    public void update()
    {
        updateAnim();
        updatePos();
    }

    public void updateAnim()
    {
        aniTick++;
        if(aniTick >= aniSpeed)
        {
            aniTick = 0;
            aniIndex++;
            if(aniIndex >= 8) {
                aniIndex = 0;
            }
        }
    }

    public void updatePoss()
    {
        float xIndex = (hitBox.x) / TILE_SIZE;
        float yIndex = (hitBox.y + hitBox.height)  / TILE_SIZE;

        int value = levelData[(int)yIndex][(int)xIndex];

        if(hitBox.x > x+300 && (value != 44))
            hitBox.x += xSpeed;
        else
            hitBox.x-=xSpeed;

    }

    public void render(Graphics g)
    {
        g.drawImage(Assets.enemy_animations_right[0][aniIndex], (int) (hitBox.x - xDrawOffset),(int) (hitBox.y - yDrawOffset),width,height,null);
        drawHitbox(g);
    }

    private void loadLvlData()
    {
        this.levelData = map;
    }

    // de sters:

    private void updatePos()
    {
        updatePoss();

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

    private void resetInAir() {
        inAir = false;
        airSpeed = 0;
    }

    private void updateXPos(float xSpeed) {
        if(CanMoveHere(hitBox.x+xSpeed,hitBox.y,hitBox.width,hitBox.height,levelData))
        {
            hitBox.x += xSpeed;
        }
        else
        {
            hitBox.x = GetEntityXPosNextToWall(hitBox,xSpeed);
        }
    }
}
