package PaooGame.UserInterface;

import PaooGame.Graphics.Assets;

import java.awt.*;

public class RestartButton extends ButtonInterface {

    public RestartButton(int x, int y) {
        this.x = x;
        this.y = y;
        buttonImageNormal = Assets.restart_button_normal;
        buttonImagePressed = Assets.resume_button_pressed;
    }
    @Override
    public void action(){};
}
