package product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import waybill.NoWaybillDTO;

public class OrdersDAO {
	private Connection conn;

	private static final String USERNAME = "javauser";
	private static final String PASSWORD = "javapass";
	private static final String URL = "jdbc:mysql://localhost:3306/yellow?verifyServerCertificate=false&useSSL=false";

	// database에 대한 커넥션을 생성
	public OrdersDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public int getCount() {
		String query = "select count(*) from orders;";
		PreparedStatement pStmt = null;
		int count = 0;
		try {
			pStmt = conn.prepareStatement(query);
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pStmt != null && !pStmt.isClosed())
					pStmt.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return count;
	}

	//주문내역 추가
	public void insertOrders(OrdersDTO oDto) {
		String query = "insert into orders(o_name, o_tel, o_address, o_time) values (?, ?, ?, now());";
		PreparedStatement pStmt = null;

		try {
			pStmt = conn.prepareStatement(query);

			pStmt.setString(1, oDto.getO_name());
			pStmt.setString(2, oDto.getO_tel());
			pStmt.setString(3, oDto.getO_address());

			pStmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pStmt != null && !pStmt.isClosed())
					pStmt.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

	// 미운송내역 추가
	public void insertNoWaybill(int o_id) {
		String query = "INSERT INTO no_waybill SELECT * FROM orders WHERE o_id = ?;";
		PreparedStatement pStmt = null;

		try {
			pStmt = conn.prepareStatement(query);
			pStmt.setInt(1, o_id);

			pStmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pStmt != null && !pStmt.isClosed())
					pStmt.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

	// 상세주문내역 추가
	public void insertDetailOrders(DetailOrderDTO dODto) {
		String query = "insert into orders_detail(o_id, p_id, p_name, o_quantity) values (?, ?, ?, ?);";
		PreparedStatement pStmt = null;

		try {
			pStmt = conn.prepareStatement(query);

			pStmt.setInt(1, dODto.getO_id());
			pStmt.setInt(2, dODto.getP_id());
			pStmt.setString(3, dODto.getP_name());
			pStmt.setInt(4, dODto.getO_quantity());

			pStmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pStmt != null && !pStmt.isClosed())
					pStmt.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

	
	// 운송db에 추가
	public void insertWaybill(int o_id) {
		String query = "INSERT INTO waybill(o_id, o_name, o_tel, o_address, w_waycode, o_time) SELECT o_id, o_name, o_tel, o_address, ?, o_time FROM orders WHERE o_id = ?;";
		PreparedStatement pStmt = null;
		String address = selectAddress(o_id);
		System.out.println(address);
		String add = address.substring(0, 2);
		System.out.println(add);
		String w_waycode = null;
		
		if(add.equals("서울") || add.equals("경기")) {
			w_waycode = "a";
		} else if(add.equals("대전") || add.equals("세종") || add.equals("충청")) {
			w_waycode = "b";
		} else if(add.equals("광주") || add.equals("전라")) {
			w_waycode = "c";
		} else if(add.equals("대구") || add.equals("울산") || add.equals("부산") || add.equals("경상")) {
			w_waycode = "d";
		} else {
			w_waycode = "e";
		}
		try {
			pStmt = conn.prepareStatement(query);
			pStmt.setString(1, w_waycode);
			pStmt.setInt(2, o_id);

			pStmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pStmt != null && !pStmt.isClosed())
					pStmt.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
	
	// 운송시간
	public void updateWaybillTime(OrdersDTO oDto) {
		String query = "update waybill set w_time=? where o_id=?;";
		PreparedStatement pStmt = null;
		String w_time = null;
		String o_time = oDto.getO_time();
		System.out.println(o_time);

		if (compareTime(o_time).after(currentTime("day")))
			w_time = timechangeString(currentTime("night"));
		else
			w_time = timechangeString(currentTime("day"));

		System.out.println(w_time);

		try {
			pStmt = conn.prepareStatement(query);

			pStmt.setString(1, w_time);
			pStmt.setInt(2, oDto.getO_id());

			pStmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pStmt != null && !pStmt.isClosed())
					pStmt.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
	
	//nowaybill의 운송시간
	public void updateWaybillTime(NoWaybillDTO nwDto) {
		String query = "update waybill set w_time=? where o_id=?;";
		PreparedStatement pStmt = null;
		String w_time = null;
		String o_time = nwDto.getO_time();
		System.out.println(o_time);

		if (compareTime(o_time).after(currentTime("day")))
			w_time = timechangeString(currentTime("night"));
		else
			w_time = timechangeString(currentTime("day"));

		System.out.println(w_time);

		try {
			pStmt = conn.prepareStatement(query);

			pStmt.setString(1, w_time);
			pStmt.setInt(2, nwDto.getO_id());

			pStmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pStmt != null && !pStmt.isClosed())
					pStmt.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
	
	
	// 운송처리위한 현재시간 변환
	public static Date currentTime(String day) {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date time = new Date();
		String time1 = format1.format(time);

		if (day.equals("day"))
			time1 += " 09:00";
		else if(day.equals("night"))
			time1 += " 18:00";
		else
			time1 += " 10:00";

		System.out.println(time1);

		try {
			time = format2.parse(time1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return time;
	}

	// 시간형식 문자열로 변환
	public String timechangeString(Date time) {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String str = format1.format(time);
		return str;
	}

	// 주문시간변환
	public Date compareTime(String o_time) {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date time = null;
		try {
			time = format1.parse(o_time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return time;
	}
	
	// 일단위 검색(문자열시간 분빼고 변환)
		public Date selectTime(String o_time) {
			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
			Date time = null;
			try {
				time = format1.parse(o_time);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return time;
		}
		
		// 일단위 검색(시간형식 문자열로 변환)
		public String selecttimechangeString(Date time) {
			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
			String str = format1.format(time);
			return str;
		}


	// 첨부추가한 주문내역
	public List<OrdersDTO> selectUpload(int count) {
		String query = "select o.o_id, o.o_name, o.o_tel, o.o_address, date_format(o.o_time, '%Y-%m-%d %H:%i'), count(*) '주문수'\r\n"
				+ "				from orders as o inner join orders_detail as d on o.o_id = d.o_id group by o.o_id order by o.o_id desc limit ?;";
		PreparedStatement pStmt = null;
		List<OrdersDTO> list = new ArrayList<OrdersDTO>();
		try {
			pStmt = conn.prepareStatement(query);
			pStmt.setInt(1, count);
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				OrdersDTO oDto = new OrdersDTO();
				oDto.setO_id(rs.getInt(1));
				oDto.setO_name(rs.getString(2));
				oDto.setO_tel(rs.getString(3));
				oDto.setO_address(rs.getString(4));
				oDto.setO_time(rs.getString(5));
				oDto.setCount(rs.getInt(6));
				System.out.println(oDto.toString());
				list.add(oDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pStmt != null && !pStmt.isClosed())
					pStmt.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return list;
	}

	// 총주문내역
	public List<OrdersDTO> selectOrderAll() {
		String query = "select o.o_id, o.o_name, o.o_tel, o.o_address, date_format(o.o_time, '%Y-%m-%d %H:%i'), count(*) '주문수'\r\n"
				+ "from orders as o inner join orders_detail as d on o.o_id = d.o_id group by o.o_id order by o.o_id;";
		PreparedStatement pStmt = null;
		List<OrdersDTO> list = new ArrayList<OrdersDTO>();
		try {
			pStmt = conn.prepareStatement(query);

			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				OrdersDTO oDto = new OrdersDTO();
				oDto.setO_id(rs.getInt(1));
				oDto.setO_name(rs.getString(2));
				oDto.setO_tel(rs.getString(3));
				oDto.setO_address(rs.getString(4));
				oDto.setO_time(rs.getString(5));
				oDto.setCount(rs.getInt(6));
				System.out.println(oDto.toString());
				list.add(oDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pStmt != null && !pStmt.isClosed())
					pStmt.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return list;
	}

	// 페이지별로 총 주문내역
	public List<OrdersDTO> selectOrderAll(int page) {
		int offset = 0;
		String sql = null;
		if (page == 0) {
			sql = "select o.o_id, o.o_name, o.o_tel, o.o_address, date_format(o.o_time, '%Y-%m-%d %H:%i'), count(*) '주문수'\r\n"
					+ "				from orders as o inner join orders_detail as d on o.o_id = d.o_id group by o.o_id order by o.o_id desc;";
		} else {
			sql = "select o.o_id, o.o_name, o.o_tel, o.o_address, date_format(o.o_time, '%Y-%m-%d %H:%i'), count(*) '주문수'\r\n"
					+ "from orders as o inner join orders_detail as d on o.o_id = d.o_id group by o.o_id order by o.o_id desc limit ?, 10;";
			offset = (page - 1) * 10;
		}
		PreparedStatement pStmt = null;
		List<OrdersDTO> list = new ArrayList<OrdersDTO>();

		try {
			pStmt = conn.prepareStatement(sql);
			if (page != 0)
				pStmt.setInt(1, offset);
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				OrdersDTO oDto = new OrdersDTO();
				oDto.setO_id(rs.getInt(1));
				oDto.setO_name(rs.getString(2));
				oDto.setO_tel(rs.getString(3));
				oDto.setO_address(rs.getString(4));
				oDto.setO_time(rs.getString(5));
				oDto.setCount(rs.getInt(6));
				System.out.println(oDto.toString());
				list.add(oDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pStmt != null && !pStmt.isClosed())
					pStmt.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return list;
	}

	// 주문번호 찾기
	public int selectOrderId(String name) {
		String query = "select o_id from orders where o_name=? order by o_id desc limit 1;";
		PreparedStatement pStmt = null;
		OrdersDTO oDto = new OrdersDTO();

		try {
			pStmt = conn.prepareStatement(query);
			pStmt.setString(1, name);
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				oDto.setO_id(rs.getInt(1));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pStmt != null && !pStmt.isClosed())
					pStmt.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return oDto.getO_id();
	}
	
	
	// 상품개수
	public int selectQuentity(int p_id) {
		String query = "select p_quantity from product where p_id = ?";
		PreparedStatement pStmt = null;
		ProductDTO pDto = new ProductDTO();

		try {
			pStmt = conn.prepareStatement(query);
			pStmt.setInt(1, p_id);
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				pDto.setP_quantity(rs.getInt(1));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pStmt != null && !pStmt.isClosed())
					pStmt.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return pDto.getP_quantity();
	}

	// 상세주문내역
	public List<DetailOrderDTO> selectDetailOrder(int id) {
		String query = "select p_id, p_name, o_quantity from orders_detail where o_id=?;";
		PreparedStatement pStmt = null;
		List<DetailOrderDTO> list = new ArrayList<DetailOrderDTO>();

		try {
			pStmt = conn.prepareStatement(query);
			pStmt.setInt(1, id);
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				DetailOrderDTO doDto = new DetailOrderDTO();
				doDto.setP_id(rs.getInt(1));
				doDto.setP_name(rs.getString(2));
				doDto.setO_quantity(rs.getInt(3));
				System.out.println(doDto.toString());
				list.add(doDto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pStmt != null && !pStmt.isClosed())
					pStmt.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return list;
	}

	// 상품개수 차감
	public void updateQuantity(DetailOrderDTO doDto) {
		String query = "update product set p_quantity=? where p_id=?;";
		PreparedStatement pStmt = null;
		int quantity = selectQuentity(doDto.getP_id()) - doDto.getO_quantity();

		try {
			pStmt = conn.prepareStatement(query);

			pStmt.setInt(1, quantity);
			pStmt.setInt(2, doDto.getP_id());

			pStmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pStmt != null && !pStmt.isClosed())
					pStmt.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

	// 상품개수 확인
	public boolean checkQuantity(DetailOrderDTO doDto) {
		int quantity = selectQuentity(doDto.getP_id()) - doDto.getO_quantity();

		if (quantity <= 0) {
			System.out.println("수량부족");
			return false;
		} else {
			return true;
		}
	}

	// 상품개수 10개미만 개수
	public boolean checkBuying(DetailOrderDTO doDto) {
		int quantity = selectQuentity(doDto.getP_id());

		if (quantity < 10) {
			System.out.println("발주신청요구");
			return true;
		} else {
			return false;
		}
	}
	
		
		// 배송코드 찾기
				public String selectAddress(int o_id) {
					String query = "select o_address from orders where o_id=?;";
					PreparedStatement pStmt = null;
					OrdersDTO oDto = new OrdersDTO();

					try {
						pStmt = conn.prepareStatement(query);
						pStmt.setInt(1, o_id);
						ResultSet rs = pStmt.executeQuery();

						while (rs.next()) {
							oDto.setO_address(rs.getString(1));
						}

					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						try {
							if (pStmt != null && !pStmt.isClosed())
								pStmt.close();
						} catch (SQLException se) {
							se.printStackTrace();
						}
					}
					return oDto.getO_address();
				}

		// 기간설정
		public List<OrdersDTO> selectTime(String date1, String date2) {
			String sql = "select o.o_id, o.o_name, o.o_tel, o.o_address, date_format(o.o_time, '%Y-%m-%d %H:%i'), count(*) '주문수' \r\n" + 
					"from orders as o inner join orders_detail as d on o.o_id = d.o_id where o.o_time between ?  and ?  group by o.o_id  order by o.o_id desc;";
			PreparedStatement pStmt = null;
			List<OrdersDTO> list = new ArrayList<OrdersDTO>();
			System.out.println(date1 + " " + date2);

			try {
				pStmt = conn.prepareStatement(sql);
				pStmt.setString(1, date1);
				pStmt.setString(2, date2);
				ResultSet rs = pStmt.executeQuery();

				while (rs.next()) {
					OrdersDTO oDto = new OrdersDTO();
					oDto.setO_id(rs.getInt(1));
					oDto.setO_name(rs.getString(2));
					oDto.setO_tel(rs.getString(3));
					oDto.setO_address(rs.getString(4));
					oDto.setO_time(rs.getString(5));
					oDto.setCount(rs.getInt(6));
					System.out.println("기간설정" + oDto.toString());
					list.add(oDto);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (pStmt != null && !pStmt.isClosed())
						pStmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			return list;
		}				
				
	public void close() {
		try {
			if (conn != null && !conn.isClosed())
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
