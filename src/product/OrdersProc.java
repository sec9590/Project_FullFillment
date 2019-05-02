package product;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.MultipartRequest;


/**
 * Servlet implementation class OrdersProc
 */
@WebServlet("/product/OrdersProcServelt")
public class OrdersProc extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public OrdersProc() {
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
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher rd = null;
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		OrdersDAO oDao = null;
		OrdersDTO oDto = null;
		BufferedReader br = null;
		String msg = null;
		String url = null;

		switch (action) {
		case "down":
			String saveDir = "C:\\Temp\\";
			int maxSize = 1024*1024*100;
			String encType = "UTF-8";

			MultipartRequest multipartRequest
			= new MultipartRequest(request, saveDir, maxSize, encType, new DefaultFileRenamePolicy());
			
		    /*System.out.println("이름 : " + multipartRequest.getParameter("name") + "<br>");
		    System.out.println("파일 : " + multipartRequest.getParameter("file") + "<br>"); //null 값을 갖는다.
		    System.out.println("업로드파일명 : " + multipartRequest.getFilesystemName("file") + "<br>");
		    System.out.println("원래파일명 : " + multipartRequest.getOriginalFileName("file") + "<br>");*/
		    
		    File file = multipartRequest.getFile("file");
		    
			//File file    =  new File("C:\\Temp\\shopping.csv");
			 
			br  =  new BufferedReader(new InputStreamReader(new FileInputStream(file),"euc-kr"));

			//br = new BufferedReader(new FileReader("C:\\Temp\\shopping.csv"));

			String line = null;			
			br.readLine();
			oDao = new OrdersDAO();
			oDto = new OrdersDTO();
			int count = 0;
			
			while ((line = br.readLine()) != null) {
				String[] lines = line.split(",");

				String o_name = lines[0];
				String o_tel = lines[1];
				String o_address = lines[2];
				int p_id = Integer.parseInt(lines[3]);
				String p_name = lines[4];
				int o_quantity = Integer.parseInt(lines[5]);
				
				oDto.setO_name(o_name);
				oDto.setO_tel(o_tel);
				oDto.setO_address(o_address);
				oDto.setP_id(p_id);
				oDto.setP_name(p_name);
				oDto.setO_quantity(o_quantity);
				
				oDao.insertOrders(oDto);
				
				System.out.println(oDto.toString());
				count++;
				System.out.println(count);

			}
			
					   	
			msg = "업로드 되었습니다.";
			url = "OrdersProcServlet?action=orderlist&count="+count;
			request.setAttribute("message", msg);
			request.setAttribute("url", url);
			rd = request.getRequestDispatcher("../alertMsg.jsp");
			rd.forward(request, response);
	    	oDao.close();
			
			break;
			
		case "orderlist":
			int num = 0;
			if (!request.getParameter("count").equals("")) {
				num = Integer.parseInt(request.getParameter("count"));
			}
			oDao = new OrdersDAO();
			oDto = new OrdersDTO();
			List<OrdersDTO> orderList = oDao.selectUpload(num);		
			request.setAttribute("OrderList", orderList);	
			rd = request.getRequestDispatcher("order.jsp");
			rd.forward(request, response);
	    	oDao.close();
	    	break;
		}

	}

}
