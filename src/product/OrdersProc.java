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

/**
 * Servlet implementation class OrdersProc
 */
@WebServlet("/OrdersProcServlet")
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
		OrdersDAO oDao = null;
		OrdersDTO oDto = null;
		DetailOrderDTO doDto = null;
		ProductDAO pDao = null;
		ProductDTO pDto = null;
		BuyingDTO bDto = null;
		WaybillDTO wDto = null;
		WaybillDAO wDao = null;
		NoWaybillDTO nwDto = null;

		BufferedReader br = null;
		String msg = null;
		String url = null;
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher rd = null;
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		List<ProductDTO> buyingList = null;
		List<String> pageList = new ArrayList<String>();
		List<OrdersDTO> orderAll = null;
		String page = null;
		int pagecount = 0;
		int curPage = 0;
		int pageNo = 0;
		int num = 0;
		boolean update = false;
		String field = null;
		String day = null;
		SimpleDateFormat mySdf;
		Date today;

		switch (action) {
		// 파일 다운하고 주문하기
		case "down":
			String saveDir = "C:\\Temp\\Invoices\\Backup\\";
			int maxSize = 1024 * 1024 * 100;
			String encType = "UTF-8";

			MultipartRequest multipartRequest = new MultipartRequest(request, saveDir, maxSize, encType,
					new DefaultFileRenamePolicy());

			/*
			 * System.out.println("이름 : " + multipartRequest.getParameter("name") + "<br>");
			 * System.out.println("파일 : " + multipartRequest.getParameter("file") + "<br>");
			 * //null 값을 갖는다. System.out.println("업로드파일명 : " +
			 * multipartRequest.getFilesystemName("file") + "<br>");
			 * System.out.println("원래파일명 : " + multipartRequest.getOriginalFileName("file")
			 * + "<br>");
			 */

			File file = multipartRequest.getFile("file");

			// File file = new File("C:\\Temp\\shopping.csv");

			br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "euc-kr"));

			String filename = multipartRequest.getFilesystemName("file");
			System.out.println("업로드파일명 : " + filename);
			filename = filename.replaceAll(filename.substring(filename.length() - 4, filename.length()), "");

			String line = null;
			br.readLine();
			oDao = new OrdersDAO();
			oDto = new OrdersDTO();
			doDto = new DetailOrderDTO();
			wDto = new WaybillDTO();
			int count = 0;

			String o_name = null;
			String o_tel = null;
			String o_address = null;
			int p_id = 0;
			String p_name = null;
			int o_quantity = 0;
			int temp = 0;

			while ((line = br.readLine()) != null) {
				String[] lines = line.split(",");

				o_name = lines[0];
				o_tel = lines[1];
				o_address = lines[2];

				if (lines[0].equals("")) {
					p_id = Integer.parseInt(lines[3]);
					p_name = lines[4];
					o_quantity = Integer.parseInt(lines[5]);

					doDto.setO_id(temp);
					doDto.setP_id(p_id);
					doDto.setP_name(p_name);
					doDto.setO_quantity(o_quantity);

					oDao.insertDetailOrders(doDto);
				} else {
					oDto.setO_name(o_name);
					oDto.setO_tel(o_tel);
					oDto.setO_address(o_address);
					oDto.setShopcode(filename);
					oDao.insertOrders(oDto);
					int orderid = oDao.selectOrderId(o_name);
					System.out.println("주문번호 : " + orderid);

					p_id = Integer.parseInt(lines[3]);
					p_name = lines[4];
					o_quantity = Integer.parseInt(lines[5]);
					temp = orderid;
					doDto.setO_id(orderid);
					doDto.setP_id(p_id);
					doDto.setP_name(p_name);
					doDto.setO_quantity(o_quantity);

					oDao.insertDetailOrders(doDto);
					count++;
				}
				System.out.println(oDto.toString());
				System.out.println(doDto.toString());
				System.out.println(count);

			}

			msg = "주문 성공 및 발주신청";
			url = "OrdersProcServlet?action=orderlist&count=" + count;
			System.out.println("count : " + count);
			request.setAttribute("count", count);
			request.setAttribute("message", msg);
			request.setAttribute("url", url);

			System.out.println(count);
			rd = request.getRequestDispatcher("alertMsg.jsp");
			rd.forward(request, response);
			oDao.close();

			break;

		// 운송신청
		case "ship":
			oDao = new OrdersDAO();
			oDto = new OrdersDTO();
			nwDto = new NoWaybillDTO();
			update = false;

			System.out.println(request.getParameter("count"));
			if (!request.getParameter("count").equals("")) {
				num = Integer.parseInt(request.getParameter("count"));
			}

			orderAll = oDao.selectUpload(num);

			for (OrdersDTO order : orderAll) {
				List<DetailOrderDTO> doDtoList = oDao.selectDetailOrder(order.getO_id());

				for (DetailOrderDTO dDto : doDtoList) {
					if (oDao.checkQuantity(dDto)) {
						update = true;
					} else {
						update = false;
						break;
					}
				}

				if (update) {
					for (DetailOrderDTO dDto : doDtoList) {
						oDao.updateQuantity(dDto);
					}
					oDao.insertWaybill(order.getO_id());
					oDao.updateWaybillTime(order);
				} else {
					oDao.insertNoWaybill(order.getO_id());
				}

			}

			msg = "운송처리되었습니다.";
			url = "order.jsp";
			request.setAttribute("message", msg);
			request.setAttribute("url", url);

			rd = request.getRequestDispatcher("alertMsg.jsp");
			rd.forward(request, response);
			oDao.close();

			break;

		case "nowaybill":
			wDao = new WaybillDAO();
			oDao = new OrdersDAO();
			update = false;

			List<NoWaybillDTO> noWaybillAll = wDao.selectNoWaybillAll();

			for (NoWaybillDTO nDto : noWaybillAll) {
				List<DetailOrderDTO> doDtoList = oDao.selectDetailOrder(nDto.getO_id());

				for (DetailOrderDTO dDto : doDtoList) {
					if (oDao.checkQuantity(dDto)) {
						update = true;
					} else {
						update = false;
						break;
					}
				}

				if (update) {
					for (DetailOrderDTO dDto : doDtoList) {
						oDao.updateQuantity(dDto);
					}
					oDao.insertWaybill(nDto.getO_id());
					oDao.updateWaybillTime(nDto);
					wDao.deleteNoWaybill(nDto);
					msg = "운송처리되었습니다.";
				} else {
					msg = "재고가 부족한 항목이 있습니다.";
				}
			}

			url = "WaybillProcServlet?action=nowaybilllist&page=1";
			request.setAttribute("message", msg);
			request.setAttribute("url", url);

			rd = request.getRequestDispatcher("alertMsg.jsp");
			rd.forward(request, response);
			oDao.close();
			break;

		// csv 속 주문내역 및 발주DB저장
		case "orderlist":
			oDao = new OrdersDAO();
			oDto = new OrdersDTO();
			pDao = new ProductDAO();

			System.out.println(request.getParameter("count"));
			if (!request.getParameter("count").equals("")) {
				num = Integer.parseInt(request.getParameter("count"));
			}

			List<OrdersDTO> orderList = oDao.selectUpload(num);

			/*
			 * for (OrdersDTO order : orderList) { List<DetailOrderDTO> doDtoList =
			 * oDao.selectDetailOrder(order.getO_id());
			 * 
			 * for (DetailOrderDTO dDto : doDtoList) { if (oDao.checkBuying((dDto))) {
			 * //10개미만으로 떨어지면 if(!pDao.isBuying(dDto.getP_id()))
			 * pDao.insertBuying(dDto.getP_id()); } } }
			 */

			System.out.println("주문내역검색 완료");
			request.setAttribute("OrderList", orderList);
			request.setAttribute("count", num);
			System.out.println(num);
			rd = request.getRequestDispatcher("order.jsp");
			rd.forward(request, response);
			break;

		// 주문 상세내역
		case "detail":
			int id = 0;
			if (!request.getParameter("id").equals("")) {
				id = Integer.parseInt(request.getParameter("id"));
			}
			String name = request.getParameter("name");
			oDao = new OrdersDAO();
			List<DetailOrderDTO> detailorderList = oDao.selectDetailOrder(id);
			System.out.println(detailorderList);
			request.setAttribute("id", id);
			request.setAttribute("name", name);
			request.setAttribute("detailorderList", detailorderList);
			rd = request.getRequestDispatcher("detail.jsp");
			rd.forward(request, response);
			break;

		// 재고목록
		case "productlist":
			pDao = new ProductDAO();
			pDto = new ProductDTO();
			List<ProductDTO> productList = pDao.selectAll();
			request.setAttribute("ProductList", productList);
			rd = request.getRequestDispatcher("commodity.jsp");
			rd.forward(request, response);
			break;

		// 총 주문내역
		case "orderAll":
			if (!request.getParameter("page").equals("")) {
				curPage = Integer.parseInt(request.getParameter("page"));
			}
			oDao = new OrdersDAO();
			pagecount = oDao.getCount();
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
				page = "&nbsp;<a href=OrdersProcServlet?action=orderAll&page=" + i + ">" + i + "</a>&nbsp;";
				pageList.add(page);
			}
			page = "&nbsp;<a href=#>&raquo;</a>";
			pageList.add(page);

			orderAll = oDao.selectOrderAll(curPage);
			request.setAttribute("orderAllList", orderAll);
			request.setAttribute("pageList", pageList);
			rd = request.getRequestDispatcher("sales.jsp");
			rd.forward(request, response);
			break;
