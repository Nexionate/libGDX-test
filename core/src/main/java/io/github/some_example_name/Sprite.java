package io.github.some_example_name;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
    private float velocityX;
    private float velocityY;

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
    }

    public void movement(){
        if (Gdx.input.isKeyPressed(Keys.LEFT)) {
            playerX -= 2;
        }
        if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
            playerX += 2;
        }
        if (Gdx.input.isKeyPressed(Keys.UP)) {
            playerY += 2;
        }
        if (Gdx.input.isKeyPressed(Keys.DOWN)) {
            playerY -= 2;
        }
    }

    public void render() {


        hitbox.setPosition(playerX, playerY);
        batch.begin();
        String positionText = "Player Position: (" + playerX + ", " + playerY + ")";
        font.draw(batch, positionText, 10, Gdx.graphics.getHeight() - 10);
        batch.draw( texture, playerX, playerY );
        batch.end();

    }
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
