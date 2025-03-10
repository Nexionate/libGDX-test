package io.github.some_example_name;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.BitmapFont;


import java.util.Objects;

public class enemyAbstract extends Game implements enemyInterface{
    private SpriteBatch batch;
    private Texture texture;
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




    @Override
    public void create() {
        batch = new SpriteBatch();
        texture = new Texture( Gdx.files.internal("assets/evilMan.png") );
        font = new BitmapFont();
        entityHitbox = new Rectangle( entityX, entityY, texture.getWidth(), texture.getHeight());
    }

    @Override
    public void updateMovement() {
        if ((int) entityX < (int)targetX){
            entityVelX += speed;
        }
        if ((int) entityX > (int)targetX){
            entityVelX -= speed;
        }
        if ((int) entityY < (int)targetY){
            entityVelY += speed;
        }
        if ((int) entityY > (int)targetY){
            entityVelY -= speed;
        }
        entityX += ((entityVelX + speed) - entityX) / 20;
        entityY += ((entityVelY + speed) - entityY) / 20;


    }

    @Override
    public void assignAttributes(enemyAbstract enemy, int health, float speed, String colour) {
        enemy.health = health;
        enemy.speed = speed;
        enemy.colour = colour;
    }

    @Override
    public void targetPlayer(final float playerX, final float playerY) {
        this.targetX = playerX;
        this.targetY = playerY;
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

    public float getEntityXCenter() {
        return getEntityX() + entityHitbox.getWidth() / 2;
    }

    public float getEntityYCenter() {
        return getEntityY() + entityHitbox.getHeight() / 2;
    }

    public void update(){
        isDead = getHealth() <= 0;
        if (lightFactor > 0.0f) {
            lightFactor -= 0.01f;

        }
        if (lightFactor <= 0.0f){
            lightFactor = 0.0f;
        }
    }


    public void render() {
        update();
        entityHitbox.setPosition(entityX, entityY);

        String healthText = "Health: " + getHealth();

        batch.begin();

        if (Objects.equals(this.colour, "blue")){
            batch.setColor(0 + lightFactor, 0 + lightFactor, 1 + lightFactor, 1); // Lighter blue
        } else if (Objects.equals(this.colour, "red")){
            batch.setColor(1 + lightFactor, 0 + lightFactor, 0 + lightFactor, 1); // Lighter red
        } else if (Objects.equals(this.colour, "green")){
            batch.setColor(0 + lightFactor, 1 + lightFactor, 0 + lightFactor, 1); // Lighter green
        }

        font.draw(batch, healthText, getEntityXCenter() - 35, getEntityYCenter() + entityHitbox.getWidth() /2 + 20);
        batch.draw(texture, entityX, entityY );

        batch.end();
    }
    public void dispose() {
        batch.dispose();
    }
}
