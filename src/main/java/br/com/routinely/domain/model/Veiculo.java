package br.com.routinely.domain.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Entity
@Table(name = "veiculos")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Veiculo {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // O Regex abaixo aceita TANTO o padrão antigo (ABC1234) QUANTO o Mercosul (ABC1D23)
    // [A-Z]{3} = 3 letras
    // [0-9] = 1 número
    // [0-9A-Z] = 1 número ou 1 letra
    // [0-9]{2} = 2 números
    @Pattern(regexp = "^[A-Z]{3}[0-9][0-9A-Z][0-9]{2}$", message = "Formato de placa inválido")
    @Column(nullable = false, unique = true, length = 7)
    private String placa;

    @Column(nullable = false)
    private String modelo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoVeiculo tipo;

    @Column(name = "status_conexao", nullable = false)
    private Boolean conectado = true;


    public Veiculo(String placa, String modelo, TipoVeiculo tipo) {
        this.placa = placa;
        this.modelo = modelo;
        this.tipo = tipo;
        this.conectado = true;
    }


    public void conectar() {
        this.conectado = true;
    }

    public void desconectar() {
        this.conectado = false;
    }


}
