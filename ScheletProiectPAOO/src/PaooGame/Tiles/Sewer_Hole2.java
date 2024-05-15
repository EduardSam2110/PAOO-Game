package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

import java.awt.*;

public class Sewer_Hole2 extends Tile{
    public Sewer_Hole2(int id)
    {
        super(Assets.sewer_hole2,id,64);
    }

    @Override
    public boolean IsSolid() {
        return false;
    }

    @Override
    public void Update() {

    }
}
