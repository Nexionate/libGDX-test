package io.github.some_example_name;

public interface enemyInterface {
    void updateMovement();
    enemyAbstract assignAttributes(int health, int speed);
    void targetPlayer(final float playerX, final float playerY);
}
