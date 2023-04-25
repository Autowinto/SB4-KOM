package dk.sdu.macl.common.services;

import dk.sdu.macl.common.data.GameData;
import dk.sdu.macl.common.data.World;

public interface IGamePluginService {
    /**
     * Starts the plugin. Requires the plugin to not already be started.
     * 
     * @param gameData
     * @param world
     */
    void start(GameData gameData, World world);

    /**
     * Stops the plugin. Requires the plugin to not already be stopped.
     * 
     * @param gameData -
     * @param world
     */
    void stop(GameData gameData, World world);
}
