package co.edu.uniquindio.fx10.controlador;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class DashboardController {

    @FXML
    private AnchorPane contenedorCentral;

    @FXML
    private Button btnListado;

    @FXML
    private Button btnFormulario;

    @FXML
    private Button btnVolverDashboardTop;

    @FXML
    public void initialize() {
        // Actualizar la barra superior según el contenido del centro
        contenedorCentral.getChildren().addListener((ListChangeListener<Node>) change -> updateTopBar());
        updateTopBar();
    }

    @FXML
    private void abrirListadoProducto() throws IOException {
        Node node = FXMLLoader.load(getClass().getResource("/co/edu/uniquindio/fx10/vista/ListadoProducto.fxml"));
        contenedorCentral.getChildren().setAll(node);
        updateTopBar();
    }

    @FXML
    private void abrirFormularioProducto() throws IOException {
        Node node = FXMLLoader.load(getClass().getResource("/co/edu/uniquindio/fx10/vista/FormularioProducto.fxml"));
        contenedorCentral.getChildren().setAll(node);
        updateTopBar();
    }

    @FXML
    private void volverAlDashboard() {
        contenedorCentral.getChildren().clear();
        updateTopBar();
    }

    private void updateTopBar() {
        boolean hayVistaActiva = !contenedorCentral.getChildren().isEmpty();
        btnListado.setVisible(!hayVistaActiva);
        btnListado.setManaged(!hayVistaActiva);

        btnFormulario.setVisible(!hayVistaActiva);
        btnFormulario.setManaged(!hayVistaActiva);

        btnVolverDashboardTop.setVisible(hayVistaActiva);
        btnVolverDashboardTop.setManaged(hayVistaActiva);
    }
}
