package PaooGame.UserInterface;

import PaooGame.Game;
import PaooGame.Graphics.Assets;

import java.awt.*;

public class StartButton extends ButtonInterface {

    public StartButton(int x, int y) {
        this.x = x;
        this.y = y;
        buttonImageNormal = Assets.start_button_normal;
        buttonImagePressed = Assets.start_button_pressed;
    }

    @Override
    public void action(){
        if(isPressed) {
            Game.START_PRESSED = true;
            Game.InGamePause_PRESSED = false;
        }
        isPressed = false;
    };

}
