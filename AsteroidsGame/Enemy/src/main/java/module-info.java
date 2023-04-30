import dk.sdu.macl.common.services.IEntityProcessingService;
import dk.sdu.macl.common.services.IGamePluginService;
import dk.sdu.macl.enemysystem.EnemyControlSystem;
import dk.sdu.macl.enemysystem.EnemyPlugin;

module Enemy {
    requires Common;

    provides IGamePluginService with EnemyPlugin;
    provides IEntityProcessingService with EnemyControlSystem;
}