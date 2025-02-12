package io.github.some_example_name;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    //@Override

    public void create() {
        //setScreen(new FirstScreen());
        Sprite player = new Sprite();
        player.create();

        player.render();
        //System.out.println("Hello World");
    }
    public void render() {
        //ScreenUtils.clear(Color.BLACK);
        super.render();
        //System.out.println("created");
    }
    public static void main (String[] args)
    {
        Main myProgram = new Main();
        myProgram.create();
        myProgram.setScreen(new FirstScreen());
        myProgram.render();
    }

}

