package utils;
import PaooGame.Game;
import PaooGame.Tiles.*;

import javax.xml.crypto.dsig.keyinfo.X509IssuerSerial;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import static PaooGame.Tiles.Tile.*;

public class HelpMethods {
    public static boolean CanMoveHere(float x, float y, float width, float height, int[][] lvlData) {

        if(!IsSolid(x,y,lvlData))
                if(!IsSolid(x+width,y+height,lvlData))
                    if(!IsSolid(x+width,y,lvlData))
                        if(!IsSolid(x,y+height,lvlData))
                            return true;
        return false;
    }


    private static boolean IsSolid(float x, float y, int[][] levelData)
    {
        if(x < 0 || x >= Game.GAME_WIDTH)
            return true;

        if(y < 0 || y >= Game.GAME_HEIGHT)
            return true;

        float xIndex = x / TILE_HEIGHT;
        float yIndex = y / TILE_HEIGHT;

        int value = levelData[(int)yIndex][(int)xIndex];

        if(value >= 112 || value < 0 || value != 99)
                return true;

        return false;
    }

    public static float GetEntityXPosNextToWall(Rectangle2D.Float hitbox, float xSpeed)
    {
        int currentTile = (int) (hitbox.x/TILE_SIZE); // se atinge ori de tile in dreapta ori in stanga
        if(xSpeed > 0 )
        {
            //Right
            int tileXPos = currentTile * TILE_SIZE;
            int xOffset = (int) (TILE_SIZE - hitbox.width); // tile ul e aici, playerul e la offset pixeli de tile
            return tileXPos + xOffset - 1;// -1 pentru ca rama hitboxului sa nu fie pe rama tileului, ci lipita de ea
        }
        else {
            //Left
            return currentTile*TILE_SIZE;
        }
    }

    public static float GetEntityYPosUnderRoofFloor(Rectangle2D.Float hitbox, float airSpeed)
    {
        int currentTile = (int) (hitbox.y/TILE_SIZE); // se atinge ori de tile in dreapta ori in stanga

        if(airSpeed > 0) {
            //Falling - touching floor
            int tileYPos = currentTile * TILE_SIZE;
            int yOffset = (int) (TILE_SIZE - hitbox.height);
            return tileYPos + yOffset - 1;
        }else{
            //Jumping
            return currentTile*TILE_SIZE;
        }
    }

    public static boolean IsEntityOnFloor(Rectangle2D.Float hitbox, int[][] levelData)
    {
        // check the piel below bottomleft and bottomright corner\
        // adica sa cada cand nu are nimic sub
        if(!IsSolid(hitbox.x,hitbox.y+hitbox.height+1,levelData))
            if(!IsSolid(hitbox.x + hitbox.width, hitbox.y + hitbox.height+1, levelData)) // adaugam +1 ca l-am scazut in celelalte metode
                return false;

        return true;
    }
}
