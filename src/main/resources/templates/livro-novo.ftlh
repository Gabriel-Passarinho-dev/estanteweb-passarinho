<header>
    <h2>EstanteWeb</h2>
    <a href="/">Voltar para o início</a>
</header>

<form action="/livros" method="post">
    <#if erro??>
        <p>${erro}</p>
    </#if>

    <label>Título:</label>
    <input type="text" name="titulo" value="${titulo!}">

    <label>Autor:</label>
    <input type="text" name="autor" value="${autor!}">

    <label>Ano:</label>
    <input type="number" name="ano" value="${ano!}">

    <label>Preço:</label>
    <input type="number" name="preco" step="0.01" value="${preco!}">

    <button type="submit">Cadastrar</button>

</form>