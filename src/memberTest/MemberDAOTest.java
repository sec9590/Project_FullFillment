package memberTest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import member.*;

public class MemberDAOTest {
	private MemberDAO mDao = new MemberDAO();
	private MemberDTO mDto = new MemberDTO();
	List<MemberDTO> list = new ArrayList<MemberDTO>();
	
	@Test
	public void verifyIdPassword() {
		int result = mDao.verifyIdPassword("admin", "1234");
		assertEquals(MemberDAO.ID_PASSWORD_MATCH, result);
	}

}
