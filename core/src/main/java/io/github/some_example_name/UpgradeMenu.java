package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class UpgradeMenu implements InputProcessor {
    private boolean keyJustPressed = false;
    public void create(){


    }
    public void render(){

    }
//    public int checkOpenMenu(int gameState){
//        boolean isPressed = Gdx.input.isKeyPressed(Input.Keys.E);
//
////        if (Gdx.input.isKeyPressed(Input.Keys.E) ) {
////            if (gameState == 1) {
////                gameState = 0;
////            } else if (gameState == 0) {
////                gameState = 1;
////            }
////
////        }
////        return gameState;
//    }

    @Override
    public boolean keyDown(int i) {
        if (i == Input.Keys.E && !keyJustPressed) {
            keyJustPressed = true;  // Mark that the key was just pressed
        }
        return false;
    }

    @Override
    public boolean keyUp(int i) {
        // Check if the E key is released to reset the flag
        if (i == Input.Keys.E) {
            keyJustPressed = false;  // Reset the flag to allow key press again
        }
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchCancelled(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(float v, float v1) {
        return false;
    }
}
