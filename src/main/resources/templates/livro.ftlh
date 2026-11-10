<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <#include "cabecalho.ftlh">
        <title>Detalhes do livro</title>
    </head>
    <body>
        <a href="/livros">Voltar para o acervo</a>

        <h1>Detalhes do livro</h1>
        <h2>${livro.titulo}</h2>
        <h3>${livro.autor}</h3>
        <p>${livro.ano}</p>
        <p>${livro.preco}</p>

        <p>
            <a href="/livros/${livro.id?c}/editar">Corrigir livro</a> |
            <a href="/livros/${livro.id?c}/remover">Remover livro</a>
        </p>
    </body>
</html>
