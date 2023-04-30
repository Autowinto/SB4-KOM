package dk.sdu.macl.bulletsystem;

import dk.sdu.macl.common.data.Entity;
import dk.sdu.macl.common.data.GameData;
import dk.sdu.macl.common.data.World;
import dk.sdu.macl.common.data.entityparts.LifePart;
import dk.sdu.macl.common.data.entityparts.MovingPart;
import dk.sdu.macl.common.data.entityparts.PositionPart;
import dk.sdu.macl.common.services.IBulletFactory;
import dk.sdu.macl.common.services.IGamePluginService;

public class BulletPlugin implements IGamePluginService, IBulletFactory {
    Entity sourceEntity;
    @Override
    public void start(GameData gameData, World world) {
    }

    @Override
    public void stop(GameData gameData, World world) {
        for (Entity entity : world.getEntities(Bullet.class)) {
            world.removeEntity(entity);
        }
    }

    private Entity spawnBullet(GameData gameData) {
        System.out.println("Aooga");
        PositionPart sourcePos = sourceEntity.getPart(PositionPart.class);

        Bullet bullet = new Bullet();
        MovingPart movingPart = new MovingPart(0, 10000, 1000, 0);
        movingPart.setUp(true);
        bullet.add(movingPart);
        bullet.add(new LifePart(1, 1));

        float x = (float) (sourcePos.getX() + Math.cos(sourcePos.getRadians()) * 15);
        float y = (float) (sourcePos.getY() + Math.sin(sourcePos.getRadians()) * 15);

        bullet.add(new PositionPart(x, y, sourcePos.getRadians()));

        return bullet;
    }

    @Override
    public Entity create(Entity sourceEntity, GameData gameData) {
        this.sourceEntity = sourceEntity;
        return this.spawnBullet(gameData);
    }
}
