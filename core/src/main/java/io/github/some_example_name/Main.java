package io.github.some_example_name;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;


public class Main extends Game {
    private Sprite player;
    private Background background;
    Texture img;
    TiledMap tiledMap;
    OrthographicCamera camera;
    TiledMapRenderer tiledMapRenderer;

    public void create() {
        //setScreen(new FirstScreen());           // initializes the screen

        camera = new OrthographicCamera();
        camera.setToOrtho(false,480,320);
        camera.update();
        tiledMap = new TmxMapLoader().load("exampleBG1.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

        player = new Sprite();             // assigns a player of object Sprite
        player.create();                        // constructs the player

        background = new Background();
        background.create();
    }

    // the order of rendering matters, so we must make sure to render the BG first
    public void render() {
        ScreenUtils.clear(Color.BLACK);         // clears the background every frame
        camera.update();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();

        //background.render();
        player.collision(background.createWalls());
        player.movement();                      // checks for movement
        player.input();                      // checks for movement
        player.render();                        // displays the player on the screen

    }
}

