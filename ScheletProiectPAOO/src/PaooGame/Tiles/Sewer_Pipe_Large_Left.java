package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class Sewer_Pipe_Large_Left extends Tile{
    public Sewer_Pipe_Large_Left(int id)
    {
        super(Assets.sewer_pipe_large_left,id);
    }

    @Override
    public boolean IsSolid() {
        return true;
    }
}
