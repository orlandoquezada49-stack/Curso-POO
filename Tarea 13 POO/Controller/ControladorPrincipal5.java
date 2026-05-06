package Controller;

import view.VentanaPrincipal5;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ControladorPrincipal5 implements MouseListener {

    private VentanaPrincipal5 view;

    public ControladorPrincipal5(VentanaPrincipal5 view) {
        this.view = view;
        this.view.getBtnSaludar().addMouseListener(this);
        this.view.getLbNombre().addMouseListener(this);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == this.view.getBtnSaludar()) {
            this.view.getLblSalida().setText("Hola " + this.view.getTxtNombre().getText());
            System.out.println("Hola desde la Poderosisima FES ARAGON");
        }

        if (e.getSource() == this.view.getLbNombre()) {
            System.out.println("Desde etiqueta 1");
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}