import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class BoulderTile implements ITile {
    private final ImageView image = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/assets/underground/obstacle_01.png")))) ;

    @Override
    public DrillStatus drillStatus() {
            return DrillStatus.UNDRILLABLE;
    }

    @Override
    public int getValue() {
        return 0;  // Boulders have no value
    }

    @Override
    public int getWeight() {
        return 0;  // Boulders have no weight
    }



    @Override
    public void drill() {
        return; // Boulders cannot be drilled
    }

    @Override
    public ImageView getImage() {
        return image;
    }


    
}
