package PaooGame.Inventory;

import PaooGame.Game;
import PaooGame.Graphics.Assets;
import PaooGame.entities.Entity;
import PaooGame.entities.Player;
import PaooGame.entities.SimpleEnemy;

import java.awt.*;
import java.util.ArrayList;

import static PaooGame.utils.Camera.xCamera;

public class Clippers extends ItemAbstractClass {
    private Clippers(SimpleEnemy e) {
        super(e);
    }

//    public static Clippers getInstnce()
//    {
//        if(clippers == null)
//            clippers = new Clippers();
//
//        return clippers;
//    }

    @Override
    public void request()
    {

    }

    @Override
    public void render(Graphics g)
    {
       if(!collected)
       {
           g.drawImage(Assets.clipperstxt, (int) hitBox.x - xCamera, (int) hitBox.y,16,16,null);
       }
       else
           g.drawImage(Assets.clipperstxt, 620, 20,32,32,null);

       if(Game.DEBUG)
           drawHitbox(g);
    }

    @Override
    public void pickItem(Player p){
        if(hitBox.x > p.hitBox.x  && hitBox.x + hitBox.width < p.hitBox.x + p.hitBox.width)
            if(hitBox.y > p.hitBox.y && hitBox.y + hitBox.height < p.hitBox.y + p.hitBox.height)
                collected = true;
    };

    @Override
    protected void useItem(Player p){

    };

    public static Clippers setTarget(ArrayList<Entity> arr) // seteaza care este inamicul care are variabila contiansClippers pe true
    {
        for(Entity e : arr)
            if(e instanceof SimpleEnemy)
                if(((SimpleEnemy) e).continsClippers)
                    return new Clippers((SimpleEnemy) e);
        return null;
    }
}
