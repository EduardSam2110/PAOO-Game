package PaooGame.UserInterface;

import PaooGame.Game;
import PaooGame.GameWindow.GameWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class ButtonInterface {
    protected BufferedImage buttonImageNormal;
    protected BufferedImage buttonImagePressed;
    protected int x,y;
    protected static int width = 128, height = 64;
    protected boolean isPressed = false;
    protected boolean isHovered = false;
    protected static JFrame component;

    public void draw(Graphics g, Game game){
        hovering(game);

        if(isHovered)
            g.drawImage(buttonImagePressed,x,y,width,height,null);
        else
            g.drawImage(buttonImageNormal,x,y,width,height,null);
    }

    public void action(){};

    public void hovering(Game game)
    {
        component = game.getWnd().getWndFrame();

        Point mousePoint = MouseInfo.getPointerInfo().getLocation();

        SwingUtilities.convertPointFromScreen(mousePoint, component);

        int mouseX = mousePoint.x;
        int mouseY = mousePoint.y;

//        System.out.println(mouseX + "  " + mouseY);

        if(mouseX > this.x && mouseX < this.x + this.width && mouseY > this.y && mouseY < this.y + this.height) {
            isHovered = true;
        }
        else
            isHovered = false;
    }
}
