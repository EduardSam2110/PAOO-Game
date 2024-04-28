package PaooGame.Tiles;

import PaooGame.entities.Player;

import java.awt.*;
import java.awt.image.BufferedImage;

import static PaooGame.Graphics.Assets.*;
import static PaooGame.Tiles.Tile.tiles;
import static PaooGame.utils.Camera.xCamera;


public class LevelManager {
    private static int[][] map;
    private static BufferedImage background;
    public static int level = 1;
    private static int i_index = 0, j_index = 0;

    public LevelManager()
    {
        setLevel();
        getEscapePos();
    }

    public void update(Player p)
    {
        //functie de update pentru a trece la nivelurile urmatoare
        setLevel();
        passLevel(p);

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
    }

    public static void passLevel(Player p)
    {
        if((p.getHitBox().x > j_index * 32) && (p.getHitBox().y > i_index * 32))
        {
            p.resetPlayerPos();
            ++level;
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
