<%@page import="java.io.ByteArrayOutputStream"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="org.apache.struts.Globals"%>
<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Exception</title>
</head>
<body>

<h1>Throwable</h1>
<p>
<%
Throwable exception = (Throwable)request.getAttribute(Globals.EXCEPTION_KEY);
out.println(exception);
out.println("<pre>");
ByteArrayOutputStream baos = new ByteArrayOutputStream();
PrintWriter writer = new PrintWriter(baos);
exception.printStackTrace(writer);
out.println(baos.toString("UTF-8"));
out.println("</pre>");
%>
</p>

</body>
</html>