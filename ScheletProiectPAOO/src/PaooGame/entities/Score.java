package PaooGame.entities;

import PaooGame.Game;

import java.awt.*;

import static PaooGame.Graphics.Assets.score;

/*
sistemul se scor din joc bazat pe trecerea secundelor
 */
public class Score {
    public static double finalScore = 0; // scorul final, poate fi aflat doar la completarea jocului
    public static double current_score = 0; // scorul curent din cadrul feicarui nivel
    private static int[] score_array = new int[4]; // pentru afisarea scorului


    /*
    functia desparte scorul final/curent cifra cu cifra, il stocheaza in score_array, pentru a-l afisa pe ecran
     */
    private static void Score_Processing(double score)
    {
        int i = 0;
        int temp_score = (int) score;
        while( i < score_array.length)
        {
            int temp = temp_score % 10;

            temp_score /= 10;

            score_array[i] = temp;

            i++;
        }

    }

    /*
    sistemul scorului
    se actualizeaza cu 1/60 pe frame, adica 1 pct/secunda
    scor final cat mai mic -> castigator
     */
    public static void Score_System(Player p)
    {
        if(Game.START_PRESSED)
        {
            current_score += 1/60.; // un frame dureaza 1/60 secunde, iar functia se apeleaza o data per frame
        }
    }

    public static void update(Graphics g)
    {
        Score_Processing(current_score);
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

    public static void resetAll()
    {
        current_score = finalScore = 0;
    }

    /*
    pentru afisarea scorului pe ecranul de castigare
     */
    public static void drawIfWon(Graphics g)
    {
        Score_Processing(finalScore);
        int w = 128;
        int x = 380;
        g.drawImage(score[score_array[3]],x,430,128,128,null);
        g.drawImage(score[score_array[2]],x+w,430,128,128,null);
        g.drawImage(score[score_array[1]],x+w*2,430,128,128,null);
        g.drawImage(score[score_array[0]],x+w*3,430,128,128,null);
    }
}
