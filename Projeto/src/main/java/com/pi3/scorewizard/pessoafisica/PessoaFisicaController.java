package com.pi3.scorewizard.pessoafisica;

import java.util.ArrayList;

import com.pi3.scorewizard.movimento.Movimento;
import com.pi3.scorewizard.movimento.MovimentoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/pessoafisica")
public class PessoaFisicaController {

	@Autowired
    private PessoaFisicaRepository pessoafisicarepository;
    
    @Autowired
	private MovimentoRepository movimentorepository;
	
	@PostMapping(path="/addpessoaf")
    public @ResponseBody String addPessoaFisica (@RequestParam String docCli,
                                            @RequestParam String sexo,
                                            @RequestParam int date,
                                            @RequestParam String cidade,
                                            @RequestParam String estado) {

      PessoaFisica pesf = new PessoaFisica(docCli, "", sexo, date, cidade, estado);
      
      pessoafisicarepository.save(pesf);
      return "Saved";
    }
	
    @GetMapping(path="/getAllPessoaFisica")
    public @ResponseBody ArrayList<PessoaFisica> getAllPessoaFisica() {
        return pessoafisicarepository.findAll();
    }

    @GetMapping(path="/getPessoaFisica")
    public @ResponseBody PessoaFisica getPessoaFisica(@RequestParam String documento) {
         return pessoafisicarepository.findByDocumento(documento);
    }
    
    @GetMapping(path="/getPessoaFisicaScore")
    public @ResponseBody int getPessoaFisicaScore(@RequestParam String documento) {
        PessoaFisica pessoa = pessoafisicarepository.findByDocumento(documento);
        Double operacao, parcela, atraso, inadimplencia, score;

        operacao = 100.00 / pessoa.getOperacoesCount();
        parcela = 100.00 / pessoa.getMovimentosCount();
        atraso = Double.valueOf((movimentorepository.findByPessoaFisicaDocumento(pessoa.getDocumento()).size() * 100) / 100);
        inadimplencia = (parcela * (atraso * 2))/100;
        score = 1000-(inadimplencia*100);
        
        System.out.println("Operacao " + pessoa.getOperacoesCount());
        System.out.println("Movimentos " + movimentorepository.findByPessoaFisicaDocumento(pessoa.getDocumento()).size());
        System.out.println("operacao: " + operacao.toString());
        System.out.println("parcela: " + parcela.toString());
        System.out.println("atraso: " + atraso.toString());
        System.out.println("inadimplencia: " + inadimplencia.toString());

        return score.intValue();
    }

}
