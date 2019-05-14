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

		switch (action) {
		// 파일 다운하고 주문하기
		case "commodity":
			cDao = new CommodityDAO();
			List<CommodityDTO> list = new ArrayList<CommodityDTO>();
			bDtoList = cDao.selectcommodityIn(); // 입고, 상품id
			doDtoList = cDao.selectcommodityOut(); // 출고, 기초재고, 상품id

			
			for (DetailOrderDTO dDto : doDtoList) {
				p_id = dDto.getP_id();
				out = dDto.getO_quantity();
				basic = dDto.getP_count(); // 기초재고

				cDto.setP_id(p_id);
				cDto.setOut(out);
				cDto.setBasic(basic);
			}

			for (BuyingDTO buyDto : bDtoList) {
				in = buyDto.getP_quantity();
				cDto.setIn(in);
			}
			
			close = basic + in - out;
			cDto.setClose(close);
			list.add(cDto);

			request.setAttribute("commodityList", list);
			System.out.println(list.toString());
			rd = request.getRequestDispatcher("admin/commodity/commodity_detail.jsp");
			rd.forward(request, response);
			break;
		}
	}

}
