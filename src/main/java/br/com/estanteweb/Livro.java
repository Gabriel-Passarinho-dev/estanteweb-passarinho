package br.com.estanteweb;

import java.util.Map;

public class Livro {

    private long id;
    private String titulo;
    private String autor;
    private int ano;
    private double preco;

    public static Livro deMap(Map<String, Object> linha) {
        Livro livro = new Livro();
        livro.setId(((Number) linha.get("id")).longValue());
        livro.setTitulo((String) linha.get("titulo"));
        livro.setAutor((String) linha.get("autor"));
        livro.setAno(((Number) linha.get("ano")).intValue());
        livro.setPreco(((Number) linha.get("preco")).doubleValue());

        return livro;
    }
    public Livro() {
    }
    public long getId() {
        return id;
    }
    public String getTitulo() {
        return titulo;
    }
    public String getAutor() {
        return autor;
    }
    public int getAno() {
        return ano;
    }
    public double getPreco() {
        return preco;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    public void setAno(int ano) {
        this.ano = ano;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }
}