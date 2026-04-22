<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*, com.Employee.Employee" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>Employee List</h2>
<table border = "1">
<tr><th>Id</th><th>Name</th><th>Email</th><th>Dept</th><th>Action</th></tr>
<%
Collection<Employee> list = (Collection<Employee>) request.getAttribute("employees");
for(Employee e : list){
%>
<tr>
<td><%= e.getId() %></td>
<td><%= e.getName() %></td>
<td><%= e.getEmail() %></td>
<td><%= e.getdepartment() %></td>
<td>
<a href = "employee?action=edit&id=<%= e.getId() %>">Edit</a>
<a href = "<%= request.getContextPath() %>/employee?action=delete&id=<%= e.getId() %>"
onclick="return confirm('are you sure?')">
Delete
</a>
</td>
</tr>
<% } %>
</table>


<hr>
Last updated Id (Session): <%= session.getAttribute("lastId") %><br>
Last updated Name (Cookie):
<%
Cookie[] cookies = request.getCookies();
if(cookies != null)
	for(Cookie c : cookies)
		if(c.getName().equals("lastName")) out.print(c.getValue());
%>
</body>
</html>