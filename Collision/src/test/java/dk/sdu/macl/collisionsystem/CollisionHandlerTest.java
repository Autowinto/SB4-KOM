package dk.sdu.macl.collisionsystem;

import dk.sdu.macl.common.data.Entity;
import dk.sdu.macl.common.data.entityparts.PositionPart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.mockito.Mockito.*;

public class CollisionHandlerTest {
    private CollisionHandler collisionHandler;

    @BeforeEach
    public void init() {
        collisionHandler = new CollisionHandler();
    }

    @Test
    public void distanceCalculationTest() {
        PositionPart p1 = new PositionPart(20, 20, 0);
        PositionPart p2 = new PositionPart(20, 10, 0);

        assertEquals(10, collisionHandler.calculateDistance(p1, p2));

        p1.setY(30);
        assertEquals(20, collisionHandler.calculateDistance(p1, p2));
    }

    @Test
    public void collisionTest() {
        Entity e1 = new Entity();
        e1.setRadius(10);
        PositionPart p1 = new PositionPart(20, 20, 0);
        e1.add(p1);;
        Entity e2 = new Entity();
        e2.setRadius(10);
        PositionPart p2 = new PositionPart(15, 18, 0);
        e2.add(p2);
        assertTrue(collisionHandler.isColliding(e1, e2));
    }

    @Test
    public void falseCollisionTest() {
        Entity e1 = new Entity();
        e1.setRadius(10);
        PositionPart p1 = new PositionPart(10, 200, 0);
        e1.add(p1);;
        Entity e2 = new Entity();
        e2.setRadius(10);
        PositionPart p2 = new PositionPart(50, 160, 0);
        e2.add(p2);
        assertFalse(collisionHandler.isColliding(e1, e2));
    }

}
