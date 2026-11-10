package br.com.estanteweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

@Controller
public class SugestaoController {

    @Autowired
    private SugestaoRepository repoSugestoes;

    @GetMapping("/sugestoes/nova")
    public String sugestoes(Model model) {
        return "sugestao-nova";
    }

    @GetMapping("/sugestoes")
    public String listar(Model model) {
        model.addAttribute("sugestoes", repoSugestoes.listarTodas());
        return "sugestoes";
    }

    @PostMapping("/sugestoes")
    public String sugestao(
            Model model,
            @RequestParam("nomeCliente") String nomeCliente,
            @RequestParam("livroSugerido") String livroSugerido) {

        if (livroSugerido.isBlank()) {
            model.addAttribute("erro", "Livro sugerido não informado");
            model.addAttribute("nomeCliente", nomeCliente);
            model.addAttribute("livroSugerido", livroSugerido);
            return "sugestao-nova";
        }

        if (nomeCliente.isBlank()) {
            model.addAttribute("erro", "Nome do cliente não informado");
            model.addAttribute("livroSugerido", livroSugerido);
            model.addAttribute("nomeCliente", nomeCliente);
            return "sugestao-nova";
        }

        Sugestao sugestao = new Sugestao(nomeCliente, livroSugerido);

        repoSugestoes.salvar(sugestao);

        return "redirect:/sugestoes";
    }
}