<#-- locale fixo: o preço precisa sair com ponto decimal para o input type="number" -->
<#setting locale="en_US">
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <#include "cabecalho.ftlh">
    <title>Corrigir livro</title>
</head>
<body>
    <h1>Corrigir livro</h1>

    <form action="/livros/${livro.id?c}" method="post">
        <#if erro??>
            <p>${erro}</p>
        </#if>

        <label>Título:</label>
        <input type="text" name="titulo" value="${livro.titulo!}">

        <label>Autor:</label>
        <input type="text" name="autor" value="${livro.autor!}">

        <label>Ano:</label>
        <input type="number" name="ano" value="${livro.ano?c}">

        <label>Preço:</label>
        <input type="number" name="preco" step="0.01" value="${livro.preco?string("0.00")}">

        <button type="submit">Salvar correção</button>
        <a href="/livros/${livro.id?c}">Cancelar</a>
    </form>
</body>
</html>
