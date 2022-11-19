package controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Ysuser;
import services.Userdb;

@WebServlet("/AddUser")
public class UserAdd extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public UserAdd() {
		super();
	}
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
			 HttpSession session = request.getSession();
			 //This page does not require user to be logged in
			 String userName = request.getParameter("userName");
			 String userEmail = request.getParameter("userEmail");
			 String userPassword = request.getParameter("userPassword");
			 String userMotto = request.getParameter("userMotto");
			 String nextURL = "/error.jsp";
			 //check if user exists (by email)
			 Ysuser user = Userdb.getUserByEmail(userEmail);
			 //create user and add them if they don't exit
			 if (user == null){
			 user = new Ysuser();
			 user.setUsername(userName);
			 user.setUseremail(userEmail);
			 user.setUserpassword(userPassword);
			 Date joindate = Calendar.getInstance().getTime();
			 user.setJoindate(joindate);
			 user.setMotto(userMotto);
			 Userdb.insert(user);
			 nextURL = "/home.jsp";
			 }else{
			 String message = "You have an account - ";
			 request.setAttribute("message",message);
			 nextURL = "/login.jsp";
			 }
			 //add the user to the session
			 session.setAttribute("user", user);
			 //redirect to next page as indicated by the
			 getServletContext().getRequestDispatcher(nextURL).forward(request,response);
			 }
}
