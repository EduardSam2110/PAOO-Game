package entities;

import PaooGame.Graphics.Assets;

import java.awt.*;

import static PaooGame.Graphics.Assets.map_lvl1;
import static PaooGame.Tiles.Tile.TILE_SIZE;
import static utils.Camera.xCamera;
import static PaooGame.Game.*;

public class Enemy extends Entity {


    private float xDrawOffset = 40;
    private float yDrawOffset = 60;
    private int enemySpeed = 2;

    private float xSpeedEnemy;

    private boolean movingLeft = false, movingRight = false;

    private int died;

    public int animation = 0;

    private int enemyCoordX, enemyCoordY, enemyCoordYDamage, enemyCoordXDamage;

    public Enemy(float x,float y,int width, int height)
    {
        super(x,y,width,height);
        initHitbox(x,y,28,28);
        loadLvlData();
        movingRight = true;
        died = 0;

    }

    @Override
    public void update()
    {
        if(died == 0)
        {
            updateAnimation();
            updatePos();
            die_if_attack();
            enemyCoordX = (int) hitBox.x;
            enemyCoordY = (int) hitBox.y;
            enemyCoordYDamage = (int) (hitBox.y + hitBox.height);
            enemyCoordXDamage = (int) (hitBox.x + hitBox.width);
        }
    }

    @Override
    public void render(Graphics g)
    {
        if(died == 1) {
            aniIndex = 0;
            aniSpeed = 5;
        }

        if(died > 0 && died < 5 && aniIndex < 8)
            aniIndex++;

        g.drawImage(Assets.enemy_animations_right[animation][aniIndex], (int) (hitBox.x - xDrawOffset - xCamera),(int) (hitBox.y - yDrawOffset),width,height,null);
//        drawHitbox(g);

    }

    private void loadLvlData()
    {
        this.levelData = map_lvl1;
    }

    // de sters:

    private void updatePos()
    {

        xSpeedEnemy = 0;

        float xIndexToRight = (hitBox.x + hitBox.width) / TILE_SIZE;
        float xIndexToLeft = hitBox.x / TILE_SIZE;
        float yIndex = (hitBox.y + 32) / TILE_SIZE;

        int value1 = levelData[(int)yIndex][(int)xIndexToRight];
        int value2 = levelData[(int)yIndex][(int)xIndexToLeft];

        if(movingRight) {
            if(value1 != 44)
                xSpeedEnemy += enemySpeed;
            else
            {
                movingRight = false;
                movingLeft = true;
            }
        }
        else if(movingLeft) {
            if(value2 != 44)
                xSpeedEnemy -= enemySpeed;
            else
            {
                movingLeft = false;
                movingRight = true;
            }
        }

        testGravity((int)xSpeedEnemy);

    }

    private void die_if_attack()
    {
        if(player.attacking == true)
        {
            int coord_playerX = (int) (player.getHitBox().x + player.getHitBox().width);
            int coord_playerXLeft = (int) (player.getHitBox().x);
            int coord_playerY = (int) (player.getHitBox().y);
            System.out.println(coord_playerX + "  " + enemyCoordX);
            System.out.println(coord_playerY + "  " + enemyCoordY);

            if((coord_playerX >= enemyCoordX) && (coord_playerXLeft <= enemyCoordXDamage) && (coord_playerY >= enemyCoordY) && (coord_playerY <= enemyCoordYDamage)) {
                animation = 3;
                ++died;
                System.out.println("apelat");
            }

        }
    }


}
