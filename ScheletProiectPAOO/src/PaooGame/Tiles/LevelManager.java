package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

import static PaooGame.Graphics.Assets.*;
import static PaooGame.Tiles.Tile.tiles;
import static utils.Camera.xCamera;


public class LevelManager {
    private static int[][] map;
    private static BufferedImage background;
    public static int level = 1;

    public LevelManager()
    {
        setLevel();
    }

    public void update()
    {
        //functie de update pentru a trece la nivelurile urmatoare
        //setLevel();
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
            case 3:
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

    public static int[][] getData()
    {
        return map;
    }
}
