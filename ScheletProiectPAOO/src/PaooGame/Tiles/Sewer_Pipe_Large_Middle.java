package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class Sewer_Pipe_Large_Middle extends Tile{
    public Sewer_Pipe_Large_Middle(int id)
    {
        super(Assets.sewer_pipe_large_middle,id);
    }

    @Override
    public boolean IsSolid() {
        return true;
    }
}
