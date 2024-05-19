package PaooGame.UserInterface;

import PaooGame.Game;
import PaooGame.Graphics.Assets;

import java.awt.*;

public class RestartButton extends ButtonInterface {

    public RestartButton(int x, int y) {
        this.x = x;
        this.y = y;
        buttonImageNormal = Assets.restart_button_normal;
        buttonImagePressed = Assets.restart_button_pressed;
    }
    @Override
    public void action(){
        if(isPressed) {
            Game.RESET_PRESSED = true;
            Game.InGamePause_PRESSED = false;
        }
        isPressed = false;
    };
}
