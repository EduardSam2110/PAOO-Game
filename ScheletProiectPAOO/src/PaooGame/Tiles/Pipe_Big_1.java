package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class Pipe_Big_1 extends Tile{
    public Pipe_Big_1(int id)
    {
        super(Assets.sewer_pipe_big1,id);
    }

    @Override
    public boolean IsSolid() {
        return false;
    }
}
