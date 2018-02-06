package com.digiwallet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;


@Path("/OfferService")
public class OfferService {

	List<String> headerMap = new ArrayList<>();

	@GET
	@Path("/offers/{location}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Offer> getShops(@PathParam("location") String phoneNumber) {

		headerMap.add("Id");
		headerMap.add("Store");
		headerMap.add("Method");
		headerMap.add("OfferSummary");
		headerMap.add("Offers");
		headerMap.add("Address");
		headerMap.add("Location");

		List<Offer> jsonObjects = new ArrayList<>();
		// find the excel
		File directory = new File("c:\\shopDetails");
		File[] files = directory.listFiles();
		for (File file : files) {
			System.out.println("File name : " + file.getName());
				FileInputStream fileToRead = null;
				try {
					fileToRead = new FileInputStream(file);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				HSSFWorkbook workbook;
				try {
					workbook = new HSSFWorkbook(fileToRead);

					// Get first sheet from the workbook
					HSSFSheet sheet = workbook.getSheetAt(0);

					System.out.println("No of rows " + sheet.getLastRowNum());
					for (int i = 1; i < sheet.getLastRowNum(); i++) {
						Map<String, Object> data = new HashMap<String, Object>();
						Row row = sheet.getRow(i);

						System.out.println(row.toString());
						for (int j = 0; j < row.getLastCellNum(); j++) {
							Cell cell = row.getCell(j);
							Object value = null;

							switch (cell.getCellType()) {
							case Cell.CELL_TYPE_NUMERIC:
								value = cell.getNumericCellValue();
								break;
							case Cell.CELL_TYPE_STRING:
								value = cell.getStringCellValue();
								break;

							}
							data.put(headerMap.get(j), value);
						}
				
						Offer tOffer = new Offer(data);
						tOffer.setCategory(file.getName().substring(0,file.getName().indexOf(".")));
						//System.out.println(tOffer.toString());
						jsonObjects.add(tOffer);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		System.out.println(jsonObjects.toString());
		return jsonObjects;
	}

	public static void main(String[] args) {
//		ShopService shopService = new ShopService();
//		List<JSONObject> shopList = shopService.getShops("1");
//		System.out.println("ShopList : " + shopList.toString());
	}
}
