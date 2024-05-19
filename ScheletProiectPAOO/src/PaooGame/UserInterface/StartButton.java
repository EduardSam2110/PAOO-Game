package PaooGame.UserInterface;

import PaooGame.Graphics.Assets;

import java.awt.*;

public class StartButton extends ButtonInterface {

    @Override
    public void draw(Graphics g){
        g.drawImage(Assets.start_button_normal,500,500,128,128,null);
    }

    @Override
    public void action(){};
}
