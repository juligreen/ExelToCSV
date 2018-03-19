import javafx.scene.control.Alert;

public class BetterErrorAlert extends Alert {

    public BetterErrorAlert(String title, String headerText, String contentText) {
        super(AlertType.ERROR);

        this.setTitle(title);
        this.setHeaderText(headerText);
        this.setContentText(contentText);
        this.showAndWait();


    }
}
