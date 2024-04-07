package entities;

import PaooGame.Game;
import PaooGame.Graphics.ImageLoader;
import PaooGame.Graphics.SpriteSheet;
import utils.LoadSave;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import static PaooGame.Tiles.LevelConstructor.map;
import static utils.Constants.Directions.*;
import static utils.Constants.Directions.DOWN;
import static utils.Constants.PLayerConstants.*;
import static PaooGame.Graphics.Assets.player_animations_left;
import static PaooGame.Graphics.Assets.player_animations_right;
import static utils.HelpMethods.*;

public class Player extends Entity{
    private BufferedImage[] current_animation;
    private boolean moving = false, attacking = false;
    private int aniIndex, aniTick, aniSpeed = 3;
    private int playerAction = CRAWLING;
    private boolean left, up, right, down, speed, jump;
    private float playerSpeed = 2.0f;
    private int[][] levelData;
    private float xDrawOffset = 16;
    private float yDrawOffset = 22;

    // Jumping & Gravity
    private float airSpeed = 0f;
    private float gravity = 0.1f;
    private float jumpSpeed = -4f;
    private float fallSpeedAfterCollision = 1f;
    private boolean inAir = false;
    public Player(float x,float y,int width, int height)
    {
        super(x,y,width,height);
        initHitbox(x,y,28,28);
        initAnimations();
        loadLvlData();
    }

    public void update()
    {
        updatePos();
        updateAnimation();
        setAnimation();
    }

    public void render(Graphics g) {
        //g.drawImage(current_animation[playerAction][aniIndex], (int)x,(int) y,width,height,null);
        g.drawImage(current_animation[aniIndex], (int) (hitBox.x - xDrawOffset),(int) (hitBox.y - yDrawOffset),width,height,null);

        drawHitbox(g);
    }
    private void updatePos()
    {
        moving = false;

        if(jump)
            jump();


        float xSpeed = 0;

        if(speed)
            playerSpeed = 5.0f;
        else
            playerSpeed = 2.0f;

        if(left)
            xSpeed -= playerSpeed;

        if(right)
            xSpeed += playerSpeed;

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

        if(!left && !right && !inAir)
            return;

        moving = true;
    }

    private void jump() {
        if(inAir)
            return;

        inAir = true;
        airSpeed = jumpSpeed;
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

    private void setAnimation()
    {
        int startAnimation = playerAction;

        if(moving) {
            if(speed)
                playerAction = RUN;
            else
                playerAction = WALK;
        }
        else {
            playerAction = IDLE;
        }

        if(inAir)
        {
            if(airSpeed < 0)// mergem in sus
                playerAction = JUMP;
            else
                playerAction = FALLING;
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


    public void loadLvlData(){
        this.levelData = map;

        if(!IsEntityOnFloor(hitBox,map))
            inAir = true;
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

    public void setSpeed(boolean speed)
    {
        this.speed = speed;
    }
    public void setJump(boolean jump)
    {
        this.jump = jump;
    }
}
