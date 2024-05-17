package PaooGame.entities;

import PaooGame.Game;

import java.awt.*;

import static PaooGame.Graphics.Assets.*;
import static PaooGame.utils.Camera.xCamera;
import static PaooGame.utils.Constants.*;
import static PaooGame.utils.Constants.EnemyATTACK;

public class BossEnemy extends SimpleEnemy{
    public BossEnemy(int x, int y, int width, int height, boolean containsClippers) {
        super(x,y,width,height, containsClippers);
        initHitbox(x,y,100,28);
        super.yDrawOffset = 160;
        super.xDrawOffset = 55;
        movingRight = true;
        b = new Bullet(this);
        current_animation = boss_animations_right[EnemyIDLE];
    }

    protected void setAnimation()
    {
        action = EnemyWALK;

//        shoot();

        if(died)
            action = EnemyDEATH;

        if (inAir == false) {

            if(shooting)
                action = EnemyATTACK;

            if (movingRight) {
                current_animation = boss_animations_right[action];
            } else if (movingLeft) {
                current_animation = boss_animations_left[action];
            }
        }
        else
            current_animation = boss_animations_right[EnemyIDLE];
    }

    @Override
    public void update()
    {
        super.update();
        setAnimation();
    }
}
