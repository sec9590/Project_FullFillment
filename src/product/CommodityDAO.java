package product;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
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
	
	//지난달 기말재고를 가져와 기초재고로 설정
	public int selectFinal(String date1, String date2, int p_id) {
		String query = "select ? from waybill as w , orders_detail as d, orders as o where o.o_id = w.o_id and o.o_id = d.o_id and w.w_time between ? and ? and d.p_id = ?";
		PreparedStatement pStmt = null;
		CommodityDTO cDto = new CommodityDTO();		
		int basic = 0;
		
		List<CommodityDTO> cDtoList = selectcommodityOutTime(date1, date2); // 출고, 기초재고, 상품id
		for (CommodityDTO coDto : cDtoList) {
			int id = coDto.getP_id();
			coDto.setC_in(selectcommodityInTime(date1, date2, p_id));
			int close = coDto.getC_basic() + coDto.getC_in() - coDto.getC_out();
			coDto.setC_close(close);
			
			if(p_id == id)
				basic = close; //지난달 기말재고
			System.out.println("지난달 상품 : " + p_id + " 기말재고: " +  basic);
		}
		
		try {
			pStmt = conn.prepareStatement(query);
			pStmt.setInt(1, basic);
			pStmt.setString(2, date1);
			pStmt.setString(3, date2);
			pStmt.setInt(4, p_id);
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				cDto.setC_basic(rs.getInt(1));
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
		return cDto.getC_basic();
	}	
	
	//재고내역에 재조정산이 되었는지 확인
	public String checkmonth(String time) {
		String query = "select c_time from commodity where c_time = ?";
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
	
	// 이번달 구하기
	public String Month(String date) {		
		DateFormat df = new SimpleDateFormat("yyyy-MM");
		Calendar cal = Calendar.getInstance( );
		cal.set(Calendar.YEAR,  Integer.parseInt(date.substring(0,4)));
		cal.set(Calendar.MONTH,  Integer.parseInt(date.substring(5,7))-1);
		cal.add ( cal.MONTH, +1 ); //이전달 플러스
		String time = df.format(cal.getTime());
		System.out.println("이전달+1 : " + time);   
		
		return time;
	}
	
	// 전달 기말재고 구하기
	public int checkClose(int p_id, String c_time) {
		String query = "select c_close from commodity where c_time = ? and p_id = ? ";
		PreparedStatement pStmt = null;
		CommodityDTO cDto = new CommodityDTO();
		c_time = lastMonth(c_time);
		System.out.println("전달 기말재고 날짜 : " + c_time);
		
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
	
	// 이번달 기말재고 구하기
	public String checkNow(String c_time) {
			String query = "select c_time from commodity where c_time = ? group by c_time";
			PreparedStatement pStmt = null;
			CommodityDTO cDto = new CommodityDTO();		
			
			try {
				pStmt = conn.prepareStatement(query);
				pStmt.setString(1, c_time);
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
	
	//재고db총
	public List<CommodityDTO> selectCommodityAll() {
		String query = "select c_time, sum(c_basic), sum(c_in), sum(c_out), sum(c_close) from commodity group by c_time;";
		PreparedStatement pStmt = null;
		List<CommodityDTO> list = new ArrayList<CommodityDTO>();

		try {
			pStmt = conn.prepareStatement(query);
			
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				CommodityDTO cDto = new CommodityDTO();
				cDto.setC_time(rs.getString(1));
				cDto.setC_basic(rs.getInt(2));
				cDto.setC_in(rs.getInt(3));
				cDto.setC_out(rs.getInt(4));
				cDto.setC_close(rs.getInt(5));
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
	
	//상세 조회
	public List<CommodityDTO> selectCommodityDetail(String c_time) {
		String query = "select p_id, c_basic, c_in, c_out, c_close from commodity where c_time = ?;";
		PreparedStatement pStmt = null;
		List<CommodityDTO> list = new ArrayList<CommodityDTO>();

		try {
			pStmt = conn.prepareStatement(query);
			pStmt.setString(1, c_time);
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				CommodityDTO cDto = new CommodityDTO();
				cDto.setP_id(rs.getInt(1));
				cDto.setC_basic(rs.getInt(2));
				cDto.setC_in(rs.getInt(3));
				cDto.setC_out(rs.getInt(4));
				cDto.setC_close(rs.getInt(5));
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
	
	//사용x 클라이언에게 내보내는것
	public String prepareDownload(String c_time) {
		List<CommodityDTO> cDtoList = selectCommodityDetail(c_time);
		StringBuffer sb = new StringBuffer();
		
		try {
			String name = "C:\\Temp\\commodity\\commodity.csv";
			FileWriter fw = new FileWriter(name);
			String head = "제품코드,기초재고,입고,출고,기말재고\r\n";
			sb.append(head);
			fw.write(head);			
			for (CommodityDTO cDto : cDtoList) {
				String line = cDto.getP_id() + "," + cDto.getC_basic() + "," + cDto.getC_in() + ","
						+ cDto.getC_out() + "," + cDto.getC_close() + "\r\n";
				sb.append(line);
				fw.write(line);
			}
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	//파일 쓰기 (서버에 저장)
	public void writeCSV(String month) {
		Writer writer = null;
		BufferedWriter bw = null;
		List<CommodityDTO> cDtoList = selectCommodityDetail(month);
		try {
			writer = new FileWriter("c:/Temp/Inventories/" + month + ".csv");
			bw = new BufferedWriter(writer);
			String head = "제품코드,기초재고,입고,출고,기말재고\r\n";
			bw.write(head);		
			for (CommodityDTO cDto: cDtoList) {
				String line = cDto.getP_id() + "," + cDto.getC_basic() + "," + cDto.getC_in() + ","
						+ cDto.getC_out() + "," + cDto.getC_close() + "\r\n";
				bw.write(line);
				
			}
			bw.flush();
			bw.close();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//파일 읽기
	public List<CommodityDTO> readCSV(String month) {
		List<CommodityDTO> iList = new ArrayList<CommodityDTO>();
		Reader reader = null;
		BufferedReader br = null;
		String line = null;
		try {
			reader = new FileReader("c:/Temp/Inventories/" + month + ".csv");
			br = new BufferedReader(reader);
			while ((line = br.readLine()) != null) {				
				CommodityDTO iDto = new CommodityDTO();
				String str[] = line.split(",");
				iDto.setP_id(Integer.parseInt(str[0]));
				iDto.setC_basic(Integer.parseInt(str[1]));
				iDto.setC_in(Integer.parseInt(str[2]));
				iDto.setC_out(Integer.parseInt(str[3]));
				iDto.setC_close(Integer.parseInt(str[4]));
				iList.add(iDto);
			}
			br.close();
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return iList;
	}
}