<<<<<<< Updated upstream
		
=======

		// 주문내역 기간별
		case "timehistory":
			String time = request.getParameter("time");
			if (!request.getParameter("page").equals("")) {
				curPage = Integer.parseInt(request.getParameter("page"));
			}
			oDao = new OrdersDAO();
			pagecount = oDao.getCount();
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
				page = "&nbsp;<a href=OrdersProcServlet?action=timehistory&time=" + time + "&page=" + i + ">" + i
						+ "</a>&nbsp;";
				pageList.add(page);
			}
			page = "&nbsp;<a href=#>&raquo;</a>";
			pageList.add(page);

			switch (time) {
			case "today":
				orderAll = oDao.selectToDay(curPage);
				request.setAttribute("orderAllList", orderAll);
				request.setAttribute("pageList", pageList);
				rd = request.getRequestDispatcher("salestoday.jsp");
				rd.forward(request, response);
				break;
			case "day":
				orderAll = oDao.selectDay(curPage);
				request.setAttribute("orderAllList", orderAll);
				request.setAttribute("pageList", pageList);
				rd = request.getRequestDispatcher("salesday.jsp");
				rd.forward(request, response);
				break;
			case "week":
				orderAll = oDao.selectWeek(curPage);
				request.setAttribute("orderAllList", orderAll);
				request.setAttribute("pageList", pageList);
				rd = request.getRequestDispatcher("salesweek.jsp");
				rd.forward(request, response);
				break;
			case "month":
				orderAll = oDao.selectMonth(curPage);
				request.setAttribute("orderAllList", orderAll);
				request.setAttribute("pageList", pageList);
				rd = request.getRequestDispatcher("salesmonth.jsp");
				rd.forward(request, response);
				break;
			case "year":
				orderAll = oDao.selectYear(curPage);
				request.setAttribute("orderAllList", orderAll);
				request.setAttribute("pageList", pageList);
				rd = request.getRequestDispatcher("salesyear.jsp");
				rd.forward(request, response);
				break;
			}
			break;
