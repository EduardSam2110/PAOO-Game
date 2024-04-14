package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class SpikeUp extends Tile{
    public SpikeUp(int id)
    {
        super(Assets.spikeUp,id);
    }

    @Override
    public boolean IsSolid() {
        return true;
    }
}
