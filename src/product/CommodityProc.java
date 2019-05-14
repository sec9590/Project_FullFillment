package product;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import waybill.NoWaybillDTO;
import waybill.WaybillDAO;
import waybill.WaybillDTO;

/**
 * Servlet implementation class CommodityProc
 */
@WebServlet("/CommodityProcServlet")
public class CommodityProc extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CommodityProc() {
		super();
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
		OrdersDAO oDao = null;
		OrdersDTO oDto = null;
		DetailOrderDTO doDto = null;
		ProductDAO pDao = null;
		ProductDTO pDto = null;
		BuyingDTO bDto = null;
		WaybillDTO wDto = null;
		WaybillDAO wDao = null;
		NoWaybillDTO nwDto = null;
		CommodityDAO cDao = null;
		CommodityDTO cDto = null;

		BufferedReader br = null;
		String msg = null;
		String url = null;
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher rd = null;
		HttpSession session = request.getSession();
		String action = request.getParameter("action");

		List<DetailOrderDTO> doDtoList = null;
		List<BuyingDTO> bDtoList = null;
		List<CommodityDTO> cDtoList = null;

		int p_id = 0;
		int out = 0;
		int in = 0;
		int basic = 0;
		int close = 0;
		String date = null;
		String date1 = null;
		String date2 = null;

		switch (action) {
		// 이번달 재고정산
		case "commodity":
			cDao = new CommodityDAO();

			cDtoList = cDao.selectcommodityOut(); // 출고, 기초재고, 상품id

			for (CommodityDTO coDto : cDtoList) {
				p_id = coDto.getP_id();
				coDto.setC_in(cDao.selectcommodityIn(p_id));
				close = coDto.getC_basic() + coDto.getC_in() - coDto.getC_out();
				coDto.setC_close(close);
			}

			request.setAttribute("commodityList", cDtoList);
			rd = request.getRequestDispatcher("admin/commodity/commodity_now.jsp");
			rd.forward(request, response);
			break;

		// 월별 재고정산
		case "selectCommodity":
			date = request.getParameter("dateInventory");
			System.out.println(date);
			date1 = date + "-01 00:00";
			System.out.println(date1);
			date2 = date + "-31 23:59";
			System.out.println(date2);

			cDao = new CommodityDAO();

			cDtoList = cDao.selectcommodityOutTime(date1, date2); // 출고, 기초재고, 상품id
			for (CommodityDTO coDto : cDtoList) {
				p_id = coDto.getP_id();
				if(!date.equals("2019-04")) {
					basic = cDao.checkClose(p_id, date1, date2);
					System.out.println(basic);
					coDto.setC_basic(basic);
				}
				coDto.setC_in(cDao.selectcommodityInTime(date1, date2, p_id));
				close = coDto.getC_basic() + coDto.getC_in() - coDto.getC_out();
				coDto.setC_close(close);
			}
			request.setAttribute("dateInventory", date);
			request.setAttribute("commodityList", cDtoList);
			rd = request.getRequestDispatcher("admin/commodity/commodity_detail.jsp");
			rd.forward(request, response);
			break;

		case "commodityDB":
			date = request.getParameter("date");
			System.out.println("재고정산 날짜 : " + date);
			
			cDao = new CommodityDAO();
			
			date1 = date + "-01 00:00";
			System.out.println(date1);
			date2 = date + "-31 23:59";
			System.out.println(date2);
			boolean finish = false;

			cDtoList = cDao.selectcommodityOutTime(date1, date2); // 출고, 기초재고, 상품id

			if (!cDao.checkInsert(date)) {
				finish = false;
			} else {
				for (CommodityDTO coDto : cDtoList) {
					finish = true;
					p_id = coDto.getP_id();
					coDto.setC_in(cDao.selectcommodityInTime(date1, date2, p_id));
					close = coDto.getC_basic() + coDto.getC_in() - coDto.getC_out();
					coDto.setC_close(close);
					coDto.setC_time(date);
					cDao.insertCommodity(coDto);
					System.out.println("재고db" + coDto.toString());
				}
			}

			if (finish) {
				msg = date + " 재고정산을 처리하였습니다.";
				url = "CommodityProcServlet?action=commodity";
				request.setAttribute("message", msg);
				request.setAttribute("url", url);
				rd = request.getRequestDispatcher("alertMsg.jsp");
				rd.forward(request, response);

			} else {
				msg = "재고정산을 이미 처리했습니다.";
				url = "CommodityProcServlet?action=commodity";
				request.setAttribute("message", msg);
				request.setAttribute("url", url);
				rd = request.getRequestDispatcher("alertMsg.jsp");
				rd.forward(request, response);
			}
			break;
		}
	}

}
