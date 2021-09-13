<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Tablea PRice</title>
</head>
<body>
<%@include file="WEB-INF/jspf/header.jspf" %>
<h1><%= "Pedro Emanoel do Nascimento silva, RA: 1290482012028" %>
</h1>
<br/>
<h1>JSPF</h1>
<h2>Tabela Price</h2>
<form action="tabela-price.jsp">
    <label>Valor do empréstimo: </label>
    <input type="number" name="vp" id="vp"><br><br>
    <label>Taxa: </label>
    <input type="number" name="taxa" id="taxa"><br><br>
    <label>Período do empréstimo</label>
    <input type="number" name="periodo" id="periodo"><br><br>
    <input type="submit" value="Calcular"><br><br>
</form>
<%@include file="WEB-INF/jspf/footer.jspf" %>

</body>
</html>