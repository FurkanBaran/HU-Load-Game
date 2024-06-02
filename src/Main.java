import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Label;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Main extends Application {
    private static final int CELL_SIZE = 50;
    private static final int GRID_SIZE = 16;
    private static final int STATS_SPACING = 10;
    private static final int FUEL_CONSUMPTION_INTERVAL = 100;
    private static final int GRAVITY_INTERVAL = 1000;

    private GameController gameController;
    private Label fuelLabel, moneyLabel, weightLabel, gameOverLabel;
    private Timeline fuelConsumptionTimeline;
    private Scene scene;

    @Override
    public void start(Stage primaryStage) {
        gameController = new GameController(GRID_SIZE); // Create a new game controller
        BorderPane root = new BorderPane(); // Create a new border pane
        VBox statsBox = new VBox(STATS_SPACING); // Create a new VBox
        statsBox.setAlignment(Pos.TOP_LEFT); // Set the alignment of the VBox to top left
        scene = new Scene(root, CELL_SIZE * GRID_SIZE, CELL_SIZE * GRID_SIZE); // Create a new scene

        fuelLabel = createLabel(30);
        moneyLabel = createLabel(30);
        weightLabel = createLabel(30);
        statsBox.getChildren().addAll(fuelLabel, moneyLabel, weightLabel);

        gameOverLabel = createLabel(48);
        gameOverLabel.setAlignment(Pos.CENTER);
        gameOverLabel.setVisible(false);
        GridPane grid = new GridPane(); // Create a new grid pane

        fuelConsumptionTimeline = new Timeline( // Create a new timeline for fuel consumption
                new KeyFrame(Duration.millis(FUEL_CONSUMPTION_INTERVAL), e -> {
                    gameController.consumeFuel(1.5F);
                    updateStatsDisplay();
                    GameController.GameState gameState = gameController.getGameState();
                    if (gameState == GameController.GameState.GAME_OVER_LOSE) {
                        handleGameOver(Color.RED, "GAME OVER", grid);
                    } else if (gameState == GameController.GameState.GAME_OVER_WIN) {
                        handleGameOver(Color.GREEN, "GAME OVER!\nCollected money: $" + gameController.getDrillMachine().getMoney(), grid);
                    }
                })
        );

        fuelConsumptionTimeline.setCycleCount(Timeline.INDEFINITE); // Set the cycle count of the timeline to indefinite
        fuelConsumptionTimeline.play(); // Play the timeline

        grid.setHgap(0); // Set the horizontal gap of the grid to 0
        grid.setVgap(0);    // Set the vertical gap of the grid to 0
        updateGrid(grid); // Update the grid

        StackPane centerStack = new StackPane(); // Create a new stack pane
        centerStack.getChildren().addAll(grid, statsBox, gameOverLabel); // Add the grid, stats box, and game over label to the stack pane
        StackPane.setAlignment(statsBox, Pos.TOP_LEFT); // Set the alignment of the stats box to top left
        StackPane.setAlignment(gameOverLabel, Pos.CENTER); // Set the alignment of the game over label to center
        root.setCenter(centerStack); // Set the center of the border pane to the stack pane

        scene.setOnKeyPressed(event -> handleKeyPress(event.getCode(), grid)); // Set the key press event handler for the scene

        primaryStage.setTitle("HU-Load Game");
        primaryStage.setScene(scene);
        primaryStage.show();
        updateStatsDisplay();
    }

    private Label createLabel(int fontSize) {
        Label label = new Label();
        label.setFont(new Font("Arial", fontSize));
        label.setTextFill(Color.WHITE);
        return label;
    }

    private void handleGameOver(Color color, String message, GridPane grid) {
        gameOverLabel.setText(message);
        //  text alignment to center
        gameOverLabel.setAlignment(Pos.CENTER);
        gameOverLabel.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

        gameOverLabel.setVisible(true);
        // set fuelLabel, moneyLabel, weightLabel invisible
        fuelLabel.setVisible(false);
        moneyLabel.setVisible(false);
        weightLabel.setVisible(false);

        fuelConsumptionTimeline.stop();
        grid.setVisible(true);
        setAllCellsBackground(color, grid);
        scene.setOnKeyPressed(event -> {});
    }

    private void updateGrid(GridPane grid) {
        if (gameController.getGameState() != GameController.GameState.PLAYING) { // If the game is not in the playing state, return
            return;
        }
        ITile[][] tiles = gameController.getGameBoard().getTiles();
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                StackPane cell = new StackPane();
                setCellBackground(i, cell);
                ImageView tileImage = tiles[i][j].getImage();
                tileImage.setFitHeight(CELL_SIZE);
                tileImage.setFitWidth(CELL_SIZE);
                cell.getChildren().add(tileImage);

                if (i == gameController.getDrillMachine().getY() && j == gameController.getDrillMachine().getX()) { // If the cell is the drill machine
                    ImageView machineImage = gameController.getDrillMachine().getImageView();
                    machineImage.setFitHeight(CELL_SIZE);
                    machineImage.setFitWidth(CELL_SIZE);
                    cell.getChildren().add(machineImage);
                }
                grid.add(cell, j, i);
            }
        }
    }

    private void setCellBackground(int row, StackPane cell) { // Set the background color of the cell based on the row. blue for sky, brown for underground
        Rectangle background = new Rectangle(CELL_SIZE, CELL_SIZE);
        background.setFill(row < 3 ? Color.BLUE : Color.SADDLEBROWN);
        cell.getChildren().add(background);
    }

    private void handleKeyPress(KeyCode code, GridPane grid) { // Handle key press events
        gameController.handleKeyPress(code);
        updateGrid(grid);
        Timeline gravityTimeline = new Timeline(    // Create a new timeline for gravity
                new KeyFrame(Duration.millis(GRAVITY_INTERVAL), e -> {
                    gameController.applyGravity();
                    updateGrid(grid);
                    updateStatsDisplay();
                })
        );
        gravityTimeline.play();
    }

    private void updateStatsDisplay() { // Update the stats display
        DrillMachine machine = gameController.getDrillMachine();
        fuelLabel.setText("Fuel: " + machine.getFuel());
        moneyLabel.setText("Money: $" + machine.getMoney());
        weightLabel.setText("Weight: " + machine.getWeight() + "kg");
    }

    private void setCellBackground(Color color, StackPane cell) { // Set the background color of the cell
        Rectangle background = new Rectangle(CELL_SIZE, CELL_SIZE);
        background.setFill(color == Color.GREEN ? Color.GREEN : Color.RED);
        cell.getChildren().add(background);
    }

    private void setAllCellsBackground(Color color, GridPane grid) { // Set the background color of all cells, its color is green if the player wins, red if the player loses
        for (int i = 0; i < grid.getChildren().size(); i++) {
            StackPane cell = (StackPane) grid.getChildren().get(i);
            setCellBackground(color, cell);
        }
    }
    public static void main(String[] args) {
        launch(args); // Launch the application
    }


}
