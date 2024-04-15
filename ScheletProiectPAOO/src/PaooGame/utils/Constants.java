package PaooGame.utils;

// Clasa contine numele animatiilor si functia GetSpriteAmount care va returna lungimea unei animatii (adica numarul de sprite-uri)

public class Constants {
        public static final int IDLE = 0;
        public static final int WALK = 1;
        public static final int RUN = 2;
        public static final int ATTACK_1 = 6;
        public static final int SWIMMING = 11;
        public static final int CRAWLING = 8;
        public static final int SIDE_WALL = 7;
        public static final int JUMP = 13;
        public static final int FALLING = 14;
        public static final int DEATH = 4;
        public static final int SIT_DOWN = 9;


        public static final int EnemyWALK = 0;
        public static final int EnemyIDLE = 1;
        public static final int EnemyATTACK = 2;
        public static final int EnemyDEATH = 3;

        public static int GetSpriteAmount(int action, String entityType) {
            if ("player".equals(entityType)) {
                switch (action) {
                    case IDLE:
                    case WALK:
                    case RUN:
                    case DEATH:
                    case CRAWLING:
                    case ATTACK_1:
                        return 8;
                    case SIDE_WALL:
                    case SWIMMING:
                    case SIT_DOWN:
                        return 4;
                    case JUMP:
                    case FALLING:
                        return 2;
                    default:
                        return -1;
                }
            }

            if ("enemy".equals(entityType)) {
                switch (action) {
                    case EnemyWALK:
                        return 8;
                    case EnemyIDLE:
                        return 6;
                    case EnemyATTACK:
                    case EnemyDEATH:
                        return 4;
                    default:
                        return -1;
                }
            }
            return -1;
        }
}
