package app;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.scene.text.Text;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;


/**
 * Controller data
 */
public class Controller implements Initializable {

    /**
     * TextField for a
     */
    @FXML
    private TextField aTxt;

    /**
     * TextField for upX
     */
    @FXML
    private TextField upTxt;

    /**
     * TextField for toX
     */
    @FXML
    private TextField toTxt;

    /**
     * TextField for step
     */
    @FXML
    private TextField stepTxt;

    /**
     * TextField for error
     */
    @FXML
    private Text errorTxt;

    /**
     * LineChart graphics
     */
    @FXML
    private LineChart<Number, Number> Graphics;

    /**
     * Error msg
     */
    private String errorMsgStr = "";

    /**
     * Series point for graphics
     */
    private XYChart.Series<Number, Number> seriesPoint = new XYChart.Series<>();

    /**
     * initialize all fxml
     * @param url            URL
     * @param resourceBundle ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        validateAllTextField();
    }

    /**
     * drawGraphics
     *
     * @param event ActionEvent
     * @return isDraw
     */
    @FXML
    private boolean drawGraphics(ActionEvent event) {
        if (validationData()) {
            visibleErrorMsg();
            return false;
        } else {
            seriesPoint.getData().removeAll(Collections.singleton(Graphics.getData().setAll()));
            createPoints();
            visibleMsg("Graphics drawn");
            return true;
        }
    }

    /**
     * visible error msg
     */
    private void visibleErrorMsg() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, errorMsgStr, ButtonType.OK);
        alert.showAndWait();
    }

    /**
     * Create series point
     */
    private void createPoints() {
        seriesPoint = new XYChart.Series<>();
        seriesPoint.setName("Witch of Agnesi");
        double nowX = Double.parseDouble(upTxt.getText());
        double toX = Double.parseDouble(toTxt.getText());
        double step = Double.parseDouble(stepTxt.getText());
        double a = Double.parseDouble(aTxt.getText());
        while (nowX <= toX) {
            seriesPoint.getData().add(new Data<>(nowX, countFunction(a, nowX)));
            nowX += step;
        }
        Graphics.setAnimated(false);
        Graphics.getData().add(seriesPoint);
    }

    /**
     * countFunction
     * @param a a
     * @param x x
     * @return y
     */
    private double countFunction(double a, double x) {
        return (8 * a * a * a) / (x * x + 4 * a * a);
    }

    /**
     * saveGraphics
     * @param event ActionEvent
     */
    @FXML
    private void saveGraphics(ActionEvent event) {
        visibleMsg("");
        if (drawGraphics(event)) {
            WritableImage image = Graphics.snapshot(new SnapshotParameters(), null);
            String childName = "a=" + aTxt.getText() + "_upX=" + upTxt.getText() + "_toX=" + toTxt.getText() + "_step=" + upTxt.getText() + ".png";
            File file = new File("img", childName);
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            visibleMsg("Drawn\nSaved...");
        }
    }

    /**
     * Validate number
     */
    private void validationNumber(TextField txtField) {
        txtField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("^[-]?[0-9]+.?[0-9]+$")) {
                txtField.setText(newValue.replaceAll("[^\\d-.]", ""));
            }
        });
    }

    /**
     * Validate AllTextField
     */
    private void validateAllTextField() {
        validationNumber(aTxt);
        validationNumber(upTxt);
        validationNumber(toTxt);
        validationNumber(stepTxt);
    }

    /**
     * Set error msg
     *
     * @param msg String
     */
    private void visibleMsg(String msg) {
        errorTxt.setText(msg);
    }

    /**
     * Validate Data
     */
    private boolean validationData() {
        errorMsgStr = "";
        boolean isError = false;

        if (validationEmptyStr()) {
            errorMsgStr = "Some fields empty";
            return true;
        }

        try {
            double upX = Double.parseDouble(upTxt.getText());
            double toX = Double.parseDouble(toTxt.getText());
            double step = Double.parseDouble(stepTxt.getText());

            if (upX > toX) {
                errorMsgStr += "Top x is less than the bottom\n";
                isError = true;
            }

            if (toX - upX < step) {
                System.out.println(upX);
                System.out.println(toX);
                System.out.println(step);
                errorMsgStr += "Step more range\n";
                isError = true;

            }

            if (step < 0) {
                errorMsgStr += "Step less 0\n";
                isError = true;
            }


        } catch (NumberFormatException ex) {
            isError = true;
            errorMsgStr = "Error parse number\n";
        }

        return isError;
    }

    /**
     * validationEmptyStr
     *
     * @return bool validationEmptyStr
     */
    private boolean validationEmptyStr() {
        return aTxt.getText().isEmpty() || upTxt.getText().isEmpty() || toTxt.getText().isEmpty() || stepTxt.getText().isEmpty();
    }
}