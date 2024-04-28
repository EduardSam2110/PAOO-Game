package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class SolidBlock3_Gold extends Tile {
    public SolidBlock3_Gold(int id)
    {
        super(Assets.solidBlock2_gold,id);
    }

    @Override
    public boolean IsSolid() {
        return true;
    }
}
