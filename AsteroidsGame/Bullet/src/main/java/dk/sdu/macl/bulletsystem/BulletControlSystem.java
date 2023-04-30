package dk.sdu.macl.bulletsystem;

import dk.sdu.macl.common.data.Entity;
import dk.sdu.macl.common.data.GameData;
import dk.sdu.macl.common.data.World;
import dk.sdu.macl.common.data.entityparts.LifePart;
import dk.sdu.macl.common.data.entityparts.MovingPart;
import dk.sdu.macl.common.data.entityparts.PositionPart;
import dk.sdu.macl.common.data.entityparts.ShootingPart;
import dk.sdu.macl.common.services.IEntityProcessingService;

public class BulletControlSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        for (Entity entity : world.getEntities()) {
            ShootingPart shootingPart = entity.getPart(ShootingPart.class);
            PositionPart positionPart = entity.getPart(PositionPart.class);

            // If entity has a shooting part. Handle it.
            if (shootingPart != null) {
                if (shootingPart.getIsShooting() && shootingPart.canShoot()) {
                    float x = positionPart.getX();
                    float y = positionPart.getY();
                    float rads = positionPart.getRadians();
                    spawnBullet(world, (float) (x + Math.cos(rads) * 15), (float) (y + Math.sin(rads) * 15), rads);
                }
            }
        }

        for (Entity entity : world.getEntities(Bullet.class)) {
            PositionPart positionPart = entity.getPart(PositionPart.class);
            MovingPart movingPart = entity.getPart(MovingPart.class);
            LifePart lifePart = entity.getPart(LifePart.class);
            movingPart.process(gameData, entity);
            positionPart.process(gameData, entity);
            lifePart.reduceExpiration(gameData.getDelta());
            lifePart.process(gameData, entity);

            if (lifePart.getExpiration() <= 0 || lifePart.isIsHit())
                world.removeEntity(entity);

            updateShape(entity);
        }
    }

    private void spawnBullet(World world, float x, float y, float rotation) {
        Bullet bullet = new Bullet();
        MovingPart movingPart = new MovingPart(0, 10000, 1000, 0);
        movingPart.setUp(true);
        bullet.add(movingPart);
        bullet.add(new LifePart(1, 1));
        bullet.add(new PositionPart(x, y, rotation));

        world.addEntity(bullet);
    }

    private void updateShape(Entity entity) {
        float[] shapeX = entity.getShapeX();
        float[] shapeY = entity.getShapeY();
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();

        shapeX[0] = (float) (x + Math.cos(radians) * 1);
        shapeY[0] = (float) (y + Math.sin(radians) * 1);

        shapeX[1] = (float) (x + Math.cos(radians) * 0);
        shapeY[1] = (float) (y + Math.sin(radians) * 0);

        shapeX[2] = (float) (x + Math.cos(radians) * 2);
        shapeY[2] = (float) (y + Math.sin(radians) * 2);

        shapeX[3] = (float) (x + Math.cos(radians) * -2);
        shapeY[3] = (float) (y + Math.sin(radians) * -2);

        entity.setShapeX(shapeX);
        entity.setShapeY(shapeY);
    }
}
