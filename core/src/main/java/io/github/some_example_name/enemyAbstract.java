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
public class enemyAbstract extends Game implements enemyInterface{
    private SpriteBatch batch;
    private Texture texture;
    private Rectangle entityHitbox;
    private float entityX = 0;
    private float entityY = 0;
    private int health = 100;
    private float targetX;
    private float targetY;
    private float speed = 2;
    private BitmapFont font;

    public enemyAbstract(){

    }
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
            entityX += speed;
        }
        if ((int) entityX > (int)targetX){
            entityX -= speed;
        }
        if ((int) entityY < (int)targetY){
            entityY += speed;
        }
        if ((int) entityY > (int)targetY){
            entityY -= speed;
        }


    }

    @Override
    public enemyAbstract assignAttributes() {
        enemyAbstract enemy = new enemyAbstract();
        enemy.health = health;
        enemy.speed = speed;
        enemy.create();
        return this;
    }

    @Override
    public void targetPlayer(final float playerX, final float playerY) {
        this.targetX = playerX;
        this.targetY = playerY;
    }

    public void render() {
        entityHitbox.setPosition(entityX, entityY);
        String targetPos = "Target X: " +  targetX + " Target Y: " +  targetY;
        String health = "Health" + health;

        batch.begin();
        font.draw(batch, targetPos, 100, 100);
        batch.draw(texture, entityX, entityY );

        batch.end();
    }
    public void dispose() {
        batch.dispose();
    }
}
