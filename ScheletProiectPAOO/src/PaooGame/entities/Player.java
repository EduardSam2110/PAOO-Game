package PaooGame.entities;

import PaooGame.Tiles.Tile;
import PaooGame.utils.Camera;

import java.awt.*;
import java.awt.image.BufferedImage;

import static PaooGame.Graphics.Assets.*;
import static PaooGame.Tiles.Tile.TILE_SIZE;
import static PaooGame.utils.Camera.xCamera;
import static PaooGame.utils.Constants.*;
import static PaooGame.utils.GravityCollisionMethods.*;

public class Player extends Entity {

    private BufferedImage[] current_animation;

    private boolean moving = false;
    private boolean left, up = true, right, down = false, speed, jump;

    private int playerSpeed = 2;

    // offset-urile pentru dreptunghiul de coliziuni
    private float xDrawOffset = 23;
    private float yDrawOffset = 22;

    // viteza jucatorului in timpul miscarii
    public static float xSpeed;

    public Player(float x,float y,int width, int height)
    {
        super(x,y,width,height);
        initHitbox(x,y,19,28);
        initAnimations();
        checkAtStart();
        action = IDLE;
    }

    @Override
    public void update()
    {
        Camera.Update(this);
        updatePos();
        updateAnimation(action, "player");
        setAnimation();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(current_animation[aniIndex], (int) (hitBox.x - xDrawOffset - xCamera),(int) (hitBox.y - yDrawOffset),width,height,null);
        HealthBar.render(g);
        //drawHitbox(g);

    }
    private void updatePos()
    {
        resetIfSpike();

        moving = false;

        if(jump)
            jump();

        xSpeed = 0;

        if(speed && up)
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
        int startAnimation = action;

        if(moving && (!left || !right)) {
            if(speed && up)
                action = RUN;
            else
                if(down)
                    action = CRAWLING;
                else if(up)
                    action = WALK;
        }
        else {
            if(up && !down)
                action = IDLE;
            else if (down && !up)
                action = SIT_DOWN;
        }

        if(inAir)
        {
            if(airSpeed < 0)
                action = JUMP;
            else
                action = FALLING;
        }

        if(attacking) {
            action = ATTACK_1;
        }

        if(left)
            current_animation = player_animations_left[action];
        else
            current_animation = player_animations_right[action];

        if(startAnimation != action)
            resetAnimationTick();
    }

    public void initAnimations()
    {
        current_animation = player_animations_right[IDLE];
    }

    private void resetAnimationTick() {
        aniIndex = aniTick = 0;
    }

    private void checkAtStart(){
        // verifica daca e in aer la inceperea nivelului pentru a seta inAir
        if(!IsEntityOnFloor(hitBox, this.levelData))
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

    public boolean IsMoving(){
        return moving;
    }

    public int GetPlayerSpeed(){
        return playerSpeed;
    }

    public void resetIfSpike(){
        float xIndex = hitBox.x / TILE_SIZE;
        float yIndex = (hitBox.y + hitBox.height + 16)  / TILE_SIZE;

        int value = levelData[(int)yIndex][(int)xIndex];

        if(value == Tile.spikeUp.GetId())
        {
            takeDamage();
        }
    }

    public void takeDamage()
    {
        if((HealthBar.counter+1) <= HealthBar.maxLife)
            HealthBar.counter++;
        resetPlayerPos();
    }

    private void resetPlayerPos()
    {
        hitBox.x = x;
        hitBox.y = y;
        Camera.resetCamera();
    }
}