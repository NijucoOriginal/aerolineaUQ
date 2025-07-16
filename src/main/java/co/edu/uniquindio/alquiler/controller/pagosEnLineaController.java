package co.edu.uniquindio.alquiler.controller;

import co.edu.uniquindio.alquiler.enums.MetodoPago;
import co.edu.uniquindio.alquiler.model.DatosSesion;
import co.edu.uniquindio.alquiler.model.Domain;
import co.edu.uniquindio.alquiler.model.Estudiante;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

public class pagosEnLineaController {
    @FXML
    Label metodoPagoLbl;
    @FXML
    ComboBox<MetodoPago> metodoPagoComboBox;
    @FXML
    Label pagosEnLineaLbl;
    @FXML
    Label identificacionLbl;
    @FXML
    TextField identificacionTxtField;
    @FXML
    ImageView logoImgView;
    @FXML
    Button consultarButton;
    @FXML
    Label codigoLbl;
    @FXML
    TextField codigoTextField;
    @FXML
    Label valorAPagarLbl;
    @FXML
    TextField valorAPagarTextField;
    @FXML
    Label nombreEstudianteLbl;
    @FXML
    TextField nombreEstudianteTextField;
    @FXML
    ImageView logimgView;
    @FXML
    Button pagarButton;

    public Domain domain= Domain.getInstance();

    DatosSesion datos=DatosSesion.getInstance();


    public void initialize() {
        this.metodoPagoComboBox.getItems().setAll(MetodoPago.values());
    }


    public void mostrarDatosAction(ActionEvent actionEvent) {
        if(domain.encontrarReciboPagar(datos.getEstudianteSeleccionado(),Integer.parseInt(identificacionTxtField.getText()))!=null)
        {
            nombreEstudianteTextField.setText(datos.getEstudianteSeleccionado().getNombre());
            codigoTextField.setText(datos.getEstudianteSeleccionado().getId());
            valorAPagarTextField.setText("300");
        }
        else
        {
            nombreEstudianteTextField.setText("");
            codigoTextField.setText("");
            valorAPagarTextField.setText("");
        }
    }


    public void pagarOnAction(ActionEvent actionEvent) {
        if(!nombreEstudianteTextField.getText().isEmpty() && !codigoTextField.getText().isEmpty() && !valorAPagarTextField.getText().isEmpty())
        {
            domain.factorySAC.getSac().pagarRecibo(domain.buscarReciboPago(datos.getEstudianteSeleccionado(),Integer.parseInt(identificacionTxtField.getText())));
            domain.pagarRecibo(datos.getEstudianteSeleccionado(),Integer.parseInt(identificacionTxtField.getText()));
            domain.factorySAC.getSac().solicitudGenerada(datos.getEstudianteSeleccionado().getCorreoEstudiante(),"Su recibo de pago con numero de referencia: "+identificacionTxtField.getText()+" ha sido pagado exitosamente.");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Informacion");
            alert.setContentText("Su recibo fue pagado exitosamente");
            alert.show();
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Alerta");
            alert.setContentText("El recibo de pago no existe");
            alert.show();
        }
    }
}
