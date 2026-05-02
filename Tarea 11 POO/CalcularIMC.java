package CalcularaImc2;

import java.awt.*;
import java.awt.event.*;

public class CalcularIMC extends Frame {

    private double altura;
    private double peso;
    private double imc;

    private TextField entrada1;
    private TextField entrada2;
    private Label resultado;
    private Button boton;

    public CalcularIMC() {
        super("Calculadora IMC");
        setSize(600, 600);
        setLayout(new FlowLayout());
        setVisible(true);

        add(new Label("Peso (kg):"));
        entrada1 = new TextField(10);
        add(entrada1);

        add(new Label("Altura (m):"));
        entrada2 = new TextField(10);
        add(entrada2);

        boton = new Button("Calcular IMC");
        add(boton);

        resultado = new Label("IMC: ");
        add(resultado);

        boton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    peso = Double.parseDouble(entrada1.getText());
                    altura = Double.parseDouble(entrada2.getText());
                    calcularImc();
                    resultado.setText("Su IMC es: " + imc);

                } catch (NumberFormatException ex) {
                    resultado.setText("Error:Pon numeros Nomas");
                }
            }
        });

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

    }

    public void calcularImc() {
        imc = peso / (altura * altura);
    }


}
