package product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
				way.setO_time(rs.getString(6));
				way.setO_time(rs.getString(7));
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
		String query = "select * from waybill where o_address like" + "'" + add1 + "%'" +" order by w_id desc;";
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
				way.setO_time(rs.getString(6));
				way.setO_time(rs.getString(7));
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
		String query = "select * from waybill where o_address like" + "'" + add1 + "%' or o_address like '" + add2 + "%'" +" order by w_id desc;";
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
				way.setO_time(rs.getString(6));
				way.setO_time(rs.getString(7));
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
		String query = "select * from waybill where o_address like" + "'" + add1 + "%' or o_address like '" + add2 + "%' or o_address like '" + add3 + "%'" +" order by w_id desc;";
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
				way.setO_time(rs.getString(6));
				way.setO_time(rs.getString(7));
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
		String query = "select * from waybill where o_address like" + "'" + add1 + "%' or o_address like '" + add2 + "%' or o_address like '" + add3 + "%' or o_address like '" + add4 + "%'" +" order by w_id desc;";
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
				way.setO_time(rs.getString(6));
				way.setO_time(rs.getString(7));
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
				wDto.setO_time(rs.getString(6).substring(2,16));
				wDto.setW_time(rs.getString(7).substring(2,16));
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
				nwDto.setO_time(rs.getString(5));
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
	
	public void close() {
		try {
			if (conn != null && !conn.isClosed())
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
