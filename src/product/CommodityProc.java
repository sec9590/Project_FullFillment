package product;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet implementation class CommodityProc
 */
@WebServlet("/CommodityProcServlet")
public class CommodityProc extends HttpServlet {
	private static final Logger LOG = LoggerFactory.getLogger(CommodityProc.class);
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
		List<ProductDTO> pDtoList = null;

		int p_id = 0;
		int out = 0;
		int in = 0;
		int basic = 0;
		int close = 0;
		String date = null;
		String date1 = null;
		String date2 = null;

		String lastmonth = null;
		String lastdate1 = null;
		String lastdate2 = null;

		switch (action) {
		// 이번달 재고정산
		case "commodity":
			cDao = new CommodityDAO();

			cDtoList = cDao.selectcommodityOut(); // 출고, 기초재고, 상품id

			for (CommodityDTO coDto : cDtoList) {
				p_id = coDto.getP_id();
				// 이번달
				DateFormat df1 = new SimpleDateFormat("yyyy-MM");
				Calendar cal1 = Calendar.getInstance();
				date = df1.format(cal1.getTime());
				LOG.info("이번달은? : "+ df1.format(cal1.getTime()));
				
				LOG.info("재고처리 유무 : " + cDao.checkNow(date));
				if(cDao.checkNow(date) == null)
					request.setAttribute("dbinsert", null);
				else
					request.setAttribute("dbinsert", 1);

				lastmonth = cDao.lastMonth(date);
				lastdate1 = lastmonth + "-01 00:00";
				lastdate2 = lastmonth + "-31 23:59";

				basic = cDao.checkClose(p_id, date);
				if (basic == 0) {
					basic = cDao.selectFinal(lastdate1, lastdate2, p_id);
					if (basic == 0) {
						basic = cDao.checkClose(p_id, lastmonth);
					}
				}
				System.out.println(basic);
				coDto.setC_basic(basic);
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
			cDao = new CommodityDAO();
			date = request.getParameter("dateInventory");
			LOG.info("월별 : " + date);
			date1 = date + "-01 00:00";
			LOG.info(date1);
			date2 = date + "-31 23:59";
			LOG.info(date2);

			lastmonth = cDao.lastMonth(date);
			lastdate1 = lastmonth + "-01 00:00";
			LOG.info("지난달 : " + lastmonth);
		
			cDtoList = cDao.selectcommodityOutTime(date1, date2); // 출고, 기초재고, 상품id
			for (CommodityDTO coDto : cDtoList) {
				p_id = coDto.getP_id();
				if (!date.equals("2019-03")) {
					basic = cDao.checkClose(p_id, date);
					coDto.setC_basic(basic);
				}
				coDto.setC_in(cDao.selectcommodityInTime(date1, date2, p_id));
				close = coDto.getC_basic() + coDto.getC_in() - coDto.getC_out();
				coDto.setC_close(close);
			}
			System.out.println("전달 채고처리 : " + cDao.checkNow(lastmonth) );
			if(!lastmonth.equals("2019-02")) {
			if(cDao.checkNow(lastmonth) == null)
				request.setAttribute("last", null);
			else
				request.setAttribute("last", 1);
			} else 
				request.setAttribute("last", 2);
			request.setAttribute("dateInventory", date);
			request.setAttribute("commodityList", cDtoList);
			rd = request.getRequestDispatcher("admin/commodity/commodity_detail.jsp");
			rd.forward(request, response);
			break;

		case "commodityDB":
			date = request.getParameter("date");
			LOG.info("재고정산 날짜 : " + date);

			cDao = new CommodityDAO();

			date1 = date + "-01 00:00";
			LOG.info(date1);
			date2 = date + "-31 23:59";
			LOG.info(date2);
			boolean finish = false;
			boolean ok = false;

			cDtoList = cDao.selectcommodityOutTime(date1, date2); // 출고, 기초재고, 상품id
			List<CommodityDTO> cProduct = cDao.selectcommodityProduct(date1, date2);
			pDtoList = cDao.IsProduct();

			if (!cDao.checkInsert(date)) {
				finish = false;
			} else {
				finish = true;
				for (ProductDTO pd : pDtoList) {
					for (CommodityDTO coDto : cDtoList) {
						if (pd.getP_id() == coDto.getP_id()) {
							ok = true;
							p_id = coDto.getP_id();
							coDto.setP_id(p_id);
							coDto.setP_name(cDao.productname(p_id));
							basic = cDao.checkClose(p_id, date);
							if (basic == 0)
								basic = 15;
							coDto.setC_basic(basic);
							coDto.setC_in(cDao.selectcommodityInTime(date1, date2, p_id));
							close = coDto.getC_basic() + coDto.getC_in() - coDto.getC_out();
							coDto.setC_close(close);
							coDto.setC_time(date);
							cDao.insertCommodity(coDto);
							LOG.info("재고db : " + coDto.toString());
							break;
						} else
							ok = false;
					}

					if (!ok) {
						p_id = pd.getP_id();
						CommodityDTO ncDto = new CommodityDTO();
						basic = cDao.checkClose(p_id, date);
						if (basic == 0)
							basic = 15;
						ncDto.setC_basic(basic);
						ncDto.setP_id(p_id);
						ncDto.setP_name(cDao.productname(p_id));
						if (cDao.selectcommodityInTime(date1, date2, p_id) == 0) {
							ncDto.setC_in(0);
						} else {
							ncDto.setC_in(cDao.selectcommodityInTime(date1, date2, p_id));
						}
						ncDto.setC_time(date);
						close = ncDto.getC_basic() + ncDto.getC_in();
						ncDto.setC_close(close);
						cDao.insertCommodity(ncDto);
						LOG.info("없는재고db : " + ncDto.toString());
					}
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

		case "commoditydbselect":
			cDao = new CommodityDAO();
			cDtoList = cDao.commodityAll();
			request.setAttribute("cDtoList", cDtoList);
			rd = request.getRequestDispatcher("admin/commodity/commoditydb.jsp");
			rd.forward(request, response);			
			break;
			
		case "commoditydbdetail":
			date = request.getParameter("date");
			
			cDao = new CommodityDAO();
			cDtoList = cDao.selectCommodityDetail(date);
			request.setAttribute("cDtoList", cDtoList);
			request.setAttribute("date", date);
			rd = request.getRequestDispatcher("admin/commodity/commoditydb_detail.jsp");
			rd.forward(request, response);		
			
			break;
		}
	}

}
