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
public class Sprite extends Game{

    private SpriteBatch batch;
    private Texture texture;
    private BitmapFont font;
    private float playerX;
    private float playerY;
    private float maxSpeed = 3.0f;
    private float velocityX;
    private float velocityY;
    private String collisionTesting;
    private String velocityText;

    private Rectangle hitbox;


    @Override
    public void create() {
        batch = new SpriteBatch();
        texture = new Texture( Gdx.files.internal("assets/anotherMan.png") );
        font = new BitmapFont();
        playerX = 0;
        playerY = 0;
        //hitbox = new Rectangle( playerX, playerY, 200, 200);
        hitbox = new Rectangle( playerX, playerY, texture.getWidth(), texture.getHeight());

//        ShapeRenderer shape = new ShapeRenderer();
//        shape.setColor(0, 1, 0, 1);
//        shape.begin(ShapeRenderer.ShapeType.Line);
//        shape.rect(playerX, playerY, texture.getWidth(), texture.getHeight());
//        shape.end();

    }


    public void movement(){
        playerX += ((velocityX + maxSpeed) - playerX) / 10;
        playerY += ((velocityY + maxSpeed) - playerY) / 10;
    }


    public void collision(Rectangle[] walls){
        if(walls[0].overlaps(hitbox)){
            collisionTesting = "North";
            velocityY -= maxSpeed;
//            velocityY = Gdx.graphics.getHeight();
            //velocityY = Gdx.graphics.getHeight() - maxSpeed;
            //velocityY = (Gdx.graphics.getHeight() - 10) - maxSpeed;
            //velocityY = Gdx.graphics.getHeight() - velocityY;
            //velocityY = Gdx.graphics.getHeight();
//            velocityY = 600 - maxSpeed;
        }
        else if(walls[1].overlaps(hitbox)){
            collisionTesting = "South";
//            velocityY = 0;
            //velocityY = 0 - maxSpeed;
            velocityY += maxSpeed;
        }
        else if(walls[2].overlaps(hitbox)){
            collisionTesting = "East";
            velocityX = Gdx.graphics.getWidth();
        }
        else if(walls[3].overlaps(hitbox)){
            collisionTesting = "West";
            velocityX = 0;
        }
        else{
            collisionTesting = "None";
        }
    }

    public void input(){
        if (Gdx.input.isKeyPressed(Keys.LEFT)) {
            velocityX -= maxSpeed;
        }
        if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
            velocityX += maxSpeed;
        }
        if (Gdx.input.isKeyPressed(Keys.UP)) {
            velocityY += maxSpeed;
        }
        if (Gdx.input.isKeyPressed(Keys.DOWN)) {
            velocityY -= maxSpeed;
        }
        if (Gdx.input.isKeyPressed(Keys.SPACE)) {
            if (Gdx.input.isKeyPressed(Keys.LEFT)) {
                velocityX -= maxSpeed * 2;
            }
            if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
                velocityX += maxSpeed * 2;
            }
            if (Gdx.input.isKeyPressed(Keys.UP)) {
                velocityY += maxSpeed* 2;
            }
            if (Gdx.input.isKeyPressed(Keys.DOWN)) {
                velocityY -= maxSpeed* 2;
            }
        }

    }

    public void render() {


        hitbox.setPosition(playerX, playerY);
        batch.begin();

        String positionText = "Player Position: (" + Math.floor(playerX * 100) / 100 + ", " + Math.floor(playerY * 100) / 100 + ")";
        String collisionText = "Current Collision: " +  collisionTesting;
        String velocityText = "Velocity X: " +  velocityX + ", Velocity X: " +  velocityY;
        font.draw(batch, positionText, 10, Gdx.graphics.getHeight() - 10);
        font.draw(batch, collisionText, 10, Gdx.graphics.getHeight() - 25);
        font.draw(batch, velocityText, 10, Gdx.graphics.getHeight() - 40);
        font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), Gdx.graphics.getWidth() - 80, Gdx.graphics.getHeight() - 10);
        //batch.draw(hitbox, playerX, playerY);
        batch.draw(texture, playerX, playerY );

        batch.end();

    }
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
