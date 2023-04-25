package dk.sdu.macl.collisionsystem;

import dk.sdu.macl.common.data.Entity;
import dk.sdu.macl.common.data.GameData;
import dk.sdu.macl.common.data.World;
import dk.sdu.macl.common.data.entityparts.LifePart;
import dk.sdu.macl.common.data.entityparts.PositionPart;
import dk.sdu.macl.common.services.IPostEntityProcessingService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CollisionHandler implements IPostEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        // Get all entities and check for collisions.
        List<Entity> entityList = world.getEntities().stream().toList();
        Set<Entity> cols = new HashSet<Entity>();

        for (int i = 0; i < entityList.size(); i++) {
            Entity e1 = entityList.get(i);
            for (int j = i + 1; j < entityList.size(); j++) {
                Entity e2 = entityList.get(j);
                if (isColliding(e1, e2)) {
                    ((LifePart) e1.getPart(LifePart.class)).setIsHit(true);
                    ((LifePart) e2.getPart(LifePart.class)).setIsHit(true);
                }
            }
        }
    }

    private boolean isColliding(Entity e1, Entity e2) {
        LifePart l1 = (LifePart) e1.getPart(LifePart.class);
        LifePart l2 = (LifePart) e2.getPart(LifePart.class);
        PositionPart p1 = (PositionPart) e1.getPart(PositionPart.class);
        PositionPart p2 = (PositionPart) e2.getPart(PositionPart.class);
        if (p1 == null || p2 == null) return false;

        double dist = calculateDistance(p1, p2);
        double radSum = e1.getRadius() + e2.getRadius();

        if (dist <= radSum) {
            return true;
        }

        return false;
    }

    private double calculateDistance(PositionPart p1, PositionPart p2) {
        return Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2));
    }
}
