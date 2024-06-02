import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class LavaTile implements ITile {

    ImageView image= new ImageView( new Image(Objects.requireNonNull(getClass().getResourceAsStream("/assets/underground/lava_01.png"))));

    @Override
    public DrillStatus drillStatus() {
        return DrillStatus.LAVA;
    }

    @Override
    public int getValue() {
        return 0;  // Lava has no value
    }

    @Override
    public int getWeight() {
        return 0;  // Lava has no weight
    }


    @Override
    public ImageView getImage() {
        return image;
    }


    @Override
    public void drill() {
          return; // Lava cannot be drilled
    }
}