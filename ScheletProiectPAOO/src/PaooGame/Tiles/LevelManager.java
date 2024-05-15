package PaooGame.Tiles;

import PaooGame.Inventory.Clippers;
import PaooGame.entities.EnemyFactory;
import PaooGame.entities.Entity;
import PaooGame.entities.Player;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static PaooGame.Graphics.Assets.*;
import static PaooGame.Tiles.Tile.tiles;
import static PaooGame.utils.Camera.xCamera;


public class LevelManager {
    private static int[][] map;
    private static BufferedImage background;
    public static int level = 1;
    public static int gateXpos = 0, gateYpos = 0;

    private static ArrayList<Entity> entities =  new ArrayList<>();

    public static Clippers clippers;

    public LevelManager()
    {
        setLevel();
        addEntities();
        getEscapePos();
    }

    public void update(Player p)
    {
        //functie de update pentru a trece la nivelurile urmatoare
        entitiesUpdate();
//        passLevel(p);
        if(clippers != null) {
            clippers.pickItem(p);
            clippers.useItem(p);
        }
    }

    private void entitiesUpdate()
    {
        for(Entity e: entities)
            e.update();

        if(clippers != null)
            clippers.update();

    }

    private void entitiesRender(Graphics g)
    {
        for(Entity e: entities)
            e.render(g);

        if(clippers != null)
            clippers.render(g);
    }

    private void setLevel()
    {
        // functia seteaza nivelul in functie de numarul lui (variabila level)

        switch(level)
        {
            case 1:
                map = map_lvl1;
                background = background_lvl1;
                break;
            case 2:
                map = map_lvl2;
                background = background_lvl2;
                break;
            case 3:
                map = map_lvl3;
                background = background_lvl3;
                break;
            default:
                break;
        }
    }

    private void addEntities()
    {
        EnemyFactory f = new EnemyFactory();

        switch(level)
        {
            case 1:
                entities.clear();
                entities.add(f.factoryMethod("simple", 900, 200, true));
                entities.add(f.factoryMethod("simple", 1800, 200, false));
                break;
            case 2:
                entities.clear();
                entities.add(f.factoryMethod("simple", 900, 200, true));
                break;
            case 3:
                entities.clear();

                break;
            default:
                break;
        }

        clippers = Clippers.setTarget(entities);
    }

    public void draw(Graphics g)
    {
        g.drawImage(background,0,0,null);
        for(int i = 0; i<23;i++)
            for(int j = 0; j<80;j++)
            {
                if(map[i][j] >= 0)
                    if(tiles[map[i][j]] != null)
                        tiles[map[i][j]].Draw(g,32*j-xCamera,32*i);
            }

        entitiesRender(g);
    }

    public void passLevel(Player p)
    {
//        if((p.getHitBox().x > gateYpos * 32) && (p.getHitBox().y > gateXpos * 32))
//        {
            p.resetPlayerPos();
            ++level;
            setLevel();
            addEntities();
//        }
    }

    private void getEscapePos()
    {
        for(int i = 0; i < map.length;i++)
            for(int j = 0; j < map[i].length; j++)
            {
                if(map[i][j] == Tile.sewer_hole2.GetId()) // SCHIMBA SA FIE SI CELELALTE SEWER HOLES SI VEZI SA FGOLOSESTI DOAR ALEA 2 O DATA
                {
                    gateXpos = i;
                    gateYpos = j;
                    break;
                }
            }
    }

    public static int[][] getData()
    {
        return map;
    }

}
