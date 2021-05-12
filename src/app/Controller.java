package app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import utils.ErrorMsg;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private boolean nowGraphics = false;

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
    private LineChart<?, ?> graphic;

    private ErrorMsg error = new ErrorMsg(false, "");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //graphic.setVisible(false);
        validateAllTextField();
    }

    @FXML
    private boolean drawGraphics(ActionEvent event) {
        visibleErrorMsg("");
        graphic.setVisible(false);
        if (validationEmptyStr()) {
            visibleErrorMsg("Some fields empty");
            return false;
        }
        validationData();
        if (error.isError) {
            visibleErrorMsg(error.errorMsg);
            return false;
        } else {
            // build graph
            visibleErrorMsg("Graphics drawn");
            graphic.setVisible(true);
        }
        return true;
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
            if (!newValue.matches("-[0-9]*")) {
                txtField.setText(newValue.replaceAll("[^\\d]", ""));
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

        double upX = Double.parseDouble(upTxt.getText());
        double toX = Double.parseDouble(toTxt.getText());
        double step = Double.parseDouble(stepTxt.getText());

        if (step < 0) {
            errorMsgStr += "Step less 0\n";
            isError = true;
        }
        if (upX > toX) {
            errorMsgStr += "Top x is less than the bottom";
            isError = true;
        }

        error = new ErrorMsg(isError, errorMsgStr);
    }

    private boolean validationEmptyStr() {
        return aTxt.getText().isEmpty() || upTxt.getText().isEmpty() || toTxt.getText().isEmpty() || stepTxt.getText().isEmpty();
    }
}
