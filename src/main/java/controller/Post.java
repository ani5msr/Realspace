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

import model.Yspost;
import model.Ysuser;
import services.Postdb;
@WebServlet("/Post")
public class Post extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public Post() {
	 super();
	}
	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
			 String posttext = request.getParameter("posttext");
			 String nextURL = "/error.jsp";
			 HttpSession session = request.getSession();
			 if (session.getAttribute("user") == null){
				 nextURL = "/login.jsp";
				 session.invalidate();
			 } else {
				 Ysuser ysuser = (Ysuser)session.getAttribute("user");
				 Yspost ysPost = new Yspost();
				 ysPost.setYsuser(ysuser);
				 Date postdate = Calendar.getInstance().getTime();//today's date
				 ysPost.setPostdate(postdate);
				 ysPost.setPosttext(posttext);
				 Postdb.insert(ysPost);
				 nextURL = "/Newsfeed";
			 }
			 getServletContext().getRequestDispatcher(nextURL).forward(request, response);
	}

}
