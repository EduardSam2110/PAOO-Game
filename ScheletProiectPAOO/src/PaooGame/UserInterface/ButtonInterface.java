package PaooGame.UserInterface;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class ButtonInterface {
    protected BufferedImage buttonImageNormal;
    protected BufferedImage buttonImagePressed;
    protected int x,y;

    public void draw(Graphics g){}
    public void action(){};
}