>>>>>>> Stashed changes

		// 발주내역
		case "orderhistory":
			String code = request.getParameter("code");
			pDao = new ProductDAO();
			pDto = new ProductDTO();
			List<BuyingDTO> orderhistoryList = pDao.selectBuyingAll(code);
			request.setAttribute("orderhistoryList", orderhistoryList);
			rd = request.getRequestDispatcher("orderhistory.jsp");
			rd.forward(request, response);
			break;

		// 발주내역(전체)
		case "orderhistoryall":
			pDao = new ProductDAO();
			pDto = new ProductDTO();
			List<BuyingDTO> orderhistoryall = pDao.selectOrderhistoryAll();
			request.setAttribute("orderhistoryList", orderhistoryall);
			rd = request.getRequestDispatcher("orderhistoryall.jsp");
			rd.forward(request, response);
			break;

		// 구매처에따른 발주 신청내역
		case "buyinglist":
			field = request.getParameter("field");
			pDao = new ProductDAO();
			bDto = new BuyingDTO();
			buyingList = pDao.selectBuying(field);
			for (ProductDTO product : buyingList) {
				int quantity = 15 - product.getP_quantity();
				product.setP_quantity(quantity);
			}
			System.out.println(buyingList);
			day = pDao.yesterday();
			request.setAttribute("yesterday", day);

			if (buyingList.size() == 0) {
				buyingList = null;
				request.setAttribute("buyingList", buyingList);
				System.out.println(buyingList);
			} else
				request.setAttribute("buyingList", buyingList);

			rd = request.getRequestDispatcher("buying.jsp");
			rd.forward(request, response);
			break;

		// 구매처에따른 발주 신청 전체 내역
		case "buyingall":
			field = request.getParameter("field");
			pDao = new ProductDAO();
			pDto = new ProductDTO();
			List<BuyingDTO> buyingall = pDao.selectBuyingAll(field);
			request.setAttribute("buyingall", buyingall);
			rd = request.getRequestDispatcher("buyingall.jsp");
			rd.forward(request, response);
			break;

		// 발주처리
		case "buying":
			field = request.getParameter("field");

			pDao = new ProductDAO();
			bDto = new BuyingDTO();
			buyingList = pDao.selectBuying(field);

			for (ProductDTO product : buyingList) {
				pDao.insertBuying(product.getP_id());
				pDao.updatep_Quantity(product);
			}

			buyingList = pDao.selectBuying(field);

			msg = "발주처리되었습니다.";
			url = "OrdersProcServlet?action=buyinglist&field=" + field;
			request.setAttribute("message", msg);
			request.setAttribute("url", url);

			rd = request.getRequestDispatcher("alertMsg.jsp");
			rd.forward(request, response);

			pDao.close();
			break;

		// 기간설정 달력
