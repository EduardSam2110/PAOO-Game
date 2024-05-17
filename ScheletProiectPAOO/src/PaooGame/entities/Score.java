package PaooGame.entities;

import PaooGame.Game;

import java.awt.*;

import static PaooGame.Graphics.Assets.score;

public class Score {
    public static double finalScore = 0;
    public static double current_score = 0;
    private static int[] score_array = new int[4];


    private static void Score_Processing()
    {
        int i = 0;
        int temp_score = (int) current_score;
        while( i < score_array.length)
        {
            int temp = temp_score % 10;

            temp_score /= 10;

            score_array[i] = temp;

            i++;
        }

    }

    public static void Score_System(Player p)
    {
        if(Game.START_PRESSED)
        {
            current_score += 1/60.; // un frame dureaza 1/60 secunde, iar functia se apeleaza o data per frame
        }
    }

    public static void update(Graphics g)
    {
        Score_Processing();
        g.drawImage(score[score_array[3]],620,20,32,32,null);
        g.drawImage(score[score_array[2]],652,20,32,32,null);
        g.drawImage(score[score_array[1]],684,20,32,32,null);
        g.drawImage(score[score_array[0]],715,20,32,32,null);
    }

    public static void updateFinalScore()
    {
        finalScore += current_score;
        current_score = 0;
    }
}
