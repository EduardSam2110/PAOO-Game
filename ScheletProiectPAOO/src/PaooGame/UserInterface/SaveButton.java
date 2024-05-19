package PaooGame.UserInterface;

import PaooGame.Game;
import PaooGame.Graphics.Assets;

import java.awt.*;

public class SaveButton extends ButtonInterface{

    public SaveButton(int x, int y){
        this.x = x;
        this.y = y;
        buttonImageNormal = Assets.save_button_normal;
        buttonImagePressed = Assets.save_button_pressed;
    }

    @Override
    public void action(){
        if(isPressed)
            Game.SAVE_SELECTED = true;
        isPressed = false;
    };
}
