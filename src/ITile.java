import javafx.scene.image.ImageView;


// Tile interface with common methods
public interface ITile {
    public enum DrillStatus {
        DRILLABLE, DRILLED, UNDRILLABLE, LAVA
    }
    DrillStatus drillStatus();
    int getValue();
    int getWeight();
    void drill();
    ImageView getImage();


}
