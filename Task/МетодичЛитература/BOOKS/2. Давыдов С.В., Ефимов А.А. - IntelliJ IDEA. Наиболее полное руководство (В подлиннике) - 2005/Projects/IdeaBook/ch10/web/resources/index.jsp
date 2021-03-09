<%@ page import="java.util.Enumeration"%>
 <%--
  "IntelliJ IDEA Book"
  Stanislav Davydov (davidovsv@yandex.ru) (c) 2004  
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head><title>Simple jsp page</title></head>
<body>
<p>Hello, World!</p>
<p>Here is my request params: </p>
<ul>
<%

    Enumeration parameterNames = request.getParameterNames();
    while (parameterNames.hasMoreElements()) {
        String name = (String) parameterNames.nextElement();
    %>
    <li><%=name%> = <%=request.getParameter(name)%></li>
    <%
    }
%>
</ul>

</body>
</html>