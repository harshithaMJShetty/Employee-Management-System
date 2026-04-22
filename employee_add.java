package com.ty;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Employee.Employee;

@WebServlet("/Employee")
public class employee_add extends HttpServlet{

    @Override
    public void init() throws ServletException {
    	// TODO Auto-generated method stub
    	Map<String, Employee> employeeMap = new HashMap<>();
    	getServletContext().setAttribute("employees", employeeMap);
    }
	
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	
        String action = req.getParameter("action");
        
        ServletContext context = getServletContext();
        Map<String, Employee> employeeMap = (Map<String, Employee>) context.getAttribute("employees");
        
        if( action == null || action.equals(" ")) {
        	req.setAttribute("employees", employeeMap.values());
        	
        	RequestDispatcher rd = req.getRequestDispatcher("View.jsp");
        	rd.forward(req, resp);
        }
        
        else if("create".equals(action)) {
        	RequestDispatcher rd = req.getRequestDispatcher("EmployeeForm.jsp");
        	rd.forward(req, resp);
        }
        
        else if("edit".equals(action)) {
        	
        	String id = req.getParameter("id");
        	Employee emp = employeeMap.get(id);
        	req.setAttribute("employee", emp);
        	
        	RequestDispatcher rd = req.getRequestDispatcher("EmployeeForm.jsp");
        	rd.forward(req, resp);
        }
        
        else if("delete".equals(action)) {
        	String id = req.getParameter("id");
        	
        	employeeMap.remove(id);
        	resp.sendRedirect(req.getContextPath() + "/employee");
        }
    }
    
    //dopost is employeeform - either new employee or updating employee which needs to be read by this form
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
        
        ServletContext context = getServletContext();
        Map<String, Employee> employeeMap = (Map<String, Employee>) context.getAttribute("employees");
        
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String department = req.getParameter("department");
        
        if(department == null || department.trim().isEmpty()) {
        	department = getServletConfig().getInitParameter("defaultDepartment");
        }
        
        if(id == null || id.trim().isEmpty()) {
        	id = "EMP" + (employeeMap.size() + 1);
        	
        	Employee emp = new Employee(id, name, email, department);
        	employeeMap.put(id, emp);
        }
        
        else {  //to add new info to existing employee using setter methods
        	Employee emp = employeeMap.get(id);
        	emp.setName(name);
        	emp.setEmail(email);
        	emp.setdepartment(department);    	
        }
        
        HttpSession session = req.getSession();
        session.setAttribute("lastId", id);   //to get number of employees by getting last id
        
        //to store last name that we have stored using cookies
        Cookie cookie = new Cookie("lastName", name);
        cookie.setMaxAge(60 * 60); //in secs and after 3600 secs cookie will be destroyed
        resp.addCookie(cookie);
        
        resp.sendRedirect(req.getContextPath() + "/employee");
        
	}
}
