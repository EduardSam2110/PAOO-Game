package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class Sewer_Pipe_Large_RightLeft extends Tile{
    public Sewer_Pipe_Large_RightLeft(int id)
    {
        super(Assets.sewer_pipe_large_rightleft,id);
    }

    @Override
    public boolean IsSolid() {
        return true;
    }
}
