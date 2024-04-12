package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class Sewer_Pipe_Large_LeftRight extends Tile{
    public Sewer_Pipe_Large_LeftRight(int id)
    {
        super(Assets.sewer_pipe_large_leftright,id);
    }

    @Override
    public boolean IsSolid() {
        return true;
    }
}
