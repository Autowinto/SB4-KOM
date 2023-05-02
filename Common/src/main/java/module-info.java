import dk.sdu.macl.common.services.IBulletFactory;
import dk.sdu.macl.common.services.IEntityProcessingService;
import dk.sdu.macl.common.services.IGamePluginService;
import dk.sdu.macl.common.services.IPostEntityProcessingService;

module Common {
    exports dk.sdu.macl.common.services;
    exports dk.sdu.macl.common.data;
    exports dk.sdu.macl.common.data.entityparts;
    exports dk.sdu.macl.common.util;

    uses IGamePluginService;
    uses IEntityProcessingService;
    uses IPostEntityProcessingService;
    uses IBulletFactory;

}