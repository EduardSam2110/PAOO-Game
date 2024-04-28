package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class SolidBlock2_Dark extends Tile {
    public SolidBlock2_Dark(int id)
    {
        super(Assets.solidBlock2_dark,id);
    }

    @Override
    public boolean IsSolid() {
        return true;
    }
}
