<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <#include "cabecalho.ftlh">
    <title>Remover livro</title>
</head>
<body>
    <h1>Remover livro</h1>

    <p>Você está prestes a remover este livro do acervo:</p>
    <h2>${livro.titulo}</h2>
    <h3>${livro.autor}</h3>
    <p>${livro.ano}</p>
    <p>${livro.preco}</p>

    <p>Esta ação não pode ser desfeita.</p>

    <form action="/livros/${livro.id?c}/remover" method="post">
        <button type="submit">Confirmar remoção</button>
        <a href="/livros/${livro.id?c}">Desistir</a>
    </form>
</body>
</html>
