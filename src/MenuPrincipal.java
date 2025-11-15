import javax.swing.*;

public class MenuPrincipal extends JFrame {

    public MenuPrincipal() {
        setTitle("Menu Principal - Biblioteca");
        setSize(400, 350); // ajustado para que quepan todos los botones
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblTitulo = new JLabel("MENU PRINCIPAL");
        lblTitulo.setBounds(150, 20, 120, 25);
        add(lblTitulo);

        // AUTORES
        JButton btnAutores = new JButton("AUTORES");
        btnAutores.setBounds(120, 70, 150, 30);
        btnAutores.addActionListener(e -> new AutoresCRUD().setVisible(true));
        add(btnAutores);

        // CATEGORIAS
        JButton btnCategorias = new JButton("CATEGORIAS");
        btnCategorias.setBounds(120, 110, 150, 30);
        btnCategorias.addActionListener(e -> new CategoriasCRUD().setVisible(true));
        add(btnCategorias);

        // LIBROS
        JButton btnLibros = new JButton("LIBROS");
        btnLibros.setBounds(120, 150, 150, 30);
        btnLibros.addActionListener(e -> new LibrosCRUD().setVisible(true));
        add(btnLibros);

        // MIS LIBROS DESTACADOS
        JButton btnDestacados = new JButton("MIS LIBROS DESTACADOS");
        btnDestacados.setBounds(80, 190, 220, 30);
        btnDestacados.addActionListener(e -> new LibrosDestacados().setVisible(true));
        add(btnDestacados);

        // ACERCA DE
        JButton btnAcercaDe = new JButton("ACERCA DE");
        btnAcercaDe.setBounds(120, 230, 150, 30);
        btnAcercaDe.addActionListener(e -> new AcercaDe().setVisible(true));
        add(btnAcercaDe);

        // SALIR
        JButton btnSalir = new JButton("SALIR");
        btnSalir.setBounds(120, 270, 150, 30);
        btnSalir.addActionListener(e -> System.exit(0));
        add(btnSalir);
    }

    public static void main(String[] args) {
        new MenuPrincipal().setVisible(true);
    }
}