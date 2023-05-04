<%@ page import="java.util.List" %>
<%@ page import="com.taw.grupo5.dto.CuentaDTO" %>
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
    <title>Title</title>
</head>
<body>

<%
    Integer idCuentaEmisora = (Integer) request.getAttribute("idAccount");
    List<CuentaDTO> listaCuentas = (List<CuentaDTO>) request.getAttribute("accountList");
%>
<h1>Selecciona una cuenta</h1>

<form action="transferView" method="post">
    <input name="idAccount" value="<%=idCuentaEmisora%>" hidden>
    Numero de Cuenta: <select name="idCuentaReceptora" required="true">
        <%
            for(CuentaDTO c: listaCuentas){
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
