package io.github.some_example_name;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
public class Player extends Game{

    private SpriteBatch batch;
    private Texture texture;
    private BitmapFont font;
    private float playerX;
    private float playerY;
    private float maxSpeed = 5.0f;
    private float velocityX;
    private float velocityY;
    private String collisionTesting;
    private String velocityText;
    private int dashTimer;
    private int fireDelay = 0;

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
        if (dashTimer >= 100){
            dashTimer = 100;
        } else if (dashTimer < 0){
            dashTimer = 0;
        }
        if (!Gdx.input.isKeyPressed(Keys.SPACE)){
            dashTimer++;
        } else if (Gdx.input.isKeyPressed(Keys.SPACE)){
            if (dashTimer >= 100){
                dashTimer -=20;
            }
            else{

                dashTimer -=2;
            }
        }

    }


    public void collision(Rectangle[] walls){
        if(walls[0].overlaps(hitbox)){
            collisionTesting = "North";
            velocityY = Gdx.graphics.getHeight() - texture.getHeight() - 10;
        }
        else if(walls[1].overlaps(hitbox)){
            collisionTesting = "South";
//            velocityY = 0;
            //velocityY = 0 - maxSpeed;
            velocityY = 0;
        }
        else if(walls[2].overlaps(hitbox)){
            collisionTesting = "East";
            velocityX = Gdx.graphics.getWidth() - texture.getWidth() - 10;

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
        if (Gdx.input.isKeyPressed(Keys.A)) {
            velocityX -= maxSpeed;
        }
        if (Gdx.input.isKeyPressed(Keys.D)) {
            velocityX += maxSpeed;
        }
        if (Gdx.input.isKeyPressed(Keys.W)) {
            velocityY += maxSpeed;
        }
        if (Gdx.input.isKeyPressed(Keys.S)) {
            velocityY -= maxSpeed;
        }
        if (Gdx.input.isKeyPressed(Keys.SPACE) && dashTimer > 60) {
            if (Gdx.input.isKeyPressed(Keys.A)) {
                velocityX -= maxSpeed * 3;
            }
            if (Gdx.input.isKeyPressed(Keys.D)) {
                velocityX += maxSpeed * 3;
            }
            if (Gdx.input.isKeyPressed(Keys.W)) {
                velocityY += maxSpeed* 3;
            }
            if (Gdx.input.isKeyPressed(Keys.S)) {
                velocityY -= maxSpeed* 3;
            }

        }


    }
    public int inputBullet(){
        if (fireDelay > 30) {
            if (Gdx.input.isKeyPressed(Keys.LEFT)) {
                fireDelay = 0;
                return 2;
            } else if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
                fireDelay = 0;
                return 4;
            } else if (Gdx.input.isKeyPressed(Keys.UP)) {
                fireDelay = 0;
                return 1;
            } else if (Gdx.input.isKeyPressed(Keys.DOWN)) {
                fireDelay = 0;
                return 3;
            }

        }
        fireDelay++;
        return 0;
    }



    public float getPlayerX() {
        return playerX;
    }
    public float getPlayerY() {
        return playerY;
    }
    public float getPlayerXCenter() {
        return playerX + (float) texture.getWidth() / 2;
    }
    public float getPlayerYCenter() {
        return playerY + (float) texture.getHeight() / 2;
    }

    public void textRender(){
        String positionText = "Player Position: (" + Math.floor(playerX * 100) / 100 + ", " + Math.floor(playerY * 100) / 100 + ")";
        String positionCenterText = "Player Position: (" + Math.floor(getPlayerXCenter() * 100) / 100 + ", " + Math.floor(getPlayerYCenter() * 100) / 100 + ")";
        String collisionText = "Current Collision: " +  collisionTesting;
        String velocityText = "Velocity X: " +  velocityX + ", Velocity X: " +  velocityY;
        String dashText = "Dash: " +  dashTimer;

        font.draw(batch, positionText, 10, Gdx.graphics.getHeight() - 10);

        font.draw(batch, collisionText, 10, Gdx.graphics.getHeight() - 25);
        font.draw(batch, velocityText, 10, Gdx.graphics.getHeight() - 40);
        font.draw(batch, dashText, 10, Gdx.graphics.getHeight() - 55);
        font.draw(batch, positionCenterText, 10, Gdx.graphics.getHeight() - 70);
        font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), Gdx.graphics.getWidth() - 80, Gdx.graphics.getHeight() - 10);
    }

    public void render() {
        hitbox.setPosition(playerX, playerY);

        batch.begin();
        textRender();
        batch.draw(texture, playerX, playerY );
        batch.end();


    }
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
