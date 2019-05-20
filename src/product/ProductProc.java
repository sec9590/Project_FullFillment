package product;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import waybill.NoWaybillDTO;
import waybill.WaybillDAO;
import waybill.WaybillDTO;

import com.oreilly.servlet.MultipartRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet implementation class OrdersProc
 */
@WebServlet("/ProductProcServlet")
public class ProductProc extends HttpServlet {
	private static final Logger LOG = LoggerFactory.getLogger(ProductProc.class);
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public ProductProc() {
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doAction(request, response);
	}

	protected void doAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProductDAO pDao = null;
		ProductDTO pDto = null;
		BufferedReader br = null;
		String msg = null;
		String url = null;
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher rd = null;
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		List<String> pageList = new ArrayList<String>();
		String page = null;
		int pagecount = 0;
		int curPage = 0;
		int pageNo = 0;
		int num = 0;
		SimpleDateFormat mySdf;
		Date today;
		String date = null;
		String date1 = null;
		String date2 = null;

		switch (action) {
		case "procuctlist" :
			if (!request.getParameter("page").equals("")) {
				curPage = Integer.parseInt(request.getParameter("page"));
			}
			pDao = new ProductDAO();
			pagecount = pDao.getCount();
			if (pagecount == 0) // 데이터가 없을 때 대비
				pagecount = 1;
			pageNo = (int) Math.ceil(pagecount / 10.0);
			if (curPage > pageNo) // 경계선에 걸렸을 때 대비
				curPage--;
			session.setAttribute("currentMemberPage", curPage);
			// 리스트 페이지의 하단 페이지 데이터 만들어 주기
			page = null;
			page = "<a href=#>&laquo;</a>&nbsp;";
			pageList.add(page);
			for (int i = 1; i <= pageNo; i++) {
				page = "&nbsp;<a href=ProductProcServlet?action=productlist&page=" + i + ">" + i + "</a>&nbsp;";
				pageList.add(page);
			}
			page = "&nbsp;<a href=#>&raquo;</a>";
			pageList.add(page);
			
			
			
			request.setAttribute("pageList", pageList);
			rd = request.getRequestDispatcher("admin/product/product_list.jsp");
			rd.forward(request, response);
			break;
		}
	}
}
