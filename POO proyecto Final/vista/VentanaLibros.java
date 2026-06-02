package org.example.vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;


public  class   VentanaLibros extends JFrame {

    private JPanel panel;
    private JPanel panel2;

    //labels
    private JLabel nombrelbl;
    private JLabel autorlbl;
    private JLabel sinopsislbl;
    private JLabel editoriallbl;
    private JLabel generolbl;
    private JLabel idlibrolbl;
    private JLabel preciolbl;
    private JLabel idiomalbl;
    private JLabel Formatolbl;
    private JLabel relojlbl;

   //textfields
    private JTextField nombretxt;
    private JTextField autortxt;
    private JTextField editorialtxt;
    private JTextField idlibrotxt;
    private JTextField preciotxt;

   //Tabla y Text area
    private JTextArea sinopsistxta;
    private JComboBox<String> generolcmb;
    private JScrollPane scrollpane;
    private JTable tablalibros;

 //Los che box
    private JCheckBox espanol;
    private JCheckBox ingles;
    private JCheckBox portugues;
    private JCheckBox italiano;

 //Radio buttons
    private JRadioButton pastadura;
    private JRadioButton pastablanda;
    private JRadioButton bolsillo;
    private ButtonGroup grupo;


    //Menu
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem LimpiarForm;
    private JMenuItem Salir;

    //Botones del crud
    private JButton guardar;
    private JButton eliminar;
    private JButton modificar;
    private JButton leer;

    //Choosers y sus botones
    private JFileChooser archivo;
    private JButton abrirArchivo;
    private JColorChooser colorChooser;
    private JButton cambiarColor;


    public VentanaLibros() {

        super("Libreria Orlando");

        setLayout(new GridLayout(2, 1));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 800);

        // Paneles 1 y 2
        panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.setBackground(new Color(108, 213, 213));
        panel2.setBackground(new Color(149, 223, 138));

        // Creacion de los labels
        nombrelbl = new JLabel("Nombre:");
        autorlbl = new JLabel("Autor:");
        sinopsislbl = new JLabel("Sinopsis:");
        editoriallbl = new JLabel("Editorial:");
        generolbl = new JLabel("Genero:");
        idlibrolbl = new JLabel("Identificacion:");
        preciolbl = new JLabel("Precio:");
        idiomalbl = new JLabel("Idioma:");
        Formatolbl = new JLabel("Formato:");

        //Creacion de los Los texts
        nombretxt = new JTextField(10);
        autortxt = new JTextField(10);
        editorialtxt = new JTextField(10);
        idlibrotxt = new JTextField(10);
        preciotxt = new JTextField(10);

        // Creacion de los ComboBox
        generolcmb = new JComboBox<>();
        generolcmb.addItem("Novela");
        generolcmb.addItem("Terror");
        generolcmb.addItem("Romance");
        generolcmb.addItem("Fantasia");
        generolcmb.addItem("Ciencia Ficcion");
        generolcmb.addItem("Aventura");

        //Creacion del Text area
        sinopsistxta = new JTextArea(5, 20);
        JScrollPane scrollSinopsis = new JScrollPane(sinopsistxta);
        scrollSinopsis.setPreferredSize(new Dimension(250, 100));

       //Creacion de la Tabla
        String[] columnas = {
                "Nombre",
                "Autor",
                "Sinopsis",
                "Editorial",
                "Genero",
                "Identificacion",
                "Precio",
                "Idioma",
                "Formato"
        };

        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.setColumnIdentifiers(columnas);
        tablalibros = new JTable(modeloTabla);
        tablalibros.getColumnModel().getColumn(2).setPreferredWidth(300);
        scrollpane = new JScrollPane(tablalibros);
        scrollpane.setPreferredSize(new Dimension(800,250));

       //Creacion de los Idiomas del chexbox
        espanol = new JCheckBox("Español");
        ingles = new JCheckBox("Ingles");
        portugues = new JCheckBox("Portugues");
        italiano  = new JCheckBox("Italiano");

       //Creacion de los Formatos de libro (Radio buttons)
        grupo = new ButtonGroup();
        pastadura= new JRadioButton("Pasta Dura");
        pastablanda = new JRadioButton("Pasta Blanda");
        bolsillo = new JRadioButton("De Bolsillo");
        grupo.add(pastadura);
        grupo.add(pastablanda);
        grupo.add(bolsillo);


        //Creacion del Menu y menu bar
        menuBar = new JMenuBar();
        menu = new JMenu("Inicio");
        LimpiarForm = new JMenuItem("Limpiar Formulario");
        Salir = new JMenuItem("Salir");

        //Creacion de los choosers
        archivo = new JFileChooser();
        abrirArchivo = new JButton("Abrir Archivo");
        colorChooser = new JColorChooser();

        //Creacion de los botones del crud
        guardar = new JButton("Guardar Libro");
        eliminar = new JButton("Eliminar Libro");
        modificar = new JButton("Modificar Libro");
        leer = new JButton("Verificar Libro");

