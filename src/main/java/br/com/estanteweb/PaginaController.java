package br.com.estanteweb;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class PaginaController {
    @ResponseBody
    @GetMapping("/")        
    public String inicio(){
        return "EstanteWeb: livros usados com história";
    }
    @ResponseBody
    @GetMapping("/sobre")
    public String descricao(){
        return "A loja EstanteWeb é uma loja de livros antigos e usados que possuem historias em si";
    }
    @GetMapping("/contato")
    public String contato(Model model) {
        return "contato";
    }
    @PostMapping("/contato")
    public String contato(Model model, @RequestParam("nome") String nome, @RequestParam("mensagem") String mensagem) {
        if(mensagem.isBlank()){
            model.addAttribute("erro", "Mensagem está vazia");
            model.addAttribute("nome", nome);
            return "contato";
        }
        return "redirect:/contato/sucesso";
    }
    @GetMapping("/contato/sucesso")
    public String sucesso(){
        return "contato-sucesso";
    }
}

