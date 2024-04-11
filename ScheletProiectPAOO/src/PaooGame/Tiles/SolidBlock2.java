package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class SolidBlock2 extends Tile {
    public SolidBlock2(int id)
    {
        super(Assets.solidBlock2,id);
    }

    @Override
    public boolean IsSolid() {
        return true;
    }
}
