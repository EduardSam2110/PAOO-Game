package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class Sewer_Pipe_Large_RightLeft_Dark extends Tile{
    public Sewer_Pipe_Large_RightLeft_Dark(int id)
    {
        super(Assets.sewer_pipe_large_rightleft_dark,id);
    }

    @Override
    public boolean IsSolid() {
        return true;
    }
}
