package PaooGame.entities;

public class EnemyFactory {
    public Entity factoryMethod(String enemyType, int x, int y, boolean containsClippers)
    {
        if(enemyType.equalsIgnoreCase("simple"))
            if(containsClippers)
                return new SimpleEnemy(x,y,128,128, containsClippers);
            else
                return new SimpleEnemy(x,y,128,128);
        else if(enemyType.equalsIgnoreCase("boss"))
            return new BossEnemy(x,y,256,256);
        else
            return null;
    }
}
