<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <#include "cabecalho.ftlh">
    <title>Sugestões</title>
</head>
<body>

    <h1>Sugestões de Livros</h1>

    <ul>
        <#list sugestoes as sugestao>
            <li>
                ${sugestao.nomeCliente} - ${sugestao.livroSugerido}
            </li>
        </#list>
    </ul>

</body>
</html>