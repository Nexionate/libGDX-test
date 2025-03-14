package io.github.some_example_name;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.BitmapFont;


import java.util.Objects;
import java.util.Random;

/**
 * Represents an enemy abstract class.
 *
 * @author Ethan O'Connor
 * @version 2025
 */
public class enemyAbstract extends Game implements enemyInterface{
    private SpriteBatch batch;
    private Texture texture;
    private Texture textureSpawn;
    private Rectangle entityHitbox;
    private float entityX = 0;
    private float entityY = 0;
    private float entityVelX = 0;
    private float entityVelY = 0;
    private int health = 100;
    private float targetX;
    private float targetY;
    private float speed;
    private String colour;
    private BitmapFont font;
    private boolean isDead = false;
    private float lightFactor = 0.0f;  // The factor by which to lighten the color (0 to 1)
    private int spawningTimer = 110;
    private Sprite sprite;
    private int targetPositionDivisionOffset = 0;
    private int targetDelayThreshhold = 0;
    private int targetDelayCounter = 0;


    @Override
    public void create() {
        batch = new SpriteBatch();
        texture = new Texture( Gdx.files.internal("assets/evilMan.png") );
        textureSpawn = new Texture( Gdx.files.internal("assets/enemySpawnPortal.png") );
        sprite = new Sprite( textureSpawn );
        font = new BitmapFont();
        entityHitbox = new Rectangle( entityX, entityY, texture.getWidth(), texture.getHeight());



    }

    @Override
    public void updateMovement() {
        if ((int) entityX + 20 < (int)targetX){
            entityVelX += speed;
        }
        if ((int) entityX - 20 > (int)targetX){
            entityVelX -= speed;
        }
        if ((int) entityY + 20 < (int)targetY){
            entityVelY += speed;
        }
        if ((int) entityY - 20 > (int)targetY){
            entityVelY -= speed;
        }
        entityX += ((entityVelX + speed) - entityX) / (20 + targetPositionDivisionOffset);
        entityY += ((entityVelY + speed) - entityY) / (20 + targetPositionDivisionOffset);


    }

    @Override
    public void assignAttributes(enemyAbstract enemy, int health, float speed, String colour, float startX, float startY) {
        enemy.health = health;
        enemy.speed = speed;
        enemy.colour = colour;
        entityX = startX;
        entityY = startY;
        entityVelX = startX;
        entityVelY = startY;

        Random rand  = new Random();
        targetPositionDivisionOffset = rand.nextInt(-8, 8);
        targetDelayThreshhold = rand.nextInt(2, 20);
    }

    @Override
    public void targetPlayer(final float playerX, final float playerY) {
        if (targetDelayCounter >= targetDelayThreshhold){
            this.targetX = playerX;
            this.targetY = playerY;
            targetDelayCounter = 0;
        }
    }
    public void hit(){
        //lightFactor += 0.05f;  // Gradually increase the light factor each frame
        lightFactor = 0.3f;
//        if (lightFactor > 1.0f) {
//            lightFactor = 1.0f;  // Clamp to the maximum value of 1 to avoid exceeding max color values
//        }

    }
    public Rectangle getEnemyHitbox() {
        return entityHitbox;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public float getEntityX() {
        return entityX;
    }

    public float getEntityY() {
        return entityY;
    }

    public String getColour() {
        return colour;
    }

    public float getEntityXCenter() {
        return getEntityX() + entityHitbox.getWidth() / 2;
    }

    public float getEntityYCenter() {
        return getEntityY() + entityHitbox.getHeight() / 2;
    }

    public int getSpawningTimer() {
        return spawningTimer;
    }

    public void update() {
        isDead = getHealth() <= 0;
        if (lightFactor > 0.0f) {
            lightFactor -= 0.01f;

        }
        if (lightFactor <= 0.0f){
            lightFactor = 0.0f;
        }
        spawningTimer--;
        if (spawningTimer <= 0){
            spawningTimer = 0;
        }

        targetDelayCounter++;
    }
    public void renderText(){
        String healthText = "Health: " + getHealth();
        font.draw(batch, healthText, getEntityXCenter() - 35, getEntityYCenter() + entityHitbox.getWidth() /2 + 20);
    }

    public void render() {
        update();
        entityHitbox.setPosition(entityX, entityY);



        batch.begin();

        renderText();

        if (Objects.equals(this.colour, "blue")){
            batch.setColor(0 + lightFactor, 0 + lightFactor, 1 + lightFactor, 1); // Lighter blue
        } else if (Objects.equals(this.colour, "red")){
            batch.setColor(1 + lightFactor, 0 + lightFactor, 0 + lightFactor, 1); // Lighter red
        } else if (Objects.equals(this.colour, "green")){
            batch.setColor(0 + lightFactor, 1 + lightFactor, 0 + lightFactor, 1); // Lighter green
        }


        if (spawningTimer <= 0){

            batch.draw(texture, entityX, entityY );
        } else{
            sprite.setPosition(entityX, entityY);
            sprite.setRotation(spawningTimer * 4.0f);
            sprite.draw(batch);
            //batch.draw(textureSpawn, entityX, entityY );
        }


        batch.end();
    }
    public void dispose() {
        batch.dispose();
    }
}
