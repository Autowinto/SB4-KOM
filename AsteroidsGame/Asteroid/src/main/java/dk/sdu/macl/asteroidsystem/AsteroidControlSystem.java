package dk.sdu.macl.asteroidsystem;

import dk.sdu.macl.common.data.Entity;
import dk.sdu.macl.common.data.GameData;
import dk.sdu.macl.common.data.World;
import dk.sdu.macl.common.data.entityparts.LifePart;
import dk.sdu.macl.common.data.entityparts.MovingPart;
import dk.sdu.macl.common.data.entityparts.PositionPart;
import dk.sdu.macl.common.services.IEntityProcessingService;

public class AsteroidControlSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        for (Entity asteroid : world.getEntities(Asteroid.class)) {
            PositionPart positionPart = asteroid.getPart(PositionPart.class);
            MovingPart movingPart = asteroid.getPart(MovingPart.class);
            LifePart lifePart = asteroid.getPart(LifePart.class);

            movingPart.setUp(true);
            if (lifePart.isIsHit()) {
                lifePart.setLife(lifePart.getLife() - 1);
                float oldX = positionPart.getX();
                float oldY = positionPart.getY();
                float oldRadians = positionPart.getRadians();
                float oldRadius = asteroid.getRadius();
                boolean shouldSplit = lifePart.getLife() == 1 ? true : false;
                /**
                 * Get position, rotation and radius of asteroid.
                 * Remove big asteroid.
                 * Spawn two small ones with directions based on orthogonality of
                 * large asteroid's rotation, with an offset based on radius large
                 */
                world.removeEntity(asteroid);

                if (shouldSplit) {
                    System.out.println("SPLIT");
                    Asteroid a1 = new Asteroid();
                    a1.add(new PositionPart(oldX, oldY, oldRadians));
                    a1.add(new LifePart(1, 0));
                    a1.add(new MovingPart(0, 10, 10, 0));
//                    Asteroid a1 = createBaseSmall();
//                    Asteroid a2 = createBaseSmall();
                    world.addEntity(a1);
//                    world.addEntity(a2);
                }

            }

            movingPart.process(gameData, asteroid);
            positionPart.process(gameData, asteroid);

            updateShape(asteroid);
        }
    }

    private Asteroid createBaseSmall() {
        Asteroid asteroid = new Asteroid();
        asteroid.setRadius(10);
//        asteroid.add(new PositionPart());
//        asteroid.add(new MovingPart(0, ));
//        asteroid.add(new LifePart());
        return asteroid;
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
