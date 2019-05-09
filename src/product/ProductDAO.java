package product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import waybill.NoWaybillDTO;

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
	
	// 재고목록
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
	
	//10개미만 떨어진 상품목록
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
	
	//주문시 10개미만 떨어진 상품 발주db저장
	public void insertBuying(int p_id) {
		String query = "INSERT INTO buying(p_id, p_name, p_img, p_price, p_quantity, buycode) SELECT p_id, p_name, p_img, p_price, p_quantity, buycode FROM product WHERE p_id = ?;";
		PreparedStatement pStmt = null;

		try {
			pStmt = conn.prepareStatement(query);
			pStmt.setInt(1, p_id);

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
	
	// 발주내역 삭제
	public void deleteNoWaybill(BuyingDTO bDto) {
		String query = "delete from buying where buycode = ?";
		PreparedStatement pStmt = null;

		try {
			pStmt = conn.prepareStatement(query);
			
			pStmt.setString(1, bDto.getBuycode());
			
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
	
	// 구매처에 따른 발주내역
		public List<BuyingDTO> selectBuyingAll(String field) {
			String query = "select p_id, p_name, p_price, p_quantity from buying where buycode=?;";
			PreparedStatement pStmt = null;
			List<BuyingDTO> list = new ArrayList<BuyingDTO>();
			try {
				pStmt = conn.prepareStatement(query);	
				pStmt.setString(1, field);
				ResultSet rs = pStmt.executeQuery();

				while (rs.next()) {
					BuyingDTO bDto = new BuyingDTO();
					bDto.setP_id(rs.getInt(1));
					bDto.setP_name(rs.getString(2));
					bDto.setP_price(rs.getString(3));
					bDto.setP_quantity(rs.getInt(4));
					list.add(bDto);
					System.out.println(bDto.toString());
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
		
	
	// 발주db에 상품번호 확인
	public List<BuyingDTO> selectBuyingAll() {
		String query = "select p_id from buying;";
		PreparedStatement pStmt = null;
		List<BuyingDTO> list = new ArrayList<BuyingDTO>();
		try {
			pStmt = conn.prepareStatement(query);			
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				BuyingDTO bDto = new BuyingDTO();
				bDto.setP_id(rs.getInt(1));
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
	
	// 상품존재유무
	public boolean isBuying(int p_id) {
		boolean check = false;
		
		List<BuyingDTO> buyingList = selectBuyingAll();
		for(BuyingDTO buying : buyingList) {
			if(buying.getP_id() == p_id)
				check = true;
			else
				check = false;			
		}		
		return check;
	}
}
