package levels;

import PaooGame.Game;
import PaooGame.Graphics.ImageLoader;
import PaooGame.Graphics.SpriteSheet;
import PaooGame.Tiles.Tile;
import utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;
import static PaooGame.Game.*;

import static utils.LoadSave.LEVEL_ATLAS;

public class LevelManager {
    private Game game;
    private BufferedImage[] levelSprite;
    private Level levelOne;
    public LevelManager(Game game) {
        this.game = game;
        //levelSprite = LoadSave.GetSpriteAtlas(LEVEL_ATLAS);
        importOutsideSprites();
        levelOne = new Level(LoadSave.GetLevelData());
    }

    private void importOutsideSprites() {
//        levelSprite = new BufferedImage[112];
//
//        BufferedImage temp = LoadSave.GetSpriteAtlas(LEVEL_ATLAS);
//        for(int j = 0; j < 8; j++)
//            for(int i = 0;i < 14; i++)
//            {
//                int index = j*14 + i;
//                levelSprite[index] = temp.getSubimage(j*32, i*32, 32 ,32);
//            }
    }

    public void draw(Graphics g)
    {
//        for(int j = 0; j < Game.TILES_IN_HEIGHT ; j++)
//            for(int i = 0; i< Game.TILES_IN_WIDTH; i++)
//            {
//                int index = levelOne.getSpriteIndex(i,j);
//                g.drawImage(levelSprite[index],i* TILE_SIZE, j* TILE_SIZE, TILE_SIZE, TILE_SIZE, null);
//            }
    }

    public void update() {

    }
}
