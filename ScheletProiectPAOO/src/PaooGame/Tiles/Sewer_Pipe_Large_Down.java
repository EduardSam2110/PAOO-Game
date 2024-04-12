package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class Sewer_Pipe_Large_Down extends Tile{
    public Sewer_Pipe_Large_Down(int id)
    {
        super(Assets.sewer_pipe_large_down,id);
    }

    @Override
    public boolean IsSolid() {
        return true;
    }
}
