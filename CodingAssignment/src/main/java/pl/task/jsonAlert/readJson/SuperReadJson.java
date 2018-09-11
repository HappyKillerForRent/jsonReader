package pl.task.jsonAlert.readJson;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

import pl.task.jsonAlert.Record;
import pl.task.jsonAlert.databaseConn.InsertQuery;

public class SuperReadJson {

	private String id;
	private String state;
	private String type;
	private String host;
	private Long timestamp;

	protected static void compareTimestamp(List<Record> recordList) {
		for (int i = 0; i < recordList.size() - 1; i++)
			for (int k = i + 1; k < recordList.size(); k++) {
				if (recordList.get(i).getId().equals(recordList.get(k).getId())) {
					System.out.println(recordList.get(i).getId() + " and " + recordList.get(k).getId() + " are pairs");
					long milliseconds1 = recordList.get(i).getTimestamp();
					long milliseconds2 = recordList.get(k).getTimestamp();
					long diff = 0;
					if (milliseconds1 > milliseconds2)
						diff = milliseconds1 - milliseconds2;
					if (milliseconds1 < milliseconds2)
						diff = milliseconds2 - milliseconds1;
					insertToTable(recordList, i, diff);
				}

				if (recordList.get(i).getId().equals(recordList.get(k).getId()))
					System.out.println(
							recordList.get(i).getId() + " and " + recordList.get(k).getId() + " are not pairs");
			}
	}

	protected static void insertToList(List<Record> recordList, BufferedReader br, Gson gson) throws IOException {
		String strLine;
		while ((strLine = br.readLine()) != null) {
			ReadJson thing = gson.fromJson(strLine, ReadJson.class);
			Record record = new Record();
			record.setId(thing.getId());
			record.setHost(thing.getHost());
			record.setState(thing.getState());
			record.setTimestamp(thing.getTimestamp());
			record.setType(thing.getType());
			recordList.add(record);

		}
	}

	private static void insertToTable(List<Record> recordList, int i, long diff) {
		if (diff >= 4) {
			System.err.println(diff + " ALERT ");
			InsertQuery.insert(recordList.get(i).getId(), diff, recordList.get(i).getType(), true);
		}
		if (diff < 4) {
			InsertQuery.insert(recordList.get(i).getId(), diff, recordList.get(i).getType(), false);
			System.out.println(diff);
		}
	}

	public SuperReadJson() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

}