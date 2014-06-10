package com.focusdays2014.inventory_core;

//Import required java libraries
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

public class UploadServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean isMultipart;
	private String filePath;
	private int maxFileSize = 5000 * 1024;
	private int maxMemSize = 400 * 1024;

	public void init() {
		// Get the file location where it would be stored.
		filePath = System.getProperty("java.io.tmpdir")+File.separator + "image-uploads"+File.separator;
		new File(filePath).mkdirs();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {
		// Check that we have a file upload request
		isMultipart = ServletFileUpload.isMultipartContent(request);
		response.setContentType("text/html");
		java.io.PrintWriter out = response.getWriter();
		if (!isMultipart) {
			out.println("error");
			return;
		}
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// maximum size that will be stored in memory
		factory.setSizeThreshold(maxMemSize);
		// Location to save data that is larger than maxMemSize.
		File large = new File(filePath+"large");
		large.mkdirs();
		factory.setRepository(large);

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);
		// maximum file size to be uploaded.
		upload.setSizeMax(maxFileSize);

		try {
			// Parse the request to get file items.
			List fileItems = upload.parseRequest(request);

			// Process the uploaded file items
			Iterator i = fileItems.iterator();

			while (i.hasNext()) {
				FileItem fi = (FileItem) i.next();
				if (!fi.isFormField()) {
					FileItemWrapper fileItem = new FileItemWrapper(fi);
					String name = UUID.randomUUID()+"."+fileItem.getExtension();
					fi.write(new File(filePath+name));
					out.println(request.getRequestURL()+"?name="+name);
				}
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {
		File file = new File(filePath + request.getParameter("name"));
		FileInputStream openInputStream = FileUtils.openInputStream(file);
		try {
			String mimeType= URLConnection.guessContentTypeFromName(request.getParameter("name"));
			byte[] bytes = new byte[4096];
			int total = 0;
			int len = openInputStream.read(bytes);
			OutputStream out = new BufferedOutputStream(response.getOutputStream(), 4096);
			try {
				while (len > 0) {
					out.write(bytes, 0, len);
					total += len;
					len = openInputStream.read(bytes);
				}
				response.setContentType(mimeType);
				response.setContentLength(total);
			} finally {
				out.close();
			}
			
		} finally {
			openInputStream.close();
		}
		;
	}
}