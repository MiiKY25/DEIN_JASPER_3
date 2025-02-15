package org.mikel.dein_jasper_3.controladores;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.mikel.dein_jasper_3.bbdd.ConexionBBDD;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Controlador para la ventana del supermercado.
 * Este controlador maneja los eventos relacionados con la generación de informes en formato Jasper.
 */
public class VentanaSupermercadoController {

    /**
     * Metodo encargado de manejar el evento de generación del informe de productos.
     * Este metodo llama a {@link #generarInforme(String)} pasando el archivo Jasper correspondiente.
     *
     * @param event El evento generado por la acción del usuario.
     */
    @FXML
    void accionProductos(ActionEvent event) {
        generarInforme("Productos.jasper");
    }

    /**
     * Metodo encargado de manejar el evento de generación del informe de secciones.
     * Este metodo llama a {@link #generarInforme(String)} pasando el archivo Jasper correspondiente.
     *
     * @param event El evento generado por la acción del usuario.
     */
    @FXML
    void accionSecciones(ActionEvent event) {
        generarInforme("Secciones.jasper");
    }

    /**
     * Metodo encargado de manejar el evento de generación del informe de la tabla.
     * Este metodo llama a {@link #generarInforme(String)} pasando el archivo Jasper correspondiente.
     *
     * @param event El evento generado por la acción del usuario.
     */
    @FXML
    void accionTabla(ActionEvent event) {
        generarInforme("Tabla.jasper");
    }

    /**
     * Metodo encargado de manejar el evento de generación del informe de gráficos.
     * Este metodo llama a {@link #generarInforme(String)} pasando el archivo Jasper correspondiente.
     *
     * @param event El evento generado por la acción del usuario.
     */
    @FXML
    void accionGraficos(ActionEvent event) {
        generarInforme("Graficos.jasper");
    }

    /**
     * Metodo que genera un informe a partir de un archivo Jasper.
     * El informe se llena con los datos obtenidos de la base de datos y se muestra al usuario.
     *
     * @param archivoJasper El nombre del archivo Jasper que se utilizará para generar el informe.
     */
    private void generarInforme(String archivoJasper) {
        ConexionBBDD db;
        try {
            // Crear una nueva conexión a la base de datos
            db = new ConexionBBDD();

            // Cargar el archivo Jasper del informe
            InputStream reportStream = db.getClass().getResourceAsStream("/jasper/" + archivoJasper);

            // Verificar si el archivo fue encontrado
            if (reportStream == null) {
                System.out.println("Archivo NO encontrado");
                return;
            }

            // Cargar el informe Jasper
            JasperReport report = (JasperReport) JRLoader.loadObject(reportStream);

            // Parámetros del informe
            Map<String, Object> parameters = new HashMap<>();

            // Añadir la ruta de las imágenes
            String imagePath = db.getClass().getResource("/imagenes/").toString(); // Ruta de la carpeta de imágenes
            parameters.put("IMAGE_PATH", imagePath);

            // Llenar el informe con datos
            JasperPrint jprint = JasperFillManager.fillReport(report, parameters, db.getConnection());

            // Mostrar el informe
            JasperViewer viewer = new JasperViewer(jprint, false);
            viewer.setVisible(true);

        } catch (SQLException e) {
            mostrarError("No se ha podido establecer conexion con la Base de Datos");
            e.printStackTrace();
        } catch (JRException e) {
            mostrarError("Error al procesar el informe Jasper");
            e.printStackTrace();
        }
    }

    /**
     * Metodo que muestra un mensaje de error en una ventana emergente.
     *
     * @param error El mensaje de error que se mostrará en la ventana emergente.
     */
    void mostrarError(String error) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(error);
        alert.showAndWait();
    }

    /**
     * Inicializa el controlador, realizando la conexión a la base de datos.
     * Si la conexión a la base de datos falla, muestra un mensaje de error
     * y cierra la aplicación.
     */
    public void initialize() {
        // Controlar acceso a la base de datos
        try {
            new ConexionBBDD();
        } catch (SQLException e) {
            mostrarError("No se ha podido establecer conexion con la Base de Datos");
            Platform.exit(); // Cierra la aplicación
        }
    }

}
