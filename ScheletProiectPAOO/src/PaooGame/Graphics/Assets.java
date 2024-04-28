package PaooGame.Graphics;

import PaooGame.Tiles.Tile;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;

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
    public static BufferedImage[][] enemy_animations_left;

    public static BufferedImage solidBlock1_dark;
    public static BufferedImage solidBlock2_dark;
    public static BufferedImage solidBlock3_dark;

    public static BufferedImage solidBlock1_gold;
    public static BufferedImage solidBlock2_gold;
    public static BufferedImage solidBlock3_gold;

    public static BufferedImage sewer_hole1;
    public static BufferedImage sewer_hole2;

    public static BufferedImage sewer_pipe_large_left_dark;
    public static BufferedImage sewer_pipe_large_middle_dark;
    public static BufferedImage sewer_pipe_large_right_dark;
    public static BufferedImage sewer_pipe_large_down_dark;
    public static BufferedImage sewer_pipe_large_leftright_dark;
    public static BufferedImage sewer_pipe_large_rightleft_dark;

    public static BufferedImage sewer_pipe_large_left_gold;
    public static BufferedImage sewer_pipe_large_middle_gold;
    public static BufferedImage sewer_pipe_large_right_gold;
    public static BufferedImage sewer_pipe_large_down_gold;
    public static BufferedImage sewer_pipe_large_leftright_gold;
    public static BufferedImage sewer_pipe_large_rightleft_gold;

    public static BufferedImage[] health_bar;
    public static BufferedImage[] score;

    public static BufferedImage spikeUp;

    public static int map_lvl1[][];
    public static BufferedImage background_lvl1 = ImageLoader.LoadImage("/textures/level1.png");

    public static int map_lvl2[][];
    public static BufferedImage background_lvl2 = ImageLoader.LoadImage("/textures/level2.png");

    public static int map_lvl3[][];
    public static BufferedImage background_lvl3 = ImageLoader.LoadImage("/textures/level1.png");


    public static BufferedImage game_over = ImageLoader.LoadImage("/textures/game_over.jpg"); // temporara, va fi schimbata
    public static BufferedImage start_game = ImageLoader.LoadImage("/textures/start_game.png");

    /*! \fn public static void Init()
        \brief Functia initializaza referintele catre elementele grafice utilizate.

        Aceasta functie poate fi rescrisa astfel incat elementele grafice incarcate/utilizate
        sa fie parametrizate. Din acest motiv referintele nu sunt finale.
     */
    public static void Init()
    {
        // Tile-uri pentru niveluri

        SpriteSheet sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/pipes.png"),32,32);

        solidBlock1_dark = sheet.crop(3, 4);
        solidBlock2_dark = sheet.crop(4, 4);
        solidBlock3_dark = sheet.crop(5,4);

        solidBlock1_gold = sheet.crop(3, 11);
        solidBlock2_gold = sheet.crop(4, 11);
        solidBlock3_gold = sheet.crop(5,11);

        sewer_hole1 = sheet.crop(0,4);
        sewer_hole2 = sheet.crop(1,4);

        sewer_pipe_large_left_dark = sheet.crop(5, 0);
        sewer_pipe_large_middle_dark = sheet.crop(6, 0);
        sewer_pipe_large_right_dark = sheet.crop(7, 0);
        sewer_pipe_large_down_dark = sheet.crop(5, 1);
        sewer_pipe_large_leftright_dark = sheet.crop(5, 2);
        sewer_pipe_large_rightleft_dark = sheet.crop(7, 2);

        sewer_pipe_large_left_gold = sheet.crop(5, 7);
        sewer_pipe_large_middle_gold = sheet.crop(6, 7);
        sewer_pipe_large_right_gold = sheet.crop(7, 7);
        sewer_pipe_large_down_gold = sheet.crop(5, 8);
        sewer_pipe_large_leftright_gold = sheet.crop(5, 9);
        sewer_pipe_large_rightleft_gold = sheet.crop(7, 9);

        SpriteSheet spikes = new SpriteSheet(ImageLoader.LoadImage("/textures/spike.png"),16,16);
        spikeUp = spikes.crop(0,0);

        // animatiile pentru viata jucatorului

        SpriteSheet life = new SpriteSheet(ImageLoader.LoadImage("/textures/HealthUI.png"),33,11);

        health_bar = new BufferedImage[4];

        for(int i = 0; i < health_bar.length;i++)
        {
            health_bar[i] = life.crop(0,i*2);
        }

        // spritesheet pentru scor

        SpriteSheet score_spritesheet = new SpriteSheet(ImageLoader.LoadImage("/textures/score.png"),7,10);
        score = new BufferedImage[10];

        for(int i = 0; i < score.length; i++)
        {
            score[i] = score_spritesheet.crop(i,0);
        }

        // Animatiile jucatorului:
        player_animations_right = new BufferedImage[15][8];
        player_animations_left = new BufferedImage[15][8];

        SpriteSheet playerRight = new SpriteSheet(ImageLoader.LoadImage("/textures/CatRight.png"));

        for(int i = 0; i <  player_animations_right.length; i++)
        {
            for(int j = 0; j < player_animations_right[i].length; j++)
            {
                player_animations_right[i][j] = playerRight.crop(j,i);
            }
        }

        SpriteSheet playerLeft = new SpriteSheet(ImageLoader.LoadImage("/textures/CatLeft.png"));

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

        // animatiile inamicului

        enemy_animations_right = new BufferedImage[4][8];
        enemy_animations_left = new BufferedImage[4][8];

        SpriteSheet enemyRight = new SpriteSheet(ImageLoader.LoadImage("/textures/EnemyRight.png"),64,64);

        for(int i = 0; i <  enemy_animations_right.length; i++)
        {
            for(int j = 0; j < enemy_animations_right[i].length; j++)
            {
                enemy_animations_right[i][j] = enemyRight.crop(j,i);
            }
        }

        SpriteSheet enemyLeft = new SpriteSheet(ImageLoader.LoadImage("/textures/EnemyLeft.png"),64,64);

        ii = 0;
        jj = 0;
        for(int i = 0; i < enemy_animations_left.length; i++)
        {
            for(int j = enemy_animations_left[i].length-1; j >= 0 ; j--)
            {
                enemy_animations_left[i][jj] = enemyLeft.crop(j,i);
                ++jj;
            }
            jj = 0;
        }

        // harta nivelului 1

        map_lvl1 = new int[23][80];
        levelReader(map_lvl1,"lvl1.csv");

        map_lvl2 = new int[23][80];
        levelReader(map_lvl2,"lvl2.csv");

        map_lvl3 = new int[23][80];
        levelReader(map_lvl3,"lvl3.csv");

    }

    public static void LoadBackgroudTiles()
    {
        // Functia incarca restul de Tile-uri care tin doar de fundalul si esteticul unui nivel, nu au alta functie

        SpriteSheet sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/pipes.png"),32,32);

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
    }

    private static void levelReader(int[][] map, String path)
    {
        // functia citeste nivelul dintr-un fisier CSV
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            int j = 0;
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                for (int i = 0; i < 80; i++) {
                    map[j][i] = Integer.parseInt(values[i]);
                }
                j++;
            }
        } catch (Exception e) {e.printStackTrace();}
    }
}
