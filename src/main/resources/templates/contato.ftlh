<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <#include "cabecalho.ftlh">
    <title>Contato</title>
</head>
<body>
    <#if erro??>
        <p>${erro}</p>
    </#if>
    <form method="POST" action="/contato">

        <input type="text" name="nome" value="${nome!}">
        <input type="text" name="mensagem">
    
        <button type="submit">Enviar</button>
    </form>
</body>