        //cambiar color de la tabla
        cambiarColor = new JButton("Cambiar Color Tabla");

        //Creacion del reloj
        relojlbl = new JLabel("Reloj: 00:00:00");

        // Agregar paneles
        add(panel);
        add(panel2);

        //-------- Cosas agregadas en primer panel-------------
        panel.add(nombrelbl);
        panel.add(nombretxt);

        panel.add(sinopsislbl);
        panel.add(scrollSinopsis);

        panel.add(autorlbl);
        panel.add(autortxt);

        panel.add(editoriallbl);
        panel.add(editorialtxt);

        panel.add(generolbl);
        panel.add(generolcmb);

        panel.add(idlibrolbl);
        panel.add(idlibrotxt);

        panel.add(preciolbl);
        panel.add(preciotxt);

        //Idiomas de los radios
        panel.add(idiomalbl);
        panel.add(espanol);
        panel.add(ingles);
        panel.add(portugues);
        panel.add(italiano);

        //Formatos de libros
        panel.add(Formatolbl);
        panel.add(pastadura);
        panel.add(pastablanda);
        panel.add(bolsillo);

        panel.add(relojlbl);

        //jfilechooser
        panel.add(abrirArchivo);

        panel.add(guardar);

        //---------Cosas agregadas en el panel 2------------

        //tabla
        panel2.add(scrollpane);

        //agregacion del crud
        panel2.add(leer);
        panel2.add(eliminar);
        panel2.add(modificar);

        //jchoosercolor
        panel2.add(cambiarColor);


      // Menu
        menu.add(LimpiarForm);
        menu.add(Salir);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        setVisible(true);


    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public JPanel getPanel2() {
        return panel2;
    }

    public void setPanel2(JPanel panel2) {
        this.panel2 = panel2;
    }

    public JLabel getNombrelbl() {
        return nombrelbl;
    }

    public void setNombrelbl(JLabel nombrelbl) {
        this.nombrelbl = nombrelbl;
    }

    public JLabel getAutorlbl() {
        return autorlbl;
    }

    public void setAutorlbl(JLabel autorlbl) {
        this.autorlbl = autorlbl;
    }

    public JLabel getSinopsislbl() {
        return sinopsislbl;
    }

    public void setSinopsislbl(JLabel sinopsislbl) {
        this.sinopsislbl = sinopsislbl;
    }

    public JLabel getEditoriallbl() {
        return editoriallbl;
    }

    public void setEditoriallbl(JLabel editoriallbl) {
        this.editoriallbl = editoriallbl;
    }

    public JLabel getGenerolbl() {
        return generolbl;
    }

    public void setGenerolbl(JLabel generolbl) {
        this.generolbl = generolbl;
    }

    public JLabel getIdlibrolbl() {
        return idlibrolbl;
    }

    public void setIdlibrolbl(JLabel idlibrolbl) {
        this.idlibrolbl = idlibrolbl;
    }

    public JLabel getPreciolbl() {
        return preciolbl;
    }

    public void setPreciolbl(JLabel preciolbl) {
        this.preciolbl = preciolbl;
    }

    public JLabel getIdiomalbl() {
        return idiomalbl;
    }

    public void setIdiomalbl(JLabel idiomalbl) {
        this.idiomalbl = idiomalbl;
    }

    public JLabel getFormatolbl() {
        return Formatolbl;
    }

    public void setFormatolbl(JLabel formatolbl) {
        Formatolbl = formatolbl;
    }

    public JLabel getRelojlbl() {
        return relojlbl;
    }

    public void setRelojlbl(JLabel relojlbl) {
        this.relojlbl = relojlbl;
    }

    public JTextField getNombretxt() {
        return nombretxt;
    }

    public void setNombretxt(JTextField nombretxt) {
        this.nombretxt = nombretxt;
    }

    public JTextField getAutortxt() {
        return autortxt;
    }

    public void setAutortxt(JTextField autortxt) {
        this.autortxt = autortxt;
    }

    public JTextField getEditorialtxt() {
        return editorialtxt;
    }

    public void setEditorialtxt(JTextField editorialtxt) {
        this.editorialtxt = editorialtxt;
    }

    public JTextField getIdlibrotxt() {
        return idlibrotxt;
    }

    public void setIdlibrotxt(JTextField idlibrotxt) {
        this.idlibrotxt = idlibrotxt;
    }

    public JTextField getPreciotxt() {
        return preciotxt;
    }

    public void setPreciotxt(JTextField preciotxt) {
        this.preciotxt = preciotxt;
    }

    public JTextArea getSinopsistxta() {
        return sinopsistxta;
    }

    public void setSinopsistxta(JTextArea sinopsistxta) {
        this.sinopsistxta = sinopsistxta;
    }

    public JComboBox<String> getGenerolcmb() {
        return generolcmb;
    }

    public void setGenerolcmb(JComboBox<String> generolcmb) {
        this.generolcmb = generolcmb;
    }

    public JScrollPane getScrollpane() {
        return scrollpane;
    }

