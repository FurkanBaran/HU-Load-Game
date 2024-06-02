import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

// EarthTile class that implements ITile interface
public class EarthTile implements ITile {
    private boolean drilled = false;
    private final ImageView image;


    public EarthTile(String subtype) {
        java.io.InputStream file;
        switch (subtype) {
            case "sky": { file = getClass().getResourceAsStream("/assets/underground/empty_15.png");
                         drilled = true;    }
            break;
            case "topsoil": file = getClass().getResourceAsStream("/assets/underground/top_01.png");
            break;
            default : file = getClass().getResourceAsStream("/assets/underground/soil_01.png");

        }
        assert file != null;
        this.image = new ImageView(new Image(file));


    }

    @Override
    public DrillStatus drillStatus() {
        if (drilled)
            return DrillStatus.DRILLED;
        else
            return DrillStatus.DRILLABLE;
    }

    @Override
    public int getValue() {
        return 0; // Earth tiles have no value
    }

    @Override
    public int getWeight() {
        return 0; // Earth tiles have no weight
    }

    @Override
    public ImageView getImage() {
        return image;
    }

    @Override
    public void drill() {
        drilled = true;
        image.setOpacity(0.0);
    }
}

