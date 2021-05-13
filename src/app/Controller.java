package app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import utils.ErrorMsg;

import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;

public class Controller implements Initializable {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text titleTxt;

    @FXML
    private TextField aTxt;

    @FXML
    private TextField upTxt;

    @FXML
    private TextField toTxt;

    @FXML
    private TextField stepTxt;

    @FXML
    private Text errorTxt;

    @FXML
    private Button drawBtn;

    @FXML
    private Button saveBtn;

    @FXML
    private LineChart<Number, Number> Graphics;

    private ErrorMsg error = new ErrorMsg(false, "");

    private XYChart.Series<Number, Number> seriesPoint = new XYChart.Series<>();;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        validateAllTextField();
    }

    @FXML
    private boolean drawGraphics(ActionEvent event) {
        visibleErrorMsg("");
        if (validationEmptyStr()) {
            visibleErrorMsg("Some fields empty");
            return false;
        }
        validationData();
        if (error.isError) {
            visibleErrorMsg(error.errorMsg);
            return false;
        } else {
            seriesPoint.getData().removeAll(Collections.singleton(Graphics.getData().setAll()));
            createPoints();
            visibleErrorMsg("Graphics drawn");
        }
        return true;
    }

    private void createPoints() {
        seriesPoint = new XYChart.Series<>();
        seriesPoint.setName("Witch of Agnesi");
        double nowX = Double.parseDouble(upTxt.getText());
        double toX = Double.parseDouble(toTxt.getText());
        double step = Double.parseDouble(stepTxt.getText());
        double a = Double.parseDouble(aTxt.getText());
        while (nowX <= toX){
            seriesPoint.getData().add(new Data<>(nowX, countFunction(a, nowX)));
            nowX += step;
        }
        Graphics.setAnimated(false);
        Graphics.getData().add(seriesPoint);
    }

    private double countFunction(double a, double x){
        return (8 * a * a * a)/(x * x + 4 * a * a);
    }

    @FXML
    private void saveGraphics(ActionEvent event) {
        visibleErrorMsg("");
        if (drawGraphics(event)) {
            visibleErrorMsg("Drawn\nSaving...");
        }
    }

    private void validationNumber(TextField txtField) {
        txtField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("^[-]?[0-9]+.?[0-9]+$")) {
                txtField.setText(newValue.replaceAll("[^\\d-.]", ""));
            }
        });
    }

    private void validateAllTextField() {
        validationNumber(aTxt);
        validationNumber(upTxt);
        validationNumber(toTxt);
        validationNumber(stepTxt);
    }

    private void visibleErrorMsg(String errorMsg) {
        errorTxt.setText(errorMsg);
    }

    private void validationData() {
        boolean isError = false;
        String errorMsgStr = "";

        try {
            double upX = Double.parseDouble(upTxt.getText());
            double toX = Double.parseDouble(toTxt.getText());
            double step = Double.parseDouble(stepTxt.getText());
            if (step < 0) {
                errorMsgStr = "Step less 0\n";
                isError = true;
            }
            if (upX > toX) {
                errorMsgStr += "Top x is less than the bottom";
                isError = true;
            }
        } catch (NumberFormatException ex) {
            isError = true;
            errorMsgStr = "Error parse number";
        }

        error = new ErrorMsg(isError, errorMsgStr);
    }

    private boolean validationEmptyStr() {
        return aTxt.getText().isEmpty() || upTxt.getText().isEmpty() || toTxt.getText().isEmpty() || stepTxt.getText().isEmpty();
    }
}
