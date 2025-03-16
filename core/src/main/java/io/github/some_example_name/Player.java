package io.github.some_example_name;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;


/**
 * Represents a player class.
 *
 * @author Ethan O'Connor
 * @version 2025
 */
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
    private int dashDuration = 20;
    private int dashCooldown = 300;

    private int fireDelay = 0;
    private int gold = 0;
    private int health = 3;
    private int hitInvincibility = 5;

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



        if (Gdx.input.isKeyPressed(Keys.SPACE) && dashCooldown <= 0) {
            dashDuration = 15;
            dashCooldown = 300;
            hitInvincibility = 30;
        }


    }


    public void collision(Rectangle[] walls){
        if(walls[0].overlaps(hitbox)){
            collisionTesting = "North";
            velocityY = Gdx.graphics.getHeight() - texture.getHeight() - 10;
        }
        else if(walls[1].overlaps(hitbox)){
            collisionTesting = "South";
            velocityY = 0;
        }
        else if(walls[2].overlaps(hitbox)){
            collisionTesting = "East";
            int screen = 1420;
            velocityX = screen - texture.getWidth() - 10;

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
        if (Gdx.input.isKeyPressed(Keys.SPACE) && dashDuration > 0) {
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
    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public void setHitInvincibility(final int duration){
        hitInvincibility = duration;
    }
    public int getHitInvincibility(){
        return hitInvincibility;
    }
    public Rectangle getHitbox() { return hitbox; }


    public int getGold() {
        return gold;
    }
    public void setGold(int gold) {
        this.gold = gold;
    }

    public void textRender(){
        String positionText = "Player Position:\n(" + Math.floor(playerX * 100) / 100 + ", " + Math.floor(playerY * 100) / 100 + ")";
        String positionCenterText = "Player Position: (" + Math.floor(getPlayerXCenter() * 100) / 100 + ", " + Math.floor(getPlayerYCenter() * 100) / 100 + ")";
        String collisionText = "Current Collision: " +  collisionTesting;
        String velocityText = "Velocity X: " +  velocityX + ", Velocity X: " +  velocityY;
        String dashCDText = "Dash CD: " +  dashCooldown;
        String dashDDText = "Dash Duration: " +  dashDuration;
        String healthText = "Health: " +  getHealth();
        String invulText = "Invul: " +  getHitInvincibility();
        String goldText = "Gold: " +  getGold();

        font.draw(batch, healthText, getPlayerXCenter() - 35, getPlayerYCenter() + (float) texture.getWidth() /2 + 20);
        font.draw(batch, velocityText, 10, Gdx.graphics.getHeight() - 40);
        font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), Gdx.graphics.getWidth() - 80, Gdx.graphics.getHeight() - 10);

        font.draw(batch, positionText, 1440, 800);
        font.draw(batch, invulText, 1440, 760);
        font.draw(batch, collisionText, 1440, 740);
        font.draw(batch, dashCDText, 1440, 400);
        font.draw(batch, dashDDText, 1440, 380);
        font.draw(batch, goldText, 1440, 360);
        //font.draw(batch, positionCenterText, 10, Gdx.graphics.getHeight() - 70);
    }

    public void update(){
        if (hitInvincibility > 0) {
            hitInvincibility--;
        }
        if (dashDuration > 0) {
            dashDuration--;
        }
        if (dashCooldown > 0) {
            dashCooldown--;
        }
    }

    public void render() {
        update();
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
