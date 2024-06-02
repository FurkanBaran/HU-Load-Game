import java.util.Random;

public class GameBoard {
    private ITile[][] tiles = new ITile[16][16];
    private Random random = new Random();
    private int GRID_SIZE;
    public GameBoard(int gridSize) {
        this.GRID_SIZE = gridSize;
        initializeTiles();
    }

    private void initializeTiles() {
        // first 3 rows are sky
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                tiles[i][j] = new EarthTile("sky");
            }
        }

        // fill the 4th row with topsoil
        for (int j = 0; j < 16; j++) {
            tiles[3][j] = new EarthTile("topsoil");
        }

        // fill the rest with soil and boulders
        for (int i = 4; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if (i == GRID_SIZE-1 || j == 0 || j == GRID_SIZE-1) {
                    tiles[i][j] = new BoulderTile();  // add boulders to the edges
                } else {
                    tiles[i][j] = new EarthTile("soil");
                }
            }
        }

        // add random valuable tiles
        int valuableCount = random.nextInt(8) +5;
        for (int k = 0; k < valuableCount; k++) {
            int x = random.nextInt(11) + 4;
            int y = random.nextInt(14) + 1;
            tiles[x][y] = new ValuableTile(randomValuableType());
        }

        // add random lava tiles
        for (int k = 0; k < valuableCount/2; k++) {
            int x = random.nextInt(GRID_SIZE-5) + 4;
            int y = random.nextInt(GRID_SIZE-2) + 1;
            tiles[x][y] = new LavaTile();
        }

        // add random boulder tiles
        for (int k = 0; k < 3; k++) {
            int x = random.nextInt(GRID_SIZE-5) + 4;
            int y = random.nextInt(GRID_SIZE-2) + 1;
            tiles[x][y] = new BoulderTile();
        }


    }

    private String randomValuableType() {
        String[] types = {"Ironium", "Bronzium", "Silverium", "Goldium", "Platinium", "Einsteinium", "Emerald", "Ruby", "Diamond", "Amazonite"};
        return types[random.nextInt(types.length)];
    }

    public ITile[][] getTiles() {
        return tiles;
    }

    public ITile getTile(int x, int y) {
        return tiles[y][x];
    }



}
