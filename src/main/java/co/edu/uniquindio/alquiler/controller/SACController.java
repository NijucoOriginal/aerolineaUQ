package co.edu.uniquindio.alquiler.controller;

import co.edu.uniquindio.alquiler.enums.EstadoRecibo;
import co.edu.uniquindio.alquiler.exceptions.PromedioBajoException;
import co.edu.uniquindio.alquiler.exceptions.ReciboExistenteException;
import co.edu.uniquindio.alquiler.model.*;
import co.edu.uniquindio.alquiler.utils.ArchivoUtils;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Formatter;
import java.util.Properties;

public class SACController {

    @FXML
    public TableColumn<Materia,String> codigoNotasColumn;
    @FXML
    public TableColumn<Materia,String> nombreNotasColumn;
    @FXML
    public TableColumn<Materia,String> nota1Column;
    @FXML
    public TableColumn<Materia,String> nota2Column;
    @FXML
    public TableColumn<Materia,String> nota3Column;
    @FXML
    public TableColumn<Materia,String> nota4Column;
    @FXML
    public TableColumn<Materia,String> definitivaNotasColumn;
    @FXML
    public TableView<Materia> notasTable;
    @FXML
    Button revisarNotasButton;
    @FXML
    Button visualizarButton;
    @FXML
    Button eliminarButton;
    @FXML
    TableView<ReciboPago> recibosPagoTable;
    @FXML
    TableColumn<ReciboPago,String> IDEstColumn;
    @FXML
    TableColumn<ReciboPago,String> estadoReciboColumn;
    @FXML
    TableColumn<ReciboPago, String> valorReciboColumn;
    @FXML
    TableColumn<ReciboPago, String> numeroReferenciaColumn;
    @FXML
    Label otrosDerechosLabel;
    @FXML
    Button solicitarPermisoButton;
    @FXML
    Label solicitudNovedadesLbl;
    @FXML
    Button novedadesButton;
    @FXML
    Button otrosDerechosButton;
    @FXML
    Button pagosButton;
    @FXML
    Button CerrarSesionButton;
    @FXML
    Button actualizarListaRecibos;
    @FXML
    ImageView logoImgView;
    @FXML
    Label signoInterrogacionNovedadesNotasLbl;
    @FXML
    Label signoInterrogacionOtrosDerechosLbl;
    @FXML
    Line barra2;
    @FXML
    Line barra1;
    @FXML
    Label signoInterrogacionPagosEnLineaLbl;
    @FXML
    TableView<Materia> materiasTable;
    @FXML
    TableColumn<Materia,String> nombreMateriaColumn;
    @FXML
    TableColumn<Materia,String> codigoMateriaColumn;
    @FXML
    TableColumn<Materia,String> definitivaColumn;

    DatosSesion datos=DatosSesion.getInstance();

    public Estudiante estudianteSesionIniciada=datos.getEstudianteSeleccionado();

    public Domain domain= Domain.getInstance();

    String remitente="diegon.penar@uqvirtual.edu.co";

    String clave="mcal qlbh vqpa aktg";

