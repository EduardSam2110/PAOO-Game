package PaooGame.Tiles;

import PaooGame.Game;
import PaooGame.Inventory.Clippers;
import PaooGame.Inventory.SuperPaw;
import PaooGame.entities.EnemyFactory;
import PaooGame.entities.Entity;
import PaooGame.entities.Player;
import PaooGame.entities.Score;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static PaooGame.Graphics.Assets.*;
import static PaooGame.Tiles.Tile.tiles;
import static PaooGame.utils.Camera.xCamera;

/*
Clasa se ocupa cu gestionarea nivelelor si a tot ce tine de ele
 */

public class LevelManager {
    private static int[][] map; // matricea hartii
    private static BufferedImage background;
    public static int level = 1;
    public static int gateXpos = 0, gateYpos = 0; // pozitiile x si y ale portii de trecere la alt nivel

    private static ArrayList<Entity> entities =  new ArrayList<>(); // array list de inamici
    public static int[] entities_spawn_table = new int[2]; // tabel pentru a tine evidenta inamicilor daca sunt omorati sau nu

    public static Clippers clippers = new Clippers(null);
    public static SuperPaw superpaw = new SuperPaw(0,0);

    public static boolean loadedFromSave = false;

    public LevelManager()
    {
        entities_spawn_table[0] = 1;
        entities_spawn_table[1] = 1;
        initALevel();
    }

    public void update(Player p)
    {
        //functie de update pentru a trece la nivelurile urmatoare
        entitiesUpdate();

        if(clippers != null) {
            clippers.pickItem(p);
            clippers.useItem(p);
        }

        superpaw.pickItem(p);

    }

    private void entitiesUpdate()
    {
        for(Entity e: entities)
            if(e != null)
                e.update();

        if(clippers != null)
            clippers.update();

        superpaw.update();

        update_entities_table();

    }

    private void entitiesRender(Graphics g)
    {
        for(Entity e: entities)
            if(e != null)
                e.render(g);

        if(clippers != null)
            clippers.render(g);

        superpaw.render(g);
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
            case 4:
                Game.GAME_FINISHED = true;
                Game.SAVE_SELECTED = true;
                Game.old_time = System.currentTimeMillis();
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
                entities.add(f.factoryMethod("simple", 900, 200, true, entities_spawn_table[0]));
                entities.add(f.factoryMethod("simple", 1800, 200, false,entities_spawn_table[1]));
                superpaw.setNewPos(1032,272);
                break;
            case 2:
                entities.clear();
                entities.add(f.factoryMethod("simple", 500, 200, false,entities_spawn_table[0]));
                entities.add(f.factoryMethod("simple", 1400, 50, true,entities_spawn_table[1]));
                superpaw.setNewPos(832,672);
                break;
            case 3:
                entities.clear();
                entities.add(f.factoryMethod("boss", 900, 300, true,entities_spawn_table[0]));
                entities.add(f.factoryMethod("simple", 900, 650, false,entities_spawn_table[1]));
                superpaw.setNewPos(1470, 580);
                break;
            default:
                break;
        }
    }

    public void draw(Graphics g)
    {
        g.drawImage(background,0,0,1280,720,null);
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
        p.resetAll();
        ++level;
        initALevel();
        Score.updateFinalScore();
    }

    // functia obtine pozitia portii spre urmatorul nivel
    private void getEscapePos()
    {
        for(int i = 0; i < map.length;i++)
            for(int j = 0; j < map[i].length; j++)
            {
                if(map[i][j] == Tile.sewer_hole2.GetId())
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

    /*
    metoda actualizeaza tabelul de entitati, pe baza daca entitatea e omorata sau nu
    0 - da
    1 - nu
     */
    private void update_entities_table()
    {
        for(Entity e : entities)
            if(e != null) {
                if (e.died)
                    entities_spawn_table[entities.indexOf(e)] = 0;
                else
                    entities_spawn_table[entities.indexOf(e)] = 1;
            }
            else
                entities_spawn_table[entities.indexOf(e)] = 0;
    }

    public static void set_entities_table(int a, int b)
    {
        entities_spawn_table[0] = a;
        entities_spawn_table[1] = b;
    }

    // metoda de initializare a unui nivel
    public void initALevel()
    {
        if(loadedFromSave == false) {
            set_entities_table(1, 1);
        }

        superpaw.setState(false);
        setLevel();
        addEntities();
        clippers = Clippers.setTarget(entities);
        getEscapePos();
    }
}
