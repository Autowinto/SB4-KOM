import dk.sdu.macl.collisionsystem.CollisionHandler;
import dk.sdu.macl.common.services.IPostEntityProcessingService;

module Collision {
    requires Common;
    provides IPostEntityProcessingService with CollisionHandler;
}