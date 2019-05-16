package product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ProductDAO {
	private static final Logger LOG = LoggerFactory.getLogger(ProductDAO.class);
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
				LOG.info(pDto.toString());
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

	// 10개미만 떨어진 상품목록(상품db)
	public List<ProductDTO> selectBuying(String code) {
		String query = "select p_id, p_name, p_price, p_quantity from product where buycode = ? and p_quantity < 10;";
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
				LOG.info(pDto.toString());
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

	// 10개미만 떨어진 상품목록(공지사항)
		public List<ProductDTO> BuyingALL() {
			String query = "select p_name from product where p_quantity < 10;";
			PreparedStatement pStmt = null;
			List<ProductDTO> list = new ArrayList<ProductDTO>();
			try {
				pStmt = conn.prepareStatement(query);
				ResultSet rs = pStmt.executeQuery();

				while (rs.next()) {
					ProductDTO pDto = new ProductDTO();
					pDto.setP_name(rs.getString(1));
					LOG.info(pDto.toString());
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

	// 주문시 10개미만 떨어진 상품 발주db저장(발주시간 및 주문수량)
	public void insertBuying(int p_id) {
		String query = "INSERT INTO buying(p_id, p_name, p_img, p_price, p_quantity, b_time, buycode) SELECT p_id, p_name, p_img, p_price, ?, ?, buycode FROM product WHERE p_id = ?;";
		PreparedStatement pStmt = null;
		int quantity = 15 - selectQuentity(p_id);
		String b_time = nextday();

		try {
			pStmt = conn.prepareStatement(query);
			pStmt.setInt(1, quantity);
			pStmt.setString(2, b_time);
			pStmt.setInt(3, p_id);

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
	public static String currentTime() {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());

		String strDate = format1.format(cal.getTime());

		LOG.info(strDate);

		return strDate;
	}

	// 발주내역시간은 다음날 오전 열시로 처리
	public String nextday() {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, +1);

		String strDate = format1.format(cal.getTime())  + " 10:00";

		LOG.info(strDate);

		return strDate;
	}

	// 상품채우기
	public void updatep_Quantity(ProductDTO pDto) {
		String query = "update product set p_quantity=15 where p_id=?;";
		PreparedStatement pStmt = null;

		try {
			pStmt = conn.prepareStatement(query);

			pStmt.setInt(1, pDto.getP_id());
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

	// 구매처에 따른 발주내역(발주db)
	public List<BuyingDTO> selectBuyingAll(String field) {
		String query = "select b_id, p_id, p_name, p_price, p_quantity, date_format(b_time, '%Y-%m-%d %H:%i') from buying where buycode=?;";
		PreparedStatement pStmt = null;
		List<BuyingDTO> list = new ArrayList<BuyingDTO>();
		try {
			pStmt = conn.prepareStatement(query);
			pStmt.setString(1, field);
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				BuyingDTO bDto = new BuyingDTO();
				bDto.setB_id(rs.getInt(1));
				bDto.setP_id(rs.getInt(2));
				bDto.setP_name(rs.getString(3));
				bDto.setP_price(rs.getString(4));
				bDto.setP_quantity(rs.getInt(5));
				bDto.setB_time(rs.getString(6));
				list.add(bDto);
				LOG.info(bDto.toString());
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

	public List<BuyingDTO> selectOrderhistoryAll() {
		String query = "select b_id, p_id, p_name, p_price, p_quantity, date_format(b_time, '%Y-%m-%d %H:%i') from buying;";
		PreparedStatement pStmt = null;
		List<BuyingDTO> list = new ArrayList<BuyingDTO>();
		try {
			pStmt = conn.prepareStatement(query);
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				BuyingDTO bDto = new BuyingDTO();
				bDto.setB_id(rs.getInt(1));
				bDto.setP_id(rs.getInt(2));
				bDto.setP_name(rs.getString(3));
				bDto.setP_price(rs.getString(4));
				bDto.setP_quantity(rs.getInt(5));
				bDto.setB_time(rs.getString(6));
				list.add(bDto);
				LOG.info(bDto.toString());
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
	
	// 월별 발주내역
	public List<BuyingDTO> selectOrderhistoryAllTime(String date1, String date2) {
		String query = "select b_id, p_id, p_name, p_price, p_quantity, date_format(b_time, '%Y-%m-%d %H:%i') from buying where b_time between ? and ?;";
		PreparedStatement pStmt = null;
		List<BuyingDTO> list = new ArrayList<BuyingDTO>();
		LOG.info(date1 + " " + date2);
		try {
			pStmt = conn.prepareStatement(query);
			pStmt.setString(1, date1);
			pStmt.setString(2, date2);
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				BuyingDTO bDto = new BuyingDTO();
				bDto.setB_id(rs.getInt(1));
				bDto.setP_id(rs.getInt(2));
				bDto.setP_name(rs.getString(3));
				bDto.setP_price(rs.getString(4));
				bDto.setP_quantity(rs.getInt(5));
				bDto.setB_time(rs.getString(6));
				list.add(bDto);
				LOG.info(bDto.toString());
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
		for (BuyingDTO buying : buyingList) {
			if (buying.getP_id() == p_id)
				check = true;
			else
				check = false;
		}
		return check;
	}

	// 발주 대금 전체 계산
	public List<BuyingDTO> buyingprofitAll() {
		String query = "select m.m_name, b.b_time, sum(b.p_price * b.p_quantity), b.buycode from buying as b, member as m where binary(m.m_field) = binary(b.buycode) group by b.buycode, b.b_time;";
		PreparedStatement pStmt = null;
		List<BuyingDTO> list = new ArrayList<BuyingDTO>();
		try {
			pStmt = conn.prepareStatement(query);
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				BuyingDTO bDto = new BuyingDTO();
				bDto.setB_name(rs.getString(1));
				bDto.setB_time(rs.getString(2).substring(0, 16));
				bDto.setTotal(rs.getInt(3));
				bDto.setBuycode(rs.getString(4));
				list.add(bDto);
				LOG.info(bDto.toString());
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

	// 발주 상세 대금 내역
	public List<BuyingDTO> buyingprofit(String b_time, String buycode) {
		String query = "select b_id, p_name, p_price, p_quantity from buying where buycode=? and b_time like ?;";
		PreparedStatement pStmt = null;
		List<BuyingDTO> list = new ArrayList<BuyingDTO>();
		try {
			pStmt = conn.prepareStatement(query);
			pStmt.setString(1, buycode);
			b_time = b_time + "%";
			pStmt.setString(2, b_time);
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				BuyingDTO bDto = new BuyingDTO();
				bDto.setB_id(rs.getInt(1));				
				bDto.setP_name(rs.getString(2));
				bDto.setP_price(rs.getString(3));
				bDto.setP_quantity(rs.getInt(4));
				list.add(bDto);
				LOG.info(bDto.toString());
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
	
	// 발주 대금 월별 계산
		public List<BuyingDTO> selectBuyingprofitAll(String date1, String date2) {
			String query = "select m.m_name, b.b_time, sum(b.p_price * b.p_quantity), b.buycode from buying as b, member as m where binary(m.m_field) = binary(b.buycode) and b.b_time between ? and ? group by b.buycode, b.b_time;";
			PreparedStatement pStmt = null;
			List<BuyingDTO> list = new ArrayList<BuyingDTO>();
			LOG.info(date1 + " " + date2);
			try {
				pStmt = conn.prepareStatement(query);
				pStmt.setString(1, date1);
				pStmt.setString(2, date2);
				ResultSet rs = pStmt.executeQuery();

				while (rs.next()) {
					BuyingDTO bDto = new BuyingDTO();
					bDto.setB_name(rs.getString(1));
					bDto.setB_time(rs.getString(2).substring(0, 16));
					bDto.setTotal(rs.getInt(3));
					bDto.setBuycode(rs.getString(4));
					list.add(bDto);
					LOG.info(bDto.toString());
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
