package PaooGame.Tiles;

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
    private static int i_index = 0, j_index = 0;

    private static ArrayList<Entity> entities =  new ArrayList<>();

    public LevelManager()
    {
        setLevel();
        getEscapePos();
    }

    public void update(Player p)
    {
        //functie de update pentru a trece la nivelurile urmatoare
        entitiesUpdate();
        passLevel(p);
    }

    private void entitiesUpdate()
    {
        for(Entity e: entities)
            e.update();
    }

    private void entitiesRender(Graphics g)
    {
        for(Entity e: entities)
            e.render(g);
    }

    private void setLevel()
    {
        // functia seteaza nivelul in functie de numarul lui (variabila level)

        EnemyFactory f = new EnemyFactory();

        switch(level)
        {
            case 1:
                entities.clear();
                map = map_lvl1;
                background = background_lvl1;

                entities.add(f.factoryMethod("simple",900,200));
                entities.add(f.factoryMethod("simple",1800,200));

                break;
            case 2:
                entities.clear();
                map = map_lvl2;
                background = background_lvl2;
                break;
            case 3:
                entities.clear();
                map = map_lvl3;
                background = background_lvl3;
                break;
            default:
                break;
        }
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
        if((p.getHitBox().x > j_index * 32) && (p.getHitBox().y > i_index * 32))
        {
            p.resetPlayerPos();
            ++level;
            setLevel();
        }
    }

    private void getEscapePos()
    {
        for(int i = 0; i < map.length;i++)
            for(int j = 0; j < map[i].length; j++)
            {
                if(map[i][j] == Tile.sewer_hole2.GetId())
                {
                    i_index = i;
                    j_index = j;
                    break;
                }
            }
    }

    public static int[][] getData()
    {
        return map;
    }

}
