<header>
    <h2>EstanteWeb</h2>
    <a href="/">Voltar para o início</a>
</header>
<body>
    <#if erro??>
        <p>${erro}</p>
    </#if>
    <form method="post" action="/sugestoes">
        <input type="text" name="nomeCliente" value="${nomeCliente!}">
        <input type="text" name="livroSugerido" value="${livroSugerido!}">

            <button type="submit">Enviar</button>
    </form>
</body>