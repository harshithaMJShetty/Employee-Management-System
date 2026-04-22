<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import = "com.Employee.Employee" %>
    <% Employee emp = (Employee) request.getAttribute("employee"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>Employee form</h2>
<form method="post" action= "employee">
<input type = "hidden" name = "id" value= "<%= emp != null ? emp.getId() : "" %>">
Name : <input type = "text" name = "name" value = "<%= emp != null ? emp.getName() : "" %>"><br><br>
Email : <input type = "email" name = "email" value = "<%= emp != null ? emp.getEmail() : "" %>"><br><br>
Department : <input type = "text" name = "department" value = "<%= emp != null ? emp.getdepartment() : "" %>">
<input type = "submit" value = "Save">
</form>

</body>
</html>