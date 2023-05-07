<%@ page import="com.taw.grupo5.entity.CuentaEntity" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Pedro
  Date: 27/04/2023
  Time: 11:45
  To change this template use File | Settings | File Templates.

  Created by Pedro Ankersmit Carrion
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style><%@include file="/css/styles.css"%></style>
    <title>Title</title>
</head>
<body>

<%
    Integer idCuentaEmisora = (Integer) request.getAttribute("idAccount");
    List<CuentaEntity> listaCuentas = (List<CuentaEntity>) request.getAttribute("accountList");
%>
<h1>Selecciona una cuenta</h1>

<form action="transferView" method="post">
    <input name="idAccount" value="<%=idCuentaEmisora%>" hidden>
    Numero de Cuenta: <select name="idCuentaReceptora" required="true">
        <%
            for(CuentaEntity c: listaCuentas){
        %>
        <option value="<%=c.getIdcuenta()%>"> <%=c.getNumerocuenta()%></option>
    <%
    }
        %>
</select>
    <button>Enviar</button>
</form>

</body>
</html>
