import com.sun.javafx.scene.traversal.Direction;
import javafx.scene.input.KeyCode;

public class GameController {
    enum GameState { PLAYING, GAME_OVER_WIN, GAME_OVER_LOSE     }


    private final DrillMachine drillMachine;
    private final GameBoard gameBoard;
    private GameState gameState = GameState.PLAYING;
    private int GRID_SIZE ;

    public GameController(int gridSize) { // Constructor
        this.GRID_SIZE = gridSize;
        this.gameBoard = new GameBoard(GRID_SIZE); // Create a new game board
        this.drillMachine = new DrillMachine((GRID_SIZE/2)-1, 2); // starting position of the drill machine
    }


    public DrillMachine getDrillMachine() {
        return drillMachine;
    }
    public GameBoard getGameBoard() {
        return gameBoard;
    }
    public GameState getGameState() {
        return gameState;
    }

    public void updateGame() {
        checkGameEndConditions();
        checkCollisions();

    }

    private void checkCollisions() { // Checks the collisions of the drill machine
        ITile currentTile = gameBoard.getTile(drillMachine.getX(), drillMachine.getY()); // Get the current tile

        if (currentTile.drillStatus()== ITile.DrillStatus.DRILLABLE ) { // If the current tile is a valuable tile and it is drillable
            drillMachine.collectValuable(currentTile); // Collect the valuable tile
        }
        else if (currentTile.drillStatus()== ITile.DrillStatus.LAVA) { // If the current tile is lava
            drillMachine.handleLavaCollision(); // Handle the lava collision
            gameState=GameState.GAME_OVER_LOSE; // Set the game state to game over lose
        }

    }

    private void checkGameEndConditions() { // Check the game end conditions
        if (drillMachine.getFuel() <= 0) { // If the fuel is less than or equal to 0
            this.gameState = GameState.GAME_OVER_WIN; // Set the game state to game over win
        }

    }

    public void applyGravity()  {
        int currentX = drillMachine.getX();
        int currentY = drillMachine.getY();
        if (currentY < GRID_SIZE) {  //  If the drill machine is not at the bottom of the game board
            ITile tileBelow = gameBoard.getTile(currentX, currentY + 1);
            if (tileBelow != null && tileBelow.drillStatus() == ITile.DrillStatus.DRILLED) {
                drillMachine.move(Direction.DOWN, true);
            }
        }
    }

    public void consumeFuel(float fuelConsumption) {
        drillMachine.consumeFuel(fuelConsumption);
        checkGameEndConditions();
    }

    public void handleKeyPress(KeyCode key) {
        if (drillMachine.getFuel() <= 100) {
            gameState = GameState.GAME_OVER_WIN;
        }
        ITile nextTile;

        switch (key) {
            case UP:
                if (drillMachine.getY() > 0) {
                    nextTile = gameBoard.getTile(drillMachine.getX(), drillMachine.getY() - 1);
                    drillMachine.move(Direction.UP, nextTile.drillStatus() == ITile.DrillStatus.DRILLED);
                }
                break;
            case DOWN:
                if (drillMachine.getY() < 15) {
                    nextTile = gameBoard.getTile(drillMachine.getX(), drillMachine.getY() + 1);
                    if (nextTile.drillStatus() != ITile.DrillStatus.UNDRILLABLE) {
                        drillMachine.move(Direction.DOWN, true);
                    }
                }
                break;
            case LEFT:
                if (drillMachine.getX() > 0) {
                    nextTile = gameBoard.getTile(drillMachine.getX() - 1, drillMachine.getY());
                    if (nextTile.drillStatus() != ITile.DrillStatus.UNDRILLABLE) {
                        drillMachine.move(Direction.LEFT, true);
                    }
                }
                break;
            case RIGHT:
                if (drillMachine.getX() < 15) {
                    nextTile = gameBoard.getTile(drillMachine.getX() + 1, drillMachine.getY());
                    if (nextTile.drillStatus() != ITile.DrillStatus.UNDRILLABLE) {
                        drillMachine.move(Direction.RIGHT, true);
                    }
                }
                break;
        }
        checkGameEndConditions();
        updateGame();
    }

}

