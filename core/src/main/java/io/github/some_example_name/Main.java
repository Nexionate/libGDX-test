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
import java.util.ArrayList;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class Main extends Game {
    private Sprite player;
    private Background background;
    private UpgradeMenu menu;
    private enemyAbstract enemy;
    private ArrayList<enemyAbstract> allEnemies;
    private ArrayList<playerBullet> allBullets;
    private int enemyTimer = 0;
    private playerBullet bullet;
    private int isFiring = 0;


    TiledMap tiledMap;
    OrthographicCamera camera;
    TiledMapRenderer tiledMapRenderer;

    public static boolean gameState = true;





    public void create() {

        allEnemies = new ArrayList<>();
        allBullets = new ArrayList<>();

        camera = new OrthographicCamera();
        camera.setToOrtho(false,480,320);
        camera.update();
        tiledMap = new TmxMapLoader().load("exampleBG1.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

        player = new Sprite();             // assigns a player of object Sprite
        player.create();                        // constructs the player


        background = new Background();
        background.create();

        menu = new UpgradeMenu();
        menu.create();



    }
    public void spawnEnemies() {
        Random rand = new Random();

        int length = allEnemies.size();
        if (length < 4 && enemyTimer > 160) {
            enemy = new enemyAbstract();
            int col = rand.nextInt(0, 3);
            int randSpeed = rand.nextInt(8, 13);
            switch (col) {
//                case 0: enemy.assignAttributes(enemy, 100, 1 + (float) randSpeed /20, "blue");
//                break;
//                case 1: enemy.assignAttributes(enemy, 50, 2 + (float) randSpeed /20, "green");
//                break;
//                case 2: enemy.assignAttributes(enemy, 25, 3 + (float) randSpeed /40, "red");
//                break;
                case 0: enemy.assignAttributes(enemy, 100, 1 * (float) randSpeed /10, "blue");
                    break;
                case 1: enemy.assignAttributes(enemy, 50, 2 * (float) randSpeed /10, "green");
                    break;
                case 2: enemy.assignAttributes(enemy, 25, 3 * (float) randSpeed /10, "red");
                    break;
            }

            enemy.create();


            allEnemies.add(enemy);
            enemyTimer = 0;
        }
        enemyTimer ++;
    }

    public void spawnBullets(int direction) {
        bullet = new playerBullet();
        bullet.setBulletPosition(player.getPlayerX(), player.getPlayerY(), direction);
        bullet.create();
        allBullets.add(bullet);
    }


    // the order of rendering matters, so we must make sure to render the BG first
    public void render() {
        spawnEnemies();

        ScreenUtils.clear(Color.BLACK);         // clears the background every frame
        camera.update();
        tiledMapRenderer.setView(camera);

        player.collision(background.createWalls());
        player.movement();                      // checks for movement
        player.input();                      // checks for movement


        /*
        This will return a number between 0-4.
        - 0: nothing firing, you're good
        - 1: Spawn bullet with direction UP
        - 2: Spawn bullet with direction LEFT
        - 3: Spawn bullet with direction DOWN
        - 4: Spawn bullet with direction RIGHT
        If not 0, call the function to spawn bullet and pass in the direction
         */
        isFiring = player.inputBullet();
        if(isFiring != 0){
            spawnBullets(isFiring);
        }

        tiledMapRenderer.render();

        for (enemyAbstract enemy : allEnemies) {
            enemy.targetPlayer(player.getPlayerX(), player.getPlayerY());
            enemy.updateMovement();
            enemy.render();
        }
        for (playerBullet bullet : allBullets) {
            bullet.updateMovement();
            bullet.render();
        }

        player.render();                        // displays the player on the screen


    }

}

