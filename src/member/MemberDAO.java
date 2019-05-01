package member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {

	public static final int ID_PASSWORD_MATCH = 1;
	public static final int ID_DOES_NOT_EXIST = 2;
	public static final int PASSWORD_IS_WRONG = 3;
	public static final int DATABASE_ERROR = -1;
	
	private Connection conn;
    private static final String USERNAME = "javauser";
    private static final String PASSWORD = "javauser";
    private static final String URL = "jdbc:mysql://localhost:3306/yellow?verifyServerCertificate=false&useSSL=false";
    
    public MemberDAO() {
    	try {
			Class.forName("com.mysql.jdbc.Driver");	
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    	} catch (Exception ex) {
			ex.printStackTrace();
		}
    }
    
    public void initPassword() {					// 비밀번호 암호화
    	List<MemberDTO> mList = selectAll();
    	for (MemberDTO member: mList) {
    		String id = member.getM_id();
    		String plainPassword = member.getM_password();
    		String hashedPassword = BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    		updatePassword(id, hashedPassword);
    	}
    }
    
    public void updatePassword(String m_id, String hashed) {			//비밀번호 암호화
    	String query = "update member set hashed=? where m_id=?;";
    	PreparedStatement pStmt = null;
    	try {
			pStmt = conn.prepareStatement(query);
			pStmt.setString(1, hashed);
			pStmt.setString(2, m_id);
			
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
    
    public List<MemberDTO> selectAll() {
    	String query = "select * from member order by m_id desc;";
    	PreparedStatement pStmt = null;
    	List<MemberDTO> memberList = new ArrayList<MemberDTO>();
    	try {
			pStmt = conn.prepareStatement(query);
			ResultSet rs = pStmt.executeQuery();
			
			while (rs.next()) {
				MemberDTO member = new MemberDTO();
				member.setM_id(rs.getString(1));
				member.setM_password(rs.getString(2));
				member.setM_name(rs.getString(3));
				member.setM_tel(rs.getString(4));
				member.setM_field(rs.getString(5));
				memberList.add(member);
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
    	return memberList;
    }
    
    public int verifyIdPassword(String m_id, String password) {
		String query = "select hashed from member where m_id=?;";
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		String hashedPassword = "";
		try {
			pStmt = conn.prepareStatement(query);
			pStmt.setString(1, m_id);
			rs = pStmt.executeQuery();
			while (rs.next()) {	
				hashedPassword = rs.getString(1);
				if (BCrypt.checkpw(password, hashedPassword))
					return ID_PASSWORD_MATCH;
				else
					return PASSWORD_IS_WRONG;
			}
			return ID_DOES_NOT_EXIST;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				if (pStmt != null && !pStmt.isClosed()) 
					pStmt.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return DATABASE_ERROR;
	}
    
    public MemberDTO selectOne(String query) {
    	PreparedStatement pStmt = null;
    	MemberDTO member = new MemberDTO();
    	try {
			pStmt = conn.prepareStatement(query);
			ResultSet rs = pStmt.executeQuery();
			
			while (rs.next()) {
				member.setM_id(rs.getString(1));
				member.setM_password(rs.getString(2));
				member.setM_name(rs.getString(3));
				member.setM_tel(rs.getString(4));
				member.setM_field(rs.getString(5));
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
    	return member;
    }
    
    public MemberDTO searchById(String memberId) {
    	String sql = "select * from member where m_id=" + memberId + ";";
    	MemberDTO mDto = selectOne(sql);
    	return mDto;
    }
    
    public void insertMember(MemberDTO member) {
    	String query = "insert into member(m_id, m_password, m_name, m_tel, m_field, hashed) values (?, ?, ?, ?, ?, ?);";
    	PreparedStatement pStmt = null;
    	try {
    		String hashedPassword = BCrypt.hashpw(member.getM_password(), BCrypt.gensalt());
			pStmt = conn.prepareStatement(query);
			pStmt.setString(1, member.getM_id());
			pStmt.setString(2, "*");
			pStmt.setString(3, member.getM_name());
			pStmt.setString(4, member.getM_tel());
			pStmt.setString(5, member.getM_field());
			pStmt.setString(6, hashedPassword);
			
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
