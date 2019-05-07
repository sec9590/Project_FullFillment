package product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
	private Connection conn;

	private static final String USERNAME = "javauser";
	private static final String PASSWORD = "javapass";
	private static final String URL = "jdbc:mysql://localhost:3306/yellow?verifyServerCertificate=false&useSSL=false";

	// database에 대한 커넥션을 생성
	public ProductDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}	
	
	public List<ProductDTO> selectAll() {
		String query = "select p_id, p_name, p_price, p_quantity from product;";
		PreparedStatement pStmt = null;
		List<ProductDTO> list = new ArrayList<ProductDTO>();
		try {
			pStmt = conn.prepareStatement(query);			
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				ProductDTO pDto = new ProductDTO();
				pDto.setP_id(rs.getInt(1));
				pDto.setP_name(rs.getString(2));
				pDto.setP_price(rs.getString(3));
				pDto.setP_quantity(rs.getInt(4));
				System.out.println(pDto.toString());
				list.add(pDto);
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
	
	public List<ProductDTO> selectOrderHistory(String code) {
		String query = "select p_id, p_name, p_price, p_quantity from product where buycode = ? and p_quantity <= 10;";
		PreparedStatement pStmt = null;
		List<ProductDTO> list = new ArrayList<ProductDTO>();
		try {
			pStmt = conn.prepareStatement(query);	
			pStmt.setString(1, code);
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				ProductDTO pDto = new ProductDTO();
				pDto.setP_id(rs.getInt(1));
				pDto.setP_name(rs.getString(2));
				pDto.setP_price(rs.getString(3));
				pDto.setP_quantity(rs.getInt(4));
				System.out.println(pDto.toString());
				list.add(pDto);
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
}
