package io.github.some_example_name;

public class WaveManager {
    private int waveNumber = 1;
    private int remainingEnemies = 0;
    private int waveEnemyCount = 0;

    public WaveManager(int waveNumber) {
        this.waveNumber = waveNumber;
    }

    public int determineWaveEnemyCount(final int waveNumber) {
        switch (waveNumber) {
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 3;
            case 4:
                return 4;
            case 5:
                return 5;
            default:
                return 0;

        }
    }


}
