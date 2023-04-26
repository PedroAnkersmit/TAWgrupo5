<%--
  Created by IntelliJ IDEA.
  User: Pedro
  Date: 24/03/2023
  Time: 15:40
  To change this template use File | Settings | File Templates.

  Created by Pedro Ankersmit Carrión
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<h6> Accediendo con el siguiente sessionid: <%= session.getId() %> </h6>
 <h1>Crear cuenta nueva</h1>
 <form action="/alta" method="post">
     <table>
         <tr>
             <td>Nombre:</td> <td><input type="text" name="name"></td>
         </tr>
         <tr>
             <td>Email:</td> <td><input type="text" name="mail"> </td>
         </tr>
         <tr>
             <td>Telefono:</td> <td><input type="text" name="phone"> </td>
         </tr>
         <tr>
             <td colspan="2"> <button>Crear cuenta</button></td>
         </tr>
     </table>
 </form>
</body>
</html>