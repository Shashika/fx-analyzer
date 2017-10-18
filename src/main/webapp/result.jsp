<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Results</title>
    <link rel="stylesheet" href="resources/css/bootstrap.min.css"/>
</head>
<body>

<%
    String type = (String) request.getAttribute( "type" );
    if ( type.equals("success") )
    {
%>
<div class="alert alert-success" role="alert">
    <h3><%=(String)request.getAttribute("message")%></h3>
</div>
<%
    }
    else{
%>
<div class="alert alert-danger" role="alert">
    <h3><%=(String)request.getAttribute("message")%></h3>
</div>

<%
    }
%>

</body>
</html>
