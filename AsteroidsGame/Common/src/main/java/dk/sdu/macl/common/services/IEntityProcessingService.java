package dk.sdu.macl.common.services;

import dk.sdu.macl.common.data.GameData;
import dk.sdu.macl.common.data.World;

public interface IEntityProcessingService {
    /**
     * A processing service that handles entities.
     * 
     * @param gameData
     * @param world
     */
    void process(GameData gameData, World world);
}
