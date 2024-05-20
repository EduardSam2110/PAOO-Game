package PaooGame.entities;

import PaooGame.Game;
import PaooGame.Graphics.Assets;

import java.awt.*;
import java.awt.geom.Rectangle2D;

//import static PaooGame.Game.player;
import static PaooGame.Tiles.Tile.TILE_SIZE;
import static PaooGame.utils.Camera.xCamera;
import static PaooGame.utils.Constants.CRAWLING;
import static PaooGame.utils.Constants.SIT_DOWN;

/*
clasa modeleaza obiectul glont
fiecare inamic are o instanta a acestei clase
*/
public class Bullet {
    private int x,y;
    public float bulletspeed = 3f;
    public int shootingRange = 5 * TILE_SIZE;
    private SimpleEnemy e;
    public Rectangle2D.Float hitBox; // dreptunghiul pentru coliziuni


    public Bullet(SimpleEnemy e) {
        //super((int) e.getHitBox().x,(int) e.getHitBox().y, 16,16);
        this.e = e;
        x = (int) e.getHitBox().x;
        y = (int) e.getHitBox().y+75;
        initHitbox(x,y,8,8);
    }

    protected void initHitbox(float x, float y, float width, float height) {
        hitBox = new Rectangle2D.Float(x,y,width,height);
    }

    private void draw(Graphics g)
    {
        if(e.shooting)
            g.drawImage(Assets.bullet, (int) hitBox.x - xCamera, (int) hitBox.y - 15,null);

        if(Game.DEBUG)
            drawHitbox(g);
    }

    private void movement()
    {
        /* miscarea glontului are 2 etape:
        - cand inamicul nu trage, pozitia se actualizeaza cu cea a inamicului - practic inamicul poarta glontul
        - cand inamicul trage, glontul se misca pe distanta shootingRange, resetandu-se la inamic cand ajunge la capatul distantei
        sau daca inamicul se misca
         */
        if(e instanceof BossEnemy) {
            if (e.getMovingLeft()) {
                if (e.shooting) {
                    hitBox.x -= bulletspeed;
                    hitBox.y += 0.5;// la inamicul Boss glontul se misca oblic,
                                    // pentru ca are dimensiunea mult mai mare decat un inamic normal
                    if (hitBox.x < e.getHitBox().x - shootingRange) {
                        hitBox.x = e.getHitBox().x;
                        hitBox.y = e.getHitBox().y;
                    }
                } else {
                    hitBox.x = e.getHitBox().x;
                    hitBox.y = e.getHitBox().y;
                }
            } else if(e.getMovingRight()) {
                if(e.shooting) {
                    hitBox.x += bulletspeed;
                    hitBox.y += 0.5;
                    if (hitBox.x > e.getHitBox().x + shootingRange) {
                        hitBox.x = e.getHitBox().x + e.getHitBox().width;
                        hitBox.y = e.getHitBox().y;
                    }
                } else {
                    hitBox.x = e.getHitBox().x + e.getHitBox().width;
                    hitBox.y = e.getHitBox().y;
                }
            }

        } else if(e instanceof SimpleEnemy){
            if (e.shooting) {
                if (e.getMovingLeft()) {
                    hitBox.x -= bulletspeed;
                    if (hitBox.x < e.getHitBox().x - shootingRange)
                        hitBox.x = e.getHitBox().x;
                } else if (e.getMovingRight()) {
                    hitBox.x += bulletspeed;
                    if (hitBox.x > e.getHitBox().x + shootingRange)
                        hitBox.x = e.getHitBox().x;
                }
            } else {
                hitBox.x = e.getHitBox().x;
                hitBox.y = e.getHitBox().y;
            }
        }
    }

    public void SHOOT(Graphics g) // metoda shoot apelata in clasele de inamici
    {
        draw(g);
        movement();
        if(!e.died && e.shooting)
            die_if_attack();
    }

    private void die_if_attack() // metoda care testeaza daca glontul face coliziune cu player-ul
    {
        int playerX = (int) Player.getInstance().getHitBox().x;
        int playerY = (int) Player.getInstance().getHitBox().y;

        if((hitBox.y >= playerY) && (hitBox.y + hitBox.height <= playerY + Player.getInstance().getHitBox().height))
            if((hitBox.x >= playerX) && (hitBox.x + hitBox.width <= playerX + Player.getInstance().getHitBox().width))
                if(Player.getInstance().action != CRAWLING && Player.getInstance().action != SIT_DOWN) // daca player-ul este jos sau se taraste, nu ia damage
                    Player.getInstance().takeDamage();
    }

    protected void drawHitbox(Graphics g)
    {
        g.setColor(Color.WHITE);
        g.drawRect((int)hitBox.x -xCamera,(int)hitBox.y,(int)hitBox.width,(int)hitBox.height);
    }
}

