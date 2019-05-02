package product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	
	public void insertOrders(OrdersDTO oDto) {
		String query = "insert into orders(o_name, o_tel, o_address, p_id, p_name, o_quantity, o_time) values (?, ?, ?, ?, ?, ?, now());";
		PreparedStatement pStmt = null;

		try {
			pStmt = conn.prepareStatement(query);
			
			pStmt.setString(1, oDto.getO_name());
			pStmt.setString(2, oDto.getO_tel());
			pStmt.setString(3, oDto.getO_address());
			pStmt.setInt(4, oDto.getP_id());
			pStmt.setString(5, oDto.getP_name());
			pStmt.setInt(6, oDto.getO_quantity());			
			
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
	
	public List<OrdersDTO> selectUpload(int count) {
		String query = "select * from orders order by o_id desc limit ?;";
		PreparedStatement pStmt = null;
		List<OrdersDTO> list = new ArrayList<OrdersDTO>();
		try {
			pStmt = conn.prepareStatement(query);
			pStmt.setInt(1, count);
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				OrdersDTO oDto = new OrdersDTO();
				oDto.setO_id(rs.getInt("o_id"));
				oDto.setO_name(rs.getString("o_name"));
				oDto.setO_tel(rs.getString("o_tel"));
				oDto.setO_address(rs.getString("o_address"));
				oDto.setP_id(rs.getInt("p_id"));
				oDto.setP_name(rs.getString("p_name"));
				oDto.setO_quantity(rs.getInt("o_quantity"));
				oDto.setO_time(rs.getString("o_time"));

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
