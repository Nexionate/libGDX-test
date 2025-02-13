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

public class Background {
    private SpriteBatch batch;
    private Texture texture;
    private Rectangle wWall;
    private Rectangle nWall;
    private Rectangle eWall;
    private Rectangle sWall;
    private int sum = 0;

    public void create(){
        batch = new SpriteBatch();
        texture = new Texture(Gdx.files.internal("background.png"));
        createWalls();

    }
    public void createWalls(){
        int resY = Gdx.graphics.getHeight();
        int resX = Gdx.graphics.getWidth();
        wWall = new Rectangle(0, 0, 1, resY);
        nWall = new Rectangle(0, resY, resX, 1);
        eWall = new Rectangle(resX, 0, 1, resY);
        sWall = new Rectangle(resX, resY, resX, 1);
            //playerY, texture.getWidth(), texture.getHeight())
    }
    public void wallCollision(Rectangle hitbox){
        if (wWall.overlaps(hitbox)){
            System.out.println("Wall collision west: " + sum);
            sum +=1;
        }
    }
    public void render(){
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(texture, 0, 0);
        batch.end();
    }
}
