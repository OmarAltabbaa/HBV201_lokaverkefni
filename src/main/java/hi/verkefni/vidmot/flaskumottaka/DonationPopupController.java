package hi.verkefni.vidmot.flaskumottaka;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

public class DonationPopupController {

    @FXML
    private ChoiceBox<String> organizationChoice;

    private FloskurController parentController;  // 👈 reference to main controller

    public void setParentController(FloskurController controller) {
        this.parentController = controller;
    }

    @FXML
    public void initialize() {
        organizationChoice.getItems().addAll(
                "UNICEF Iceland",
                "Icelandic Nature Conservation Association",
                "Barnaheill"
        );
        organizationChoice.setValue("UNICEF Iceland");
    }

    @FXML
    private void onDonateClicked() {
        String selected = organizationChoice.getValue();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Takk fyrir!");
        alert.setHeaderText(null);
        alert.setContentText("Takk fyrir að styrkja " + selected );
        alert.showAndWait();

        if (parentController != null) {
            parentController.resetAfterDonation();
        }
        // Close the popup window
        Stage stage = (Stage) organizationChoice.getScene().getWindow();
        stage.close();
    }
}
