package waybillTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import waybill.WaybillDAO;

public class WaybillDAOTest {
	WaybillDAO wDao = new WaybillDAO();
	
	@Test
	public void selectQuentity() {
		assertEquals(69, wDao.getCount());
	}	
	
	@Test
	public void selectwaybill() {
		assertEquals(true, wDao.selectwaybill(10105));
	}	
	
	@Test
	public void selectnowaybill() {
		assertEquals(false, wDao.selectnowaybill(10105));
	}	
}
