package com.assignment;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.assignment.model.TaskModel;
import com.assignment.model.User;
import com.assignment.utils.Common;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

/**
 * Servlet implementation class RootServlet
 */
public class RootServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RootServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("In Get");
        String thisURL = request.getRequestURI();
        System.out.println(thisURL);
        if (request.getUserPrincipal() != null) {
        	
        	Key k = Common.getUserKey(Common.getUser());
        	hasUserLoggedIn(k);
        	
        	response.sendRedirect("/root.jsp");
        }else{
        	getServletContext().getRequestDispatcher("/root.jsp").forward(request,response);
        }

	}
	
	static void hasUserLoggedIn(final Key k){
		PersistenceManager pm = JDOHelper.getPersistenceManagerFactory("transactions-optional").getPersistenceManager();
		
		Query q = pm.newQuery("select id from " + User.class.getName());
		List<Key> ids = (List<Key>) q.execute();
		
		System.out.println("Size : " +ids.size());
		
		if(!ids.contains(k)){
			User user = new User(Common.getUserKey(Common.getUser()));
			pm.makePersistent(user);
		}else{
			for(Key id : ids)
				System.out.println(id.getKind());
		}
		pm.close();
	}
}
