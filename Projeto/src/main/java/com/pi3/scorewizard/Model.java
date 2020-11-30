package com.pi3.scorewizard;

import org.hibernate.annotations.common.reflection.XPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.pi3.scorewizard.experiencia.ExperienciaVerificacao;
import com.pi3.scorewizard.experiencia.ExperienciaVerificacaoRepository;
import com.pi3.scorewizard.pessoafisica.PessoaFisica;
import com.pi3.scorewizard.pessoafisica.PessoaFisicaRepository;

public class Model {
	
	@Autowired
	private PessoaFisicaRepository pessoafisicarepository;
	
	@Autowired
	private ExperienciaVerificacaoRepository experienciaVerificacao;

    public int CalcularXP(String documento) {
        PessoaFisica pessoa = pessoafisicarepository.findByDocumento(documento);
        ExperienciaVerificacao experiencia = experienciaVerificacao.findByDocumentoCliente(documento);
        int operacao, parcela;
        
        operacao = pessoa.getOperacoesCount();
        parcela = pessoa.getMovimentosCount();
        
        System.out.println("operacao: " + operacao);
        System.out.println("parcela: " + parcela);
        
        int oldoperacao = experiencia.getQtd_parcelas_operacoes();
        int oldparcela = experiencia.getQtd_parcelas_movimentos();
        int XPAdiquirido = 0;
        int XPMovimento = 0;
        int XPOperacao = 0;
        
        if(operacao < oldoperacao){
        	int operacoesToXP = oldoperacao - operacao;
			XPOperacao = operacoesToXP*10;
        }
        
        if(parcela < oldparcela){
			int parcelasToXP = oldparcela - parcela;
			XPMovimento = parcelasToXP*20;
        }
        
        XPAdiquirido = XPOperacao + XPMovimento;
        
        pessoa.setXP(XPAdiquirido+pessoa.getXP());
        experiencia.setQtd_parcelas_movimentos(parcela);
        experiencia.setQtd_parcelas_operacoes(operacao);

		int nivelUser = pessoa.getXP()/100;
		System.out.println(nivelUser);

        return nivelUser;
    }
}
