package co.edu.uniquindio.alquiler.controller;
import co.edu.uniquindio.alquiler.exceptions.EstudianteNoRegistradoException;
import co.edu.uniquindio.alquiler.model.DatosSesion;
import co.edu.uniquindio.alquiler.model.Domain;
import co.edu.uniquindio.alquiler.model.Estudiante;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class IngresarSACController {

    @FXML
    TextField identificacionTxtField;
    @FXML
    TextField palabraClaveTxtField;
    @FXML
    Label palabraClaveLbl;
    @FXML
    Label iconoClaveLbl;
    @FXML
    Button trianguloButton;
    @FXML
    Button cuadradoButton;
    @FXML
    Button circuloButton;
    @FXML
    Button caritaFelizButton;
    @FXML
    Button iniciarSesionButton;
    @FXML
    ImageView logoImgView;
    @FXML
    Label identificacionLbl;
    @FXML
    Label inicioSesionLbl;

    String palabraClaveSeleccionada;

    public Domain domain= Domain.getInstance();

    DatosSesion datos=DatosSesion.getInstance();

    public void initialize() {

    }

    public void trianguloAction(ActionEvent actionEvent)
    {
        palabraClaveSeleccionada="▲";
    }

    public void cuadradoAction(ActionEvent actionEvent)
    {
        palabraClaveSeleccionada="∎";
    }

    public void circuloAction(ActionEvent actionEvent)
    {
        palabraClaveSeleccionada="●";
    }

    public void caritaFelizAction(ActionEvent actionEvent) {
        palabraClaveSeleccionada="☻";
    }

    public void iniciarSesionAction(ActionEvent actionEvent)
    {
        try
        {
            String palabraClave=palabraClaveTxtField.getText();
            String identificacion=identificacionTxtField.getText();
            if(palabraClave.isEmpty()||identificacion.isEmpty()||palabraClaveSeleccionada.isEmpty())
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Alerta");
                alert.setContentText("Ingrese bien sus datos de acceso");
                alert.show();
            }
            else
            {
                Estudiante estudianteSeleccionado=domain.accederPlataforma(palabraClave,identificacion,palabraClaveSeleccionada);

                if(estudianteSeleccionado==null)
                {
                    throw new NullPointerException();
                }

                datos.setEstudianteSeleccionado(estudianteSeleccionado);

                inicializarSACInterfaz();
            }
        }
        catch(NullPointerException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Alerta");
            alert.setContentText("Estudiante no registrado");
            alert.show();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

    }

    public void inicializarSACInterfaz() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ventanas/SAC.fxml"));
        Parent root = loader.load();

        SACController SACController =loader.getController();

        // Mostramos la nueva ventana
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

}
