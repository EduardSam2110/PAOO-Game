package PaooGame.entities;

public class EnemyFactory {
    /*
    Implementarea design-ului Factory pentru cele 2 tipuri de inamici
     */
    public Entity factoryMethod(String enemyType, int x, int y, boolean containsClippers, int toSpawn)
    {
        if(toSpawn == 1){
            if (enemyType.equalsIgnoreCase("simple"))
                if (containsClippers)
                    return new SimpleEnemy(x, y, 128, 128, containsClippers);
                else
                    return new SimpleEnemy(x, y, 128, 128);
            else if (enemyType.equalsIgnoreCase("boss"))
                return new BossEnemy(x, y, 256, 256, containsClippers);
        }

        return null;
    }
}
