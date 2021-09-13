<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    double vp = 0, taxa = 0, periodo = 0, amortizacao = 0, parcela = 0, juros = 0;
    String errorMessage = null;
    try
    {
        vp = Double.parseDouble(request.getParameter("vp"));
        taxa = Double.parseDouble(request.getParameter("taxa"));
        periodo = Double.parseDouble(request.getParameter("periodo"));

        parcela = vp * ((taxa/100)/ (1 - (Math.pow((1 + taxa/100), (periodo*(-1))))));
        parcela = Math.round((parcela*100d)/100d);
    }
    catch (Exception ex)
    {
        errorMessage = "Erro na leitura dos parâmetros";
    }
%>
<html>
<head>
    <title>Cálculo de Amortização</title>
</head>
<body>
<%@include file="WEB-INF/jspf/header.jspf" %>
<h1>Cálculo de Amortização</h1>
<table border="1">
    <tr>
        <th>Período</th>
        <th>Saldo Devedor</th>
        <th>Parcela</th>
        <th>Juros</th>
        <th>Amortização</th>
    </tr>
    <tr>
        <td>0</td>
        <td>R$ <%= vp %></td>
        <td></td>
        <td></td>
        <td></td>
    </tr>
    <%for(int i = 0; i < periodo; i++){%>
    <tr>
        <%
            juros = Math.round(taxa/100 * vp);
            amortizacao = Math.round(parcela - juros);
            vp = Math.round(vp - amortizacao);
        %>
        <td><%= i+1 %></td>
        <td><%= vp %></td>
        <td><%= parcela %></td>
        <td><%= juros %></td>
        <td><%= amortizacao %></td>
    </tr>
    <%}%>
</table>

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