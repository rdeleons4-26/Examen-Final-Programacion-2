import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AutoresCRUD extends JFrame {

    private JTable tablaAutores;
    private DefaultTableModel modelo;
    private JTextField txtNombre, txtNacionalidad, txtID;

    public AutoresCRUD() {
        setTitle("AUTORES");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel lblTitulo = new JLabel("Gestión de Autores");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setBounds(200, 10, 200, 30);
        add(lblTitulo);

        JLabel lblID = new JLabel("ID:");
        lblID.setBounds(30, 60, 80, 25);
        add(lblID);

        txtID = new JTextField();
        txtID.setBounds(120, 60, 150, 25);
        txtID.setEditable(false);
        add(txtID);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(30, 100, 80, 25);
        add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(120, 100, 150, 25);
        add(txtNombre);

        JLabel lblNacionalidad = new JLabel("Nacionalidad:");
        lblNacionalidad.setBounds(30, 140, 100, 25);
        add(lblNacionalidad);

        txtNacionalidad = new JTextField();
        txtNacionalidad.setBounds(120, 140, 150, 25);
        add(txtNacionalidad);

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(320, 60, 100, 25);
        btnAgregar.addActionListener(e -> agregarAutor());
        add(btnAgregar);

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(320, 100, 100, 25);
        btnActualizar.addActionListener(e -> actualizarAutor());
        add(btnActualizar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(320, 140, 100, 25);
        btnEliminar.addActionListener(e -> eliminarAutor());
        add(btnEliminar);

        JButton btnListar = new JButton("Listar");
        btnListar.setBounds(440, 100, 100, 25);
        btnListar.addActionListener(e -> cargarAutores());
        add(btnListar);

        modelo = new DefaultTableModel(new String[]{"ID", "Nombre", "Nacionalidad"}, 0);
        tablaAutores = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tablaAutores);
        scroll.setBounds(30, 190, 520, 150);
        add(scroll);

        tablaAutores.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int fila = tablaAutores.getSelectedRow();
                if (fila >= 0) {
                    txtID.setText(modelo.getValueAt(fila, 0).toString());
                    txtNombre.setText(modelo.getValueAt(fila, 1).toString());
                    txtNacionalidad.setText(modelo.getValueAt(fila, 2).toString());
                }
            }
        });

        cargarAutores();
    }

    // Conexion a la base de datos
    private Connection conectar() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca?useSSL=false&serverTimezone=UTC", "root", "2126");
    }

    //cargar lista
    private void cargarAutores() {
        modelo.setRowCount(0);
        try (Connection conn = conectar();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT id, nombre, nacionalidad FROM autores")) {

            while (rs.next()) {
                Object[] fila = {rs.getInt("id"), rs.getString("nombre"), rs.getString("nacionalidad")};
                modelo.addRow(fila);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar autores: " + e.getMessage());
        }
    }

    //agregar autor
    private void agregarAutor() {
        if (txtNombre.getText().trim().isEmpty() || txtNacionalidad.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Por favor, completa todos los campos antes de guardar.");
        return;
    }
        try (Connection conn = conectar();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO autores (nombre, nacionalidad) VALUES (?, ?)")) {

            ps.setString(1, txtNombre.getText());
            ps.setString(2, txtNacionalidad.getText());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Autor agregado correctamente.");
            cargarAutores();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al agregar autor: " + e.getMessage());
        }
    }

    //actualizar autor
    private void actualizarAutor() {
        if (txtID.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione un autor para actualizar.");
            return;
        }

        try (Connection conn = conectar();
             PreparedStatement ps = conn.prepareStatement("UPDATE autores SET nombre=?, nacionalidad=? WHERE id=?")) {

            ps.setString(1, txtNombre.getText());
            ps.setString(2, txtNacionalidad.getText());
            ps.setInt(3, Integer.parseInt(txtID.getText()));
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Autor actualizado correctamente.");
            cargarAutores();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al actualizar autor: " + e.getMessage());
        }
    }

    //eliminar
    private void eliminarAutor() {
        if (txtID.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione un autor para eliminar.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar este autor?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;

        try (Connection conn = conectar();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM autores WHERE id=?")) {

            ps.setInt(1, Integer.parseInt(txtID.getText()));
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Autor eliminado correctamente.");
            cargarAutores();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al eliminar autor: " + e.getMessage());
        }
    }

    // metodo principal 
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AutoresCRUD().setVisible(true));
    }
}