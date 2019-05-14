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

	// 이번달 운송된 상품에서 출고된 상품갯수
	public List<CommodityDTO> selectcommodityOut() {
		String query = "select 15, d.p_id, sum(d.o_quantity) from waybill as w , orders_detail as d, orders as o where o.o_id = w.o_id and o.o_id = d.o_id and (w.w_time > last_day(now() - interval 1 month) and w.w_time <= last_day(now())) group by d.p_id;";
		PreparedStatement pStmt = null;
		List<CommodityDTO> list = new ArrayList<CommodityDTO>();

		try {
			pStmt = conn.prepareStatement(query);
			
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				CommodityDTO cDto = new CommodityDTO();
				cDto.setBasic(rs.getInt(1));
				cDto.setP_id(rs.getInt(2));
				cDto.setOut(rs.getInt(3));		
				System.out.println(cDto.toString());
				list.add(cDto);
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
	
	// 월별 운송된 상품에서 출고된 상품갯수
		public List<CommodityDTO> selectcommodityOutTime(String date1, String date2) {
			String query = "select 15, d.p_id, sum(d.o_quantity) from waybill as w , orders_detail as d, orders as o where o.o_id = w.o_id and o.o_id = d.o_id and w.w_time between ? and ? group by d.p_id;";
			PreparedStatement pStmt = null;
			List<CommodityDTO> list = new ArrayList<CommodityDTO>();

			try {
				pStmt = conn.prepareStatement(query);
				pStmt.setString(1, date1);
				pStmt.setString(2, date2);
				ResultSet rs = pStmt.executeQuery();

				while (rs.next()) {
					CommodityDTO cDto = new CommodityDTO();
					cDto.setBasic(rs.getInt(1));
					cDto.setP_id(rs.getInt(2));
					cDto.setOut(rs.getInt(3));		
					System.out.println(cDto.toString());
					list.add(cDto);
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

		// 이번달 발주된 상품에서 입고된 상품갯수
		public int selectcommodityIn(int p_id) {
			String query = "select sum(p_quantity) from buying where (b_time > last_day(now() - interval 1 month) and b_time <= last_day(now())) and p_id = ? group by p_id;";
			PreparedStatement pStmt = null;
			BuyingDTO bDto = new BuyingDTO();
			
			try {
				pStmt = conn.prepareStatement(query);
				pStmt.setInt(1, p_id);
				ResultSet rs = pStmt.executeQuery();

				while (rs.next()) {
					bDto.setP_quantity(rs.getInt(1));
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
			return bDto.getP_quantity();
		}	
		
	// 발주된 상품에서 입고된 상품갯수
	public int selectcommodityInTime(String date1, String date2, int p_id) {
		String query = "select sum(p_quantity) from buying where w.w_time between ? and ? and p_id = ? group by p_id;";
		PreparedStatement pStmt = null;
		BuyingDTO bDto = new BuyingDTO();
		
		try {
			pStmt = conn.prepareStatement(query);
			pStmt.setString(1, date1);
			pStmt.setString(2, date2);
			pStmt.setInt(3, p_id);
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				bDto.setP_quantity(rs.getInt(1));
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
		return bDto.getP_quantity();
	}

}
