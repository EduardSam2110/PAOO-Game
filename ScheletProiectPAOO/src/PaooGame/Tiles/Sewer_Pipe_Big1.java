package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class Sewer_Pipe_Big1 extends Tile{
    public Sewer_Pipe_Big1(int id)
    {
        super(Assets.sewer_pipe_big1,id);
    }

    @Override
    public boolean IsSolid() {
        return true;
    }
}
