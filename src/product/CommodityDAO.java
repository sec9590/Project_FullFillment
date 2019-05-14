package product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommodityDAO {
	private Connection conn;

	private static final String USERNAME = "javauser";
	private static final String PASSWORD = "javapass";
	private static final String URL = "jdbc:mysql://localhost:3306/yellow?verifyServerCertificate=false&useSSL=false";

	// database에 대한 커넥션을 생성
	public CommodityDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	// 운송된 상품에서 출고된 상품갯수 
	public List<DetailOrderDTO> selectcommodityOut() {
		String query = "select d.p_id, sum(d.o_quantity), 15 from waybill as w , orders_detail as d, orders as o where o.o_id = w.o_id and o.o_id = d.o_id and w.w_time between '2019-05-13' and '2019-05-30' group by d.p_id;";
		PreparedStatement pStmt = null;
		List<DetailOrderDTO> list = new ArrayList<DetailOrderDTO>();

		try {
			pStmt = conn.prepareStatement(query);		
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				DetailOrderDTO doDto = new DetailOrderDTO();
				doDto.setP_id(rs.getInt(1));
				doDto.setO_quantity(rs.getInt(2));
				doDto.setP_count(rs.getInt(3)); //초기 기초재고
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
	
	// 발주된 상품에서 입고된 상품갯수
	public List<BuyingDTO> selectcommodityIn() {
		String query = "select p_id, sum(p_quantity) from buying where b_time between '2019-05-13' and '2019-05-30' group by p_id;";
		PreparedStatement pStmt = null;
		List<BuyingDTO> list = new ArrayList<BuyingDTO>();
		try {
			pStmt = conn.prepareStatement(query);
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				BuyingDTO bDto = new BuyingDTO();
				bDto.setP_id(rs.getInt(1));				
				bDto.setP_quantity(rs.getInt(2));
				System.out.println(bDto.toString());
				list.add(bDto);
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
