package utils;

public class Constants {
    public static class PLayerConstants{
        // clasa playerului ce contine tot ce tine de el
        public static final int IDLE = 0;
        public static final int WALK = 1;
        public static final int RUN = 2;
        public static final int ATTACK_1 = 6;
        public static final int ATTACK_2 = 4;
        public static final int SWIMMING = 11;
        public static final int CRAWLING = 8;
        public static final int SIDE_WALL= 7;
        public static final int JUMP = 13;
        public static final int FALLING = 14;

        public static final int DEATH = 9;

        public static final int EnemyIDLE = 1;

        //metoda pentru a stabili lungimea animatiei (y adica)
        public static int GetSpriteAmount(int player_action)
        {
            // FA SA IA DUPA TIPUL ENTITATII SI SA SELECTEZE PT ENEMY SI PLAYER DIFERIT
             switch(player_action) // modifica aici daca animatiile o iau razna sau se repeta de prea multe ori
             {
                 case IDLE:
                 case WALK:
                 case RUN:
                 case DEATH:
                 case CRAWLING:
                     return 8;
                 case SIDE_WALL:
                 case SWIMMING:
                 case ATTACK_1:
                     return 4;
                 case JUMP:
                 case FALLING:
                     return 2;
                 default:
                     return -1;
             }
        }
    }
    public static class Directions {
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;

    }
}
/*
Idle
Walk
Run
 Attack 1
Attack 2
 Attack 3
Crouching
Crwaling
Use Item
Swiming
SideWall
Jump
Fall
Hurt
Deat
 */