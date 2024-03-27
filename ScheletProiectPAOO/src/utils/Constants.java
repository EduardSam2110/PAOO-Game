package utils;

public class Constants {
    public static class PLayerConstants{
        // clasa playerului ce contine tot ce tine de el
        public static final int IDLE = 0;
        public static final int WALK = 1;
        public static final int RUN = 2;
        public static final int ATTACK_1 = 3;
        public static final int ATTACK_2 = 4;
        public static final int SWIMMING = 11;
        public static final int CRAWLING = 6;
        public static final int SIDE_WALL= 7;
        public static final int JUMP = 8;
        public static final int DEATH = 9;

        //metoda pentru a stabili lungimea animatiei (y adica)
        public static int GetSpriteAmount(int player_action)
        {
             switch(player_action)
             {
                 case IDLE:
                 case WALK:
                 case RUN:
                 case DEATH:
                 case CRAWLING:
                 case JUMP:
                     return 8;
                 case SIDE_WALL:
                 case SWIMMING:
                     return 4;
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