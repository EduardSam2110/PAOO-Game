package PaooGame.Tiles;

import PaooGame.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

/*! \class public class Tile
    \brief Retine toate dalele intr-un vector si ofera posibilitatea regasirii dupa un id.
 */
public class Tile
{
    private static final int NO_TILES   = 112;
    public static Tile[] tiles          = new Tile[NO_TILES];       /*!< Vector de referinte de tipuri de dale.*/

        /// De remarcat ca urmatoarele dale sunt statice si publice. Acest lucru imi permite sa le am incarcate
        /// o singura data in memorie
//    public static Tile grassTile        = new GrassTile(0);     /*!< Dala de tip iarba*/
//    public static Tile mountainTile     = new MountainTile(1);  /*!< Dala de tip munte/piatra*/
//    public static Tile waterTile        = new WaterTile(2);     /*!< Dala de tip apa*/
//    public static Tile treeTile         = new TreeTile(3);      /*!< Dala de tip copac*/
//    public static Tile soilTile         = new SoilTile(4);      /*!< Dala de tip sol/pamant*/


    public static final int TILE_WIDTH  = 32;                       /*!< Latimea unei dale.*/
    public static final int TILE_HEIGHT = 32;                       /*!< Inaltimea unei dale.*/

    protected BufferedImage img;                                    /*!< Imaginea aferenta tipului de dala.*/
    protected int id;                                         /*!< Id-ul unic aferent tipului de dala.*/

    /*! \fn public Tile(BufferedImage texture, int id)
        \brief Constructorul aferent clasei.

        \param image Imaginea corespunzatoare dalei.
        \param id Id-ul dalei.
     */
    public Tile(BufferedImage image, int idd)
    {
        img = image;
        id = idd;

        //tiles[id] = this;
    }

    /*! \fn public void Update()
        \brief Actualizeaza proprietatile dalei.
     */
    public void Update()
    {

    }

    /*! \fn public void Draw(Graphics g, int x, int y)
        \brief Deseneaza in fereastra dala.

        \param g Contextul grafic in care sa se realizeze desenarea
        \param x Coordonata x in cadrul ferestrei unde sa fie desenata dala
        \param y Coordonata y in cadrul ferestrei unde sa fie desenata dala
     */
    public void Draw(Graphics g, int x, int y)
    {
            /// Desenare dala
        g.drawImage(img, x, y, TILE_WIDTH, TILE_HEIGHT, null);
    }

    /*! \fn public boolean IsSolid()
        \brief Returneaza proprietatea de dala solida (supusa coliziunilor) sau nu.
     */
    public static boolean IsSolid(float x, float y, int[][] map)
    {
        if(x < 0 || x >= Game.GAME_WIDTH)
            return true;
        if(y < 0 || y >= Game.GAME_HEIGHT)
            return true;

        float xIndex = x / Game.TILE_SIZE;
        float yIndex = y / Game.TILE_SIZE;

        int value = map[(int) yIndex][(int) xIndex];

        if(value >= 112 || value < 0 || value == 61)
            return true;

        return false;
    }

    public static boolean CanMoveHere(float x, float y, int width, int height, int[][] map)
    {
        if(!IsSolid(x,y,map))
            if(!IsSolid(x+width,y+height,map))
                if(!IsSolid(x+width,y,map))
                    if(!IsSolid(x,y+height,map))
                        return true;
        return false;
    }

    /*! \fn public int GetId()
        \brief Returneaza id-ul dalei.
     */
    public int GetId()
    {
        return id;
    }
}
