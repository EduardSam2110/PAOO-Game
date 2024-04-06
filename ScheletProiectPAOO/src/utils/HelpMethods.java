package utils;
import PaooGame.Game;
import PaooGame.Tiles.*;

import javax.xml.crypto.dsig.keyinfo.X509IssuerSerial;

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
}
