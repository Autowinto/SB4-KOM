package dk.sdu.macl.main;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import dk.sdu.macl.bulletsystem.BulletControlSystem;
import dk.sdu.macl.common.data.Entity;
import dk.sdu.macl.common.data.GameData;
import dk.sdu.macl.common.data.World;
import dk.sdu.macl.common.services.IEntityProcessingService;
import dk.sdu.macl.common.services.IGamePluginService;
import dk.sdu.macl.common.services.IPostEntityProcessingService;
import dk.sdu.macl.common.util.SPILocator;
import dk.sdu.macl.enemysystem.EnemyPlugin;
import dk.sdu.macl.managers.GameInputProcessor;
import dk.sdu.macl.playersystem.PlayerControlSystem;
import dk.sdu.macl.playersystem.PlayerPlugin;
import dk.sdu.macl.enemysystem.EnemyControlSystem;
import dk.sdu.macl.asteroidsystem.AsteroidControlSystem;
import dk.sdu.macl.asteroidsystem.AsteroidPlugin;
import dk.sdu.macl.collisionsystem.CollisionHandler;
import dk.sdu.macl.bulletsystem.BulletPlugin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Game implements ApplicationListener {
    private static OrthographicCamera cam;
    private ShapeRenderer sr;
    private final GameData gameData = new GameData();
    private List<IEntityProcessingService> entityProcessors = new ArrayList<>();
    private List<IPostEntityProcessingService> postEntityProcessors = new ArrayList<>();
    private List<IGamePluginService> entityPlugins = new ArrayList<>();
    private World world = new World();

    private int MAX_ASTEROIDS = 10;

    @Override
    public void create() {

        gameData.setDisplayWidth(Gdx.graphics.getWidth());
        gameData.setDisplayHeight(Gdx.graphics.getHeight());

        cam = new OrthographicCamera(gameData.getDisplayWidth(), gameData.getDisplayHeight());
        cam.translate(gameData.getDisplayWidth() / 2, gameData.getDisplayHeight() / 2);
        cam.update();

        sr = new ShapeRenderer();

        Gdx.input.setInputProcessor(
                new GameInputProcessor(gameData)
        );

//        IGamePluginService playerPlugin = new PlayerPlugin();
//        IEntityProcessingService playerProcess = new PlayerControlSystem();
//        entityPlugins.add(playerPlugin);
//        entityProcessors.add(playerProcess);
//
//        IGamePluginService enemyPlugin = new EnemyPlugin();
//        IEntityProcessingService enemyProcess = new EnemyControlSystem();
//        entityPlugins.add(enemyPlugin);
//        entityProcessors.add(enemyProcess);
//
//        IGamePluginService asteroidPlugin = new AsteroidPlugin(20);
//        IEntityProcessingService asteroidProcess = new AsteroidControlSystem();
//        entityPlugins.add(asteroidPlugin);
//        entityProcessors.add(asteroidProcess);
//
//        IGamePluginService bulletPlugin = new BulletPlugin();
//        IEntityProcessingService bulletProcess = new BulletControlSystem();
//        entityPlugins.add(bulletPlugin);
//        entityProcessors.add(bulletProcess);
//
//        IPostEntityProcessingService collisionHandler = new CollisionHandler();
//        postEntityProcessors.add(collisionHandler);


        // Lookup all Game Plugins using ServiceLoader and register them
        for (IGamePluginService gamePlugin : getPluginServices()) {
            gamePlugin.start(gameData, world);
        }
    }

    @Override
    public void render() {

        // clear screen to black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameData.setDelta(Gdx.graphics.getDeltaTime());

        update();

        draw();

        gameData.getKeys().update();
    }

    private void update() {
        // Update
        for (IEntityProcessingService entityProcessorService : getEntityProcessingServices()) {
            entityProcessorService.process(gameData, world);
        }
        for (IPostEntityProcessingService postEntityProcessorService : getPostEntityProcessingServices()) {
            postEntityProcessorService.process(gameData, world);
        }
    }

    private void draw() {
        for (Entity entity : world.getEntities()) {

            sr.setColor(1, 1, 1, 1);

            sr.begin(ShapeRenderer.ShapeType.Line);

            float[] shapex = entity.getShapeX();
            float[] shapey = entity.getShapeY();

            for (int i = 0, j = shapex.length - 1;
                 i < shapex.length;
                 j = i++) {

                sr.line(shapex[i], shapey[i], shapex[j], shapey[j]);
            }

            sr.end();
        }
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }

    private Collection<? extends IGamePluginService> getPluginServices() {
        return SPILocator.locateAll(IGamePluginService.class);
    }

    private Collection<? extends IEntityProcessingService> getEntityProcessingServices() {
        return SPILocator.locateAll(IEntityProcessingService.class);
    }

    private Collection<? extends IPostEntityProcessingService> getPostEntityProcessingServices() {
        return SPILocator.locateAll(IPostEntityProcessingService.class);
    }
}
