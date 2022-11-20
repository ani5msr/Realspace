<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;
charset=UTF-8"/>
<jsp:include page="bootstrap.jsp"></jsp:include>
 </head>
 <body>
 <h1>Login</h1>
 <div class="container">
 <form class="form-signin" method="post"
action="Login">
 <h2 class="form-signin-heading">${message}Please sign
in</h2>
 <label for="inputEmail" class="sr-only">Email address
</label>
 <input name="email" type="email" id="inputEmail"
class="form-control"
 placeholder="Email address" required autofocus>
 <input type="hidden" name="action" id="action"
value="login"/>
 <label for="inputPassword" class="sr-only">Password
</label>
 <input name="password" type="password"
id="inputPassword"
 class="form-control" placeholder="Password" required>
 <button class="btn btn-lg btn-primary btn-block"
type="submit">Sign in</button>
 </form>
 <a href="adduser.jsp">Join</a>
 </div> <!-- /container -->
<jsp:include page="footer.jsp"></jsp:include>
</head>
<body>

</body>
</html>