package PaooGame.UserInterface;

import PaooGame.Game;
import PaooGame.Graphics.Assets;

import java.awt.*;

public class ExitButton extends ButtonInterface{

    public ExitButton(int x, int y){
        this.x = x;
        this.y = y;
        buttonImageNormal = Assets.exit_button_normal;
        buttonImagePressed = Assets.exit_button_pressed;
    }

    @Override
    public void action(){
        if(isPressed)
            Game.EXIT_PRESSED = true;
        isPressed = false;
    };
}
