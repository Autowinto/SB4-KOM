package dk.sdu.macl.asteroidsystem;

import dk.sdu.macl.common.data.Entity;
import dk.sdu.macl.common.data.GameData;
import dk.sdu.macl.common.data.World;
import dk.sdu.macl.common.data.entityparts.LifePart;
import dk.sdu.macl.common.data.entityparts.MovingPart;
import dk.sdu.macl.common.data.entityparts.PositionPart;
import dk.sdu.macl.common.services.IGamePluginService;

public class AsteroidPlugin implements IGamePluginService {
    private int asteroidLimit = 20;

    @Override
    public void start(GameData gameData, World world) {
        for (int i = 0; i < asteroidLimit; i++) {
            System.out.println("Spawning asteroid");
            world.addEntity(spawnAsteroid(gameData));
        }
    }

    private Entity spawnAsteroid(GameData gameData) {
        System.out.println("Spawning asteroid");

        float deacceleration = 10;
        float acceleration = 20;
        float maxSpeed = 50;
        float rotationSpeed = 5;
        float x = gameData.getDisplayWidth() * (float) Math.random();
        float y = gameData.getDisplayHeight() * (float) Math.random();
        float radians = 3.1415f / (float) Math.random() * 2;

        Entity asteroid = new Asteroid();
        asteroid.add(new MovingPart(deacceleration, acceleration, maxSpeed, rotationSpeed));
        asteroid.add(new PositionPart(x, y, radians));
        asteroid.add(new LifePart(2, 1));


        return asteroid;
    }

    @Override
    public void stop(GameData gameData, World world) {
        for (Entity entity : world.getEntities(Asteroid.class)) {
            world.removeEntity(entity);
        }
    }
}
