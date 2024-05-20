package PaooGame.entities;

import PaooGame.Game;

import java.awt.*;

import static PaooGame.Graphics.Assets.*;
import static PaooGame.Tiles.LevelManager.superpaw;
import static PaooGame.Tiles.Tile.TILE_SIZE;
import static PaooGame.utils.Camera.xCamera;
import static PaooGame.utils.Constants.*;

public class BossEnemy extends SimpleEnemy{
    public BossEnemy(int x, int y, int width, int height, boolean containsClippers) {
        super(x,y,width,height, containsClippers);
        initHitbox(x,y,80,28);
        super.yDrawOffset = 160;
        super.xDrawOffset = 70;
        movingRight = true;
        b = new Bullet(this);
        b.bulletspeed = 6f;
        b.shootingRange = 7 * TILE_SIZE;
        current_animation = boss_animations_right[EnemyIDLE];
        current_animation_left = boss_animations_left;
        current_animation_right = boss_animations_right;
        health.lifeCount = 10;
    }

    public void render(Graphics g)
    {

        g.drawImage(current_animation[aniIndex], (int) (hitBox.x - xDrawOffset - xCamera),(int) (hitBox.y - yDrawOffset),width,height,null);

        b.SHOOT(g);

        System.out.println(health.lifeCount);

        //debugg
        if(Game.DEBUG)
            drawHitbox(g);
    }

    public void takeDamage() {
        super.checkIfDead();

        if (health.lifeCount > 0) {
            if (superpaw.used)
                health.lifeCount -= 2; // nu inteleg de ce se apeleaza de 32 de ori cand da o lovitura player-ul :))
            else
                health.lifeCount -= 1; // nu inteleg de ce se apeleaza de 32 de ori cand da o lovitura player-ul :)) /32.

            Player.getInstance().setAttacking(false);

            if (movingLeft && !shooting) {
                movingLeft = false;
                movingRight = true;
            } else if (movingRight && !shooting) {
                movingRight = false;
                movingLeft = true;
            }
        }
    }
}
