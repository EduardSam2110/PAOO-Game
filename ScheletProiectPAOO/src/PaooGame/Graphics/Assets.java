package PaooGame.Graphics;

import PaooGame.Tiles.Tile;
import utils.LoadSave;

import java.awt.image.BufferedImage;

import static PaooGame.Tiles.Tile.tiles;

/*! \class public class Assets
    \brief Clasa incarca fiecare element grafic necesar jocului.

    Game assets include tot ce este folosit intr-un joc: imagini, sunete, harti etc.
 */
public class Assets
{
        /// Referinte catre elementele grafice (dale) utilizate in joc.
    public static BufferedImage[][] player_animations_right;
    public static BufferedImage[][] player_animations_left;

    public static BufferedImage[][] enemy_animations_right;

    public static BufferedImage solidBlock1;
    public static BufferedImage solidBlock2;
    public static BufferedImage solidBlock3;

    public static BufferedImage sewer_hole1;
    public static BufferedImage sewer_hole2;

    public static BufferedImage sewer_pipe_large_left;
    public static BufferedImage sewer_pipe_large_middle;
    public static BufferedImage sewer_pipe_large_right;
    public static BufferedImage sewer_pipe_large_down;
    public static BufferedImage sewer_pipe_large_leftright;
    public static BufferedImage sewer_pipe_large_rightleft;

    public static BufferedImage[][] health_bar;

    /*! \fn public static void Init()
        \brief Functia initializaza referintele catre elementele grafice utilizate.

        Aceasta functie poate fi rescrisa astfel incat elementele grafice incarcate/utilizate
        sa fie parametrizate. Din acest motiv referintele nu sunt finale.
     */
    public static void Init()
    {
        // Tile-uri pentru niveluri

        SpriteSheet sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/pipes.png"),32,32);

        solidBlock1 = sheet.crop(3, 4);
        solidBlock2 = sheet.crop(4, 4);
        solidBlock3 = sheet.crop(5,4);

        sewer_hole1 = sheet.crop(0,4);
        sewer_hole2 = sheet.crop(1,4);

        sewer_pipe_large_left = sheet.crop(5, 0);
        sewer_pipe_large_middle = sheet.crop(6, 0);
        sewer_pipe_large_right = sheet.crop(7, 0);
        sewer_pipe_large_down = sheet.crop(5, 1);
        sewer_pipe_large_leftright = sheet.crop(5, 2);
        sewer_pipe_large_rightleft = sheet.crop(7, 2);

        for(int i  = 0; i < 14; i++)
        {
            for(int j = 0; j < 8; j++)
            {
                int index = i*8 + j;

                if(tiles[index] == null)
                {
                    tiles[index] = new Tile(sheet.crop(j, i), index);
                }
            }
        }


        SpriteSheet life = new SpriteSheet(ImageLoader.LoadImage("/textures/HealthUI.png"),11,11);

        health_bar = new BufferedImage[7][3];

        for(int i = 0; i<health_bar.length;i++)
        {
            for(int j  = 0; j<health_bar[i].length;j++)
            {
                health_bar[i][j] = life.crop(j,i);
            }
        }

        // Animatiile jucatorului:
        player_animations_right = new BufferedImage[15][8];
        player_animations_left = new BufferedImage[15][8];

        SpriteSheet playerRight = new SpriteSheet(ImageLoader.LoadImage("/textures/Cat Adventure Right.png"));

        for(int i = 0; i <  player_animations_right.length; i++)
        {
            for(int j = 0; j < player_animations_right[i].length; j++)
            {
                player_animations_right[i][j] = playerRight.crop(j,i);
            }
        }

        SpriteSheet playerLeft = new SpriteSheet(ImageLoader.LoadImage("/textures/Cat Adventure Left.png"));

        int ii = 0, jj = 0;
        for(int i = 0; i < player_animations_left.length; i++)
        {
            for(int j = player_animations_left[i].length-1; j >= 0 ; j--)
            {
                player_animations_left[i][jj] = playerLeft.crop(j,i);
                ++jj;
            }
            jj = 0;
        }

        enemy_animations_right = new BufferedImage[4][8];

        SpriteSheet enemyRight = new SpriteSheet(ImageLoader.LoadImage("/textures/Enemy.png"),64,64);

        for(int i = 0; i <  enemy_animations_right.length; i++)
        {
            for(int j = 0; j < enemy_animations_right[i].length; j++)
            {
                enemy_animations_right[i][j] = enemyRight.crop(j,i);
            }
        }
    }
}
