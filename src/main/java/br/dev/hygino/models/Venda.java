package br.dev.hygino.models;

import java.time.LocalDateTime;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Venda {

    private Integer id;
    private int clientId;
    private LocalDateTime datetime;
    private double totalVenda;
    private String observacaoes;
}
