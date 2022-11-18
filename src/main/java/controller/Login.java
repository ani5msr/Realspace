package controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import javax.servlet.*;
import services.Userdb;
import model.Ysuser;
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public Login() {
	 super();
	}
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,
			IOException {
			 //this page does not require user to be
			 String useremail = request.getParameter("email");
			 String userpassword = request.getParameter("password");
			 String action = request.getParameter("action");
			 //String remember = request.getParameter
			 String nextURL = "/error.jsp";
			 HttpSession session = request.getSession();
			 //create an instance of the user and put it inthe session
			 //only add the user to the session if the userif valid.
			 //The presence of the user is used to determinewho
			 //owns the site and will be used to connect the database
			 if (action.equals("logout")){
			 session.invalidate();
			 nextURL = "/login.jsp";
			 }else{
			 if (Userdb.isValidUser(useremail,userpassword)){
			 Ysuser user = Userdb.getUserByEmail(useremail);
			 session.setAttribute("user", user);
			 int gravatarImageWidth = 30;
			 String gravatarURL = Userdb.getGravatarURL(useremail,gravatarImageWidth);
			 session.setAttribute("gravatarURL", gravatarURL);
			 nextURL = "/home.jsp";
			 }else{
			 nextURL = "/login.jsp";
			 }
			 }
			 //redirect to next page as indicated by the
			 getServletContext().getRequestDispatcher(nextURL).forward(request,response);
			  }			
}
