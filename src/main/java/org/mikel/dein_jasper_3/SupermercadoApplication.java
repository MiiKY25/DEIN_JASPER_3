package org.mikel.dein_jasper_3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * La clase principal de la aplicación Supermercado que extiende {@link Application}.
 * Esta clase se encarga de iniciar la interfaz gráfica de usuario (GUI) y mostrar la ventana principal.
 */
public class SupermercadoApplication extends Application {

    /**
     * Metodo principal que inicia la aplicación.
     * Carga el archivo FXML de la ventana principal y configura la escena y el escenario (stage).
     *
     * @param stage El escenario principal de la aplicación.
     * @throws IOException Si ocurre un error al cargar el archivo FXML.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SupermercadoApplication.class.getResource("/fxml/VentanaSupermercado.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("SUPERMERCADO");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Metodo principal que lanza la aplicación.
     *
     * @param args Argumentos de línea de comandos (no se utilizan en este caso).
     */
    public static void main(String[] args) {
        launch();
    }
}