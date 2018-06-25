<%@ page import="ru.xml.filter.service.RestrictionService" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.xml.filter.restrictions.Restriction" %>
<%@ page import="ru.xml.filter.model.Subject" %><%--
  Created by IntelliJ IDEA.
  User: dsaetgareev
  Date: 25.06.2018
  Time: 8:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%
    RestrictionService restrictionService = new RestrictionService();
    List<Restriction> restrictions = restrictionService.getRestrictions();

%>

</body>
</html>
