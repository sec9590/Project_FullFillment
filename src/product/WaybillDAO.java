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
	private static final String PASSWORD = "javauser";
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
		String query = "select * from waybill;";
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
		String query = "select * from waybill where o_address like" + "'" + add1 + "%'" +";";
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
		String query = "select * from waybill where o_address like" + "'" + add1 + "%' or o_address like '" + add2 + "%'" +";";
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
		String query = "select * from waybill where o_address like" + "'" + add1 + "%' or o_address like '" + add2 + "%' or o_address like '" + add3 + "%'" +";";
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
	
	
	public void close() {
		try {
			if (conn != null && !conn.isClosed())
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
