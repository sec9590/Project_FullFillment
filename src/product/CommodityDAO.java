package product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
				cDto.setC_basic(rs.getInt(1));
				cDto.setP_id(rs.getInt(2));
				cDto.setC_out(rs.getInt(3));		
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
					cDto.setC_basic(rs.getInt(1));
					cDto.setP_id(rs.getInt(2));
					cDto.setC_out(rs.getInt(3));		
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

	public List<CommodityDTO> selectcommodityProduct(String date1, String date2) {
		String query = "select d.p_id from waybill as w , orders_detail as d, orders as o where o.o_id = w.o_id and o.o_id = d.o_id and w.w_time between ? and ? group by d.p_id;";
		PreparedStatement pStmt = null;
		List<CommodityDTO> list = new ArrayList<CommodityDTO>();
	
		try {
			pStmt = conn.prepareStatement(query);
			pStmt.setString(1, date1);
			pStmt.setString(2, date2);
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				CommodityDTO cDto = new CommodityDTO();				
				cDto.setP_id(rs.getInt(1));
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
	
	//재고내역에 재조정산이 되었는지 확인
	public String checkmonth(String time) {
		String query = "select time from commodity where time = ?";
		PreparedStatement pStmt = null;
		CommodityDTO cDto = new CommodityDTO();
		
		try {
			pStmt = conn.prepareStatement(query);
			pStmt.setString(1, time);
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				cDto.setC_time(rs.getString(1));
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
		return cDto.getC_time();
	}	
	
	// 이전달 구하기
	public String lastMonth(String date) {		
		DateFormat df = new SimpleDateFormat("yyyy-MM");
		Calendar cal = Calendar.getInstance( );
		cal.set(Calendar.YEAR,  Integer.parseInt(date.substring(0,4)));
		cal.set(Calendar.MONTH,  Integer.parseInt(date.substring(5,7))-1);
		cal.add ( cal.MONTH, -1 ); //이전달
		String time = df.format(cal.getTime());
		System.out.println("이전달 : " + time);   
		
		return time;
	}
	
	// 전달 기말재고 구하기
	public int checkClose(int p_id, String c_time) {
		String query = "select c_close from commodity where c_time = ? and p_id = ? ";
		PreparedStatement pStmt = null;
		CommodityDTO cDto = new CommodityDTO();
		c_time = lastMonth(c_time);
		
		try {
			pStmt = conn.prepareStatement(query);
			pStmt.setString(1, c_time);
			pStmt.setInt(2, p_id);
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				cDto.setC_close(rs.getInt(1));
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
		return cDto.getC_close();
	}	
		
	// 발주된 상품에서 입고된 상품갯수
	public int selectcommodityInTime(String date1, String date2, int p_id) {
		String query = "select sum(p_quantity) from buying where b_time between ? and ? and p_id = ? group by p_id;";
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

	// 재고 db삽입
	public void insertCommodity(CommodityDTO cDto) {
		String query = "INSERT INTO commodity(p_id, c_basic, c_in, c_out, c_close, c_time) values (?, ?, ?, ?, ?, ?);";
		PreparedStatement pStmt = null;

		try {
			pStmt = conn.prepareStatement(query);

			pStmt.setInt(1, cDto.getP_id());
			pStmt.setInt(2, cDto.getC_basic());
			pStmt.setInt(3, cDto.getC_in());
			pStmt.setInt(4, cDto.getC_out());
			pStmt.setInt(5, cDto.getC_close());
			pStmt.setString(6, cDto.getC_time());

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

	// 재고가 확정 되어있는지 확인
	public boolean checkInsert(String date) {
		if(checkmonth(date)==null) {
			System.out.println("없다.");
			return true;
		}else
			return false;
	}
	
	// 상품이 있는지 
	public List<ProductDTO> IsProduct() {
		String query = "select p_id from product;";
		PreparedStatement pStmt = null;
		List<ProductDTO> list = new ArrayList<ProductDTO>();

		try {
			pStmt = conn.prepareStatement(query);
			
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				ProductDTO pDto = new ProductDTO();
				pDto.setP_id(rs.getInt(1));
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
