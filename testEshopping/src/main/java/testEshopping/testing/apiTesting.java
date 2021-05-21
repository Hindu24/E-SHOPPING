package testEshopping.testing;
import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Test;

import testEshopping.functions.api;
public class apiTesting {
	public api func = new api();
	
	
	@SuppressWarnings("static-access")
	@Test
	public void bulkOrderedItems() {
		assertEquals(3, func.bulkOrderedItems());
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void total() throws ClassNotFoundException, SQLException {
		assertEquals(2580, func.total());
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void totalAfterOffer() throws ClassNotFoundException, SQLException {
		assertEquals(516.0, func.offerOnTotal(), 0);
	}
}
