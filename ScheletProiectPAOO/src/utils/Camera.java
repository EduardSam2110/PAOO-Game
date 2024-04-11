package utils;

import PaooGame.Game;
import entities.Player;

import java.awt.*;

import static PaooGame.Tiles.LevelConstructor.map;
import static PaooGame.Tiles.Tile.TILE_SIZE;
import static entities.Player.xSpeed;

public class Camera {
    public static int xCamera = 0;
    public static int MinBorder = 0;
    public static int MaxBorder = 3690;
    public static int LeftBorder = 128;
    public static int xCameraPos = 128;
    public static int CameraWidth = 1000;

     public static void Draw(Graphics g, Player p)
    {
        int x = (int) p.getHitBox().x;
//        System.out.println(x);
        g.drawRect(128,200,CameraWidth,500); // creste / scande odata cu camera

    }

    public static void Update(Player p)
    {
        int xLeftWall = (int) p.getHitBox().x;
        int xRightWall = xLeftWall + (int) p.getHitBox().width;
//        System.out.println(x + "       " + xCameraPos + "    "+ (x + (int) p.getHitBox().width) + "   "  + (xCameraPos + CameraWidth));
        if(xLeftWall > MinBorder && xRightWall < MaxBorder) {
            if(p.IsMoving())
            {
                if((xLeftWall <= xCameraPos) && (xLeftWall >= LeftBorder )&& p.isLeft()) {
                    xCamera -= p.GetPlayerSpeed();
                    xCameraPos = xLeftWall - p.GetPlayerSpeed();
                }


                if((xRightWall >= xCameraPos+CameraWidth)  && p.isRight()) {
                    xCamera += p.GetPlayerSpeed();
                    xCameraPos = xRightWall - CameraWidth + p.GetPlayerSpeed();
                }
            }
        }

//        if((x < xCameraPos) || (x > xCameraPos + CameraWidth)) {
//            if (p.IsMoving()) {
////                    if (p.isLeft())
////                        xCamera -= p.GetPlayerSpeed();
//                if (p.isRight())
//                    xCamera += p.GetPlayerSpeed();
//            }
//        }
    }
}
