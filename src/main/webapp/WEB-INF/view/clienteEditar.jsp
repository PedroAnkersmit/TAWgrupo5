<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

<h1>Tus Datos:</h1>
<form:form action="/clienteHome/guardar" modelAttribute="user" method="post">
    <form:hidden path="idcliente"/>
    <form:hidden path="idconversacion"/>
    Nombre: <form:input path="nombre" size="30px" maxlength="30"/>
    Email: <form:input path="email" size="60px" maxlength="80"/>
    Telefono: <form:input path="telefono" size="30px" maxlength="15"/>
    <form:hidden path="tipoclienteByIdtipocliente"/>

    <form:button>Guardar</form:button>
</form:form>

</body>
</html>