import dk.sdu.macl.bulletsystem.BulletControlSystem;
import dk.sdu.macl.bulletsystem.BulletPlugin;
import dk.sdu.macl.common.services.IBulletFactory;
import dk.sdu.macl.common.services.IEntityProcessingService;
import dk.sdu.macl.common.services.IGamePluginService;

module Bullet {
    requires Common;
    provides IGamePluginService with BulletPlugin;
    provides IBulletFactory with BulletPlugin;
    provides IEntityProcessingService with BulletControlSystem;

}