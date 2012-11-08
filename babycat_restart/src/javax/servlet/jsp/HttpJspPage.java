package javax.servlet.jsp;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface HttpJspPage {

	void _jspService(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
