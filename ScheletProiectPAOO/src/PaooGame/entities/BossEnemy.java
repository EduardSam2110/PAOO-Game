package PaooGame.entities;

import PaooGame.Game;

import java.awt.*;

import static PaooGame.utils.Camera.xCamera;

public class BossEnemy extends SimpleEnemy{
    public BossEnemy(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void render(Graphics g)
    {

//        g.drawImage(current_animation[aniIndex], (int) (hitBox.x - xDrawOffset - xCamera),(int) (hitBox.y - yDrawOffset),width,height,null);

//        b.SHOOT(g);

//        if(!died)
//            health.render(g,(int) (hitBox.x - xCamera),(int) (hitBox.y - yDrawOffset),48,16);

        g.drawRect(100,200,300,500);

        //debugg
        if(Game.DEBUG)
            drawHitbox(g);
    }
}
