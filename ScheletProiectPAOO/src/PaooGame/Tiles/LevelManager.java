package PaooGame.Tiles;

import java.awt.*;

import static PaooGame.Graphics.Assets.background_lvl1;
import static PaooGame.Tiles.Tile.tiles;
import static utils.Camera.xCamera;
import static PaooGame.Graphics.Assets.map_lvl1;


public class LevelManager {

    public void draw(Graphics g)
    {
        g.drawImage(background_lvl1,0,0,null);
        for(int i = 0; i<23;i++)
            for(int j = 0; j<80;j++)
            {
                if(map_lvl1[i][j] >= 0)
                    if(tiles[map_lvl1[i][j]] != null)
                        tiles[map_lvl1[i][j]].Draw(g,32*j-xCamera,32*i);
            }
    }

    public int[][] getData()
    {
        return map_lvl1;
    }
}
