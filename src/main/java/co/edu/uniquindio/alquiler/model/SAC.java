package co.edu.uniquindio.alquiler.model;

import co.edu.uniquindio.alquiler.enums.EstadoRecibo;
import db.Conexion;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.*;
import java.sql.Date;

public class SAC {

    public FactoryBanco factoryBanco=FactoryBanco.getInstance();
    public FactoryEstudiante factoryEstudiante=FactoryEstudiante.getInstance();
    public FactoryMateria factoryMateria=FactoryMateria.getInstance();
    public FactoryReciboPago factoryReciboPago=FactoryReciboPago.getInstance();
    public Conexion conexionBD=Conexion.getInstance();

    public LocalDate fechaCierrePlataforma=LocalDate.of(2025, 6, 7);

    String remitente="";

    String clave="";

    public SAC() {
        conexionBD.conectarBDSoftware2();
        importarDatosSQL();
    }

    public double calcularDefinitiva(ArrayList<Double> listadoNotas) {
        double definitiva=0.0;
        int centinela=0;
        for(int i=0;i<listadoNotas.size();i++)
        {
            definitiva+=listadoNotas.get(i);
            centinela++;
        }
        return definitiva=definitiva/centinela;
    }


    public void solicitudGenerada(String destinatario,String asunto) {
        Properties propiedades=System.getProperties();
        propiedades.put("mail.smtp.host", "smtp.gmail.com");
        propiedades.put("mail.smtp.user", remitente);
        propiedades.put("mail.smtp.clave", clave);
        propiedades.put("mail.smtp.auth", "true");
        propiedades.put("mail.smtp.starttls.enable", "true");
        propiedades.put("mail.smtp.port", "587");
        propiedades.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session sesion=Session.getDefaultInstance(propiedades);
        MimeMessage message = new MimeMessage(sesion);

        try
        {
            message.setFrom(new InternetAddress(remitente));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            message.setSubject(asunto);
            message.setText(asunto);
            Transport transport = sesion.getTransport("smtp");
            transport.connect("smtp.gmail.com", remitente, clave);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }
        catch (MessagingException me)
        {
            me.printStackTrace();
        }
    }

