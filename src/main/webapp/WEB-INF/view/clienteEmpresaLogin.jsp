<!DOCTYPE html>
<html lang="en">
<head>
    <style><%@include file="/css/styles.css"%></style>
    <title>Login</title>
</head>
<body>

<h6> Accediendo con el siguiente sessionid: <%= session.getId() %> </h6>
<form action="/empresa/login" method="post">
    <table>
        <tr>
            <td>Email:</td> <td><input type="text" name="mail"> </td>
        </tr>
        <tr>
            <td colspan="2"> <button>Enviar</button></td>
        </tr>
    </table>
    </form>
</body>
</html>