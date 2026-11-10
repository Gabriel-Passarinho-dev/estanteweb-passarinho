package br.com.estanteweb;

import java.util.Map;

public class Sugestao {

    private String nomeCliente;
    private String livroSugerido;

    public Sugestao(String nomeCliente, String livroSugerido) {
        this.nomeCliente = nomeCliente;
        this.livroSugerido = livroSugerido;
    }

    public static Sugestao deMap(Map<String, Object> linha) {
        return new Sugestao(
            (String) linha.get("nome_cliente"),
            (String) linha.get("livro_sugerido")
        );
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public String getLivroSugerido() {
        return livroSugerido;
    }
}