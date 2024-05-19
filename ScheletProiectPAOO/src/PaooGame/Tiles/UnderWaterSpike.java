package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

import java.awt.image.BufferedImage;

public class UnderWaterSpike extends Tile{

    public UnderWaterSpike(int idd, int new_size) {
        super(Assets.underWaterSpike, idd, new_size);
    }

    @Override
    public boolean IsSolid() {
        return true;
    }
}
