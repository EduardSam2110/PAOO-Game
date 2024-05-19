package PaooGame.entities;

import PaooGame.Game;
import PaooGame.Inventory.Clippers;
import PaooGame.Tiles.LevelManager;
import PaooGame.Tiles.Tile;
import PaooGame.utils.Camera;

import java.awt.*;
import java.awt.image.BufferedImage;

import static PaooGame.Graphics.Assets.*;
import static PaooGame.Tiles.Tile.TILE_SIZE;
import static PaooGame.Tiles.Tile.waterBlock;
import static PaooGame.utils.Camera.xCamera;
import static PaooGame.utils.Camera.xCameraPos;
import static PaooGame.utils.Constants.*;
import static PaooGame.utils.GravityCollisionMethods.*;

public class Player extends Entity {

    private BufferedImage[] current_animation;

    private boolean moving = false;
    private boolean left, up = true, right, down = false, speed, jump;
    private boolean swimUP = false, swimDOWN = false;

    public boolean clipperPicked = false;
    public boolean superPawPicked = false;

    private int playerSpeed = 2;

    // offset-urile pentru dreptunghiul de coliziuni
    private float xDrawOffset = 23;
    private float yDrawOffset = 22;

    // viteza jucatorului in timpul miscarii
    public static float xSpeed;

    private static Player instance;

    private Player(float x,float y,int width, int height)
    {
        super(x,y,width,height);
        initHitbox(x,y,19,28);
        initAnimations();
        checkAtStart();
        action = IDLE;
    }

    public static Player getInstance()
    {
        if(instance == null)
            instance = new Player(90,450,64,64);

        return instance;
    }

    @Override
    public void update()
    {
        Camera.Update(this);
        Score.Score_System(this);
        updatePos();
        updateAnimation(action, "player");
        setAnimation();
        super.loadLvlData();
//        System.out.println(hitBox.x );
//        System.out.println(hitBox.y );
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(current_animation[aniIndex], (int) (hitBox.x - xDrawOffset - xCamera),(int) (hitBox.y - yDrawOffset),width,height,null);
        health.render(g,50,20,96,32);
        Score.update(g);
        //debugg
        if(Game.DEBUG) {
            drawHitbox(g);
            Camera.Draw(g,this);
        }

    }
    private void updatePos()
    {
        getSpikeDamage();

        moving = false;

        if(detectWater())
        {
            gravity = 0.001f;
            fallSpeedAfterCollision = 0f;
            WaterBehaviour();
        }
        else {
            gravity = 0.1f;
            fallSpeedAfterCollision = 1f;

            if(jump)
                jump();

        }

        xSpeed = 0;

        if(speed && up)
            playerSpeed = 3;
        else
            playerSpeed = 2;

        if(left)
            xSpeed -= playerSpeed;

        if(right)
            xSpeed += playerSpeed;

        testGravity((int)xSpeed);

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

    private void setAnimation()
    {
        int startAnimation = action;

        if(moving && (!left || !right)) {
            if(speed && up)
                action = RUN;
            else {
                if (down)
                    action = CRAWLING;
                else if (up)
                    action = WALK;
            }
        }
        else {
            if (down && !up)
                action = SIT_DOWN;
            else
                action = IDLE;
        }

        if(inAir)
        {
            if(airSpeed < 0)
                action = JUMP;
            else
                action = FALLING;
        }


        if(attacking)
            action = ATTACK_1;

        if(detectWater())
            action = SWIMMING;

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
        left = right = attacking = speed = jump = false;
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

    public void setJumpSpeed(float speed) {
        jumpSpeed = speed;
    }

    public void setSwimUP(boolean swimUP) {this.swimUP = swimUP;}
    public void setSwimDOWN(boolean swimDOWN) {this.swimDOWN = swimDOWN;}

    public void LoadFromSave(float x, float y, int health, int score, int xCam, int xCamPos, boolean clipperPicked, boolean superPawPicked, int final_score)
    {
        hitBox.x = x;
        hitBox.y = y;
        this.health.lifeCount = health;
        Score.current_score = (float) score;
        Score.finalScore = (float) final_score;
        xCamera = xCam;
        xCameraPos = xCamPos;
        LevelManager.clippers.collected = clipperPicked;
        LevelManager.superpaw.setState(superPawPicked);
    }

    public void getSpikeDamage(){
        float xIndex = hitBox.x / TILE_SIZE;
        float yIndex = (hitBox.y + hitBox.height + 8)  / TILE_SIZE;

        int value = levelData[(int)yIndex][(int)xIndex];

        if(value == Tile.spikeUp.GetId() || value == Tile.underwaterSpike.GetId())
        {
            takeDamage();
        }
    }

    public void takeDamage()
    {
        if(health.lifeCount > 1) {
            --health.lifeCount;
            resetPlayerPos();
        }
        else {
            died = true;
            Game.old_time = System.currentTimeMillis();
        }
    }

    public void resetPlayerPos()
    {
        hitBox.x = x;
        hitBox.y = y;
        inAir = true;
        Camera.resetCamera();
    }

    public void resetAll()
    {
        died = false;
        health.lifeCount = 3;
        resetPlayerPos();
    }

    public void WaterBehaviour()
    {
        if(swimDOWN)
            hitBox.y += 1;
        else if(swimUP)
            hitBox.y -= 1;
    }

    private boolean detectWater()
    {
        int playerX = (int) Player.getInstance().getHitBox().x / TILE_SIZE;
        int playerY = (int) (Player.getInstance().getHitBox().y + TILE_SIZE) / TILE_SIZE;

        if(levelData[playerY][playerX] == waterBlock.GetId())
            return true;

        return false;
    }
}
