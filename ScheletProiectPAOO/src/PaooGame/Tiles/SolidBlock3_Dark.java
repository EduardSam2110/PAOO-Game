package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class SolidBlock3_Dark extends Tile{
    public SolidBlock3_Dark(int id)
    {
        super(Assets.solidBlock3_dark,id);
    }

    @Override
    public boolean IsSolid() {
        return true;
    }
}
