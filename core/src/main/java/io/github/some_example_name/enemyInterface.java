package io.github.some_example_name;

public interface enemyInterface {
    void updateMovement();
    void assignAttributes(enemyAbstract enemy, int health, float speed, String colour);
    void targetPlayer(final float playerX, final float playerY);
}
