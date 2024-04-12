package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class Sewer_Pipe_Large_Right extends Tile{
    public Sewer_Pipe_Large_Right(int id)
    {
        super(Assets.sewer_pipe_large_right,id);
    }

    @Override
    public boolean IsSolid() {
        return true;
    }
}
