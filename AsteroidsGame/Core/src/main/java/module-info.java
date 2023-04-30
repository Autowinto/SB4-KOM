import dk.sdu.macl.common.services.IEntityProcessingService;
import dk.sdu.macl.common.services.IGamePluginService;
import dk.sdu.macl.common.services.IPostEntityProcessingService;

module Core {
    requires Common;
    requires com.badlogic.gdx;

    uses IGamePluginService;
    uses IEntityProcessingService;
    uses IPostEntityProcessingService;
}