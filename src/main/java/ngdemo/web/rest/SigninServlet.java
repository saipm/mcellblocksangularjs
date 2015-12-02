    package ngdemo.web.rest;  
      
    import java.io.IOException;  
    import java.io.PrintWriter;  
      
    import javax.servlet.RequestDispatcher;  
    import javax.servlet.ServletException;  
    import javax.servlet.http.HttpServlet;  
    import javax.servlet.http.HttpServletRequest;  
    import javax.servlet.http.HttpServletResponse;  
    import javax.servlet.http.HttpSession;  
      

      
    public class SigninServlet extends HttpServlet{  
      
        private static final long serialVersionUID = 1L;  
      
        public void doPost(HttpServletRequest request, HttpServletResponse response)    
                throws ServletException, IOException {    
     
            response.setContentType("text/html");    
            PrintWriter out = response.getWriter();    
            String n=request.getParameter("username");    
            String p=request.getParameter("password");  
            HttpSession session = request.getSession(false);  
            if(session!=null)  
            session.setAttribute("name", n);  
      
            if(SigninDao.validate(n, p)){    
            	System.out.println("inside.....................");
                RequestDispatcher rd=request.getRequestDispatcher("dashboard.jsp");    
                rd.forward(request,response);    
            }    
            else{    
            	System.out.println("here:::::::::::::");
                out.print("<p style=\"color:red\">Sorry username or password error</p>");    
                RequestDispatcher rd=request.getRequestDispatcher("dummynew.html");    
                rd.include(request,response);    
            }    
      
            out.close();    
        }    
    }   