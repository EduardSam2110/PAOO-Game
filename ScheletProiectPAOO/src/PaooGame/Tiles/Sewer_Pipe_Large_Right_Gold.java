package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class Sewer_Pipe_Large_Right_Gold extends Tile{
    public Sewer_Pipe_Large_Right_Gold(int id)
    {
        super(Assets.sewer_pipe_large_right_gold,id);
    }

    @Override
    public boolean IsSolid() {
        return true;
    }
}
