package com.callippus.water.erp.service.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class GetUploadedFile {

	public static List<File> get(String uploadDir, String pattern) {
		Path dir = FileSystems.getDefault().getPath(uploadDir);

		List<File> files = new ArrayList<>();
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir,
				pattern)) {
			for (Path entry : stream) {
				files.add(entry.toFile());
			}
			return files;
		} catch (IOException x) {
			throw new RuntimeException(String.format(
					"error reading folder %s: %s", dir, x.getMessage()), x);
		}
	}
}
