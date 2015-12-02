<%@page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@page import="net.tanesha.recaptcha.*" %>
<%@page import="net.tanesha.recaptcha.ReCaptchaFactory" %>
<%@page import="net.tanesha.recaptcha.ReCaptcha" %>
<%-- <%@page import="net.tanesha.recaptcha.ReCaptchaFactory" %> --%>
    
<!DOCTYPE html>
<html lang="en">
<head>
<style>
body{
color:"white";
}
</style>
    <title> MCELLBLOCKS</title>
    <link href="assets/css/bootstrap.css" rel="stylesheet" />
    <link href="assets/css/font-awesome.min.css" rel="stylesheet" />
    <link href="css/session.css" rel="stylesheet" /> 
    <link href="css/elusive-webfont.css" rel="stylesheet" />
    <link href="css/pages.css" rel="stylesheet" />
    <link href="css/sunburst.css" rel="stylesheet" />
    <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
</head>
  <body>

  <div id="session">
    <h1><img src="img/bootstrap/mobile-integration-workgroup-squarelogo.png" border="0" alt="MIW" style="margin-bottom:'0px';height:80px;width:'80px;'"/></h1>
    <h2>Create an account</h2>
    
    <form class="commonForm" action="registerServlet" id="session-form" method="post" onsubmit="return checkform(this);" accept-charset="utf-8">
      <fieldset>
        <div style='display:none'></div>
        <p><label for="id_email">Email:</label> <input type="text" placeholder="E-mail address" name="email" id="id_email" /></p>
<p><label for="id_username">Username:</label> <input id="id_username" type="text" placeholder="Username" name="username" maxlength="30" /></p>
<p><label for="id_password1">Password1:</label> <input type="password" placeholder="Password" name="password1" id="id_password1" /></p>
<p><label for="id_password2">Password2:</label> <input type="password" placeholder="Password confirmation" name="password2" id="id_password2" /></p>
<p><%
	ReCaptcha c = ReCaptchaFactory.newReCaptcha(
       		  	"6LdlHOsSAAAAAM8ypy8W2KXvgMtY2dFsiQT3HVq-","6LdlHOsSAAAAACe2WYaGCjU2sc95EZqCI9wLcLXY", false);
	out.print(c.createRecaptchaHtml(null, null));
%></p>

<!-- <p><label for="id_captcha">Captcha:</label> <script type="text/javascript">
    var DjangoRecaptchaOptions = {
  "lang": "en"
};
    if (typeof RecaptchaOptions !== 'object') {
        RecaptchaOptions = DjangoRecaptchaOptions;
    } else {
        for (key in DjangoRecaptchaOptions) {
            RecaptchaOptions[key] = DjangoRecaptchaOptions[key];
        }
    }
</script> -->
<!-- <script type="text/javascript" src="https://www.google.com/recaptcha/api/challenge?k=6LcBlucSAAAAAKOP-nLexk702KilC0KGPaMegt-I&hl=en"></script>
<noscript>
  <iframe src="https://www.google.com/recaptcha/api/noscript?k=6LcBlucSAAAAAKOP-nLexk702KilC0KGPaMegt-I&hl=en" height="300" width="500" frameborder="0"></iframe><br />
  <textarea name="recaptcha_challenge_field" rows="3" cols="40"></textarea>
  <input type='hidden' name='recaptcha_response_field' value='manual_challenge' />
</noscript> -->
<!-- </p> -->
        <div class="submitCont">
          <input type="submit" value="Create an account" />
        </div>
      </fieldset>
      <p>
        <a href="/ngdemo/dummynew.html">Log in</a> or <a href="/ngdemo/reset_password.html">reset your password</a>
      </p>
  </form> <!-- /commonForm  -->
  </div> <!-- /session -->


    <script src="assets/plugins/jquery-1.10.2.js"></script>
    <!-- BOOTSTRAP CORE SCRIPT   -->
    <script src="assets/plugins/bootstrap.js"></script>
  
    <!-- CUSTOM SCRIPTS -->
    <script src="assets/js/custom.js"></script>
<!-- JQuery ================================================================ -->
<script src="js/jquery/jquery-2.0.3.js"></script>

<!-- Bootstrap ============================================================= -->
<script src="js/bootstrap/bootstrap.js"></script>

<script src="lib/angular/angular.js"></script>
<script src="lib/angular/angular-resource.js"></script>

<!-- AngularJS App Code ==================================================== -->
<script src="js/app.js"></script>
<script src="js/services.js"></script>
<script src="js/controllers.js"></script>
<script src="js/filters.js"></script>
<script src="js/directives.js"></script>

</body>
</html>