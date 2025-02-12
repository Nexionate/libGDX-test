package io.github.some_example_name;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    //@Override
    Sprite player = new Sprite();
    public void create() {
        player.create();
    }
    public void render() {
        ScreenUtils.clear(Color.BLACK);
        player.render();

    }
    public static void main (String[] args)
    {
        Main myProgram = new Main();
        myProgram.create();
        myProgram.setScreen(new FirstScreen());
        //myProgram.render();
    }

}

