package PaooGame.Tiles;

import PaooGame.Graphics.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;

import static PaooGame.Tiles.Tile.tiles;

public class LevelConstructor {
    private static final BufferedImage img = ImageLoader.LoadImage("/textures/level1.png");
    private static int map[][] = new int[23][120];
    public void LevelConstructor() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("nivel.csv"));
            int j = 0;
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                for (int i = 0; i < 120; i++) {
                    map[j][i] = Integer.parseInt(values[i]);
                }
                j++;
            }
        } catch (Exception e) {e.printStackTrace();}

//        for(int i = 0; i<23;i++)
//            for(int j = 0; j<120;j++)
//            {
//                System.out.println(map[i][j]);
//            }

    }

    // ecuatia indexului unei dale = (coloana - 1) * 14 + (pozitie pe verticala - 1)

    private void LevelBuilder()
    {

    }

    public void draw(Graphics g)
    {

        g.drawImage(img,0,0,null);
        LevelConstructor();
        for(int i = 0; i<23;i++)
            for(int j = 0; j<120;j++)
            {
                if(map[i][j] >= 0)
                    if(tiles[map[i][j]] != null)
                        tiles[map[i][j]].Draw(g,32*j,32*i);
            }

//
//        for(int i = 0; i < 23; i++)
//        {
//            for(int j = 0; j < 37; j++)
//            {
//                int index = map[i][j];
//                if (index <= 111 && index >= 0)
//                    tiles[index].Draw(g,32*j,32*i);
//            }
//        }
    }

    public int[][] getData()
    {
        return map;
    }
}
