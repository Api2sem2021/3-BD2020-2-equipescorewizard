package com.pi3.scorewizard.experiencia;

import java.util.ArrayList;
import org.springframework.data.repository.CrudRepository;

import com.pi3.scorewizard.pessoafisica.PessoaFisica;

public interface ExperienciaVerificacaoRepository extends CrudRepository<ExperienciaVerificacao, Integer>{
	
	ArrayList<ExperienciaVerificacao> findAll();
	
	ExperienciaVerificacao findByDocumentoCliente(String documento);
	
}
