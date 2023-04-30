import dk.sdu.macl.common.services.IEntityProcessingService;
import dk.sdu.macl.common.services.IGamePluginService;
import dk.sdu.macl.playersystem.PlayerControlSystem;
import dk.sdu.macl.playersystem.PlayerPlugin;

module Player {
    requires Common;

    provides IGamePluginService with PlayerPlugin;
    provides IEntityProcessingService with PlayerControlSystem;
}