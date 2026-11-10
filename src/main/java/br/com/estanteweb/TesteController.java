package br.com.estanteweb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class TesteController {
    @GetMapping("/ola")
    public String ola(Model model) {
        model.addAttribute("nomeLoja", "EstanteWeb");
        model.addAttribute("anoFundacao", 1998);
        return "ola";
    }
    
}
