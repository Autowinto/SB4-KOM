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
                /**
                 * Get position, rotation and radius of asteroid.
                 * Remove big asteroid.
                 * Spawn two small ones with directions based on orthogonality of
                 * large asteroid's rotation, with an offset based on radius large
                 */
                world.removeEntity(asteroid);

                // We now spawn a small asteroid. We need to spawn two asteroids instead now.
                if (lifePart.getLife() == 1) {
                    System.out.println("SPLIT");
                    Asteroid a1 = createBaseSmall(oldX, oldY);
                    Asteroid a2 = createBaseSmall(oldX, oldY);
                    world.addEntity(a1);
                    world.addEntity(a2);
                }

            }

            movingPart.process(gameData, asteroid);
            positionPart.process(gameData, asteroid);

            updateShape(asteroid);
        }
    }

    private Asteroid createBaseSmall(float x, float y) {
        Asteroid a = new Asteroid();
        a.setRadius(10);
        a.add(new PositionPart(x, y, (float) (Math.random() * Math.PI * 2)));
        a.add(new LifePart(1, 0));
        a.add(new MovingPart(0, 1000, 50, 0));
        return a;
    }

    private void updateShape(Entity entity) {
        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();
        PositionPart positionPart = entity.getPart(PositionPart.class);
        LifePart lifePart = entity.getPart(LifePart.class);
        int size = lifePart.getLife();
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();

        shapex[0] = (float) (x + Math.cos(radians - 4 * 3.1415f) * 8 * size);
        shapey[0] = (float) (y + Math.sin(radians - 4 * 3.1415f) * 8 * size);

        shapex[1] = (float) (x + Math.cos(radians - 4 * 3.1415f / 8) * 8 * size);
        shapey[1] = (float) (y + Math.sin(radians - 4 * 3.1145f / 8) * 8 * size);

        shapex[2] = (float) (x + Math.cos(radians + 3.1415f) * 8 * size);
        shapey[2] = (float) (y + Math.sin(radians + 3.1415f) * 8 * size);

        shapex[3] = (float) (x + Math.cos(radians + 4 * 3.1415f / 8) * 8 * size);
        shapey[3] = (float) (y + Math.sin(radians + 4 * 3.1415f / 8) * 8 * size);

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }
}
