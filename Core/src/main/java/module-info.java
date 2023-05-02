import dk.sdu.macl.common.services.IEntityProcessingService;
import dk.sdu.macl.common.services.IGamePluginService;
import dk.sdu.macl.common.services.IPostEntityProcessingService;

module Core {
    requires Common;
    requires com.badlogic.gdx;
    requires spring.context;

    uses IGamePluginService;
    uses IEntityProcessingService;
    uses IPostEntityProcessingService;
    opens dk.sdu.macl.main;
    exports dk.sdu.macl.main;
}