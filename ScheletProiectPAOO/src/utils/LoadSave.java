package utils;

import PaooGame.Game;
import PaooGame.Graphics.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class LoadSave {
    public static final String PLAYER_ATLAS = "/textures/Cat Adventure.png";
    public static final String LEVEL_ATLAS = "/textures/pipes.png";
    public static final String LEVEL_ONE = "/textures/level_one_data.png";

    // functia ajuta pentru a incarca orice sprite avem nevoie
    public static BufferedImage GetSpriteAtlas(String filename){
         return ImageLoader.LoadImage(filename);
    }

    public static int[][] GetLevelData()
    {
        int[][] lvlData = new int[Game.TILES_IN_HEIGHT][Game.TILES_IN_WIDTH];
        BufferedImage img = ImageLoader.LoadImage(LEVEL_ONE);

        for(int j = 0; j < img.getHeight() ; j++)
            for(int i = 0; i< img.getWidth(); i++)
            {
                Color color = new Color(img.getRGB(i,j)); // pentru ca e x y nu y x
                int value = color.getGreen();
                if(value >=40)
                        value = 0;
                lvlData[j][i] = value;
            }

        return lvlData;
    }
}
