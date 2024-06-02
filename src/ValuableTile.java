import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.Locale;
import java.util.Objects;

public class ValuableTile implements ITile {
    private int value;
    private int weight;
    private final ImageView image;
    private boolean drilled = false;
    private final boolean drillable = true;

    public ValuableTile(String subtype) {
        Image image = loadImage(subtype);
        this.image = new ImageView(image);
        setProperties(subtype);
    }

    private Image loadImage(String subtype) { // Loads the image of the valuable tile
        return new Image(Objects.requireNonNull(getClass().getResourceAsStream("/assets/underground/valuable_" + subtype.toLowerCase(Locale.ROOT) + ".png")));
    }

    private void setProperties(String subtype) {
        switch (subtype) {
            case "Ironium":
                this.value = 30;
                this.weight = 10;
                break;
            case "Bronzium":
                this.value = 60;
                this.weight = 10;
                break;
            case "Silverium":
                this.value = 100;
                this.weight = 10;
                break;
            case "Goldium":
                this.value = 250;
                this.weight = 20;
                break;
            case "Platinium":
                this.value = 750;
                this.weight = 30;
                break;
            case "Einsteinium":
                this.value = 2000;
                this.weight = 40;
                break;
            case "Emerald":
                this.value = 5000;
                this.weight = 60;
                break;
            case "Ruby":
                this.value = 20000;
                this.weight = 80;
                break;
            case "Diamond":
                this.value = 100000;
                this.weight = 100;
                break;
            case "Amazonite":
                this.value = 500000;
                this.weight = 120;
                break;
                default:
                System.out.println("Invalid mineral type specified: " + subtype);
                break;
        }
    }

    @Override
    public DrillStatus drillStatus() {
        return drilled ? DrillStatus.DRILLED : (drillable ? DrillStatus.DRILLABLE : DrillStatus.UNDRILLABLE);
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    @Override
    public ImageView getImage() {
        return image;
    }

    @Override
    public void drill() {
        if (drillable) {
            drilled = true;
            image.setOpacity(0.0);
            value = 0;
            weight = 0;
        }
    }
}
