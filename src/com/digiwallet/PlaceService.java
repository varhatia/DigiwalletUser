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


@Path("/PlaceService")
public class PlaceService {

	List<String> headerMap = new ArrayList<>();

	@GET
	@Path("/places/{type}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Place> getPlaces(@PathParam("type") String type) {

		headerMap.add("Id");
		headerMap.add("Store");
		headerMap.add("Method");
		headerMap.add("OfferSummary");
		headerMap.add("Offers");
		headerMap.add("Address");
		headerMap.add("Location");

		List<Place> jsonObjects = new ArrayList<>();
		// find the excel
		File directory = new File("c:\\shopDetails");
		File[] files = directory.listFiles();
		for (File file : files) {
			System.out.println("Finding files");
			System.out.println("File name : " + file.getName());
			System.out.println("File to find name : " + type);
			if (file.getName().equals(type + ".xls")) {
				FileInputStream fileToRead = null;
				try {
					fileToRead = new FileInputStream(file);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println("Found the file");
				// Get the workbook instance for XLS file
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
								System.out.print(cell.getNumericCellValue()
										+ "\t\t");
								value = cell.getNumericCellValue();
								break;
							case Cell.CELL_TYPE_STRING:
								System.out.print(cell.getStringCellValue()
										+ "\t\t");
								value = cell.getStringCellValue();
								break;

							}
							data.put(headerMap.get(j), value);
						}
				
						Place tShop = new Place(data);
						jsonObjects.add(tShop);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println(jsonObjects.toString());
		return jsonObjects;
	}

	@GET
	@Path("/place/{type}/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Place> getPlace(@PathParam("type") String type,
			@PathParam("id") String shopId) {

		headerMap.add("Id");
		headerMap.add("Store");
		headerMap.add("Method");
		headerMap.add("OfferSummary");
		headerMap.add("Offers");
		headerMap.add("Address");
		headerMap.add("Location");
		List<Place> jsonObjects = new ArrayList<>();
		// find the excel
		File directory = new File("c:\\shopDetails");
		File[] files = directory.listFiles();
		FileInputStream fileToRead = null;
		for (File file : files) {
			System.out.println("Finding files for getPlace");
			System.out.println("File name : " + file.getName());
			System.out.println("File to find name : " + type);
			if (file.getName().equals(type + ".xls")) {
				try {
					fileToRead = new FileInputStream(file);
					break;
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println("Found the file");
				// Get the workbook instance for XLS file
			}
		}
		
		if(fileToRead != null)
		{
			HSSFWorkbook workbook;
				try {
					workbook = new HSSFWorkbook(fileToRead);

					// Get first sheet from the workbook
					HSSFSheet sheet = workbook.getSheetAt(0);
					System.out.println("No of rows " + sheet.getLastRowNum());
					Map<String, Object> data = new HashMap<String, Object>();
					
					for (int i = 1; i < sheet.getLastRowNum(); i++) {
						Row row = sheet.getRow(i);
						if(row.getCell(0).getNumericCellValue() == Double.valueOf(shopId))
						{	

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
						}
					}
					System.out.println(data);
					if(data!=null)
					{
						Place tShop = new Place(data);
						jsonObjects.add(tShop);
					}
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		return jsonObjects;
	}

	public static void main(String[] args) {
//		ShopService shopService = new ShopService();
//		List<JSONObject> shopList = shopService.getShops("1");
//		System.out.println("ShopList : " + shopList.toString());
	}
}