    public void setScrollpane(JScrollPane scrollpane) {
        this.scrollpane = scrollpane;
    }

    public JTable getTablalibros() {
        return tablalibros;
    }

    public void setTablalibros(JTable tablalibros) {
        this.tablalibros = tablalibros;
    }

    public JCheckBox getEspanol() {
        return espanol;
    }

    public void setEspanol(JCheckBox espanol) {
        this.espanol = espanol;
    }

    public JCheckBox getIngles() {
        return ingles;
    }

    public void setIngles(JCheckBox ingles) {
        this.ingles = ingles;
    }

    public JCheckBox getPortugues() {
        return portugues;
    }

    public void setPortugues(JCheckBox portugues) {
        this.portugues = portugues;
    }

    public JCheckBox getItaliano() {
        return italiano;
    }

    public void setItaliano(JCheckBox italiano) {
        this.italiano = italiano;
    }

    public JRadioButton getPastadura() {
        return pastadura;
    }

    public void setPastadura(JRadioButton pastadura) {
        this.pastadura = pastadura;
    }

    public JRadioButton getPastablanda() {
        return pastablanda;
    }

    public void setPastablanda(JRadioButton pastablanda) {
        this.pastablanda = pastablanda;
    }

    public JRadioButton getBolsillo() {
        return bolsillo;
    }

    public void setBolsillo(JRadioButton bolsillo) {
        this.bolsillo = bolsillo;
    }

    public ButtonGroup getGrupo() {
        return grupo;
    }

    public void setGrupo(ButtonGroup grupo) {
        this.grupo = grupo;
    }


    public JMenuBar getMenuBarPrincipal() {
        return menuBar;
    }

    public void setMenuBar(JMenuBar menuBar) {
        this.menuBar = menuBar;
    }

    public JMenu getMenu() {
        return menu;
    }

    public void setMenu(JMenu menu) {
        this.menu = menu;
    }

    public JMenuItem getLimpiarForm() {
        return LimpiarForm;
    }

    public void setLimpiarForm(JMenuItem limpiarForm) {
        LimpiarForm = limpiarForm;
    }

    public JMenuItem getSalir() {
        return Salir;
    }

    public void setSalir(JMenuItem salir) {
        Salir = salir;
    }

    public JButton getGuardar() {
        return guardar;
    }

    public void setGuardar(JButton guardar) {
        this.guardar = guardar;
    }

    public JButton getEliminar() {
        return eliminar;
    }

    public void setEliminar(JButton eliminar) {
        this.eliminar = eliminar;
    }

    public JButton getModificar() {
        return modificar;
    }

    public void setModificar(JButton modificar) {
        this.modificar = modificar;
    }

    public JButton getLeer() {
        return leer;
    }

    public void setLeer(JButton leer) {
        this.leer = leer;
    }

    public JFileChooser getArchivo() {
        return archivo;
    }

    public void setArchivo(JFileChooser archivo) {
        this.archivo = archivo;
    }

    public JColorChooser getColorChooser() {
        return colorChooser;
    }

    public void setColorChooser(JColorChooser colorChooser) {
        this.colorChooser = colorChooser;
    }
    public JButton getAbrirArchivo() {
        return abrirArchivo;
    }
    public JButton getCambiarColor() {
        return cambiarColor;
    }

    @Override
    public String toString() {
        return "VentanaLibros{" +
                "panel=" + panel +
                ", panel2=" + panel2 +
                ", nombrelbl=" + nombrelbl +
                ", autorlbl=" + autorlbl +
                ", sinopsislbl=" + sinopsislbl +
                ", editoriallbl=" + editoriallbl +
                ", generolbl=" + generolbl +
                ", idlibrolbl=" + idlibrolbl +
                ", preciolbl=" + preciolbl +
                ", idiomalbl=" + idiomalbl +
                ", Formatolbl=" + Formatolbl +
                ", relojlbl=" + relojlbl +
                ", nombretxt=" + nombretxt +
                ", autortxt=" + autortxt +
                ", editorialtxt=" + editorialtxt +
                ", idlibrotxt=" + idlibrotxt +
                ", preciotxt=" + preciotxt +
                ", sinopsistxta=" + sinopsistxta +
                ", generolcmb=" + generolcmb +
                ", scrollpane=" + scrollpane +
                ", tablalibros=" + tablalibros +
                ", espanol=" + espanol +
                ", ingles=" + ingles +
                ", portugues=" + portugues +
                ", italiano=" + italiano +
                ", pastadura=" + pastadura +
                ", pastablanda=" + pastablanda +
                ", bolsillo=" + bolsillo +
                ", grupo=" + grupo +
                ", menuBar=" + menuBar +
                ", menu=" + menu +
                ", LimpiarForm=" + LimpiarForm +
                ", Salir=" + Salir +
                ", guardar=" + guardar +
                ", eliminar=" + eliminar +
                ", modificar=" + modificar +
                ", leer=" + leer +
                ", archivo=" + archivo +
                ", colorChooser=" + colorChooser +
                '}';
    }
}