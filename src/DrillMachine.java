import com.sun.javafx.scene.traversal.Direction;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.Objects;

public class DrillMachine {
    private static final float MOVE_CONSUMPTION = 100F;
    private static final float INITIAL_FUEL = 10000F;

    private int x, y;
    private float fuel;
    private int money;
    private int weight;
    private Direction facing;
    private final ImageView imageView;


    public DrillMachine(int x, int y) { // Constructor , sets the initial values of the drill machine and its position
        this.x = x;
        this.y = y;
        this.fuel = INITIAL_FUEL;
        this.money = 0;
        this.facing = Direction.DOWN;
        this.imageView = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/drill/drill_11.png"))));
    }

    public void move(Direction direction, boolean goUp)  { // Moves the drill machine in the given direction
        if (fuel < MOVE_CONSUMPTION) return; // if the fuel is less than the consumption, return

        facing = direction;
        switch (direction) {
            case UP:
                if ((y > 0)) { if (goUp) y--;} // if its able to go up, go up
            break;
            case DOWN:
                if (y < 15) y++;
                break;
            case LEFT:
                if (x > 0) x--;
                break;
            case RIGHT:
                if (x < 15) x++;
                break;
        }
        consumeFuel(MOVE_CONSUMPTION);
        updateImage(); // update the image of the drill machine
    }

    public void consumeFuel(float fuelConsumption) {
        if (fuel > 0 && fuel >= fuelConsumption)
            fuel -= fuelConsumption;
        else {
            fuel = 0;
        }
    }

    public void collectValuable(ITile valuable) { // Collects the valuable tile
        if (valuable.drillStatus() == ITile.DrillStatus.DRILLABLE) {
            money += valuable.getValue();
            weight += valuable.getWeight();
            valuable.drill();
        }

    }

    public void handleLavaCollision() {
        money = 0;
        weight = 0;
    }

    public void updateImage()  { // Updates the image of the drill machine according to its direction
        switch (facing) {
            case UP:
                imageView.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/drill/drill_25.png"))));
                break;
            case DOWN:
                imageView.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/drill/drill_40.png"))));
                break;
            case LEFT:
                imageView.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/drill/drill_01.png"))));
                break;
            case RIGHT:
                imageView.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/drill/drill_55.png"))));
                break;
        }
    }

    public ImageView getImageView() {
        return imageView;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public float getFuel() {
        return fuel;
    }

    public int getMoney() {
        return money;
    }

    public int getWeight() {
        return weight;
    }


}
