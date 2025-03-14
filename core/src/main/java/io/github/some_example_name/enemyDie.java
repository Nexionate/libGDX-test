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


public class enemyDie extends Game{
    private SpriteBatch batch;
    private Texture[] frames;
    private int currentFrame;
    private float timePerFrame = 0.1f; // Time for each frame (in seconds)
    private float timeElapsed;
    private float xPos = 300;
    private float yPos = 300;
    private String colour;
    private Sprite sprite;

    @Override
    public void create() {
        batch = new SpriteBatch();
        frames = new Texture[]{
            new Texture("enemyDie/frame_00_delay-0.05s.png"),
            new Texture("enemyDie/frame_01_delay-0.05s.png"),
            new Texture("enemyDie/frame_02_delay-0.05s.png"),
            new Texture("enemyDie/frame_03_delay-0.05s.png"),
            new Texture("enemyDie/frame_04_delay-0.05s.png"),
            new Texture("enemyDie/frame_05_delay-0.05s.png"),
            new Texture("enemyDie/frame_06_delay-0.05s.png"),
            new Texture("enemyDie/frame_07_delay-0.05s.png"),
            new Texture("enemyDie/frame_08_delay-0.05s.png"),
            new Texture("enemyDie/frame_09_delay-0.05s.png"),
            new Texture("enemyDie/frame_11_delay-0.05s.png"),
            new Texture("enemyDie/frame_12_delay-0.05s.png"),
            new Texture("enemyDie/frame_10_delay-0.05s.png"),
            new Texture("enemyDie/frame_13_delay-0.05s.png"),
            new Texture("enemyDie/frame_14_delay-0.05s.png"),
            new Texture("enemyDie/frame_15_delay-0.05s.png"),
            new Texture("enemyDie/frame_16_delay-0.05s.png"),
            new Texture("enemyDie/frame_17_delay-0.05s.png"),
            new Texture("enemyDie/frame_18_delay-0.05s.png"),
            new Texture("enemyDie/frame_19_delay-0.05s.png"),
            new Texture("enemyDie/frame_20_delay-0.05s.png"),
            new Texture("enemyDie/frame_21_delay-0.05s.png"),
            new Texture("enemyDie/frame_22_delay-0.05s.png"),
            new Texture("enemyDie/frame_23_delay-0.05s.png"),
            new Texture("enemyDie/frame_24_delay-0.05s.png"),
            new Texture("enemyDie/frame_25_delay-0.05s.png"),
            new Texture("enemyDie/frame_26_delay-0.05s.png"),
        };
        currentFrame = 0;
        timeElapsed = 0f;
    }

    public void setPosition(float x, float y, String thisColour){
        yPos = y;
        xPos = x;
        colour = thisColour;
    }
    public boolean getDone(){
        return this.currentFrame < 25;
    }
    public void render() {
        sprite = new Sprite(frames[currentFrame]);
        sprite.setScale(0.5f, 0.5f);
        if (Objects.equals(this.colour, "blue")) {
            sprite.setColor(0 , 0 , 1 , 1); // Lighter blue
        } else if (Objects.equals(this.colour, "red")) {
            sprite.setColor(1 , 0 , 0 , 1); // Lighter red
        } else if (Objects.equals(this.colour, "green")) {
            sprite.setColor(0 , 1 , 0 , 1); // Lighter green
        }

        timeElapsed += Gdx.graphics.getDeltaTime();
        if (timeElapsed >= timePerFrame) {
            timeElapsed -= timePerFrame;
            currentFrame = (currentFrame + 1) % frames.length; // Loop through frames
        }


        sprite.setPosition(xPos - 175, yPos - 150);
        batch.begin();
        sprite.draw(batch);
        batch.end();
    }
}
