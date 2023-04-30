import dk.sdu.macl.asteroidsystem.AsteroidControlSystem;
import dk.sdu.macl.asteroidsystem.AsteroidPlugin;
import dk.sdu.macl.common.services.IEntityProcessingService;
import dk.sdu.macl.common.services.IGamePluginService;

module Asteroid {
    requires Common;
    provides IGamePluginService with AsteroidPlugin;
    provides IEntityProcessingService with AsteroidControlSystem;
}