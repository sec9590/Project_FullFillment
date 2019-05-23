package productTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import product.*;

public class CommodityDAOTest {
	private CommodityDAO cDao = new CommodityDAO();
	
	@Test
	public void lastMonth() {
		assertEquals("2019-04", cDao.lastMonth("2019-05"));
	}
	
	@Test
	public void Month() {
		assertEquals("2019-06", cDao.Month("2019-05"));
	}
	
	@Test
	public void checkInsert() {
		assertEquals(true, cDao.checkInsert("2019-05"));
	}	
	
}
