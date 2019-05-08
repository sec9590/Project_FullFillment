package product;


import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class OrdersProc
 */
@WebServlet("/WaybillProcServlet")
public class WaybillProc extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public WaybillProc() {
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
		WaybillDAO wDao = null;
		WaybillDTO wDto = null;
		List<WaybillDTO> wayList = null;
		String msg = null;
		String url = null;
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher rd = null;
		HttpSession session = request.getSession();
		String action = request.getParameter("action");

		switch (action) {
		case "waybilllist":
			wDao = new WaybillDAO();
			wDto = new WaybillDTO();
			wayList = wDao.selectAll();
			request.setAttribute("wayList", wayList);
			rd = request.getRequestDispatcher("shippinghistory.jsp");			
			rd.forward(request, response);
			break;
			
		case "shipping"	:
			
			String add = request.getParameter("add");
			String add1 = null;
			String add2 = null;
			String add3 = null;
			wDao = new WaybillDAO();
			wDto = new WaybillDTO();
			if(add.length() <= 2) {
				add1 = add.substring(0,2);
				wayList = wDao.selectAdd1(add1);
			} else if(add.length() >= 6) {
				add1 = add.substring(0, 2);
				add2 = add.substring(2, 4);
				add3 = add.substring(4, 6);
				wayList = wDao.selectAdd3(add1, add2, add3);
			} else {
				add1 = add.substring(0, 2);
				add2 = add.substring(2, 3);
				wayList = wDao.selectAdd2(add1, add2);
			}
			
			System.out.println("add= " + add);
			request.setAttribute("wayList", wayList);
			rd = request.getRequestDispatcher("shipping.jsp");			
			rd.forward(request, response);
			break;
		}
	}

}
