package com.digiwallet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import com.sun.jersey.core.header.ContentDisposition;
import com.sun.jersey.multipart.FormDataBodyPart;
import com.sun.jersey.multipart.FormDataMultiPart;

@Path("/ImageUpload")
public class ImageUpload {

//	private static final String SUCCESS_RESULT = "success";
//	private static final String FAILURE_RESULT = "failure";
//	
////	@POST
////	@Path("/upload")
////	@Consumes(MediaType.MULTIPART_FORM_DATA)
////	public Response uploadFile(
////		@FormDataParam("file") InputStream uploadedInputStream,
////		@FormDataParam("file") FormDataContentDisposition fileDetail) {
//
//		String uploadedFileLocation = "C://Android Experiments//upload//" + fileDetail.getFileName();
//
//		// save it
//		boolean result = writeToFile(uploadedInputStream, uploadedFileLocation);
//		String output = "File uploaded to : " + uploadedFileLocation;
//
//		return Response.status(200).entity(output).build();
////		JSONObject obj = new JSONObject();
////
////		try{
////			if (result) {
////				obj.put("result", SUCCESS_RESULT);
////			} else {
////				obj.put("result", FAILURE_RESULT);
////			}
////		}
////		catch(Exception e)
////		{
////			e.printStackTrace();
////		}
////		return obj;
////
//		
//	}
//
//	// save uploaded file to new location
//	private boolean writeToFile(InputStream uploadedInputStream,
//		String uploadedFileLocation) {
//
//		try {
//			OutputStream out = new FileOutputStream(new File(
//					uploadedFileLocation));
//			int read = 0;
//			byte[] bytes = new byte[1024];
//
//			out = new FileOutputStream(new File(uploadedFileLocation));
//			while ((read = uploadedInputStream.read(bytes)) != -1) {
//				out.write(bytes, 0, read);
//			}
//			out.flush();
//			out.close();
//			
//
//		} catch (IOException e) {
//
//			e.printStackTrace();
//			return false;
//		}
//		return true;
//
//	}

	private static final String SERVER_UPLOAD_LOCATION_FOLDER = "C://upload/";

	/**
	 * Upload a File
	 */

	@POST
	@Path("/upload")
	@Produces(MediaType.TEXT_PLAIN) 
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(FormDataMultiPart form) {

		System.out.println("Inside ImageUpload");
		 FormDataBodyPart filePart = form.getField("file");
		 
		 ContentDisposition headerOfFilePart =  filePart.getContentDisposition();
		 
		 InputStream fileInputStream = filePart.getValueAs(InputStream.class);
		 
		 String filePath = SERVER_UPLOAD_LOCATION_FOLDER + headerOfFilePart.getFileName();

		// save the file to the server
		saveFile(fileInputStream, filePath);

		String output = "File saved to server location using FormDataMultiPart : " + filePath;

		return Response.status(200).entity(output).build();

	}

	// save uploaded file to a defined location on the server
	private void saveFile(InputStream uploadedInputStream, String serverLocation) {

		try {
			OutputStream outpuStream = new FileOutputStream(new File(
					serverLocation));
			int read = 0;
			byte[] bytes = new byte[1024];

			outpuStream = new FileOutputStream(new File(serverLocation));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				outpuStream.write(bytes, 0, read);
			}
			
			outpuStream.flush();
			outpuStream.close();
			
			uploadedInputStream.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}