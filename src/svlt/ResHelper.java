package svlt;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

public class ResHelper {
	  // Writes a JSONObject to http response.
	  public static void writeJsonObject(HttpServletResponse response, JSONObject obj) {
	    try {
	      response.setContentType("application/json");
	      response.addHeader("Access-Control-Allow-Origin", "*");
	      PrintWriter out = response.getWriter();
	      out.print(obj);
	      out.flush();
	      out.close();
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	  }
}
