package dk.sdu.macl.common.services;

import dk.sdu.macl.common.data.Entity;
import dk.sdu.macl.common.data.GameData;

public interface IBulletFactory {

    public Entity create(Entity sourceEntity, GameData gameData);
}
