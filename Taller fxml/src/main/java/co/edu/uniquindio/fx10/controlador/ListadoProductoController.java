package co.edu.uniquindio.fx10.controlador;

import co.edu.uniquindio.fx10.modelo.Producto;
import co.edu.uniquindio.fx10.repositorio.ProductoRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class ListadoProductoController {

    @FXML
    private BorderPane root;

    @FXML
    private TableView<Producto> tablaProductos;

    @FXML
    private TableColumn<Producto, String> colCodigo;

    @FXML
    private TableColumn<Producto, String> colNombre;

    @FXML
    private TableColumn<Producto, String> colDescripcion;

    @FXML
    private TableColumn<Producto, Double> colPrecio;

    @FXML
    private TableColumn<Producto, Integer> colStock;

    @FXML
    public void initialize() {
        // Configurar columnas
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));

        // Vincular la tabla con la lista observable del repositorio
        tablaProductos.setItems(ProductoRepository.getInstancia().getProductos());
    }

    @FXML
    private void onVolverDashboard() {
        AnchorPane contenedor = (AnchorPane) root.getParent();
        if (contenedor != null) {
            contenedor.getChildren().clear();
        }
    }

    @FXML
    private void onEliminarProducto() {
        Producto seleccionado = tablaProductos.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarAlerta("Información", "Seleccione un producto para eliminar", Alert.AlertType.INFORMATION);
            return;
        }
        boolean eliminado = ProductoRepository.getInstancia().eliminarProducto(seleccionado);
        if (!eliminado) {
            mostrarAlerta("Error", "No se pudo eliminar el producto", Alert.AlertType.ERROR);
        }
        // Si se elimina correctamente, la ObservableList notifica automáticamente a la tabla.
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
