package utils;

import entities.Player;

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
        int x = (int) p.getHitBox().x;
//        System.out.println(x);
        g.drawRect(LeftBorder,200,CameraWidth,500); // creste / scande odata cu camera

    }

    public static void Update(Player p)
    {
        int xLeftWall = (int) p.getHitBox().x;
        int xRightWall = xLeftWall + (int) p.getHitBox().width;
        int speed = p.GetPlayerSpeed();
//        System.out.println(xCameraPos + "      " + (xCameraPos + CameraWidth));
//        System.out.println(x + "       " + xCameraPos + "    "+ (x + (int) p.getHitBox().width) + "   "  + (xCameraPos + CameraWidth));
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
