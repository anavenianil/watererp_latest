package com.callippus.water.erp.web.rest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerMapping;

import com.callippus.water.erp.service.util.GetUploadedFile;
import com.callippus.water.erp.service.util.MD5Util;

/**
 * Controller for view and managing Log Level at runtime.
 */
@RestController
@RequestMapping("/api")
public class UploadDownloadResource implements ResourceLoaderAware {
	private ResourceLoader resourceLoader;

	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/upload")
	public void upload(HttpServletRequest request,
			@RequestParam("file") MultipartFile file,
			@RequestParam("objectType") String objectType) throws IOException {

		byte[] bytes;

		if (!file.isEmpty()) {
			bytes = file.getBytes();
			String tempDir = request.getServletContext().getRealPath(
					"/WEB-INF/assets/tempDir");
			
			File f = new File(tempDir);
			
			if(!f.isDirectory())
			{
				f.mkdirs();
			}

			FileOutputStream fos = new FileOutputStream(tempDir
					+ File.separator + "upload_" + request.getSession().getId()
					+ "_" + objectType + "_" + file.getOriginalFilename());
			fos.write(bytes);
			fos.close();
			System.out.println(String.format("Written to: %s", tempDir
					+ File.separator + "upload_" + request.getSession().getId()
					+ "_" + objectType + "_" + file.getOriginalFilename()));
		}
	}

	@RequestMapping(value = "/download/**", method = RequestMethod.GET)
	public HttpEntity<byte[]> download(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String assetPath = ((String) request
				.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE))
				.substring("/api/download/".length());

		String saveDir = request.getServletContext().getRealPath(
				"/WEB-INF/assets/saveDir");
		
		File f = new File(saveDir);
		
		if(!f.isDirectory())
		{
			f.mkdirs();
		}
				
		Resource resource = resourceLoader
				.getResource("WEB-INF/assets/saveDir/" + assetPath);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		response.setHeader("Content-Disposition", "attachment; filename="
				+ resource.getFile().getName());

		return new HttpEntity<byte[]>(FileCopyUtils.copyToByteArray(resource
				.getFile()), headers);
	}

	public static void setValues(Object o, HashMap<String, String> hm,
			HttpServletRequest request, Long id) throws Exception {

		String rootDir = request.getServletContext().getRealPath("/");
		String tempDir = rootDir + "/WEB-INF/assets/tempDir";
		String saveDir = rootDir + "/WEB-INF/assets/saveDir";

		String pattern = "upload_" + request.getSession().getId()
				+ "_waterErp*";
		Calendar calendar = Calendar.getInstance();

		for (File file : GetUploadedFile.get(tempDir, pattern)) {
			String name[] = file.getName().split("_");
			String original = name[name.length - 1];
			String dest = id
					+ "_"
					+ MD5Util.getMD5(calendar.getTimeInMillis() + "_"
							+ file.getName()) + "_" + original;
			file.renameTo(new File(saveDir + File.separator + dest));

			Class c = o.getClass();
			Class[] paramTypes = new Class[1];
			paramTypes[0] = String.class;
			Iterator<Map.Entry<String, String>> it = hm.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String, String> pair = (Map.Entry<String, String>) it
						.next();
				if (file.getName().contains(pair.getKey())==false) {
					Method m = c.getDeclaredMethod(pair.getValue(), paramTypes);
					m.invoke(o, "/api/download/" + dest);
					break;
				}
			}

		}
	}
}
