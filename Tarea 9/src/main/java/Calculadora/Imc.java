package Calculadora;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public @Data class Imc {
    private double altura;
    private double peso;
    private double imc;
    private String estado;

    public void calcularImc(){
        this.imc = this.peso /(this.altura*this.altura);
    }

}
