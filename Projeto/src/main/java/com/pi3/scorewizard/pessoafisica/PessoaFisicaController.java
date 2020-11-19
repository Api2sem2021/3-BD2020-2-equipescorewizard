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
    public @ResponseBody String addpessoaf (@RequestParam String docCli,
    										@RequestParam String nome,
                                            @RequestParam String sexo,
                                            @RequestParam int date,
                                            @RequestParam String cidade,
                                            @RequestParam String estado, 
                                            @RequestParam String senha) {

      PessoaFisica pesf = new PessoaFisica(docCli, nome, sexo, date, cidade, estado, senha); 

      pessoafisicarepository.save(pesf);
      
      return "Saved";
    }
	
    @GetMapping(path="/getPessoaFisica")
    public @ResponseBody ArrayList<PessoaFisica> getAllPessoaFisica() {
        return pessoafisicarepository.findAll();
    }
}
