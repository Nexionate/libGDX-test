package io.github.some_example_name;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.Game;

public class playerBullet extends Game {
    private Sprite sprite;
    private SpriteBatch batch;
    private Texture texture;

    private Rectangle bulletHitbox;
    private float bulletX = 0;
    private float bulletY = 0;
    private float speed = 5;
    private int direction;
    private int damage = 5;

    @Override
    public void create() {

        batch = new SpriteBatch();

        texture = new Texture( Gdx.files.internal("assets/bullet.png") );
        sprite = new Sprite(texture);
        bulletHitbox = new Rectangle( bulletX, bulletY, texture.getWidth(), texture.getHeight());
    }

    public void setBulletPosition(float x, float y, int direction) {
        bulletX = x;
        bulletY = y;
        this.direction = direction;
    }

    public Rectangle getBulletHitbox() {
        return bulletHitbox;
    }

    public int getDamage() {
        return damage;
    }

    public void updateMovement() {
        // FOLLOWS  1:W,    2:A,     3:S,   4:D

//        switch (direction) {
//            case 1: bulletY += speed; break;
//            case 2: bulletX -= speed; break;
//            case 3: bulletY -= speed; break;
//            case 4: bulletX += speed; break;
//        }
        switch (direction) {
            case 1: bulletY += speed; break;
            case 2: bulletX -= speed; break;
            case 3: bulletY -= speed; break;
            case 4: bulletX += speed; break;
        }

    }
    public void render() {
        sprite.setRotation((direction - 1) * 90);
        bulletHitbox.setPosition(bulletX, bulletY);
        sprite.setPosition(bulletX, bulletY);
        batch.begin();
//        if (!((bulletX > Gdx.graphics.getWidth() || bulletX < 0) && (bulletY > Gdx.graphics.getHeight() || bulletY < 0))){
        if (!((bulletX + sprite.getWidth() - 10 > Gdx.graphics.getWidth() || bulletX < -10))){

            sprite.draw(batch);
        }



        batch.end();
    }
    public void dispose() {
        batch.dispose();
    }
}
