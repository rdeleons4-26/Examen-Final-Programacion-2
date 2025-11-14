import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AcercaDe extends JFrame {

    private JTextField txtNombre;
    private JTextField txtCarne;
    private JTextField txtProyecto;
    private JTextField txtVersion;
    private JTextField txtFecha;
    private JLabel lblFoto;
    private JButton btnCargarFoto;
    private JButton btnGuardar;

    private String rutaFoto = ""; // Para almacenar la ruta de la foto

    public AcercaDe() {
        setTitle("Acerca de");
        setSize(450, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblTitulo = new JLabel("Informacioón del Alumno");
        lblTitulo.setBounds(130, 10, 200, 25);
        add(lblTitulo);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(30, 50, 100, 25);
        add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(130, 50, 250, 25);
        add(txtNombre);

        JLabel lblCarne = new JLabel("Carnet:");
        lblCarne.setBounds(30, 90, 100, 25);
        add(lblCarne);

        txtCarne = new JTextField();
        txtCarne.setBounds(130, 90, 250, 25);
        add(txtCarne);

        JLabel lblProyecto = new JLabel("Proyecto Final:");
        lblProyecto.setBounds(30, 130, 100, 25);
        add(lblProyecto);

        txtProyecto = new JTextField();
        txtProyecto.setBounds(130, 130, 250, 25);
        add(txtProyecto);

        JLabel lblVersion = new JLabel("Version:");
        lblVersion.setBounds(30, 170, 100, 25);
        add(lblVersion);

        txtVersion = new JTextField();
        txtVersion.setBounds(130, 170, 250, 25);
        add(txtVersion);

        JLabel lblFecha = new JLabel("Fecha:");
        lblFecha.setBounds(30, 210, 100, 25);
        add(lblFecha);

        txtFecha = new JTextField();
        txtFecha.setBounds(130, 210, 250, 25);
        add(txtFecha);

        lblFoto = new JLabel();
        lblFoto.setBounds(160, 250, 120, 120);
        add(lblFoto);

        btnCargarFoto = new JButton("Cargar Foto");
        btnCargarFoto.setBounds(160, 380, 120, 25);
        btnCargarFoto.addActionListener(e -> cargarFoto());
        add(btnCargarFoto);

        btnGuardar = new JButton("Guardar Cambios");
        btnGuardar.setBounds(140, 410, 160, 25);
        btnGuardar.addActionListener(e -> guardarDatos());
        add(btnGuardar);

        cargarDatos(); 
    }

    private void cargarFoto() {
        JFileChooser fileChooser = new JFileChooser();
        int opcion = fileChooser.showOpenDialog(this);
        if(opcion == JFileChooser.APPROVE_OPTION) {
            rutaFoto = fileChooser.getSelectedFile().getAbsolutePath();
            ImageIcon foto = new ImageIcon(rutaFoto);
            Image img = foto.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
            lblFoto.setIcon(new ImageIcon(img));
        }
    }

    private void cargarDatos() {
        try {
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("SELECT * FROM acercaa_de WHERE id = 1");
            ResultSet rs = pst.executeQuery();
            if(rs.next()) {
                txtNombre.setText(rs.getString("nombre"));
                txtCarne.setText(rs.getString("carne"));
                txtProyecto.setText(rs.getString("proyecto"));
                txtVersion.setText(rs.getString("version"));
                txtFecha.setText(rs.getString("fecha"));
                rutaFoto = rs.getString("ruta_foto");

                if(rutaFoto != null && !rutaFoto.isEmpty()) {
                    ImageIcon foto = new ImageIcon(rutaFoto);
                    Image img = foto.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
                    lblFoto.setIcon(new ImageIcon(img));
                }
            }
            cn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los datos: " + e.getMessage());
        }
    }

    private void guardarDatos() {
        try {
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement(
                    "UPDATE acercaa_de SET nombre = ?, carne = ?, proyecto = ?, version = ?, fecha = ?, ruta_foto = ? WHERE id = 1"
            );
            pst.setString(1, txtNombre.getText());
            pst.setString(2, txtCarne.getText());
            pst.setString(3, txtProyecto.getText());
            pst.setString(4, txtVersion.getText());
            pst.setString(5, txtFecha.getText());
            pst.setString(6, rutaFoto);
            pst.executeUpdate();
            cn.close();
            JOptionPane.showMessageDialog(this, "Datos actualizados correctamente!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar los datos: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new AcercaDe().setVisible(true);
    }
}