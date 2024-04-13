package entities;

class EntityFactory {

    public Entity createEntity(String type, int x, int y, int width, int height)
    {
        if(type.equals("Player"))
            return new Player(x,y,width,height);
        else if (type.equals("Enemy"))
            return new Enemy(x,y,width,height);
        else
            return null;
    }
}
