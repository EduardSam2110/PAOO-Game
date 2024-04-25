package PaooGame.entities;

import java.awt.*;
import java.time.LocalTime;

import static PaooGame.Graphics.Assets.score;

public class Score {
    private static double current_score = 0;
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
        current_score = LocalTime.now().getSecond();

    }

    public static void update(Graphics g)
    {
        System.out.println(current_score);
        Score_Processing();
        g.drawImage(score[score_array[3]],1000,20,32,32,null);
        g.drawImage(score[score_array[2]],1032,20,32,32,null);
        g.drawImage(score[score_array[1]],1064,20,32,32,null);
        g.drawImage(score[score_array[0]],1096,20,32,32,null);
    }


}