    public void importarDatosSQL() {
        try
        {
            String consulta="SELECT * FROM Materias";
            Statement stmt = conexionBD.getConexionT().createStatement();
            ResultSet rs = stmt.executeQuery(consulta);

            while(rs.next())
            {
                String nombre = rs.getString("nombre");
                String codigo = rs.getString("codigoMateria");
                String programaPerteneciente = rs.getString("programaPerteneciente");
                float nota1 = rs.getFloat("nota1");
                float nota2=rs.getFloat("nota2");
                float nota3=rs.getFloat("nota3");
                float nota4=rs.getFloat("nota4");
                float notaDefinitiva=rs.getFloat("notaDefinitiva");
                Materia materia=new Materia(programaPerteneciente,nombre,codigo);
                materia.getListaNotas().add(Double.parseDouble(String.valueOf(nota1)));
                materia.getListaNotas().add(Double.parseDouble(String.valueOf(nota2)));
                materia.getListaNotas().add(Double.parseDouble(String.valueOf(nota3)));
                materia.getListaNotas().add(Double.parseDouble(String.valueOf(nota4)));
                materia.setNotaDefinitiva(notaDefinitiva);
                factoryMateria.getListaMaterias().add(materia);
            }

            consulta="SELECT * FROM Bancos";
            rs = stmt.executeQuery(consulta);

            while(rs.next())
            {
                String nombre = rs.getString("nombre");

                Banco banco=new Banco(nombre);

                factoryBanco.getListaBancos().add(banco);
            }

            consulta="SELECT * FROM RecibosPago";
            rs = stmt.executeQuery(consulta);

            while(rs.next())
            {
                String IDestudiante = rs.getString("IDestudiante");
                String estadoRecibo = rs.getString("estadoRecibo");
                LocalDate fechaExpedicion = rs.getDate("fechaExpedicion").toLocalDate();
                Date sqlDate = rs.getDate("fechaPago");
                LocalDate fechaPago = (sqlDate != null) ? sqlDate.toLocalDate() : null;
                LocalDate fechaVencimiento = rs.getDate("fechaVencimiento").toLocalDate();
                int numeroReferencia = rs.getInt("numeroReferencia");
                String nombreMateria = rs.getString("nombreMateria");
                String programaPerteneciente=rs.getString("programaPerteneciente");
                String codigoMateria=rs.getString("codigoMateria");

                ReciboPago reciboPago=new ReciboPago(IDestudiante,EstadoRecibo.valueOf(estadoRecibo),fechaExpedicion,fechaPago,fechaVencimiento,nombreMateria,numeroReferencia,programaPerteneciente,codigoMateria);
                factoryReciboPago.getListaRecibosPago().add(reciboPago);
            }

            consulta="SELECT * FROM Estudiantes";
            rs = stmt.executeQuery(consulta);

            while(rs.next())
            {
                String nombre = rs.getString("nombre");
                String IDestudiante = rs.getString("IDestudiante");
                String palabraClave = rs.getString("palabraClave");
                String iconoClave = rs.getString("iconoClave");
                String correo=rs.getString("correo");

                Estudiante estudiante=new Estudiante(nombre,IDestudiante,palabraClave,iconoClave,correo);
                System.out.print(estudiante.getId());
                estudiante.listaMaterias.addAll(verificarMateriasEstudiantes(estudiante));
                estudiante.listaRecibosPago.addAll(verificarRecibosPago(IDestudiante));
                factoryEstudiante.getEstudiantes().add(estudiante);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }


    public ArrayList<Materia> verificarMateriasEstudiantes(Estudiante estudiante) {
        ArrayList<Materia> materias=new ArrayList<>();
        try
        {
            String consulta="SELECT * FROM Estudiantes_Materias";
            Statement stmt = conexionBD.getConexionT().createStatement();
            ResultSet rs = stmt.executeQuery(consulta);

            while(rs.next())
            {
                String IDestudiante = rs.getString("IDestudiante");
                String codigoMateria = rs.getString("codigoMateria");

                if(IDestudiante.equals(estudiante.getId()))
                {
                    for(int i=0;i<factoryMateria.getListaMaterias().size();i++)
                    {
                        if(factoryMateria.getListaMaterias().get(i).getCodigo().equals(codigoMateria))
                        {
                            materias.add(factoryMateria.getListaMaterias().get(i));
                        }
                    }
                }
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return materias;
    }

    public ArrayList<ReciboPago> verificarRecibosPago(String IDestudiante) {
        ArrayList<ReciboPago> recibosPagos=new ArrayList<>();
        for(int i=0;i<factoryReciboPago.getListaRecibosPago().size();i++)
        {
            if(factoryReciboPago.getListaRecibosPago().get(i).getIDestudiante().equals(IDestudiante))
            {
                recibosPagos.add(factoryReciboPago.getListaRecibosPago().get(i));
            }
        }
        return recibosPagos;
    }

    public void agregarReciboTablaSql(ReciboPago reciboPago) {
        String insertador = "INSERT INTO RecibosPago(IDestudiante,estadoRecibo,fechaExpedicion,fechaPago,valorPagar,fechaVencimiento,numeroReferencia,codigoMateria,nombreMateria,programaPerteneciente) VALUES (?, ?, ?,?,?,?,?,?,?,?)";
        try
        {

            PreparedStatement statement = conexionBD.getConexionT().prepareStatement(insertador);
            statement.setString(1,reciboPago.getIDestudiante());
            statement.setString(2,""+reciboPago.getEstadoRecibo());
            statement.setDate(3,Date.valueOf(reciboPago.getFechaExpedicion()));
            statement.setNull(4,java.sql.Types.DATE);
            statement.setDouble(5,reciboPago.getValorPagar());
            statement.setDate(6,Date.valueOf(reciboPago.getFechaVencimiento()));
            statement.setInt(7,reciboPago.getNumeroReferencia());
            statement.setString(8,reciboPago.getCodigoMateria());
            statement.setString(9,reciboPago.getNombreMateria());
            statement.setString(10,reciboPago.getProgramaPerteneciente());

            statement.executeUpdate();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

    }

    public void eliminarReciboTablaSql(ReciboPago reciboPago) {
        String eliminador = "DELETE FROM RecibosPago WHERE numeroReferencia = ?";
        try {

            PreparedStatement stmt = conexionBD.getConexionT().prepareStatement(eliminador);
            stmt.setInt(1, reciboPago.getNumeroReferencia());
            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void pagarRecibo(ReciboPago reciboPago) {
        String actualizador = "UPDATE RecibosPago SET estadoRecibo = ? WHERE numeroReferencia = ?";
        try {

            PreparedStatement stmt = conexionBD.getConexionT().prepareStatement(actualizador);

            stmt.setString(1,String.valueOf(EstadoRecibo.PAGADO));
            stmt.setInt(2, reciboPago.getNumeroReferencia());

            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

}
