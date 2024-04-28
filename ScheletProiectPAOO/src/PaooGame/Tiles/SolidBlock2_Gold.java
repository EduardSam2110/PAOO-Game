package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class SolidBlock2_Gold extends Tile {
    public SolidBlock2_Gold(int id)
    {
        super(Assets.solidBlock2_gold,id);
    }

    @Override
    public boolean IsSolid() {
        return true;
    }
}
