package com.pi3.scorewizard.experiencia;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity 
@Getter
@Setter
@AllArgsConstructor
public class ExperienciaVerificacao {

	@Id
	private String documentoCliente;
	
	private String tipoPagamento;
	private int qtd_parcelas_operacoes;
	private int qtd_parcelas_movimentos;
	private Date data_login;
	
	
}
