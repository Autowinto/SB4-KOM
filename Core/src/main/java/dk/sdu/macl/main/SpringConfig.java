package dk.sdu.macl.main;

import dk.sdu.macl.common.services.IEntityProcessingService;
import dk.sdu.macl.common.services.IGamePluginService;
import dk.sdu.macl.common.services.IPostEntityProcessingService;
import dk.sdu.macl.common.util.SPILocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;

@Configuration
public class SpringConfig {

    @Bean
    public Game game() {
        return new Game(gamePluginServices(), entityProcessingServices(), entityPostProcessingServices());
    }

    @Bean
    public Collection<IEntityProcessingService> entityProcessingServices() {
        return SPILocator.locateAll(IEntityProcessingService.class);
    }

    @Bean
    public Collection<IPostEntityProcessingService> entityPostProcessingServices() {
        return SPILocator.locateAll(IPostEntityProcessingService.class);
    }

    @Bean
    public Collection<IGamePluginService> gamePluginServices() {
        return SPILocator.locateAll(IGamePluginService.class);
    }


}
