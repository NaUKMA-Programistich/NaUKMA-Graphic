package sample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text ttitleTxt;

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

    @FXML
    void initialize() {
        assert ttitleTxt != null : "fx:id=\"ttitleTxt\" was not injected: check your FXML file 'start_screen.fxml'.";
        assert aTxt != null : "fx:id=\"aTxt\" was not injected: check your FXML file 'start_screen.fxml'.";
        assert upTxt != null : "fx:id=\"upTxt\" was not injected: check your FXML file 'start_screen.fxml'.";
        assert toTxt != null : "fx:id=\"toTxt\" was not injected: check your FXML file 'start_screen.fxml'.";
        assert stepTxt != null : "fx:id=\"stepTxt\" was not injected: check your FXML file 'start_screen.fxml'.";
        assert errorTxt != null : "fx:id=\"errorTxt\" was not injected: check your FXML file 'start_screen.fxml'.";
        assert drawBtn != null : "fx:id=\"drawBtn\" was not injected: check your FXML file 'start_screen.fxml'.";
        assert saveBtn != null : "fx:id=\"saveBtn\" was not injected: check your FXML file 'start_screen.fxml'.";
        assert graphic != null : "fx:id=\"graphic\" was not injected: check your FXML file 'start_screen.fxml'.";

    }
}
