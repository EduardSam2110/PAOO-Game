package entities;

import PaooGame.Graphics.ImageLoader;
import PaooGame.Graphics.SpriteSheet;
import utils.LoadSave;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import static utils.Constants.Directions.*;
import static utils.Constants.Directions.DOWN;
import static utils.Constants.PLayerConstants.*;
import static PaooGame.Graphics.Assets.player_animations_left;
import static PaooGame.Graphics.Assets.player_animations_right;

public class Player extends Entity{
    private BufferedImage[] current_animation;
    private boolean moving = false, attacking = false;
    private String lastPressed;
    private int aniIndex, aniTick, aniSpeed = 3;
    private int playerAction = CRAWLING;
    private boolean left, up, right, down;
    private float playerSpeed = 2.0f;
    private int[][] levelData;
    public Player(float x,float y,int width, int height)
    {
        super(x,y,width,height);
        initAnimations();
    }

    public void update()
    {
        updatePos();
        updateHitbox();
        updateAnimation();
        setAnimation();

    }

    public void render(Graphics g) {
        //g.drawImage(current_animation[playerAction][aniIndex], (int)x,(int) y,width,height,null);
        g.drawImage(current_animation[aniIndex], (int)x,(int) y,width,height,null);

        drawHitbox(g);
    }
    private void updatePos()
    {
        moving = false;

        if(left && !right)
        {
            x-=playerSpeed;
            moving = true;
        }
        else if(right && !left)
        {
            x+=playerSpeed;
            moving = true;
        }

        if(up && !down)
        {
            y-=playerSpeed;
            moving = true;

        }
        else if(down && !up)
        {
            y+=playerSpeed;
            moving = true;
        }
    }

    private void setAnimation()
    {
        int startAnimation = playerAction;

        if(moving) {
            playerAction = WALK;
        }
        else {
            playerAction = IDLE;
        }

        if(attacking) {
            playerAction = ATTACK_1;
        }

        if(left)
            current_animation = player_animations_left[playerAction];
        else
            current_animation = player_animations_right[playerAction];



        if(startAnimation != playerAction)
            resetAnimationTick();
    }

    public void initAnimations()
    {
        current_animation = player_animations_right[IDLE];
    }
    private void resetAnimationTick() {
        aniIndex = aniTick = 0;
    }

    private void updateAnimation()
    {
        aniTick++;
        if(aniTick >= aniSpeed)
        {
            aniTick = 0;
            aniIndex++;
            if(aniIndex >= GetSpriteAmount(playerAction)) { 
                aniIndex = 0;
                attacking = false;
            }
        }
    }


    public void loadLvlData(int[][] lvlData){
        this.levelData = lvlData;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void resetDirBooleans() {
        up = down = left = right = false;
    }

    public void setAttacking(boolean attack)
    {
        attacking = attack;
    }
}
