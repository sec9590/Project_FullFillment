package productTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import product.*;

public class OrdersDAOTest {
	OrdersDAO oDao = new OrdersDAO();

	@Test
	public void getCount() {
		assertEquals(72, oDao.getCount());
	}	
		
	@Test
	public void selectOrderId() {
		assertEquals(10105, oDao.selectOrderId("라이언"));
	}
	
	@Test
	public void selectQuentity() {
		assertEquals(12, oDao.selectQuentity(15));
	}
	
	@Test
	public void selectAddress() {
		assertEquals("경상북도 구미시 산호대로 142-46", oDao.selectAddress(10105));
	}
	
	@Test
	public void getorderCount() {
		assertEquals(10, oDao.getorderCount("2019-03"));
	}
	
	@Test
	public void IsWaybill() {
		assertEquals(true, oDao.IsWaybill(10105));
	}
}
