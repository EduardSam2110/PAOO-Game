package PaooGame.Tiles;

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
    public static Tile solidBlock1        = new SolidBlock1(35);
    public static Tile solidBlock2        = new SolidBlock2(36);
    public static Tile solidBlock3        = new SolidBlock3(37);


    public static Tile sewer_hole1       = new Sewer_Hole1(32);
    public static Tile sewer_hole2      = new Sewer_Hole2(33);

    public static Tile sewer_pipe_large_left = new Sewer_Pipe_Large_Left(5);
    public static Tile sewer_pipe_large_middle = new Sewer_Pipe_Large_Middle(6);
    public static Tile sewer_pipe_large_right = new Sewer_Pipe_Large_Right(7);
    public static Tile sewer_pipe_large_down = new Sewer_Pipe_Large_Down(13);
    public static Tile sewer_pipe_large_leftright = new Sewer_Pipe_Large_LeftRight(21);
    public static Tile sewer_pipe_large_rightleft = new Sewer_Pipe_Large_RightLeft(23);


    public int TILE_WIDTH = 32;                       /*!< Latimea unei dale.*/
    public int TILE_HEIGHT = 32;                       /*!< Inaltimea unei dale.*/

    public static final int TILE_SIZE = 32;
    protected BufferedImage img;                                    /*!< Imaginea aferenta tipului de dala.*/
    protected final int id;                                         /*!< Id-ul unic aferent tipului de dala.*/

    /*! \fn public Tile(BufferedImage texture, int id)
        \brief Constructorul aferent clasei.

        \param image Imaginea corespunzatoare dalei.
        \param id Id-ul dalei.
     */
    public Tile(BufferedImage image, int idd)
    {
        img = image;
        id = idd;
        tiles[id] = this;
    }

    public Tile(BufferedImage image, int idd, int new_size)
    {
        img = image;
        id = idd;
        TILE_WIDTH = TILE_HEIGHT = new_size;
        tiles[id] = this;
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
    public boolean IsSolid()
    {
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
