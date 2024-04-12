package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class Sewer_Hole1 extends Tile{
    public Sewer_Hole1(int id)
    {
        super(Assets.sewer_hole1,id,64);
    }

    @Override
    public boolean IsSolid() {
        return false;
    }
}
