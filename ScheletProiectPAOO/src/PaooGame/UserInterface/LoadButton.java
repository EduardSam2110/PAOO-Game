package PaooGame.UserInterface;

import PaooGame.Graphics.Assets;

import java.awt.*;

public class LoadButton extends ButtonInterface{

    public LoadButton(int x, int y){
        this.x = x;
        this.y = y;
        buttonImageNormal = Assets.load_button_normal;
        buttonImagePressed = Assets.load_button_pressed;
    }

    @Override
    public void action(){};
}
