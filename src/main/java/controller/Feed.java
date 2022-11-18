package controller;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Yspost;
import services.Postdb;

@WebServlet("/Newsfeed")
public class Feed extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 public Feed() {
	 super();
	 }
	 protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,
			 IOException {
		 doPost(request,response);
	 }
	 protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,
			 IOException {
			  long filterByUserID = 0;
			  String searchtext = "";
			  String nextURL = "/error.jsp";
			  HttpSession session = request.getSession();
			  if (session.getAttribute("user")==null){
				  nextURL = "/login.jsp";
				  session.invalidate();
				  response.sendRedirect(request.getContextPath() + nextURL);
				  return;//return prevents an error
			  }
			  List<Yspost> posts = null;
			  if (request.getParameter("userid")!= null && !request.getParameter("userid").isEmpty()){
				  filterByUserID = Integer.parseInt(request.getParameter("userid"));
				  posts = Postdb.postsofUser(filterByUserID);
			  }
			  else if (request.getParameter("searchtext")!= null && !request.getParameter("searchtext").isEmpty()){
				  searchtext = request.getParameter("searchtext").toString();
				  posts = Postdb.searchPosts(searchtext);
			  }
			  else{
				  posts = Postdb.ysPost();
				  }
				  //add posts to request
				  request.setAttribute("posts", posts);
				  //display posts in newsfeed.jsp
				  nextURL = "/newsfeed.jsp";
				  //redirect to next page as indicated by the value of the nextURL variable
				  getServletContext().getRequestDispatcher(nextURL).forward(request,response);
				  }
}
