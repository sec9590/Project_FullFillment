package productTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import product.ProductDAO;

public class ProductDAOTest {
	ProductDAO pDao = new ProductDAO();
	
	@Test
	public void selectQuentity() {
		assertEquals(12, pDao.selectQuentity(15));
	}	
	
	@Test
	public void currentTime() {
		assertEquals("2019-05-23", pDao.currentTime());
	}
	
	@Test
	public void nextday() {
		assertEquals("2019-05-24 10:00", pDao.nextday());
	}	
	
	@Test
	public void isBuying() {
		assertEquals(false, pDao.isBuying(6));
	}		
}

