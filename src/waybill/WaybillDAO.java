package waybill;

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

public class WaybillDAO {
	private Connection conn;

	private static final String USERNAME = "javauser";
	private static final String PASSWORD = "javapass";
	private static final String URL = "jdbc:mysql://localhost:3306/yellow?verifyServerCertificate=false&useSSL=false";

	// database에 대한 커넥션을 생성
	public WaybillDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public List<WaybillDTO> selectAll() {
		String query = "select * from waybill order by w_id desc;";
		PreparedStatement pStmt = null;
		List<WaybillDTO> wList = new ArrayList<WaybillDTO>();
		try {
			pStmt = conn.prepareStatement(query);
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				WaybillDTO way = new WaybillDTO();
				way.setW_id(rs.getInt(1));
				way.setO_id(rs.getInt(2));
				way.setO_name(rs.getString(3));
				way.setO_tel(rs.getString(4));
				way.setO_address(rs.getString(5));
				way.setW_waycode(rs.getString(6));
				way.setO_time(rs.getString(7).substring(2, 16));
				way.setW_time(rs.getString(8).substring(2, 16));
				wList.add(way);
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
		return wList;
	}

	public List<WaybillDTO> selectAdd1(String add1) {
		String query = "select * from waybill where o_address like" + "'" + add1 + "%'" + " order by w_id desc;";
		PreparedStatement pStmt = null;
		List<WaybillDTO> wList = new ArrayList<WaybillDTO>();
		try {
			pStmt = conn.prepareStatement(query);
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				WaybillDTO way = new WaybillDTO();
				way.setW_id(rs.getInt(1));
				way.setO_id(rs.getInt(2));
				way.setO_name(rs.getString(3));
				way.setO_tel(rs.getString(4));
				way.setO_address(rs.getString(5));
				way.setW_waycode(rs.getString(6));
				way.setO_time(rs.getString(7).substring(2, 16));
				way.setW_time(rs.getString(8).substring(2, 16));
				wList.add(way);
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
		return wList;
	}

	public List<WaybillDTO> selectAdd2(String add1, String add2) {
		String query = "select * from waybill where o_address like" + "'" + add1 + "%' or o_address like '" + add2
				+ "%'" + " order by w_id desc;";
		PreparedStatement pStmt = null;
		List<WaybillDTO> wList = new ArrayList<WaybillDTO>();
		try {
			pStmt = conn.prepareStatement(query);
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				WaybillDTO way = new WaybillDTO();
				way.setW_id(rs.getInt(1));
				way.setO_id(rs.getInt(2));
				way.setO_name(rs.getString(3));
				way.setO_tel(rs.getString(4));
				way.setO_address(rs.getString(5));
				way.setW_waycode(rs.getString(6));
				way.setO_time(rs.getString(7).substring(2, 16));
				way.setW_time(rs.getString(8).substring(2, 16));
				wList.add(way);
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
		return wList;
	}

	public List<WaybillDTO> selectAdd3(String add1, String add2, String add3) {
		String query = "select * from waybill where o_address like" + "'" + add1 + "%' or o_address like '" + add2
				+ "%' or o_address like '" + add3 + "%'" + " order by w_id desc;";
		PreparedStatement pStmt = null;
		List<WaybillDTO> wList = new ArrayList<WaybillDTO>();
		try {
			pStmt = conn.prepareStatement(query);
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				WaybillDTO way = new WaybillDTO();
				way.setW_id(rs.getInt(1));
				way.setO_id(rs.getInt(2));
				way.setO_name(rs.getString(3));
				way.setO_tel(rs.getString(4));
				way.setO_address(rs.getString(5));
				way.setW_waycode(rs.getString(6));
				way.setO_time(rs.getString(7).substring(2, 16));
				way.setW_time(rs.getString(8).substring(2, 16));
				wList.add(way);
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
		return wList;
	}

	public List<WaybillDTO> selectAdd4(String add1, String add2, String add3, String add4) {
		String query = "select * from waybill where o_address like" + "'" + add1 + "%' or o_address like '" + add2
				+ "%' or o_address like '" + add3 + "%' or o_address like '" + add4 + "%'" + " order by w_id desc;";
		PreparedStatement pStmt = null;
		List<WaybillDTO> wList = new ArrayList<WaybillDTO>();
		try {
			pStmt = conn.prepareStatement(query);
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				WaybillDTO way = new WaybillDTO();
				way.setW_id(rs.getInt(1));
				way.setO_id(rs.getInt(2));
				way.setO_name(rs.getString(3));
				way.setO_tel(rs.getString(4));
				way.setO_address(rs.getString(5));
				way.setW_waycode(rs.getString(6));
				way.setO_time(rs.getString(7).substring(2, 16));
				way.setW_time(rs.getString(8).substring(2, 16));
				wList.add(way);
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
		return wList;
	}

	public int getCount() {
		String query = "select count(*) from waybill;";
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

	public List<WaybillDTO> selectWaybillAll(int page) {
		int offset = 0;
		String sql = null;
		if (page == 0) {
			sql = "select * from waybill order by w_id desc;";
		} else {
			sql = "select * from waybill order by w_id desc limit ?, 10;";
			offset = (page - 1) * 10;
		}
		PreparedStatement pStmt = null;
		List<WaybillDTO> list = new ArrayList<WaybillDTO>();

		try {
			pStmt = conn.prepareStatement(sql);
			if (page != 0)
				pStmt.setInt(1, offset);
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				WaybillDTO wDto = new WaybillDTO();
				wDto.setW_id(rs.getInt(1));
				wDto.setO_id(rs.getInt(2));
				wDto.setO_name(rs.getString(3));
				wDto.setO_tel(rs.getString(4));
				wDto.setO_address(rs.getString(5));
				wDto.setW_waycode(rs.getString(6));
				wDto.setO_time(rs.getString(7).substring(2, 16));
				wDto.setW_time(rs.getString(8).substring(2, 16));
				list.add(wDto);
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

	public List<NoWaybillDTO> selectNoWaybillAll() {

		String sql = "select * from no_waybill;";

		PreparedStatement pStmt = null;
		List<NoWaybillDTO> list = new ArrayList<NoWaybillDTO>();

		try {
			pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				NoWaybillDTO nwDto = new NoWaybillDTO();
				nwDto.setO_id(rs.getInt(1));
				nwDto.setO_name(rs.getString(2));
				nwDto.setO_tel(rs.getString(3));
				nwDto.setO_address(rs.getString(4));
				nwDto.setO_time(rs.getString(5).substring(2, 16));
				list.add(nwDto);
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

	public void deleteNoWaybill(NoWaybillDTO nwDto) {
		String query = "delete from no_waybill where o_id = ?";
		PreparedStatement pStmt = null;

		try {
			pStmt = conn.prepareStatement(query);

			pStmt.setInt(1, nwDto.getO_id());

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

	// 운송회사에 따른 운송내역
	public List<WaybillDTO> selectCarrierAll(String field) {
		String query = "select * from waybill where w_waycode=?;";
		PreparedStatement pStmt = null;
		List<WaybillDTO> list = new ArrayList<WaybillDTO>();
		try {
			pStmt = conn.prepareStatement(query);
			pStmt.setString(1, field);
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				WaybillDTO wDto = new WaybillDTO();
				wDto.setW_id(rs.getInt(1));
				wDto.setO_id(rs.getInt(2));
				wDto.setO_name(rs.getString(3));
				wDto.setO_tel(rs.getString(4));
				wDto.setO_address(rs.getString(5));
				wDto.setW_waycode(rs.getString(6));
				wDto.setO_time(rs.getString(7).substring(2, 16));
				wDto.setW_time(rs.getString(8).substring(2, 16));
				list.add(wDto);
				System.out.println(wDto.toString());
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

	// 운송회사 전체 대금청구
	public List<WaybillDTO> shipprofitAll() {
		String query = "select m.m_name, w.w_time, count(*), w.w_waycode from waybill as w inner join member as m on binary(m.m_field) = binary(w.w_waycode) group by w.w_time, w.w_waycode;";
		PreparedStatement pStmt = null;
		List<WaybillDTO> list = new ArrayList<WaybillDTO>();
		try {
			pStmt = conn.prepareStatement(query);
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				WaybillDTO wDto = new WaybillDTO();
				wDto.setW_name(rs.getString(1));
				wDto.setW_time(rs.getString(2).substring(0, 16));
				wDto.setCount(rs.getInt(3));
				wDto.setW_waycode(rs.getString(4));
				list.add(wDto);
				System.out.println(wDto.toString());
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

	public List<WaybillDTO> shipprofit(String w_time, String w_waycode) {
		String query = "select w.w_id, o.o_id, o.o_name, o.o_tel, o.o_address, count(*) '주문수'\r\n"
				+ "				from orders as o, waybill as w, orders_detail as d where d.o_id=o.o_id and o.o_id = w.o_id and w.w_waycode=? and w.w_time like ? group by o.o_id;";
		PreparedStatement pStmt = null;
		List<WaybillDTO> list = new ArrayList<WaybillDTO>();
		try {
			pStmt = conn.prepareStatement(query);
			pStmt.setString(1, w_waycode);
			w_time = w_time + "%";
			System.out.println("운송시간 : " + w_time);
			pStmt.setString(2, w_time);
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				WaybillDTO wDto = new WaybillDTO();
				wDto.setW_id(rs.getInt(1));
				wDto.setO_id(rs.getInt(2));
				wDto.setO_name(rs.getString(3));
				wDto.setO_tel(rs.getString(4));
				wDto.setO_address(rs.getString(5));
				wDto.setCount(rs.getInt(6));
				list.add(wDto);
				System.out.println(wDto.toString());
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

	
	// 운송회사에 따른 운송내역
	public List<WaybillDTO> selectWaybill(String date1, String date2) {
		String query = "select * from waybill where o_time between ? and ?;";
		PreparedStatement pStmt = null;
		List<WaybillDTO> list = new ArrayList<WaybillDTO>();
		System.out.println(date1 + " " + date2);
		try {
			pStmt = conn.prepareStatement(query);
			pStmt.setString(1, date1);
			pStmt.setString(2, date2);
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				WaybillDTO wDto = new WaybillDTO();
				wDto.setW_id(rs.getInt(1));
				wDto.setO_id(rs.getInt(2));
				wDto.setO_name(rs.getString(3));
				wDto.setO_tel(rs.getString(4));
				wDto.setO_address(rs.getString(5));
				wDto.setW_waycode(rs.getString(6));
				wDto.setO_time(rs.getString(7).substring(2, 16));
				wDto.setW_time(rs.getString(8).substring(2, 16));
				list.add(wDto);
				System.out.println("한달" + wDto.toString());
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

	public String timechangeString(Date time) {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String str = format1.format(time);
		return str;
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