<<<<<<< Updated upstream
		case "selecttime":		// 일단위 상품별 주문 내역
			if (!request.getParameter("page").equals("")) {
				curPage = Integer.parseInt(request.getParameter("page"));
			}
			oDao = new OrdersDAO();
			pagecount = oDao.getCount();
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
				page = "&nbsp;<a href=OrdersProcServlet?action=selecttime&page=" + i + ">" + i
						+ "</a>&nbsp;";
				pageList.add(page);
			}
			page = "&nbsp;<a href=#>&raquo;</a>";
			pageList.add(page);
=======
		case "selecttime": // 일단위 상품별 주문 내역
>>>>>>> Stashed changes
			oDao = new OrdersDAO();
			String date = request.getParameter("dateInventory");
			date = oDao.selecttimechangeString(oDao.selectTime(date));
			System.out.println(date);
			String date1 = date + " 00:00";
			System.out.println(date1);
			String date2 = date + " 23:59";

			date1 = oDao.timechangeString(oDao.compareTime(date1));

			date2 = oDao.timechangeString(oDao.compareTime(date2));
<<<<<<< Updated upstream
		
=======

>>>>>>> Stashed changes
			orderAll = oDao.selectTime(date1, date2);
			System.out.println("기간설정 달력");
			request.setAttribute("dateInventory", date);
			request.setAttribute("orderAllList", orderAll);
			request.setAttribute("pageList", pageList);
			rd = request.getRequestDispatcher("selecttime.jsp");
			rd.forward(request, response);
			break;

		case "shopprofit":
			oDao = new OrdersDAO();
			
			List<OrdersDTO> shopList = oDao.selectShop();
			
			for (OrdersDTO shop : shopList) {
				int sum = 0;
				int shippay = 0;
				System.out.println("이거" + shop.getO_time());
				List<DetailOrderDTO> shopList_total = oDao.selectShopDetail(shop.getO_time());
				
				for (DetailOrderDTO dDto : shopList_total) {
					sum += (int) (dDto.getP_total() + (dDto.getP_total() * 0.1));					
				}
				shop.setTotal(sum);
				System.out.println(sum);
				shippay = oDao.getorderCount(shop.getO_time()) * 10000;
				System.out.println("운송비" + shippay);
				shop.setShippay(shippay);
			}
			
			request.setAttribute("shopList", shopList);
			rd = request.getRequestDispatcher("grossprofit_shop.jsp");
			rd.forward(request, response);
			break;

		case "shopprofit_detail":
			oDao = new OrdersDAO();
			String o_time = request.getParameter("o_time");
			String shopcode = request.getParameter("shopcode");
			List<DetailOrderDTO> shopList_detail = oDao.selectShopDetail(o_time);
			for(DetailOrderDTO dDto : shopList_detail) {
				int total = (int) (dDto.getP_total() + (dDto.getP_total() * 0.1));
				dDto.setTotal(total);				
			}
			request.setAttribute("shopcode", shopcode);
			request.setAttribute("shopList_detail", shopList_detail);
			rd = request.getRequestDispatcher("grossprofit_shop_detail.jsp");
			rd.forward(request, response);
			break;

		}
	}
}
