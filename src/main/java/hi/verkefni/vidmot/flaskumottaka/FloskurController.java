/**
 * Controller for managing user input and interactions related to cans and bottles.
 * Updates the UI labels and processes data using the Floskur model.
 */
package hi.verkefni.vidmot.flaskumottaka;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import vinnsla.Floskur;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;


import java.io.IOException;

public class FloskurController {

    @FXML
    private TextField dosirTextField;

    @FXML
    private TextField floskurTextField;

    @FXML
    private Label samtalsVirdiLabel;
    @FXML
    private Label totalDosirLabel;
    @FXML
    private Label totalFloskurLabel;
    @FXML
    private Label totalOverallLabel;
    @FXML
    private Label usdLabel;
    @FXML
    private Label eurLabel;
    @FXML
    private Label usdTotalLabel;
    @FXML
    private Label eurTotalLabel;
    @FXML
    private Label treesSavedLabel;



    private Floskur vinnsla;

    private static final double ISK_TO_USD = 0.0073;
    private static final double ISK_TO_EUR = 0.0067;

    /**
     * Initializes the controller and its associated model.
     */
    public void initialize() {
        vinnsla = new Floskur();
    }

    /**
     * Handles user input for cans, validates it, and updates the value in the model.
     */
    @FXML
    private void onDosirEntered() {
        try {
            int dosir = Integer.parseInt(dosirTextField.getText());
            if (dosir < 0) {
                throw new IllegalArgumentException("Dósir cannot be negative.");
            }
            vinnsla.setDosir(dosir);
            updateLabels();
        } catch (NumberFormatException e) {
            dosirTextField.setStyle("-fx-background-color: #ffcccc;");
            System.out.println("Invalid input for dósir: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            dosirTextField.setStyle("-fx-background-color: #ffcccc;");
            System.out.println(e.getMessage());
        }
    }

    /**
     * Handles user input for bottles, validates it, and updates the value in the model.
     */
    @FXML
    private void onFloskurEntered() {
        try {
            int floskur = Integer.parseInt(floskurTextField.getText());
            if (floskur < 0) {
                throw new IllegalArgumentException("Flöskur cannot be negative.");
            }
            vinnsla.setFloskur(floskur);
            updateLabels();
        } catch (NumberFormatException e) {
            floskurTextField.setStyle("-fx-background-color: #ffcccc;");
            System.out.println("Invalid input for floskur: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            floskurTextField.setStyle("-fx-background-color: #ffcccc;");
            System.out.println(e.getMessage());
        }
    }

    /**
     * Resets the current counts and clears the input fields.
     */
    @FXML
    private void onHreinsa() {
        vinnsla.hreinsa();
        dosirTextField.clear();
        floskurTextField.clear();
        updateLabels();
        resetTextFieldStyles();

        totalDosirLabel.setText("0 kr");
        totalFloskurLabel.setText("0 kr");
    }

    /**
     * Processes the current values and accumulates the total.
     */
    @FXML
    private void onGreida() {
        vinnsla.greida();
        updateLabels();
        updateTotalLabels();
        resetTextFieldStyles();
        updateCurrencyLabels();
        updateTreesSaved();


    }
    @FXML
    private void onDonatetClicked() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("donation-popup.fxml"));
            Scene scene = new Scene(loader.load());

            DonationPopupController popupController = loader.getController();
            popupController.setParentController(this);

            Stage popupStage = new Stage();
            popupStage.setTitle("Styrkja stofnun");
            popupStage.setScene(scene);
            popupStage.initModality(Modality.APPLICATION_MODAL); // block main window
            popupStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Updates the label showing the current combined value of cans and bottles.
     */
    private void updateLabels() {
        int currentISK = vinnsla.getSamtalsVirdi();

        samtalsVirdiLabel.setText(currentISK + " kr");

        double usd = currentISK * ISK_TO_USD;
        double eur = currentISK * ISK_TO_EUR;

        usdLabel.setText(String.format("%.2f USD", usd));
        eurLabel.setText(String.format("%.2f EUR", eur));

        dosirTextField.setStyle("");
        floskurTextField.setStyle("");
    }

    /**
     * Updates the labels showing the total accumulated value of cans and bottles.
     */
    private void updateTotalLabels() {
        totalDosirLabel.setText(String.valueOf(vinnsla.getSamtalsDosirVirdi()) + " kr ");
        totalFloskurLabel.setText(String.valueOf(vinnsla.getSamtalsFloskurVirdi()) + " kr ");
        totalOverallLabel.setText(String.valueOf(vinnsla.getTotalVirdi()) + " kr ");
    }

    private void updateCurrencyLabels() {
        double currentISK = vinnsla.getSamtalsVirdi();
        double totalISK = vinnsla.getTotalVirdi();

        usdLabel.setText(String.format("%.2f USD", currentISK * ISK_TO_USD));
        eurLabel.setText(String.format("%.2f EUR", currentISK * ISK_TO_EUR));

        usdTotalLabel.setText(String.format("%.2f USD", totalISK * ISK_TO_USD));
        eurTotalLabel.setText(String.format("%.2f EUR", totalISK * ISK_TO_EUR));
    }

    /**
     * Resets the style of the input text fields to default.
     */
    private void resetTextFieldStyles() {
        dosirTextField.setStyle("");
        floskurTextField.setStyle("");
    }
    public void resetAfterDonation() {
        vinnsla.hreinsa();

        dosirTextField.clear();
        floskurTextField.clear();

        samtalsVirdiLabel.setText("0 kr");
        usdLabel.setText("0.00 USD");
        eurLabel.setText("0.00 EUR");

        // Optional: also reset totals if donation means "give all"
        totalDosirLabel.setText("0 kr");
        totalFloskurLabel.setText("0 kr");
        totalOverallLabel.setText("0 kr");
        usdTotalLabel.setText("0.00 USD");
        eurTotalLabel.setText("0.00 EUR");
        treesSavedLabel.setText("You have saved 0 trees 🌲");

    }
    private void updateTreesSaved() {
        int totalItems = vinnsla.getSamtalsDosir() + vinnsla.getSamtalsFloskur(); // Total cans + bottles
        int treesSaved = totalItems / 10;

        treesSavedLabel.setText("You have saved " + treesSaved + " tree" + (treesSaved != 1 ? "s" : "") + " 🌲");
    }

}