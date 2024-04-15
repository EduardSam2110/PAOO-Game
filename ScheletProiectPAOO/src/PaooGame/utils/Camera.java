package PaooGame.utils;

import PaooGame.entities.Player;

import java.awt.*;

public class Camera {
    public static int xCamera = 0;
    public static int CameraWidth = 800;
    public static int MinBorder = 0;
    public static int MaxBorder = 1280*2 - 279;
    public static int LeftBorder = 200;
    public static int xCameraPos = 200;

     public static void Draw(Graphics g, Player p)
    {
        g.drawRect(LeftBorder,200,CameraWidth,500); // creste / scande odata cu camera
    }

    // Functie de update a camerei. Verifica daca player-ul se afla intre Min si Max Border si dupa verifica
    // daca, pe dimensiunea unui ecran de 1280 x 720, se afla intre xCameraPos si xCameraPos + CameraWidth
    // atunci cand player-ul atinge aceste doua margini din urma, camera incepe sa se miste, xCamera modificandu-se.
    public static void Update(Player p)
    {
        int xLeftWall = (int) p.getHitBox().x;
        int xRightWall = xLeftWall + (int) p.getHitBox().width;
        int speed = p.GetPlayerSpeed();

        if(xLeftWall > MinBorder && xRightWall < MaxBorder) {
            if(p.IsMoving())
            {
                if((xLeftWall <= xCameraPos) && (xLeftWall > LeftBorder) && p.isLeft()) {
                    xCamera -= speed;
                    xCameraPos = xLeftWall - speed;
                }

                if((xRightWall >= xCameraPos+CameraWidth) && p.isRight()) {
                    xCamera += speed;
                    xCameraPos = xRightWall - CameraWidth + speed;
                }
            }
        }
    }

    public static void resetCamera()
    {
        xCamera = 0;
        xCameraPos = 200;
    }
}
