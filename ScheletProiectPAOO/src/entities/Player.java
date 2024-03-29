package entities;

import PaooGame.Graphics.ImageLoader;
import PaooGame.Graphics.SpriteSheet;
import utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utils.Constants.Directions.*;
import static utils.Constants.Directions.DOWN;
import static utils.Constants.PLayerConstants.*;

public class Player extends Entity{
    private BufferedImage[][] idleAnimation;
    private boolean moving = false, attacking = false;
    private int aniIndex, aniTick, aniSpeed = 3;

    private int playerAction = IDLE;
    private boolean left, up, right, down;
    private float playerSpeed = 5.0f;
    public Player(float x,float y)
    {
        super(x,y);
        loadAnimations();
    }

    public void update()
    {
        updateAnimation();
        setAnimation();
        updatePos();
    }

    public void render(Graphics g) {
        g.drawImage(idleAnimation[playerAction][aniIndex], (int)x,(int) y,64,64,null);
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


        if(startAnimation != playerAction)
            resetAnimationTick();
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
            if(aniIndex >= GetSpriteAmount(playerAction)) { // nu stiu daca e 1 sau get sprite amount
                aniIndex = 0;
                attacking = false;
            }
        }
    }
    private void loadAnimations()
    {
        SpriteSheet img = new SpriteSheet(LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS));
        idleAnimation = new BufferedImage[15][8];// 15 animatii si maxim 8 spirteuri
        for(int i = 0; i<  idleAnimation.length; i++)
        {
            for(int j = 0; j < idleAnimation[i].length; j++)
            {
                idleAnimation[i][j] = img.crop(j,i);
            }
        }
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
