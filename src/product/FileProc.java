package product;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/FileProcServlet")
public class FileProc extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FileProc() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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
		String cookieId = null;
		
		// 세션이 만료되었으면 다시 로그인하게 만들어 줌
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie: cookies) {
			
			if (cookie.getName().equals("Yellow")) {
				cookieId = cookie.getValue();
				break;
			}
		}
		System.out.println("쿠키" + cookieId);
		request.setAttribute("cookieId", cookieId);
		
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

			sb = cDao.prepareDownload(date);
			client = request.getHeader("User-Agent");
			// 파일 다운로드 헤더 지정
			response.reset();
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Description", "JSP Generated Data");

			if (client.indexOf("MSIE") != -1) { // Internet Explorer
				response.setHeader("Content-Disposition", "attachment; filename=commodity.csv");
			} else { // IE 이외
				response.setHeader("Content-Disposition", "attachment; filename=\"commodity.csv\"");
				response.setHeader("Content-Type", "application/octet-stream; charset=utf-8");
			}

			file = new File("C:\\Temp\\commodity\\commodity.csv");
			response.setHeader("Content-Length", "" + file.length());
			try {
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				bos = new BufferedOutputStream(response.getOutputStream());
				byte[] bytes = new byte[1024];
				while ((length = bis.read(bytes)) != -1) {

					bos.write(bytes, 0, length);
				}
				bos.flush();
				bos.close();
				bis.close();
				fis.close();
			} catch (IllegalStateException e1) {

			} catch (Exception e) {
			}
			break;
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}