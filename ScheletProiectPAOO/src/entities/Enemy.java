package entities;

import PaooGame.Graphics.Assets;

import java.awt.*;

import static PaooGame.Tiles.LevelConstructor.map;
import static PaooGame.Tiles.Tile.TILE_SIZE;
import static utils.Camera.xCamera;
import static utils.HelpMethods.*;

public class Enemy extends Entity {

    private float xDrawOffset = 40;
    private float yDrawOffset = 60;
    private static int enemySpeed = 2;

    private static float xSpeedEnemy;

    private static boolean movingLeft = false, movingRight = false;

    private static int died;

    public static int animation = 0;

    private static int enemyCoord;

    public Enemy(float x,float y,int width, int height)
    {
        super(x,y,width,height);
        initHitbox(x,y,28,28);
        loadLvlData();
        movingRight = true;
        died = 0;
    }

    public void update()
    {
        if(died == 0)
        {
            updateAnimation();
            updatePos();
            enemyCoord = (int) hitBox.x;
        }
    }


    public void render(Graphics g)
    {
        if(died == 1) {
            aniIndex = 0;
            aniSpeed = 5;
        }

        if(died > 0 && died < 5)
            aniIndex++;

        g.drawImage(Assets.enemy_animations_right[animation][aniIndex], (int) (hitBox.x - xDrawOffset - xCamera),(int) (hitBox.y - yDrawOffset),width,height,null);
        //drawHitbox(g);

    }

    private void loadLvlData()
    {
        this.levelData = map;
    }

    // de sters:

    private void updatePos()
    {

        xSpeedEnemy = 0;

        if(movingRight) {
            if(hitBox.x < (x+100))
                xSpeedEnemy += enemySpeed;
            else
            {
                movingRight = false;
                movingLeft = true;
            }
        }
        else if(movingLeft) {
            if(hitBox.x > (x-100))
                xSpeedEnemy -= enemySpeed;
            else
            {
                movingLeft = false;
                movingRight = true;
            }
        }

        testGravity((int)xSpeedEnemy);

    }

    public static void die_if_attack(Player p)
    {
        if(p.attacking == true)
        {
            int coord_player = (int) (p.getHitBox().x + p.getHitBox().width);

            if(coord_player + 10 >= enemyCoord) {
                animation = 3;
                ++died;
            }

        }
    }


}
