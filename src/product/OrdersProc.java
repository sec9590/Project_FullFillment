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
@WebServlet("/OrdersProcServlet")
public class OrdersProc extends HttpServlet {
	private static final Logger LOG = LoggerFactory.getLogger(OrdersProc.class);
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
		List<OrdersDTO> shopListall = null;
		List<BuyingDTO> buyingall = null;
		List<BuyingDTO> orderhistoryall = null;
		List<BuyingDTO> buyingProfitall = null;
		List<WaybillDTO> shipProfit = null;
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
		int shoptotal = 0;
		int buyingtotal = 0;
		int shiptotal = 0;
		String date = null;
		String date1 = null;
		String date2 = null;

		switch (action) {
		// 파일 다운하고 주문하기
		case "down":
			String saveDir = "C:\\Temp\\Invoices\\Backup\\";
			int maxSize = 1024 * 1024 * 100;
			String encType = "UTF-8";

			MultipartRequest multipartRequest = new MultipartRequest(request, saveDir, maxSize, encType,
					new DefaultFileRenamePolicy());

			/*
			 * LOG.info("이름 : " + multipartRequest.getParameter("name") + "<br>");
			 * LOG.info("파일 : " + multipartRequest.getParameter("file") + "<br>");
			 * //null 값을 갖는다. LOG.info("업로드파일명 : " +
			 * multipartRequest.getFilesystemName("file") + "<br>");
			 * LOG.info("원래파일명 : " + multipartRequest.getOriginalFileName("file")
			 * + "<br>");
			 */

			File file = multipartRequest.getFile("file");

			// File file = new File("C:\\Temp\\shopping.csv");

			br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "euc-kr"));
			LOG.info("원래파일명 : " + multipartRequest.getOriginalFileName("file"));
			String filename = multipartRequest.getOriginalFileName("file");			
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
					LOG.info("주문번호 : " + orderid);

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
				LOG.info(oDto.toString());
				LOG.info(doDto.toString());
				System.out.println(count);

			}

			msg = "주문 성공 및 발주신청";
			url = "OrdersProcServlet?action=orderlist&count=" + count;
			LOG.info("count : " + count);
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

			LOG.info(request.getParameter("count"));
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

			LOG.info(request.getParameter("count"));
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

			LOG.info("주문내역검색 완료");
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
			rd = request.getRequestDispatcher("admin/order/detail.jsp");
			rd.forward(request, response);
			break;

		// 재고목록
		case "productlist":
			pDao = new ProductDAO();
			pDto = new ProductDTO();
			List<ProductDTO> productList = pDao.selectAll();
			request.setAttribute("ProductList", productList);
			rd = request.getRequestDispatcher("admin/commodity/commodity.jsp");
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
			for(OrdersDTO o : orderAll) {
				if(oDao.IsWaybill(o.getO_id())) {
					request.setAttribute("waybill", true);
					o.setStatus("운송");
				}else {
					request.setAttribute("waybill", false);
					o.setStatus("지연");
				}
			}
			request.setAttribute("orderAllList", orderAll);
			request.setAttribute("pageList", pageList);
			rd = request.getRequestDispatcher("admin/order/sales.jsp");
			rd.forward(request, response);
			break;

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

		// 항목별 발주내역
		case "orderhistory":
			String code = request.getParameter("code");
			pDao = new ProductDAO();
			pDto = new ProductDTO();
			List<BuyingDTO> orderhistoryList = pDao.selectBuyingAll(code);
			request.setAttribute("orderhistoryList", orderhistoryList);
			rd = request.getRequestDispatcher("admin/buying/orderhistory.jsp");
			rd.forward(request, response);
			break;

		// 발주내역(전체)
		case "orderhistoryall":
			pDao = new ProductDAO();
			pDto = new ProductDTO();
			orderhistoryall = pDao.selectOrderhistoryAll();
			request.setAttribute("orderhistoryList", orderhistoryall);
			rd = request.getRequestDispatcher("admin/buying/orderhistoryall.jsp");
			rd.forward(request, response);
			break;
			
		// 발주내역(월별)
		case "selectOrders":
			date = request.getParameter("dateInventory");
			LOG.info(date);
			date1 = date + "-01 00:00";
			LOG.info(date1);
			date2 = date + "-31 23:59";
			LOG.info(date2);
			
			pDao = new ProductDAO();
			pDto = new ProductDTO();
			orderhistoryall = pDao.selectOrderhistoryAllTime(date1, date2);
			request.setAttribute("dateInventory", date);
			request.setAttribute("orderhistoryList", orderhistoryall);
			rd = request.getRequestDispatcher("admin/buying/orderhistoryall_selectTime.jsp");
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
			day = pDao.currentTime();
			request.setAttribute("currentTime", day);

			if (buyingList.size() == 0) {
				buyingList = null;
				request.setAttribute("buyingList", buyingList);
				System.out.println(buyingList);
			} else
				request.setAttribute("buyingList", buyingList);

			rd = request.getRequestDispatcher("buying/buying.jsp");
			rd.forward(request, response);
			break;

		// 구매처에따른 발주 신청 전체 내역
		case "buyingall":
			field = request.getParameter("field");
			pDao = new ProductDAO();
			pDto = new ProductDTO();
			buyingall = pDao.selectBuyingAll(field);
			request.setAttribute("buyingall", buyingall);
			rd = request.getRequestDispatcher("buying/buyingall.jsp");
			rd.forward(request, response);
			break;
			
		// 구매처 월별 내역
		case "buyingselectall":
			date = request.getParameter("dateInventory");
			LOG.info(date);
			date1 = date + "-01 00:00";
			LOG.info(date1);
			date2 = date + "-31 23:59";
			LOG.info(date2);
			
			field = request.getParameter("field");
			pDao = new ProductDAO();
			pDto = new ProductDTO();
			
			buyingall = pDao.selectOrderhistoryAllTime(date1, date2);
			request.setAttribute("dateInventory", date);
			request.setAttribute("buyingall", buyingall);
			rd = request.getRequestDispatcher("buying/buyingall_selectTime.jsp");
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

		case "selecttime": // 일단위 상품별 주문 내역
			oDao = new OrdersDAO();
			date = request.getParameter("dateInventory");
			date = oDao.selecttimechangeString(oDao.selectTime(date));
			LOG.info(date);
			date1 = date + " 00:00";
			LOG.info(date1);
			date2 = date + " 23:59";

			date1 = oDao.timechangeString(oDao.compareTime(date1));
			date2 = oDao.timechangeString(oDao.compareTime(date2));

			orderAll = oDao.selectTime(date1, date2);
			LOG.info("기간설정 달력");
			request.setAttribute("dateInventory", date);
			request.setAttribute("orderAllList", orderAll);
			request.setAttribute("pageList", pageList);
			rd = request.getRequestDispatcher("admin/order/selecttime.jsp");
			rd.forward(request, response);
			break;

		// 쇼핑몰 대금청구
		case "shopprofit":
			oDao = new OrdersDAO();

			List<OrdersDTO> shopList = oDao.selectShop();

			for (OrdersDTO shop : shopList) {
				int sum = 0;
				int shippay = 0;
				LOG.info("이거" + shop.getO_time());
				List<DetailOrderDTO> shopList_total = oDao.selectShopDetail(shop.getO_time(), shop.getShopcode());

				for (DetailOrderDTO dDto : shopList_total) {
					sum += (int) (dDto.getP_total() + (dDto.getP_total() * 0.1));
				}
				shop.setTotal(sum);
				System.out.println(sum);
				shippay = oDao.getorderCount(shop.getO_time()) * 10000;
				LOG.info("운송비" + shippay);
				shop.setShippay(shippay);
				shoptotal += (sum + shippay);
			}

			request.setAttribute("shopList", shopList);
			System.out.println(shopList);
			rd = request.getRequestDispatcher("admin/grossprofit/grossprofit_shop.jsp");
			rd.forward(request, response);
			break;

		// 쇼핑몰 대금상세내역
		case "shopprofit_detail":
			oDao = new OrdersDAO();
			int pay = 0;
			String o_time = request.getParameter("o_time");
			String shopcode = request.getParameter("shopcode");
			List<DetailOrderDTO> shopList_detail = oDao.selectShopDetail(o_time, shopcode);
			for (DetailOrderDTO dDto : shopList_detail) {
				int total = (int) (dDto.getP_total() + (dDto.getP_total() * 0.1));
				dDto.setTotal(total);
				pay = oDao.getorderCount(o_time);
			}
			request.setAttribute("shopcode", shopcode);
			request.setAttribute("shippay", pay);
			request.setAttribute("shopList_detail", shopList_detail);
			rd = request.getRequestDispatcher("admin/grossprofit/grossprofit_shop_detail.jsp");
			rd.forward(request, response);
			break;

		// 발주 가격 내역
		case "buyingprofitAll":
			pDao = new ProductDAO();
			bDto = new BuyingDTO();
			List<BuyingDTO> buyingProfit = pDao.buyingprofitAll();
			request.setAttribute("buyingProfit", buyingProfit);
			rd = request.getRequestDispatcher("admin/grossprofit/grossprofit_buying.jsp");
			rd.forward(request, response);
			break;

		// 발주 가격 상세 내역
		case "buyingprofit_detail":
			pDao = new ProductDAO();
			bDto = new BuyingDTO();
			String b_time = request.getParameter("b_time");
			String buycode = request.getParameter("buycode");
			String b_name = request.getParameter("b_name");
			List<BuyingDTO> buyingList_detail = pDao.buyingprofit(b_time, buycode);

			request.setAttribute("b_name", b_name);
			request.setAttribute("buycode", buycode);
			request.setAttribute("buyingList_detail", buyingList_detail);
			rd = request.getRequestDispatcher("admin/grossprofit/grossprofit_buying_detail.jsp");
			rd.forward(request, response);
			break;
		
			//매출총이익
		case "grossprofit":
			oDao = new OrdersDAO();

			shopListall = oDao.selectShop();

			for (OrdersDTO shop : shopListall) {
				int sum = 0;
				int shippay = 0;
				
				List<DetailOrderDTO> shopList_total = oDao.selectShopDetail(shop.getO_time(), shop.getShopcode());

				for (DetailOrderDTO dDto : shopList_total) {
					sum += (int) (dDto.getP_total() + (dDto.getP_total() * 0.1));
				}
				shop.setTotal(sum);
				System.out.println(sum);
				shippay = oDao.getorderCount(shop.getO_time()) * 10000;
				LOG.info("운송비" + shippay);
				shop.setShippay(shippay);
				shoptotal += (sum + shippay);
			}
			
			pDao = new ProductDAO();
			bDto = new BuyingDTO();
			buyingProfitall = pDao.buyingprofitAll();
			for(BuyingDTO buydto : buyingProfitall) {
				buyingtotal += buydto.getTotal();
			}
			
			wDao = new WaybillDAO();
			wDto = new WaybillDTO();

			shipProfit = wDao.shipprofitAll();
			for(WaybillDTO waydto : shipProfit) {
				shiptotal += waydto.getCount() * 10000;
			}
			
			request.setAttribute("shoptotal", shoptotal);
			request.setAttribute("buyingtotal", buyingtotal);
			request.setAttribute("shiptotal", shiptotal);
			rd = request.getRequestDispatcher("admin/grossprofit/grossprofitAll.jsp");
			rd.forward(request, response);
			break;
			
			
		// 매출 총이익 페이지 월단위 내역	
		case "selectGrossprofit": 
			date = request.getParameter("dateInventory");
			LOG.info(date);
			date1 = date + "-01 00:00";
			LOG.info(date1);
			date2 = date + "-31 23:59";
			LOG.info(date2);
			
			oDao = new OrdersDAO();
			shopListall = oDao.selectShopTime(date1, date2);

			for (OrdersDTO shop : shopListall) {
				int sum = 0;
				int shippay = 0;
				
				List<DetailOrderDTO> shopList_total = oDao.selectShopDetailTime(date1, date2);

				for (DetailOrderDTO dDto : shopList_total) {
					sum += (int) (dDto.getP_total() + (dDto.getP_total() * 0.1));
				}
				shop.setTotal(sum);
				System.out.println(sum);
				shippay = oDao.getorderCount(shop.getO_time()) * 10000;
				LOG.info("운송비" + shippay);
				shop.setShippay(shippay);
				shoptotal += (sum + shippay);
			}
			
			pDao = new ProductDAO();
			bDto = new BuyingDTO();
			buyingProfitall = pDao.selectBuyingprofitAll(date1, date2);
			for(BuyingDTO buydto : buyingProfitall) {
				buyingtotal += buydto.getTotal();
			}
			
			wDao = new WaybillDAO();
			wDto = new WaybillDTO();

			shipProfit = wDao.selectShipprofitAll(date1, date2);
			for(WaybillDTO waydto : shipProfit) {
				shiptotal += waydto.getCount() * 10000;
			}
			
			
			LOG.info("기간설정 달력");
			request.setAttribute("dateInventory", date);
			request.setAttribute("shoptotal", shoptotal);
			request.setAttribute("buyingtotal", buyingtotal);
			request.setAttribute("shiptotal", shiptotal);
			rd = request.getRequestDispatcher("admin/grossprofit/grossprofitAll_selectTime.jsp");
			rd.forward(request, response);
			break;	
			
		case "shopSelectTime": 
			date = request.getParameter("dateInventory");
			LOG.info(date);
			date1 = date + "-01 00:00";
			LOG.info(date1);
			date2 = date + "-31 23:59";
			LOG.info(date2);
			
			oDao = new OrdersDAO();
			shopListall = oDao.selectShopTime(date1, date2);

			for (OrdersDTO shop : shopListall) {
				int sum = 0;
				int shippay = 0;
				
				List<DetailOrderDTO> shopList_total = oDao.selectShopDetailTime(date1, date2);

				for (DetailOrderDTO dDto : shopList_total) {
					sum += (int) (dDto.getP_total() + (dDto.getP_total() * 0.1));
				}
				shop.setTotal(sum);
				System.out.println(sum);
				shippay = oDao.getorderCount(shop.getO_time()) * 10000;
				LOG.info("운송비" + shippay);
				shop.setShippay(shippay);
				shoptotal += (sum + shippay);
			}
			
	
			LOG.info("쇼핑몰 한달 내역");
			request.setAttribute("dateInventory", date);
			request.setAttribute("shopList", shopListall);
			request.setAttribute("shoptotal", shoptotal);
			rd = request.getRequestDispatcher("admin/grossprofit/grossprofit_shop_selectTime.jsp");
			rd.forward(request, response);
			break;	
			
		case "buyingselectTime": 
			date = request.getParameter("dateInventory");
			LOG.info(date);
			date1 = date + "-01 00:00";
			LOG.info(date1);
			date2 = date + "-31 23:59";
			LOG.info(date2);
			
			pDao = new ProductDAO();
			bDto = new BuyingDTO();
			buyingProfitall = pDao.selectBuyingprofitAll(date1, date2);
			for(BuyingDTO buydto : buyingProfitall) {
				buyingtotal += buydto.getTotal();
			}
			
			LOG.info("구매처 한달 내역");
			request.setAttribute("dateInventory", date);
			request.setAttribute("buyingProfit", buyingProfitall);
			rd = request.getRequestDispatcher("admin/grossprofit/grossprofit_buying_selectTime.jsp");
			rd.forward(request, response);
			break;	
			
		
			
		}
	}
}
