package product;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/ProductProcServlet")
public class ProductProc extends HttpServlet {
	private static final Logger LOG = LoggerFactory.getLogger(OrdersProc.class);
	private static final long serialVersionUID = 1L;
       
    public ProductProc() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}

	protected void doAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher rd = null;
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		ProductDAO pDao = null;
		ProductDTO pDto = null;
		String msg = null;
		String url = null;
		List<String> pageList = new ArrayList<String>();
		String page = null;
		int pagecount = 0;
		int curPage = 0;
		int pageNo = 0;
		int num = 0;
		String cookieId = null;		
		BufferedReader br = null;
		
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie: cookies) {
			LOG.trace("{}, {}", cookie.getName(), cookie.getValue());
			if (cookie.getName().equals("Yellow")) {
				cookieId = cookie.getValue();
				break;
			}
		}
		
		request.setAttribute("cookieId", cookieId);
		
		switch(action) {
		case "product":
			rd = request.getRequestDispatcher("product_list.jsp");
			rd.forward(request, response);			
			break;
		case "product_list":
			pDao = new ProductDAO();
			if (!request.getParameter("page").equals("")) {
				curPage = Integer.parseInt(request.getParameter("page"));
			}
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
				page = "&nbsp;<a href=ProductProcServlet?action=product_list&page=" + i + ">" + i + "</a>&nbsp;";
				pageList.add(page);
			}
			page = "&nbsp;<a href=#>&raquo;</a>";
			pageList.add(page);
			List<ProductDTO> productList = pDao.productAll(curPage);
			request.setAttribute("pageList", pageList);
			request.setAttribute("ProductList", productList);
			rd = request.getRequestDispatcher("admin/product/product_list.jsp");
			rd.forward(request, response);
			break;
			
		case "insert" :
			rd = request.getRequestDispatcher("admin/product/product_insert.jsp");
			rd.forward(request, response);
			break;
			
		case "insert_product":
			pDao = new ProductDAO();
			pDto = new ProductDTO();
			
			String saveDir = "D:\\workspace\\EGov\\project02\\WebContent\\img\\bg-img\\";
			int maxSize = 1024 * 1024 * 100;
			String encType = "UTF-8";

			MultipartRequest multipartRequest = new MultipartRequest(request, saveDir, maxSize, encType, new DefaultFileRenamePolicy());


			File file = multipartRequest.getFile("file");

			br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "euc-kr"));
			LOG.info("원래파일명 : " + multipartRequest.getOriginalFileName("file"));
			String filename = multipartRequest.getOriginalFileName("file");
			
			String p_img = "img/bg-img/" + filename;
			System.out.println(p_img);
			String p_name = request.getParameter("p_name");
			String p_price = request.getParameter("p_price");
			String buycode = request.getParameter("buycode");
			
			pDto = new ProductDTO(p_name, p_img, p_price, 15, buycode);
			pDao.insertProduct(pDto);
			

			msg = "상품등록이 완료되었습니다.";
			url = "ProductProcServlet?action=product_list&page=1";
			
			request.setAttribute("message", msg);
			request.setAttribute("url", url);

			rd = request.getRequestDispatcher("alertMsg.jsp");
			rd.forward(request, response);
			pDao.close();
			break;
		}
	}
}
