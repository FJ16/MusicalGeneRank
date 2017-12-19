package svlt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import algorithm.MusicalRank;

/**
 * Servlet implementation class queryScore
 */
@WebServlet("/score")
public class QueryScore extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryScore() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Create a PrintWriter from response such that we can add data to response.
		// ResHelper helps to encapsulate the write JASON object function
		
		// Get parameter from HTTP request
		String userID = request.getParameter("user_id");
		
		MusicalRank res = new MusicalRank();
		String score = res.calculateScore(userID);
		
		JSONObject obj = new JSONObject();
		try {
			obj.put(userID, score);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// write the JSONObject
		ResHelper.writeJsonObject(response, obj);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
