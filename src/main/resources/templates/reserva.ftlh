<header>
    <h2>EstanteWeb</h2>
    <a href="/">Voltar para o início</a>
</header>
<h1>Forumlário</h1>

<#if erro??>
    <p>${erro}</p>
</#if>
<form method="POST" action="">

    <input type="text" name="nome" value="${nome!}">
    <input type="tel" name="telefone">
    
    <button type="submit">Reservar</button>
</form>