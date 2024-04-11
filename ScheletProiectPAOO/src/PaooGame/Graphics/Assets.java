package PaooGame.Graphics;

import utils.LoadSave;

import java.awt.image.BufferedImage;

/*! \class public class Assets
    \brief Clasa incarca fiecare element grafic necesar jocului.

    Game assets include tot ce este folosit intr-un joc: imagini, sunete, harti etc.
 */
public class Assets
{
        /// Referinte catre elementele grafice (dale) utilizate in joc.
    public static BufferedImage[][] player_animations_right;
    public static BufferedImage[][] player_animations_left;

    public static BufferedImage solidBlock1;
    public static BufferedImage solidBlock2;
    public static BufferedImage soil;
    public static BufferedImage grass;
    public static BufferedImage mountain;
    public static BufferedImage townGrass;
    public static BufferedImage sewer_pipe_big1;
    public static BufferedImage townSoil;
    public static BufferedImage rockUp;
    public static BufferedImage rockDown;
    public static BufferedImage rockLeft;
    public static BufferedImage rockRight;
    public static BufferedImage tree;

    /*! \fn public static void Init()
        \brief Functia initializaza referintele catre elementele grafice utilizate.

        Aceasta functie poate fi rescrisa astfel incat elementele grafice incarcate/utilizate
        sa fie parametrizate. Din acest motiv referintele nu sunt finale.
     */
    public static void Init()
    {
            /// Se creaza temporar un obiect SpriteSheet initializat prin intermediul clasei ImageLoader
        SpriteSheet sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/pipes.png"),32,32);
//
//            /// Se obtin subimaginile corespunzatoare elementelor necesare.
        solidBlock1 = sheet.crop(3, 4);
        solidBlock2 = sheet.crop(4, 4);


        sewer_pipe_big1 = sheet.crop(5, 1);
//
//        townGrass = sheet.crop(0, 1);
//        townGrassDestroyed = sheet.crop(1, 1);
//        townSoil = sheet.crop(2, 1);
//        tree = sheet.crop(3, 1);
//        playerLeft = sheet.crop(0, 2);
//        playerRight = sheet.crop(1, 2);
//        rockUp = sheet.crop(2, 2);
//        rockDown = sheet.crop(3, 2);
//        rockLeft = sheet.crop(0, 3);
//        rockRight = sheet.crop(1, 3);

        player_animations_right = new BufferedImage[15][8];
        player_animations_left = new BufferedImage[15][8];

        SpriteSheet img1 = new SpriteSheet(ImageLoader.LoadImage("/textures/Cat Adventure Right.png"));

        for(int i = 0; i <  player_animations_right.length; i++)
        {
            for(int j = 0; j < player_animations_right[i].length; j++)
            {
                player_animations_right[i][j] = img1.crop(j,i);
            }
        }

        SpriteSheet img2 = new SpriteSheet(ImageLoader.LoadImage("/textures/Cat Adventure Left.png"));

        int ii = 0, jj = 0;
        for(int i = 0; i < player_animations_left.length; i++)
        {
            for(int j = player_animations_left[i].length-1; j >= 0 ; j--)
            {
                player_animations_left[i][jj] = img2.crop(j,i);
                ++jj;
            }
            jj = 0;
        }
    }
}