    public void initialize() {
        materiasTable.setVisible(false);
        solicitudNovedadesLbl.setVisible(false);
        solicitarPermisoButton.setVisible(false);
        recibosPagoTable.setVisible(false);
        otrosDerechosLabel.setVisible(false);
        actualizarListaRecibos.setVisible(false);
        eliminarButton.setVisible(false);
        visualizarButton.setVisible(false);
        revisarNotasButton.setVisible(false);
        notasTable.setVisible(false);

        nombreMateriaColumn.setCellValueFactory( cellData -> new SimpleStringProperty( cellData.getValue().getNombre()));
        codigoMateriaColumn.setCellValueFactory( cellData -> new SimpleStringProperty( cellData.getValue().getCodigo()));
        definitivaColumn.setCellValueFactory( cellData -> new SimpleStringProperty( String.valueOf(cellData.getValue().getNotaDefinitiva()) ) );

        estadoReciboColumn.setCellValueFactory( cellData -> new SimpleStringProperty( String.valueOf(cellData.getValue().getEstadoRecibo()) ) );
        IDEstColumn.setCellValueFactory( cellData -> new SimpleStringProperty( cellData.getValue().getIDestudiante()));
        valorReciboColumn.setCellValueFactory( cellData -> new SimpleStringProperty( cellData.getValue().getValorPagar()+""));;
        numeroReferenciaColumn.setCellValueFactory( cellData -> new SimpleStringProperty( cellData.getValue().getNumeroReferencia()+""));

        codigoNotasColumn.setCellValueFactory(cellData -> new SimpleStringProperty( String.valueOf(cellData.getValue().getCodigo())));
        nombreNotasColumn.setCellValueFactory(cellData -> new SimpleStringProperty( cellData.getValue().getNombre()));
        nota1Column.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getListaNotas().get(0))));
        nota2Column.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getListaNotas().get(1))));
        nota3Column.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getListaNotas().get(2))));
        nota4Column.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getListaNotas().get(3))));
        definitivaNotasColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getNotaDefinitiva())));


        this.materiasTable.setItems(FXCollections.observableList(estudianteSesionIniciada.getListaMaterias()));
        this.recibosPagoTable.setItems(FXCollections.observableList(estudianteSesionIniciada.getListaRecibosPago()));
        this.notasTable.setItems(FXCollections.observableList(estudianteSesionIniciada.getListaMaterias()));

    }

    public void aparecerNotasAction(ActionEvent actionEvent) {
        materiasTable.setVisible(true);
        solicitudNovedadesLbl.setVisible(true);
        solicitarPermisoButton.setVisible(true);
        recibosPagoTable.setVisible(false);
        otrosDerechosLabel.setVisible(false);
        actualizarListaRecibos.setVisible(false);
        eliminarButton.setVisible(false);
        visualizarButton.setVisible(false);
        revisarNotasButton.setVisible(true);
        notasTable.setVisible(false);
    }

    public void otrosDerechosAction(ActionEvent actionEvent) {
        materiasTable.setVisible(false);
        solicitudNovedadesLbl.setVisible(false);
        solicitarPermisoButton.setVisible(false);
        recibosPagoTable.setVisible(true);
        otrosDerechosLabel.setVisible(true);
        actualizarListaRecibos.setVisible(true);
        eliminarButton.setVisible(true);
        visualizarButton.setVisible(true);
        revisarNotasButton.setVisible(false);
        notasTable.setVisible(false);
        recibosPagoTable.refresh();
    }



    public void solicitarPermisoAction(ActionEvent actionEvent) {
        agregarSolicitudHabilitacion();
    }

    public void pagosEnLineaAction(ActionEvent actionEvent) {
        try
        {
            inicializarPagosEnLineaInterfaz();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void inicializarPagosEnLineaInterfaz() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ventanas/pagosEnLinea.fxml"));
        Parent root = loader.load();

        pagosEnLineaController pagosEnLineaController =loader.getController();

        // Mostramos la nueva ventana
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }


    public void actualizarListaRecibosOnAction(ActionEvent actionEvent) {
        actualizarListaRecibos.setVisible(true);
        recibosPagoTable.refresh();
    }

    public void cerrarSesionOnAction(ActionEvent actionEvent) {
        Stage ventanaActual = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        ventanaActual.close();
        estudianteSesionIniciada=null;
    }

    public void agregarSolicitudHabilitacion() {
        Materia materiaSeleccionada=materiasTable.getSelectionModel().getSelectedItem();

        if(materiaSeleccionada!=null)
        {
            LocalDate fecha=LocalDate.now();
            ReciboPago reciboPago=new ReciboPago(estudianteSesionIniciada.getId(), EstadoRecibo.GENERADO, fecha,null,domain.factorySAC.getSac().fechaCierrePlataforma,materiaSeleccionada.getNombre(), (int) (Math.random() * 900) + 100,materiaSeleccionada.getProgramaPerteneciente(),materiaSeleccionada.getCodigo());
            try
            {
                domain.agregarRecibodePago(estudianteSesionIniciada,reciboPago,materiaSeleccionada);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Informacion");
                alert.setContentText("Su recibo acaba de ser generado");
                alert.show();
                crearArchivoHTMLReciboPago(reciboPago);
                domain.factorySAC.getSac().agregarReciboTablaSql(reciboPago);
                domain.factorySAC.getSac().solicitudGenerada(datos.getEstudianteSeleccionado().getCorreoEstudiante(),"Su recibo de pago con numero de referencia: "+reciboPago.getNumeroReferencia()+" ha sido creado exitosamente.");
            }
            catch (PromedioBajoException e)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Alerta");
                alert.setContentText(e.getMessage());
                alert.show();
            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Alerta");
            alert.setContentText("No ha seleccionado ninguna materia");
            alert.show();
        }
    }

    public void eliminarButtonOnAction(ActionEvent actionEvent) {
        ReciboPago reciboPagoEliminar=recibosPagoTable.getSelectionModel().getSelectedItem();
        if(reciboPagoEliminar!=null)
        {
            if(reciboPagoEliminar.getEstadoRecibo()==EstadoRecibo.PAGADO)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Alerta");
                alert.setContentText("No puede eliminar un recibo que ya ha pagado previamente");
                alert.show();
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.getButtonTypes().clear();
                ButtonType deshacerButton = new ButtonType("Deshacer");
                ButtonType aceptarButton = new ButtonType("Aceptar");
                alert.getButtonTypes().setAll(deshacerButton,aceptarButton);
                alert.setHeaderText("Informacion");
                alert.setContentText("Su recibo de pago fue eliminado exitosamente");
                ReciboPago reciboPagoGuardado=new ReciboPago(reciboPagoEliminar.getIDestudiante(), reciboPagoEliminar.getEstadoRecibo(),reciboPagoEliminar.getFechaExpedicion(),reciboPagoEliminar.getFechaPago(),reciboPagoEliminar.getFechaVencimiento(),reciboPagoEliminar.getNombreMateria(),reciboPagoEliminar.getNumeroReferencia(),reciboPagoEliminar.getProgramaPerteneciente(),reciboPagoEliminar.getCodigoMateria());
                alert.showAndWait().ifPresent(response -> {
                    if (response == deshacerButton)
                    {
                        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                        alert1.setHeaderText("Informacion");
                        alert1.setContentText("La accion se deshizo exitosamente");
                        alert1.show();
                    }
                    else if(response==aceptarButton)
                    {
                        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                        alert1.setHeaderText("Informacion");
                        alert1.setContentText("Su recibo de pago fue eliminado exitosamente");
                        alert1.show();
                        domain.eliminarReciboPago(estudianteSesionIniciada, reciboPagoEliminar);
                        recibosPagoTable.refresh();
                        domain.factorySAC.getSac().solicitudGenerada(datos.getEstudianteSeleccionado().getCorreoEstudiante(),"Su recibo de pago con numero de referencia: "+reciboPagoEliminar.getNumeroReferencia()+" ha sido eliminado exitosamente.");
                        eliminarArchivoHTM(reciboPagoEliminar);
                        domain.factorySAC.getSac().eliminarReciboTablaSql(reciboPagoEliminar);
                    }
                });

            }
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Alerta");
            alert.setContentText("No ha seleccionado ningun recibo de pago para eliminar");
            alert.show();
        }
    }

    public void visualizarOnAction(ActionEvent actionEvent) {
        ReciboPago reciboPagoSeleccionado=recibosPagoTable.getSelectionModel().getSelectedItem();
        if(reciboPagoSeleccionado!=null)
        {
            domain.ejecutarArchivoHtml("src/main/resources/ArchivosHTMl/"+"reciboPagoDE;"+datos.getEstudianteSeleccionado().getId()+"NumeroRef;"+reciboPagoSeleccionado.getNumeroReferencia());
            System.out.print("src/main/resources/ArchivosHTMl/"+"reciboPagoDE;"+datos.getEstudianteSeleccionado().getId()+"NumeroRef;"+reciboPagoSeleccionado.getNumeroReferencia());
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Alerta");
            alert.setContentText("No ha seleccionado ningun recibo de pago para visualizar");
            alert.show();
        }
    }

    public void crearArchivoHTMLReciboPago(ReciboPago reciboPagoCrear) throws IOException {
        String reciboPago="<!DOCTYPE html>\n" +
                    "<html lang=\"es\">\n" +
                    "<head>\n" +
                    "  <meta charset=\"UTF-8\">\n" +
                    "  <title>Recibo de Pago - Universidad del Quindío</title>\n" +
                    "  <style>\n" +
                    "    body {\n" +
                    "      font-family: Arial, sans-serif;\n" +
                    "      margin: 40px;\n" +
                    "      background-color: #f8f8f8;\n" +
                    "    }\n" +
                    "    .recibo {\n" +
                    "      background: white;\n" +
                    "      padding: 30px;\n" +
                    "      max-width: 800px;\n" +
                    "      margin: auto;\n" +
                    "      border: 1px solid #ccc;\n" +
                    "    }\n" +
                    "    .encabezado {\n" +
                    "      text-align: center;\n" +
                    "      margin-bottom: 30px;\n" +
                    "    }\n" +
                    "    .encabezado img {\n" +
                    "      width: 80px;\n" +
                    "    }\n" +
                    "    .titulo {\n" +
                    "      font-size: 22px;\n" +
                    "      font-weight: bold;\n" +
                    "      margin-top: 10px;\n" +
                    "    }\n" +
                    "    .datos, .detalle {\n" +
                    "      margin-bottom: 20px;\n" +
                    "    }\n" +
                    "    .datos td, .detalle td {\n" +
                    "      padding: 5px 10px;\n" +
                    "    }\n" +
                    "    .detalle th {\n" +
                    "      background-color: #eee;\n" +
                    "      padding: 10px;\n" +
                    "    }\n" +
                    "    .valor {\n" +
                    "      font-weight: bold;\n" +
                    "      color: green;\n" +
                    "    }\n" +
                    "    .footer {\n" +
                    "      margin-top: 30px;\n" +
                    "      font-size: 12px;\n" +
                    "      text-align: center;\n" +
                    "      color: gray;\n" +
                    "    }\n" +
                    "  </style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "\n" +
                    "  <div class=\"recibo\">\n" +
                    "    <div class=\"encabezado\">\n" +
                    "      <img src=\"src\\main\\resources\\Imagenes\\Uniqlog.png\" alt=\"Escudo\">\n" +
                    "      <div class=\"titulo\">Universidad del Quindío</div>\n" +
                    "      <div>Recibo de Pago Estudiantil</div>\n" +
                    "    </div>\n" +
                    "\n" +
                    "    <table class=\"datos\">\n" +
                    "      <tr>\n" +
                    "        <td><strong>Nombre del estudiante:</strong></td>\n" +
                    "        <td>"+datos.getEstudianteSeleccionado().getNombre()+"</td>\n" +
                    "      </tr>\n" +
                    "      <tr>\n" +
                    "        <td><strong>ID Estudiante:</strong></td>\n" +
                    "        <td>"+datos.getEstudianteSeleccionado().getId()+"</td>\n" +
                    "      </tr>\n" +
                    "      <tr>\n" +
                    "        <td><strong>Programa:</strong></td>\n" +
                    "        <td>"+reciboPagoCrear.getProgramaPerteneciente()+"</td>\n" +
                    "      </tr>\n" +
                    "      <tr>\n" +
                    "        <td><strong>Fecha de Expedición:</strong></td>\n" +
                    "        <td>"+reciboPagoCrear.getFechaExpedicion().toString()+"</td>\n" +
                    "      </tr>\n" +
                    "      <tr>\n" +
                    "        <td><strong>Fecha de Vencimiento:</strong></td>\n" +
                    "        <td>"+reciboPagoCrear.getFechaVencimiento().toString()+"</td>\n" +
                    "      </tr>\n" +
                    "    </table>\n" +
                    "\n" +
                    "    <table class=\"detalle\" width=\"100%\" border=\"1\" cellspacing=\"0\">\n" +
                    "      <tr>\n" +
                    "        <th>Referencia</th>\n" +
                    "        <th>Materia</th>\n" +
                    "        <th>Valor a Pagar</th>\n" +
                    "        <th>Estado</th>\n" +
                    "      </tr>\n" +
                    "      <tr>\n" +
                    "        <td>"+reciboPagoCrear.getNumeroReferencia()+"</td>\n" +
                    "        <td>"+reciboPagoCrear.getNombreMateria()+"</td>\n" +
                    "        <td class=\"valor\">"+reciboPagoCrear.getValorPagar()+"</td>\n" +
                    "        <td>"+reciboPagoCrear.getEstadoRecibo().toString()+"</td>\n" +
                    "      </tr>\n" +
                    "    </table>\n" +
                    "\n" +
                    "    <div class=\"footer\">\n" +
                    "      Este documento es válido solo para efectos informativos. Para pagar, diríjase al banco autorizado con este recibo impreso o \tacceda a la opcion de pagos en linea que ofrece la plataforma SAC.<br>\n" +
                    "      Universidad del Quindío - Armenia, Quindío, Colombia\n" +
                    "    </div>\n" +
                    "  </div>\n" +
                    "\n" +
                    "</body>\n" +
                    "\n" +
                    "<a href=\"recibo_pago.html\" download=\"Recibo_UQ.html\">\n" +
                    "  <button>Descargar Recibo HTML</button>\n" +
                    "</a>\n" +
                    "\n" +
                    "</html>";

        ArchivoUtils.crearYEscribirArchivo("reciboPagoDE;"+datos.getEstudianteSeleccionado().getId()+"NumeroRef;"+reciboPagoCrear.getNumeroReferencia(),reciboPago);
    }

    public void eliminarArchivoHTM(ReciboPago reciboPagoEliminar) {
        String nombreArchivo="reciboPagoDE;"+datos.getEstudianteSeleccionado().getId()+"NumeroRef;"+reciboPagoEliminar.getNumeroReferencia();
        ArchivoUtils.eliminarArchivoHTML(nombreArchivo);
    }


    public void revisarNotasOnAction(ActionEvent actionEvent) {
        notasTable.setVisible(true);
        revisarNotasButton.setVisible(false);
        solicitarPermisoButton.setVisible(false);
        eliminarButton.setVisible(false);
        visualizarButton.setVisible(false);
        revisarNotasButton.setVisible(false);
        recibosPagoTable.setVisible(false);
        materiasTable.setVisible(false);
        visualizarButton.setVisible(false);
    }
}
