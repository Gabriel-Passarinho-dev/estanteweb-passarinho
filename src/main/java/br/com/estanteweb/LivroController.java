package br.com.estanteweb;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.ui.Model;

@Controller
public class LivroController {
    @Autowired 
    private LivroRepository repoLivros;
    @GetMapping("/livros")
    public String books(Model model) {
        model.addAttribute("livros", repoLivros.listarTodos());

        return "livros";
    }
    @ResponseBody
    @GetMapping("/livros/total")
    public String contBooks(){
        String response = repoLivros.contarLivros();
        return response;
    }
    @GetMapping("/livros/busca")
    public ResponseEntity<String> buscaLivros(@RequestParam(defaultValue = "999") double precoMaximo){
        String resposta = "Acervo EstanteWeb: ";
        for(Livro l : repoLivros.listarAtePreco(precoMaximo)){
            resposta += l.getTitulo() + ", " +
            l.getAutor() + " " +
            l.getAno() + " (R$ " +
            String.format("%.2f", l.getPreco()) + ") | ";
        }
        List<Livro> resultado = repoLivros.listarAtePreco(precoMaximo);
        if(resultado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum livro encontrado");
        }
        return ResponseEntity.ok()
        .header("X-Total-Encontrados", String.valueOf(resultado.size()))
        .body(resposta);
    }

    @GetMapping(value = "/livros/html", produces = "text/html")
    public String listarHtml(
            @RequestParam(required = false) String ordem,
            Model model) {

        List<Livro> livros;

        if (ordem == null || ordem.isBlank()) {
            livros = repoLivros.listarTodos();
        } else {
            livros = repoLivros.listarOrdenadoPor(ordem);
        }

        model.addAttribute("livros", livros);

        return "livros";
    }

    @ResponseBody
    @GetMapping("/livros/quem-acessa")
    public String quemAcessa(@RequestHeader("User-Agent") String userAgent) {
        return "User-Agent em questão: " + userAgent;
    }

    @GetMapping("/livros/valor-total")
    @ResponseBody
    public String valorTotal() {
        Double total = repoLivros.somarValorAcervo();

        return String.format(
            "Valor total do acervo: R$ %.2f",
            total
        );
    }

    @GetMapping("/livros/novo")
    public String cadastro(){
        return "livro-novo";
    }

    @PostMapping("/livros")
    public String cadastrar(
            @RequestParam String titulo,
            @RequestParam String autor,
            @RequestParam int ano,
            @RequestParam double preco,
            Model model) {

        String erro = validar(titulo, autor, preco);

        if (erro != null) {
            model.addAttribute("erro", erro);

            model.addAttribute("titulo", titulo);
            model.addAttribute("autor", autor);
            model.addAttribute("ano", ano);
            model.addAttribute("preco", preco);

            return "livro-novo";
        }

        Livro livro = new Livro();

        livro.setTitulo(titulo);
        livro.setAutor(autor);
        livro.setAno(ano);
        livro.setPreco(preco);

        repoLivros.salvar(livro);

        return "redirect:/livros/html";
    }
    
    @GetMapping("/livros/{id}")
    public String mostrar(@PathVariable Long id, Model model){
        Livro livro = repoLivros.buscarPorId(id);

        if (livro == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        model.addAttribute("livro", livro);
        return "livro";
    }

    @GetMapping("/livros/{id}/editar")
    public String editar(@PathVariable Long id, Model model){
        model.addAttribute("livro", buscarOu404(id));
        return "livro-editar";
    }

    @PostMapping("/livros/{id}")
    public String salvarEdicao(
            @PathVariable Long id,
            @RequestParam String titulo,
            @RequestParam String autor,
            @RequestParam int ano,
            @RequestParam double preco,
            Model model){

        buscarOu404(id);

        Livro livro = new Livro();
        livro.setId(id);
        livro.setTitulo(titulo);
        livro.setAutor(autor);
        livro.setAno(ano);
        livro.setPreco(preco);

        String erro = validar(titulo, autor, preco);

        if (erro != null) {
            model.addAttribute("erro", erro);
            model.addAttribute("livro", livro);
            return "livro-editar";
        }

        repoLivros.atualizar(livro);
        return "redirect:/livros/" + id;
    }

    @GetMapping("/livros/{id}/remover")
    public String confirmarRemocao(@PathVariable Long id, Model model){
        model.addAttribute("livro", buscarOu404(id));
        return "livro-remover";
    }

    @PostMapping("/livros/{id}/remover")
    public String remover(@PathVariable Long id){
        if (repoLivros.excluir(id) == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/livros/html";
    }

    private Livro buscarOu404(Long id){
        Livro livro = repoLivros.buscarPorId(id);

        if (livro == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return livro;
    }

    // Regras de validação compartilhadas entre cadastro e correção.
    private String validar(String titulo, String autor, double preco){
        if (titulo.isBlank()) {
            return "O título é obrigatório.";
        }
        if (autor.isBlank()) {
            return "O autor é obrigatório.";
        }
        if (preco <= 0) {
            return "O preço deve ser maior que zero.";
        }
        return null;
    }
}
