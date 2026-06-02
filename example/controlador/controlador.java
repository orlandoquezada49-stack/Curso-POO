package org.example.controlador;

import org.example.modelo.model;
import org.example.vista.VentanaLibros;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.io.*;
import java.util.ArrayList;

//Llamar a M y V
public class controlador {

    private VentanaLibros vista;
    private DefaultTableModel modeloTabla;
    private ArrayList<model> libros;


    /*Aqui se pone lo que el controlador va controlar, la vista, el modelo y crud,
    *todo lo que se haga en estos 2*/
    public controlador(VentanaLibros vista) {

        this.vista = vista;
        libros = new ArrayList<>();

        modeloTabla = (DefaultTableModel)vista.getTablalibros().getModel();
        cargarArchivo();
        iniciarEventos();
        iniciarReloj();
    }

    // Inicializa todos los eventos de los componentes de la interfaz

    private void iniciarEventos() {

        vista.getGuardar().addActionListener(e -> guardarLibro());
        vista.getEliminar().addActionListener(e -> eliminarLibro());
        vista.getModificar().addActionListener(e -> modificarLibro());
        vista.getLimpiarForm().addActionListener(e -> limpiarFormulario());
        vista.getSalir().addActionListener(e -> System.exit(0));
        vista.getLeer().addActionListener(e -> cargarDatosTabla());
        vista.getAbrirArchivo().addActionListener(e -> cargarArchivoSinopsis());
        vista.getCambiarColor().addActionListener(e -> cambiarColorTabla());
        vista.getTablalibros().getSelectionModel().addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()) {

                int fila = vista.getTablalibros().getSelectedRow();

                if (fila >= 0) {

                    vista.getNombretxt().setText(
                            modeloTabla.getValueAt(fila, 0).toString());

                    vista.getAutortxt().setText(
                            modeloTabla.getValueAt(fila, 1).toString());

                    vista.getSinopsistxta().setText(
                            modeloTabla.getValueAt(fila, 2).toString());

                    vista.getEditorialtxt().setText(
                            modeloTabla.getValueAt(fila, 3).toString());

                    vista.getGenerolcmb().setSelectedItem(
                            modeloTabla.getValueAt(fila, 4).toString());

                    vista.getIdlibrotxt().setText(
                            modeloTabla.getValueAt(fila, 5).toString());

                    vista.getPreciotxt().setText(
                            modeloTabla.getValueAt(fila, 6).toString());
                }
            }
        });


        vista.getSinopsistxta().addMouseListener(
                new java.awt.event.MouseAdapter() {

                    @Override
                    public void mouseClicked(
                            java.awt.event.MouseEvent e) {

                        if (e.getClickCount() == 2) {

                            cargarArchivoSinopsis();
                        }
                    }
                }
        );
    }

    //Guardar los libros del CRUD

    private void guardarLibro() {

        try {

            // Validación de campos vacíos
            if (vista.getNombretxt().getText().trim().isEmpty() ||
                    vista.getAutortxt().getText().trim().isEmpty() ||
                    vista.getSinopsistxta().getText().trim().isEmpty() ||
                    vista.getEditorialtxt().getText().trim().isEmpty() ||
                    vista.getIdlibrotxt().getText().trim().isEmpty() ||
                    vista.getPreciotxt().getText().trim().isEmpty() ||
                    obtenerIdioma().equals("No definido") ||
                    obtenerFormato().equals("No definido")) {

                JOptionPane.showMessageDialog(
                        vista,
                        "Tienes que llenar todos los campos."
                );
                return;
            }
            // Validar ID numérico positivo
            int id = Integer.parseInt(
                    vista.getIdlibrotxt().getText()
            );
            for (model libro : libros) {

                if (libro.getIdlibro() == id) {

                    JOptionPane.showMessageDialog(
                            vista,
                            "Ya existe un libro con ese ID"
                    );

                    return;
                }
            }

            if (id <= 0) {

                JOptionPane.showMessageDialog(
                        vista,
                        "El ID debe ser un número positivo"
                );

                return;
            }

            double precio = Double.parseDouble(
                    vista.getPreciotxt().getText()
            );

            if (precio <= 0) {

                JOptionPane.showMessageDialog(
                        vista,
                        "El precio debe ser mayor a cero"
                );

                return;
            }
            if (contarIdiomas() > 1) {

                JOptionPane.showMessageDialog(
                        vista,
                        "Solo puede seleccionar un idioma"
                );

                return;
            }

            model libro = new model();

            libro.setNombre(vista.getNombretxt().getText());
            libro.setAutor(vista.getAutortxt().getText());
            libro.setSinopsis(vista.getSinopsistxta().getText());
            libro.setEditorial(vista.getEditorialtxt().getText());
            libro.setGenero(vista.getGenerolcmb().getSelectedItem().toString());
            libro.setIdlibro(id);
            libro.setPrecio(precio);
            libro.setIdioma(obtenerIdioma());
            libro.setFormato(obtenerFormato());
            libros.add(libro);
            guardarArchivo();

            modeloTabla.addRow(new Object[]{
                    libro.getNombre(),
                    libro.getAutor(),
                    libro.getSinopsis(),
                    libro.getEditorial(),
                    libro.getGenero(),
                    libro.getIdlibro(),
                    libro.getPrecio(),
                    libro.getIdioma(),
                    libro.getFormato()
            });

            JOptionPane.showMessageDialog(
                    vista,
                    "El se ha Libro guardado correctamente"
            );

            limpiarFormulario();

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    vista,
                    "ID y Precio deben contener únicamente números"
            );

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    vista,
                    "Error: " + ex.getMessage()
            );
        }
    }

    //elimina libros
    private void eliminarLibro() {

        int fila = vista.getTablalibros().getSelectedRow();

        if (fila >= 0) {

            int opcion = JOptionPane.showConfirmDialog(
                    vista,
                    "¿Desea eliminar este libro?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION
            );

            if (opcion == JOptionPane.YES_OPTION) {

                modeloTabla.removeRow(fila);

                libros.remove(fila);

                guardarArchivo();

                JOptionPane.showMessageDialog(
                        vista,
                        "El libro ha eliminado"
                );
            }

        } else {

            JOptionPane.showMessageDialog(
                    vista,
                    "Porfavor seleccione una fila"
            );
        }
    }

    //modfica libros
    private void modificarLibro() {

        int fila = vista.getTablalibros().getSelectedRow();

        if (fila < 0) {

            JOptionPane.showMessageDialog(
                    vista,
                    "Seleccione una fila"
            );

            return;
        }

        try {

            if (vista.getNombretxt().getText().trim().isEmpty() ||
                    vista.getAutortxt().getText().trim().isEmpty() ||
                    vista.getSinopsistxta().getText().trim().isEmpty() ||
                    vista.getEditorialtxt().getText().trim().isEmpty() ||
                    vista.getIdlibrotxt().getText().trim().isEmpty() ||
                    vista.getPreciotxt().getText().trim().isEmpty() ||
                    obtenerIdioma().equals("No definido") ||
                    obtenerFormato().equals("No definido")) {

                JOptionPane.showMessageDialog(
                        vista,
                        "Debe llenar todos los campos y seleccionar idioma y formato"
                );

                return;
            }
            // Validar ID
            int id = Integer.parseInt(
                    vista.getIdlibrotxt().getText()
            );

            if (id <= 0) {

                JOptionPane.showMessageDialog(
                        vista,
                        "El ID debe ser un número positivo"
                );

                return;
            }

            // Validar Precio
            double precio = Double.parseDouble(
                    vista.getPreciotxt().getText()
            );


            if (precio <= 0) {

                JOptionPane.showMessageDialog(
                        vista,
                        "El precio debe ser mayor a cero"
                );

                return;

            }
            if (contarIdiomas() > 1) {

                JOptionPane.showMessageDialog(
                        vista,
                        "Solo puede seleccionar un idioma"
                );

                return;
            }

            modeloTabla.setValueAt(
                    vista.getNombretxt().getText(),
                    fila,
                    0
            );

            modeloTabla.setValueAt(
                    vista.getAutortxt().getText(),
                    fila,
                    1
            );

            modeloTabla.setValueAt(
                    vista.getSinopsistxta().getText(),
                    fila,
                    2
            );

            modeloTabla.setValueAt(
                    vista.getEditorialtxt().getText(),
                    fila,
                    3
            );

            modeloTabla.setValueAt(
                    vista.getGenerolcmb().getSelectedItem(),
                    fila,
                    4
            );

            modeloTabla.setValueAt(
                    id,
                    fila,
                    5
            );

            modeloTabla.setValueAt(
                    precio,
                    fila,
                    6
            );

            modeloTabla.setValueAt(
                    obtenerIdioma(),
                    fila,
                    7
            );

            modeloTabla.setValueAt(
                    obtenerFormato(),
                    fila,
                    8
            );

            for (model libro : libros) {if (libro.getIdlibro() == id) {JOptionPane.showMessageDialog(vista, "Ya existe un libro con ese ID"
                    );

                    return;
                }
            }
            model libro = libros.get(fila);

            libro.setNombre(vista.getNombretxt().getText());
            libro.setAutor(vista.getAutortxt().getText());
            libro.setSinopsis(vista.getSinopsistxta().getText());
            libro.setEditorial(vista.getEditorialtxt().getText());
            libro.setGenero(vista.getGenerolcmb().getSelectedItem().toString());
            libro.setIdlibro(id);
            libro.setPrecio(precio);
            libro.setIdioma(obtenerIdioma());
            libro.setFormato(obtenerFormato());

            guardarArchivo();



            JOptionPane.showMessageDialog(
                    vista,
                    "Libro modificado correctamente"
            );

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    vista,
                    "ID y Precio deben ser numéricos"
            );

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    vista,
                    "Error: " + ex.getMessage()
            );
        }
    }
    //verefica los datos puestos
    private void cargarDatosTabla() {

        int fila = vista.getTablalibros().getSelectedRow();

        if (fila >= 0) {

            String mensaje =
                    "Los Datos Libro a verificar\n\n" +
                            "Nombre: " + modeloTabla.getValueAt(fila, 0) + "\n" +
                            "Autor: " + modeloTabla.getValueAt(fila, 1) + "\n" +
                            "Sinopsis: " + modeloTabla.getValueAt(fila, 2) + "\n" +
                            "Editorial: " + modeloTabla.getValueAt(fila, 3) + "\n" +
                            "Género: " + modeloTabla.getValueAt(fila, 4) + "\n" +
                            "ID: " + modeloTabla.getValueAt(fila, 5) + "\n" +
                            "Precio: $" + modeloTabla.getValueAt(fila, 6) + "\n" +
                            "Idioma: " + modeloTabla.getValueAt(fila, 7) + "\n" +
                            "Formato: " + modeloTabla.getValueAt(fila, 8);

            JOptionPane.showMessageDialog(
                    vista,
                    mensaje,
                    "Verificación del Libro",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } else {

            JOptionPane.showMessageDialog(
                    vista,
                    "Seleccione un libro para verificar"
            );
        }
    }

    //limpia el formulario
    private void limpiarFormulario() {

        vista.getNombretxt().setText("");
        vista.getAutortxt().setText("");
        vista.getSinopsistxta().setText("");
        vista.getEditorialtxt().setText("");
        vista.getIdlibrotxt().setText("");
        vista.getPreciotxt().setText("");

        vista.getGenerolcmb().setSelectedIndex(0);

        vista.getEspanol().setSelected(false);
        vista.getIngles().setSelected(false);
        vista.getPortugues().setSelected(false);
        vista.getItaliano().setSelected(false);

        vista.getGrupo().clearSelection();
    }

    //Obtiene el idioma elegido por el usuario

    private String obtenerIdioma() {

        if (vista.getEspanol().isSelected())
            return "Español";

        if (vista.getIngles().isSelected())
            return "Inglés";

        if (vista.getPortugues().isSelected())
            return "Portugués";

        if (vista.getItaliano().isSelected())
            return "Italiano";

        return "No definido";
    }

    //Obtiene el formato del libro seleccionado

    private String obtenerFormato() {

        if (vista.getPastadura().isSelected())
            return "Pasta Dura";

        if (vista.getPastablanda().isSelected())
            return "Pasta Blanda";

        if (vista.getBolsillo().isSelected())
            return "Bolsillo";

        return "No definido";
    }
    //Un selecionar cualquier archivo del ordenador para cualquier libro

    private void cargarArchivoSinopsis
            () {

        int opcion = vista.getArchivo().showOpenDialog(vista);

        if (opcion == JFileChooser.APPROVE_OPTION) {

            File archivoSeleccionado = vista.getArchivo().getSelectedFile();

            try {

                BufferedReader br = new BufferedReader(new FileReader(archivoSeleccionado));

                String linea;
                StringBuilder contenido =
                        new StringBuilder();

                while ((linea = br.readLine()) != null) {contenido.append(linea).append("\n");
                }

                br.close();

                vista.getSinopsistxta().setText(
                        contenido.toString()
                );

                JOptionPane.showMessageDialog(
                        vista,
                        "Archivo cargado correctamente"
                );
            } catch (IOException e) {
                JOptionPane.showMessageDialog(
                        vista,
                        "Error al leer el archivo"
                );
            }
        }
    }
    //con el jchooser cambia color de la tabla
    private void cambiarColorTabla() {

        Color color = JColorChooser.showDialog(vista, "Seleccione el color de su preferencia para la tabla", Color.WHITE);

        if (color != null) {

            vista.getTablalibros().setBackground(color);

            vista.getTablalibros().setSelectionBackground(
                    color.darker()
            );
        }
    }
    //Reloj funcional
    private void iniciarReloj() {

        Thread reloj = new Thread(() -> {

            while (true) {

                LocalTime hora = LocalTime.now();

                String tiempo = hora.format(DateTimeFormatter.ofPattern("HH:mm:ss"));

                vista.getRelojlbl().setText(
                        "Reloj: " + tiempo
                );

                try {

                    Thread.sleep(1000);

                } catch (InterruptedException e) {

                    e.printStackTrace();
                }
            }
        });

        reloj.start();
    }

    //use la serualizacion para guardar el archivo
    private void guardarArchivo() {

        try {

            ObjectOutputStream salida =
                    new ObjectOutputStream(
                            new FileOutputStream("libros.dat")
                    );

            salida.writeObject(libros);

            salida.close();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    vista,
                    "Error"
            );
        }
    }

//guarda los libros con la serializacion cuando no cierre la app
    @SuppressWarnings("unchecked")
    private void cargarArchivo() {

        try {
            ObjectInputStream entrada =
                    new ObjectInputStream(
                            new FileInputStream("libros.dat")
                    );

            libros = (ArrayList<model>) entrada.readObject();

            entrada.close();

            for (model libro : libros) {

                modeloTabla.addRow(new Object[]{
                        libro.getNombre(),
                        libro.getAutor(),
                        libro.getSinopsis(),
                        libro.getEditorial(),
                        libro.getGenero(),
                        libro.getIdlibro(),
                        libro.getPrecio(),
                        libro.getIdioma(),
                        libro.getFormato()
                });
            }

        } catch (Exception e) {

            libros = new ArrayList<>();
        }
    }

    //cuenta los iodamas seleccionados en el chexbox
    private int contarIdiomas() {
        int contador = 0;
        if (vista.getEspanol().isSelected()) contador++;
        if (vista.getIngles().isSelected()) contador++;
        if (vista.getPortugues().isSelected()) contador++;
        if (vista.getItaliano().isSelected()) contador++;

        return contador;
    }
}