package io.github.some_example_name;

public class WaveManager {
    private int waveNumber = 1;
    private int remainingEnemies = 0;
    private int waveEnemyCount = 0;
    private enemyAbstract enemy;

    public WaveManager(int waveNumber, int waveEnemyCount) {

        this.waveNumber = waveNumber;
        enemy = new enemyAbstract();
    }




}
