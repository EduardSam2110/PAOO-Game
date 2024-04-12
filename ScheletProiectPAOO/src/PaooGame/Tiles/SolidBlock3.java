package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class SolidBlock3 extends Tile{
    public SolidBlock3(int id)
    {
        super(Assets.solidBlock3,id);
    }

    @Override
    public boolean IsSolid() {
        return true;
    }
}
