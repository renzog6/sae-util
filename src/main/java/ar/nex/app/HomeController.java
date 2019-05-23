package ar.nex.app;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Renzo
 */
public class HomeController implements Initializable {

    public HomeController() {
    }

    @FXML
    private Button btnEmpresa;
    @FXML
    private Button btnEquipo;
    @FXML
    private Button btnPedido;
    @FXML
    private Button btnRepuesto;

    @FXML
    private BorderPane bpHome;

    private Stage stage;


    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 
    }

    public void initControllers() {

    }

    public void show(Parent root) {
        bpHome.getStylesheets().add(root.getStyle());
        bpHome.setCenter(root);
    }



}
