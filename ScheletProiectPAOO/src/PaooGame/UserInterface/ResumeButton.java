package PaooGame.UserInterface;

import PaooGame.Graphics.Assets;

import java.awt.*;

public class ResumeButton extends ButtonInterface{

    public ResumeButton(int x, int y){
        this.x = x;
        this.y = y;
        buttonImageNormal = Assets.resume_button_normal;
        buttonImagePressed = Assets.resume_button_pressed;
    }

    @Override
    public void action(){};
}
