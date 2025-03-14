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


    public void create(){
        batch = new SpriteBatch();
        //texture = new Texture(Gdx.files.internal("exampleBG1.png"));
        createWalls();

    }
    public Rectangle[] createWalls(){
        Rectangle[] walls = new Rectangle[4];
        int resY = Gdx.graphics.getHeight();
        int resX = 1420;
        walls[0] = new Rectangle(0, resY, resX, 1);
        walls[1] = new Rectangle(0, 0, resX, 1);
        walls[2] = new Rectangle(resX, 0, 1, resY);
        walls[3] = new Rectangle(0, 0, 1, resY);
        return walls;

    }


    public void render(){
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        //batch.draw(texture, 0, 0);
        batch.end();
    }
}
