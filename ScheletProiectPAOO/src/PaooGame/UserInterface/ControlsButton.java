package PaooGame.UserInterface;

import PaooGame.Graphics.Assets;

import java.awt.*;

public class ControlsButton extends ButtonInterface{

    public ControlsButton(int x, int y){
        this.x = x;
        this.y = y;
        buttonImageNormal = Assets.controls_button_normal;
        buttonImagePressed = Assets.controls_button_pressed;
    }

    @Override
    public void action(){};
}
