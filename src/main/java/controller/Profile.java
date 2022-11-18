package controller;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import model.Ysuser;
import services.Userdb;
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public Profile() {
	 super();
	 }
	protected void doGet(HttpServletRequest request,
			 HttpServletResponse response) throws ServletException,
			 IOException {
			  doPost(request,response);
			  }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
				  HttpSession session = request.getSession();
				  String nextURL = "/error.jsp";
				  long userid = 0;
				  String action = "";
				  Ysuser profileUser = null;
				  Ysuser loggedInUser = null;
				  if (session.getAttribute("user")==null){
					  nextURL = "/login.jsp";
					  session.invalidate();
					  response.sendRedirect(request.
					 getContextPath() + nextURL);
					  return;//return prevents an error
				  }
				  try{
					  userid = Long.parseLong(request.getParameter("userid"));
					  action = request.getParameter("action");
					  //update profile for user in request variable if action = updateprofile
					  if (request.getParameter("action").equals("updateprofile")){
						  long uid = Long.parseLong(request.getParameter("userid"));
						  String userEmail = request.getParameter("useremail");
						  String userMotto = request.getParameter("usermotto");
						  Ysuser updateUser = Userdb.getUser(uid);
						  updateUser.setMotto(userMotto);
						  updateUser.setUseremail(userEmail);
						  Userdb.update(updateUser);
					  }
				//get the user from the parameter
				  profileUser = Userdb.getUser(userid);
				  //get the current user out of the session
				  loggedInUser = (Ysuser)session.getAttribute("user");
				  if (profileUser.getYsuserid()==loggedInUser.getYsuserid()){
				  //display profile as form
				  //the session variable editProfile is used by the JSP to
				  //display the profile in edit mode
				  session.setAttribute("editProfile",true);
				  }else{
				  //display the profile in read-only mode
				  session.setAttribute("editProfile",false);
				  }
				//populate the data in the attributes
				  int imgSize = 120;
				  SimpleDateFormat sdf = new SimpleDateFormat("MMM d, yyyy");
				  String joindate = sdf.format(profileUser.getJoindate());
				  request.setAttribute("userid", profileUser.getYsuserid());
				  request.setAttribute("userimage",Userdb.getGravatarURL(profileUser.getUseremail(),imgSize));
				  request.setAttribute("username", profileUser.getUsername());
				  request.setAttribute("useremail", profileUser.getUseremail());
				  request.setAttribute("usermotto", profileUser.getMotto());
				  request.setAttribute("userjoindate", joindate);
				  nextURL = "/profile.jsp";
			  }
				  catch(Exception e){
					  System.out.println(e);
					  }
				  getServletContext().getRequestDispatcher(nextURL).forward(request,response);
	}
	
}
