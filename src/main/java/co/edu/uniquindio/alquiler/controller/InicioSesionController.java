package co.edu.uniquindio.alquiler.controller;

import co.edu.uniquindio.alquiler.exceptions.AtributoVacioException;
import co.edu.uniquindio.alquiler.exceptions.ObjetoInexistente;
import co.edu.uniquindio.alquiler.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class InicioSesionController {

    @FXML
    private Label tituloLbl;
    @FXML
    private Label idLbl;
    @FXML
    private Label contraLbl;
    @FXML
    private TextField idTextField;
    @FXML
    private Button ingresarButton;
    @FXML
    private PasswordField contraPassField;

    public Aerolinea aero=Aerolinea.getInstance();

    DatosSesionPasajero datosPasajero=DatosSesionPasajero.getInstance();
    DatosSesionAdministrador datosAdministrador=DatosSesionAdministrador.getInstance();

    public void ingresarOnAction(ActionEvent actionEvent) {
        String ID=idTextField.getText();
        String contrasenia=contraPassField.getText();
        try
        {
            if(ID.isEmpty())
            {
                throw new AtributoVacioException("Debe ingresar su identificacion");
            }
            if(contrasenia.isEmpty())
            {
                throw new AtributoVacioException("Debe ingresar su contraseña");
            }

            Administrador admin=aero.verificarAdministrador(ID,contrasenia);
            Pasajero pasajero=aero.verificarPasajero(ID,contrasenia);

            if(admin!=null)
            {
                abrirVentanas(1);
                datosAdministrador.setAdministradorActivo(admin);
            }
            else if(pasajero!=null)
            {
                abrirVentanas(2);
                datosPasajero.setPasajeroActivo(pasajero);
            }
            else
            {
                throw new ObjetoInexistente("Datos no validos, usted no se encuentra registrado");
            }
        }
        catch (AtributoVacioException e)
        {
            aero.mostrarAlerta(e.getMessage());
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void abrirVentanas(int ventana) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ventanas/SAC.fxml"));
        Parent root = loader.load();

        if (ventana==1)
        {
            VentanaAdministradorController adminController =loader.getController();
        }
        else
        {
            VentanaPasajeroController pasajeroController =loader.getController();
        }

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
