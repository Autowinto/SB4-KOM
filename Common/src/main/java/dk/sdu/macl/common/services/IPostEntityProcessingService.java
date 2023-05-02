package dk.sdu.macl.common.services;

import dk.sdu.macl.common.data.GameData;
import dk.sdu.macl.common.data.World;

public interface IPostEntityProcessingService {
        /**
         * Processes game data in the world.
         * 
         * @param gameData
         * @param world
         */
        void process(GameData gameData, World world);
}
