package pl.task.jsonAlert.jsonReadPackage;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import pl.task.jsonAlert.Record;
import pl.task.jsonAlert.databaseConn.CreateTable;

public class ReadJson extends SuperReadJson {

	public static void main(String[] args) {
		CreateTable.createTable();
		BufferedReader bri = null;
		String filePath = null;
		filePath = readFilePath(bri, filePath);
		readFile(filePath);

	}

	protected static String readFilePath(BufferedReader bri, String filePath) {
		try {
			bri = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Enter file path : ");
			String input = bri.readLine();
			filePath = input;
			System.out.println("input : " + input);
			System.out.println("-----------\n");

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bri != null) {
				try {
					bri.close();
				} catch (IOException e) {
					System.err.println("Error: " + e.getMessage());
				}
			}
		}
		return filePath;
	}

	protected static void readFile(String filePath) {
		List<Record> recordList = new ArrayList<Record>();
		;
		try {
			System.out.println("checking  " + filePath + "  ......");
			FileInputStream fstream = new FileInputStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			Gson gson = new Gson();
			insertToList(recordList, br, gson);
			compareTimestamp(recordList);
			br.close();
			System.exit(0);
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}
}
