package com.pi3.scorewizard.pessoafisica;

import java.util.ArrayList;
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
	
	@PostMapping(path="/addpessoaf")
    public @ResponseBody String addPessoaFisica (@RequestParam String docCli,
                                            @RequestParam String sexo,
                                            @RequestParam int date,
                                            @RequestParam String cidade,
                                            @RequestParam String estado) {

      PessoaFisica pesf = new PessoaFisica(docCli, "", sexo, date, cidade, estado, 0);
      
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
        atraso = 1.00; // (pessoa.getTotalAtrasos() * 100) / 100
        inadimplencia = (parcela * atraso)/100;
        score = 1000-(inadimplencia*10);

        System.out.println("operacao: " + operacao.toString());
        System.out.println("parcela: " + parcela.toString());
        System.out.println("atraso: " + atraso.toString());
        System.out.println("inadimplencia: " + inadimplencia.toString());

        return score.intValue();
    }
    
}
