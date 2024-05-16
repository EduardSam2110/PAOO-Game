package PaooGame.Tiles;

import PaooGame.Graphics.Assets;
import PaooGame.entities.Player;


public class WaterTile extends Tile{

    public WaterTile(int idd) {
        super(Assets.water, idd);
    }

    @Override
    public boolean IsSolid() {
        return false;
    }
}
