package PaooGame.UserInterface;

import PaooGame.Graphics.Assets;

import java.awt.*;

public class StartButton extends ButtonInterface {

    public StartButton() {
        buttonImageNormal = Assets.start_button_normal;
        buttonImagePressed = Assets.start_button_pressed;
    }
    @Override
    public void draw(Graphics g){
        g.drawImage(Assets.start_button_normal,500,500,128,128,null);
        System.out.println("merge?");
    }

    @Override
    public void action(){};
}
