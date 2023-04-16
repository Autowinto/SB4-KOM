package dk.sdu.mmmi.cbse.asteroidsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import static dk.sdu.mmmi.cbse.common.data.GameKeys.*;

public class AsteroidControlSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        for (Entity asteroid : world.getEntities(Asteroid.class)) {
            PositionPart positionPart = asteroid.getPart(PositionPart.class);
            MovingPart movingPart = asteroid.getPart(MovingPart.class);
            LifePart lifePart = asteroid.getPart(LifePart.class);

            movingPart.setUp(true);
            if (lifePart.isIsHit()) {
                System.out.println("Asteroid hit at " + positionPart.getY() + ", " +  positionPart.getY() + " Removing");
                if (lifePart.getLife() <= 0) {
                    world.removeEntity(asteroid);
                } else {
                    createSplitAsteroids(asteroid);
                }

            }

            movingPart.process(gameData, asteroid);
            positionPart.process(gameData, asteroid);

            updateShape(asteroid);
        }
    }

    private Asteroid createSplitAsteroids(Entity asteroid) {
        Asteroid a1 = createBaseSmall();
        Asteroid a2 = createBaseSmall();

        // Set positions and rotation based on asteroid's position

    }

    private Asteroid createBaseSmall() {
        Asteroid asteroid = new Asteroid();
        asteroid.setRadius(10);
        asteroid.add(new PositionPart());
        asteroid.add(new MovingPart(0, ));
        asteroid.add(new LifePart());
    }

    private void updateShape(Entity entity) {
        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();

        shapex[0] = (float) (x + Math.cos(radians - 4 * 3.1415f) * 16);
        shapey[0] = (float) (y + Math.sin(radians - 4 * 3.1415f) * 16);

        shapex[1] = (float) (x + Math.cos(radians - 4 * 3.1415f / 8) * 16);
        shapey[1] = (float) (y + Math.sin(radians - 4 * 3.1145f / 8) * 16);

        shapex[2] = (float) (x + Math.cos(radians + 3.1415f) * 16);
        shapey[2] = (float) (y + Math.sin(radians + 3.1415f) * 16);

        shapex[3] = (float) (x + Math.cos(radians + 4 * 3.1415f / 8) * 16);
        shapey[3] = (float) (y + Math.sin(radians + 4 * 3.1415f / 8) * 16);

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }
}
