package io.github.some_example_name;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;


public class Main extends Game {
    private Sprite player;
    private Background background;

    public void create() {
        setScreen(new FirstScreen());           // initializes the screen
        player = new Sprite();             // assigns a player of object Sprite
        player.create();                        // constructs the player

        background = new Background();
        background.create();
    }

    // the order of rendering matters, so we must make sure to render the BG first
    public void render() {
        ScreenUtils.clear(Color.BLACK);         // clears the background every frame

        background.render();

        player.movement();                      // checks for movement
        player.render();                        // displays the player on the screen

    }
}

