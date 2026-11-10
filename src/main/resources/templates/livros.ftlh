<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <#include "cabecalho.ftlh">
    <title>Acervo de Livros</title>
</head>
<body>

    <h1>Acervo de Livros</h1>

    <#if livros?size == 0>
        <p>Nenhum livro no acervo no momento.</p>
    <#else>
        <ul>
            <#list livros as livro>
                <li>
                    <a href="/livros/${livro.id?c}">${livro.titulo}</a>
                </li>
            </#list>
        </ul>
    </#if>

</body>
</html>