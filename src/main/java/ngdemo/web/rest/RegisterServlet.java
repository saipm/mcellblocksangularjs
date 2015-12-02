    package ngdemo.web.rest;  
      
    import java.io.IOException;  
    import java.io.PrintWriter;  
      
    import javax.servlet.RequestDispatcher;  
    import javax.servlet.ServletException;  
    import javax.servlet.http.HttpServlet;  
    import javax.servlet.http.HttpServletRequest;  
    import javax.servlet.http.HttpServletResponse;  
    import javax.servlet.http.HttpSession;

import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;  
      

      
    public class RegisterServlet extends HttpServlet{  
      
        private static final long serialVersionUID = 1L;  
      
        public void doPost(HttpServletRequest request, HttpServletResponse response)    
                throws ServletException, IOException {    
            System.out.println("inside registerservlet::::::::::::::");
            response.setContentType("text/html");   
            PrintWriter out = response.getWriter();   
            Boolean passwordcheck=false;
            String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
            String email1 = request.getParameter("email");
            Boolean b = email1.matches(EMAIL_REGEX);
            System.out.println("is e-mail: "+email1+" :Valid = " + b);
   
            String strName = request.getParameter("username");
            String strPassword = request.getParameter("password1");
            String strConfirmPassword = request.getParameter("password2");
            if(strPassword.equals(strConfirmPassword))
            	passwordcheck=true;
            
            String remoteAddr = request.getRemoteAddr();
    		ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
    		reCaptcha.setPrivateKey("6LdlHOsSAAAAACe2WYaGCjU2sc95EZqCI9wLcLXY");

    		String challenge = request
    				.getParameter("recaptcha_challenge_field");
    		String uresponse = request.getParameter("recaptcha_response_field");
    		ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(
    				remoteAddr, challenge, uresponse);
            System.out.println("value:::"+reCaptchaResponse.isValid());
    		if (reCaptchaResponse.isValid()) {
    			if(b==false){
    				out.print("Invalid email Id");
    			}
    			if(passwordcheck==false){
    				out.println("password mismatch");
    			}
    			if(b && passwordcheck){
    				if(LoginDao.insert(strName, strPassword, email1)==1){
    					out.print(strName+" registered successfully");
    					RequestDispatcher rd=request.getRequestDispatcher("dummynew.html");    
    	                rd.include(request,response);  
    				}
    				else{
    					out.print("Registration Failed! Try Again.");  
    	                RequestDispatcher rd=request.getRequestDispatcher("signup.jsp");    
    	                rd.include(request,response);
    				}
    				
    			}
    			//out.print("CAPTCHA Validation Success! User "+user+" registered.");
    		} else {
    			out.print("CAPTCHA Validation Failed! Try Again.");  
                RequestDispatcher rd=request.getRequestDispatcher("signup.jsp");    
                rd.include(request,response);
    		}  
      
            out.close();    
        }    
    }   