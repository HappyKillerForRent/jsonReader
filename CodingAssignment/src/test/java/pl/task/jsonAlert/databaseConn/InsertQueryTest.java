package pl.task.jsonAlert.databaseConn;

import org.junit.Test;

public class InsertQueryTest {

	@Test
	public final void testInsert() {
		String id = "abc";
		long diff = 0;
		String type = "abc";
		Boolean alert = false;
		InsertQuery.insert(id, diff, type, alert);
	}

	@Test
	public final void testInsertNull() {
		String id = null;
		long diff = 0;
		String type = null;
		Boolean alert = null;
		InsertQuery.insert(id, diff, type, alert);
	}

}
