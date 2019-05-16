package product;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/FileProcServlet")
public class FileProc extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(FileProc.class);

	public FileProc() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOG.trace("");
		request.setCharacterEncoding("UTF-8");
		FileInputStream fis = null;
		BufferedOutputStream bos = null;
		BufferedInputStream bis = null;
		int length;
		String action = request.getParameter("action");

		String sb = null;
		String client = null;
		File file = null;
		CommodityDAO cDao = new CommodityDAO();

		String msg = null;
		String url = null;
		RequestDispatcher rd = null;
		HttpSession session = request.getSession();

		switch (action) {

		case "down":
			cDao = new CommodityDAO();
			String date = request.getParameter("date");
			cDao.writeCSV(date);
			cDao.readCSV(date);

			msg = date + " 재고정산이 저장되었습니다.";
			url = "CommodityProcServlet?action=commoditydbdetail&date=" + date;
			request.setAttribute("message", msg);
			request.setAttribute("url", url);
			System.out.println("저장완료");
			rd = request.getRequestDispatcher("alertMsg.jsp");
			rd.forward(request, response);

			break;
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
