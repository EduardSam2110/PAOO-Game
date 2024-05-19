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
    protected int mouseXLocation, mouseYLocation;


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

        mouseXLocation = mousePoint.x;
        mouseYLocation = mousePoint.y;

//        System.out.println(mouseX + "  " + mouseY);

        if(mouseXLocation >= this.x && mouseXLocation <= this.x + this.width && mouseYLocation >= this.y && mouseYLocation <= this.y + this.height)
            isHovered = true;
        else
            isHovered = false;
    }

    public void getClick(int clickX, int clickY){
        if(clickX > this.x && clickX < this.x + this.width && clickY > this.y && clickY < this.y + this.height && isHovered)
            isPressed = true;
        else
            isPressed = false;
    }
}
