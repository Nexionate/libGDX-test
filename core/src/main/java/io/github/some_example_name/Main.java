package io.github.some_example_name;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import java.util.ArrayList;


import java.util.Iterator;
import java.util.Random;

/**
 * Represents the Main class.
 *
 * @author Ethan O'Connor
 * @version 2025
 */
public class Main extends Game {
    private Player player;
    private Background background;
    private UpgradeMenu menu;
    private enemyAbstract enemy;
    private ArrayList<enemyAbstract> allEnemies;
    private ArrayList<playerBullet> allBullets;
    private ArrayList<enemyDie> allDie;
    private int enemyTimer = 0;
    private playerBullet bullet;
    private int isFiring = 0;
    private enemyDie dead;
    private final int MAX_ENEMIES = 4;
    private int remainingEnemies;
    private int waveNum = 1;
    private WaveManager waveManager;
    private Button button;

    TiledMap tiledMap;
    OrthographicCamera camera;
    TiledMapRenderer tiledMapRenderer;


    //public static boolean gameState = true;




    /**
     * Constructs the main driver.
     */
    public void create() {


        allEnemies = new ArrayList<>();
        remainingEnemies = 0;
        allBullets = new ArrayList<>();
        allDie = new ArrayList<>();
        //waveManager = new WaveManager(1);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 540, 320);
        //camera.setToOrtho(false, 480, 320);
        camera.update();
        tiledMap = new TmxMapLoader().load("exampleBG1.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

        player = new Player();             // assigns a player of object player
        player.create();                        // constructs the player

        background = new Background();
        background.create();

        menu = new UpgradeMenu();
        menu.create();

    }

    /**
     * Returns the distance between two entities.
     *
     * @param entity1X as a float
     * @param entity1Y as a float
     * @param entity2X as a float
     * @param entity2Y as a float
     * @return distance as a double
     */
    public double distanceToEntity(final float entity1X, final float entity1Y,
                                   final float entity2X, final float entity2Y) {
        float distanceToPlayerX = Math.abs (entity1X - entity2X);
        float distanceToPlayerY = Math.abs (entity1Y - entity2Y);
        return  Math.sqrt(Math.pow(distanceToPlayerX, 2) + Math.pow(distanceToPlayerY, 2));

    }

    /**
     * Creates an enemy type object with random stats and location.
     */
    public void spawnEnemies(int amount) {
        double distanceTotal;
        int possibleSpawnX;
        int possibleSpawnY;
        Random rand = new Random();
        do {
            possibleSpawnX = rand.nextInt(0, 1340);
            possibleSpawnY = rand.nextInt(0, Gdx.graphics.getHeight() - 100);
            distanceTotal = distanceToEntity(possibleSpawnX, possibleSpawnY, player.getPlayerX(), player.getPlayerY());
        } while (distanceTotal < 300);


        //int length = allEnemies.size();
        int counter = 0;

//            if (enemyTimer < 160){
//                enemyTimer++;
//            } else {
//
//                enemy = new enemyAbstract();
//                int col = rand.nextInt(0, 3);
//                int randSpeed = rand.nextInt(8, 13);
//                counter++;
//                enemyTimer = 0;
//                switch (difficulty) {
//
//                    case 1:
//                        enemy.assignAttributes(enemy, 25, 1 * (float) randSpeed / 10,
//                            "blue", possibleSpawnX, possibleSpawnY);
//                        break;
//                    case 2:
//                        enemy.assignAttributes(enemy, 35, 2 * (float) randSpeed / 10,
//                            "green", possibleSpawnX, possibleSpawnY);
//                        break;
//                    case 3:
//                        enemy.assignAttributes(enemy, 30, 3 * (float) randSpeed / 10,
//                            "red", possibleSpawnX, possibleSpawnY);
//                        break;
//
//                }
//
//            }
        if (remainingEnemies < amount && enemyTimer > 60) {
            enemy = new enemyAbstract();
            int col = rand.nextInt(0, 3);
            int randSpeed = rand.nextInt(8, 13);
            counter ++;
            switch (col) {
                case 0:
                    enemy.assignAttributes(enemy, 25, 1 * (float) randSpeed / 10,
                        "blue", possibleSpawnX, possibleSpawnY);
                    break;
                case 1:
                    enemy.assignAttributes(enemy, 35, 2 * (float) randSpeed / 10,
                        "green", possibleSpawnX, possibleSpawnY);
                    break;
                case 2:
                    enemy.assignAttributes(enemy, 30, 3 * (float) randSpeed / 10,
                        "red", possibleSpawnX, possibleSpawnY);
                    break;
            }

            enemy.create();
            allEnemies.add(enemy);
            enemyTimer = 0;

        }
        enemyTimer ++;
    }

    public int startWave(int waveNum) {
        switch (waveNum) {
            case 1:
                return 3;

            case 2:
                return 4;

            case 3:
                return 5;

            case 4:
                return 6;

            case 5:
                return 5;

            default:
                return 1;
        }
    }

    /**
     * Creates a bullet type object.
     */
    public void spawnBullets(int direction) {
        bullet = new playerBullet();
        bullet.setBulletPosition(player.getPlayerXCenter(), player.getPlayerYCenter(), direction);
        bullet.create();
        allBullets.add(bullet);
    }





    public void playerCollision() {
        for (enemyAbstract enemy : allEnemies) {
            if (distanceToEntity(enemy.getEntityX(), enemy.getEntityY(), player.getPlayerX(), player.getPlayerY()) < 100) {
                if (enemy.getEnemyHitbox().overlaps(player.getHitbox())) {
                    if ((player.getHitInvincibility() <= 0)) {
                        player.setHealth(player.getHealth() - 1);
                        player.setHitInvincibility(150);
                    }
                }
            }

        }
    }
    /**
     * Checks the bullet object collision with enemy objects.
     */
    public void bulletCollision() {
        if(!(allBullets.isEmpty() && allEnemies.isEmpty())) {

            for (Iterator<playerBullet> bulletIterator = allBullets.iterator(); bulletIterator.hasNext(); ) {
                playerBullet bullet = bulletIterator.next();

                for (Iterator<enemyAbstract> enemyIterator = allEnemies.iterator(); enemyIterator.hasNext(); ) {
                    enemyAbstract enemy = enemyIterator.next();

                    // this is mainly for performance, only collision check if bullet is close by
                    if (distanceToEntity(bullet.getBulletX(), bullet.getBulletY(),
                        enemy.getEntityX(), enemy.getEntityY()) < 200) {

                        if (bullet.getBulletHitbox().overlaps(enemy.getEnemyHitbox())) {
                            // Apply damage to the enemy
                            enemy.setHealth(enemy.getHealth() - bullet.getDamage());
                            enemy.hit();
                            // Remove the bullet from the list using the bullet iterator
                            bulletIterator.remove();

                            // Check if the enemy's health is less than 0 and remove it
                            if (enemy.getHealth() <= 0) {
                                dead = new enemyDie();
                                allDie.add(dead);
                                dead.setPosition(enemy.getEntityX(), enemy.getEntityY(), enemy.getColour());
                                dead.create();
                                enemyIterator.remove();  // Remove the enemy from allEnemies
                                enemy.dispose();
                            }
                            break;  // Break out of the inner loop to stop further checks for this bullet
                    }

                    }
                }
            }

        }
    }

    /**
     * Updates the game logic each frame.
     */
    public void update() {
        remainingEnemies = allEnemies.size();
        int enemiesToSpawn = startWave(waveNum);
        spawnEnemies(enemiesToSpawn - remainingEnemies);



        bulletCollision();
        playerCollision();
        camera.update();
        tiledMapRenderer.setView(camera);

        player.collision(background.createWalls());     // checks for walls
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
        DON'T USE ANYTHING HIGHER THAN 4!
         */
        isFiring = player.inputBullet();
        if (isFiring != 0){
            spawnBullets(isFiring);
        }
    }

    /**
     * Renders all actors to the screen.
     * the order of rendering matters, so we must make sure to render the BG first
     */
    public void render() {
        ScreenUtils.clear(Color.BLACK);         // clears the background every frame

        update();                               // runs all game logic

        tiledMapRenderer.render();

        if (dead != null) {         // render death animation
            if(dead.getDone()){
                dead.render();
            }
        }

        for (enemyAbstract enemy : allEnemies) {            // render all enemies
            enemy.targetPlayer(player.getPlayerX(), player.getPlayerY());
            /*
             * Enemies will show a small animation when they initially spawn in to tell
             * the players so they don't get hit. Instead of a timer, i made a placeholder
             * timer variable that tells the enemy when it can start moving around and targeting the player
             */
            if (enemy.getSpawningTimer() <= 0) {
                enemy.updateMovement();
            }
            enemy.render();
        }

        for (playerBullet bullet : allBullets) {            // render all bullets
            bullet.updateMovement();
            bullet.render();
        }

        player.render();                        // displays the player on the screen
    }

}

