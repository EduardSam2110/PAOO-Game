package PaooGame.entities;

import PaooGame.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

import static PaooGame.Graphics.Assets.*;
import static PaooGame.Tiles.Tile.TILE_SIZE;
import static PaooGame.utils.Camera.xCamera;
import static PaooGame.Game.*;
import static PaooGame.utils.Constants.*;

public class Enemy extends Entity {


    private float xDrawOffset = 40;
    private float yDrawOffset = 60;
    private int enemySpeed = 2;

    private float xSpeedEnemy;

    private boolean movingLeft, movingRight;

    private boolean died = false;
    public static boolean shooting = false;


    private float deathAnimTick = 0;

    private int enemyCoordX, enemyCoordY, enemyCoordYHeight, enemyCoordXWidth;

    private BufferedImage[] current_animation;

    public Enemy(float x,float y,int width, int height)
    {
        super(x,y,width,height);
        initHitbox(x,y,28,28);
        movingRight = true;
        action = EnemyWALK;
    }

    @Override
    public void update()
    {
        if(!died)
        {
            die_if_attack();
            setAnimation();
            updateAnimation(action, "enemy");
            updatePos();
            super.loadLvlData();
        }
        else
            deathAnimation();
    }

    @Override
    public void render(Graphics g)
    {
        g.drawImage(current_animation[aniIndex], (int) (hitBox.x - xDrawOffset - xCamera),(int) (hitBox.y - yDrawOffset),width,height,null);

        //debugg
        if(Game.DEBUGG)
            drawHitbox(g);
    }

    private void updatePos()
    {
        xSpeedEnemy = 0;

        calculatePos();

        float xIndexToRight = (hitBox.x + hitBox.width) / TILE_SIZE;
        float xIndexToLeft = hitBox.x / TILE_SIZE;
        float yIndex = (hitBox.y + 32) / TILE_SIZE;

        int value1 = levelData[(int) yIndex][(int) xIndexToRight];
        int value2 = levelData[(int) yIndex][(int) xIndexToLeft];


        if (inAir == false) {
            if (movingRight && !shooting) {
                if (value1 != 44)
                    xSpeedEnemy += enemySpeed;
                else {
                    movingRight = false;
                    movingLeft = true;
                }
            } else if (movingLeft && !shooting) {
                if (value2 != 44)
                    xSpeedEnemy -= enemySpeed;
                else {
                    movingLeft = false;
                    movingRight = true;
                }
            }
        }

        testGravity((int) xSpeedEnemy);
    }

    private void setAnimation()
    {
        if(died)
            action = EnemyDEATH;
        else
            action = EnemyWALK;

        if (inAir == false) {

            if(shooting)
                action = EnemyATTACK;

            if (movingRight) {
                current_animation = enemy_animations_right[action];
            } else if (movingLeft) {
                current_animation = enemy_animations_left[action];
            }
        }
        else
            current_animation = enemy_animations_right[EnemyIDLE];
    }


    private void calculatePos()
    {
        enemyCoordX = (int) hitBox.x;
        enemyCoordY = (int) hitBox.y;
        enemyCoordYHeight = (int) (hitBox.y + hitBox.height);
        enemyCoordXWidth = (int) (hitBox.x + hitBox.width);
    }


    //functia verifica coliziunea playerului cu inamicul si seteaza care din cei doi moare
    private void die_if_attack()
    {
        int coord_playerX = (int) (player.getHitBox().x + player.getHitBox().width);
            int coord_playerXLeft = (int) (player.getHitBox().x);
            int coord_playerY = (int) (player.getHitBox().y);

            if((coord_playerX + 10 >= enemyCoordX) && (coord_playerXLeft - 10 <= enemyCoordXWidth)
                    && (coord_playerY >= enemyCoordY) && (coord_playerY <= enemyCoordYHeight)) {
                if(player.attacking) {
                    action = EnemyDEATH;
                    aniIndex = 0;
                    shooting = false;
                    died = true;
                }
                else {
//                    player.takeDamage();
                }
            }
    }

    private void deathAnimation()
    {
        action = EnemyDEATH;
        if(aniIndex < 5) {
            deathAnimTick += 0.2;
            aniIndex = (int) deathAnimTick;
        }
    }
}
