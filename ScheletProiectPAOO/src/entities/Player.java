package entities;

import utils.Camera;

import java.awt.*;
import java.awt.image.BufferedImage;

import static PaooGame.Graphics.Assets.*;
import static PaooGame.Tiles.LevelConstructor.map;
import static PaooGame.Tiles.Tile.TILE_SIZE;
import static utils.Camera.xCamera;
import static utils.Constants.PLayerConstants.*;
import static utils.HelpMethods.*;

public class Player extends Entity {
    private BufferedImage[] current_animation;
    private boolean moving = false;

    private boolean left, up, right, down, speed, jump;
    private int playerSpeed = 2;
    private int[][] levelData;
    private float xDrawOffset = 16;
    private float yDrawOffset = 22;

    public static float xSpeed;
    public static int temp = 0;

    public Player(float x,float y,int width, int height)
    {
        super(x,y,width,height);
        initHitbox(x,y,28,28);
        initAnimations();
        loadLvlData();
    }

    @Override
    public void update()
    {
        Camera.Update(this);
        updatePos();
        updateAnimation();
        setAnimation();
    }

    @Override
    public void render(Graphics g) {
        //g.drawImage(current_animation[playerAction][aniIndex], (int)x,(int) y,width,height,null);
        g.drawImage(current_animation[aniIndex], (int) (hitBox.x - xDrawOffset - xCamera),(int) (hitBox.y - yDrawOffset),width,height,null);
        g.drawImage(health_bar[temp][0],1100,20,32,32, null);
        g.drawImage(health_bar[temp][1],1132,20,32,32, null);
        g.drawImage(health_bar[temp][2],1164,20,32,32, null);


    }
    private void updatePos()
    {
        //resetPlayerPos();

        moving = false;

        if(jump)
            jump();

        xSpeed = 0;

        if(speed)
            playerSpeed = 3;
        else
            playerSpeed = 2;

        if(left)
            xSpeed -= playerSpeed;

        if(right)
            xSpeed += playerSpeed;

        if(!left && !right && !inAir)
            return;

        testGravity((int)xSpeed);

        moving = true;
    }

    private void jump() {
        if(inAir)
            return;

        inAir = true;
        airSpeed = jumpSpeed;
    }

    private void setAnimation()
    {
        int startAnimation = playerAction;

        if(moving && (!left || !right)) {
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
    public boolean IsMoving(){return moving;}
    public int GetPlayerSpeed(){return playerSpeed;}

    public void resetPlayerPos(){
        float xIndex = hitBox.x / TILE_SIZE;
        float yIndex = (hitBox.y + hitBox.height + 32)  / TILE_SIZE;

//        System.out.println(xIndex + "  " + yIndex);

        int value = levelData[(int)yIndex][(int)xIndex];

//        System.out.println(value);

        if(value == 37)
        {
            hitBox.x = 90;
            hitBox.y = 370;
            if((temp+2) < 7)
                temp+=2;

        }
    }
}
