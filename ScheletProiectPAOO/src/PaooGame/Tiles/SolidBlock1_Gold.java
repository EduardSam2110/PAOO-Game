package PaooGame.Tiles;


import PaooGame.Graphics.Assets;

public class SolidBlock1_Gold extends Tile {
    public SolidBlock1_Gold(int id)
    {
        super(Assets.solidBlock1_gold,id);
    }

    @Override
    public boolean IsSolid() {
        return true;
    }
}
