package com.pi3.scorewizard;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.pi3.scorewizard.pessoafisica.PessoaFisica;
import com.pi3.scorewizard.pessoafisica.PessoaFisicaController;
import com.pi3.scorewizard.Model;
import java.io.IOException;
import java.util.ArrayList;

@RestController
// @Controller
public class IndexController {
	
	@Autowired
	PessoaFisicaController pf = new PessoaFisicaController();
	
	HelloController cont = new HelloController();
	
	Model model = new Model();
	
	PessoaFisicaController pc = new PessoaFisicaController();
	
	@PostMapping(value = "/submitFunc")
	public ResponseEntity<Object> salvarFunc(@RequestBody final String funcData) throws IOException {
				
	// 	System.out.println("Funcionou!");
	// 	System.out.println(funcData);
		
	 	JSONObject obj = new JSONObject();
		try {
			obj = new JSONObject(funcData);
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
	 	
	 	ArrayList<PessoaFisica> pessoaf = new ArrayList<>();
	 	pessoaf.addAll(pf.getAllPessoaFisica());
	 	
	 	int i = 1;
	 	// while( i <= pessoaf.size()) {
	 	// 	if(i == pessoaf.size()) {
		//   		try {
		// 			 pf.addpessoaf(obj.getString("docCli"), obj.getString("senha"), obj.getString("sexo"),
		// 			 obj.getInt("anoNascimento"), obj.getString("cidade"), obj.getString("estado"));
		// 		} catch (JSONException e) {
		// 			e.printStackTrace();
		// 		}
		//   		  break;
		//     	}
		//   	  	try {
		// 			if (obj.getString("docCli").equals(pessoaf.get(i).getDocumento())) {
		// 				break;
		// 			}
		// 		} catch (JSONException e) {
		// 			e.printStackTrace();
		// 		}
		//   	  i++;
		// }
	 	
	 	String documento = "11819838777";
	 	model.CalcularXP(documento);
	 	
	 	return new ResponseEntity<Object>(funcData, HttpStatus.OK);
	}
	  
	@GetMapping("/index")
	public String LoginForm(Model model) {
	// model.addAttribute("index", new Login());
	return "index";
	}

	// @PostMapping("/index")
	// public String LoginSubmit(@ModelAttribute Login greeting, Model model) {
		
	// 	ArrayList<PessoaFisica> pessoaf = new ArrayList<>();
	// 	pessoaf.addAll(pf.getAllPessoaFisica());
		
	// 	int i = 1;
	// 	while( i <= pessoaf.size()) {
	// 		if(greeting.getCpf().equals(pessoaf.get(i).getDocumento()) && greeting.getSenha().equals(pessoaf.get(i).getSenha())) {
	// 			System.out.println("User j cadastrado");
	// 			return "dashboard";
	// 		}
	// 		if(i == (pessoaf.size() - 1)) {
	// 			return "index";
	// 		}
	// 		i++;
	// 	}

	// return "/index";	  
	// }
	  
}
