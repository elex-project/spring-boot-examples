/*
 * Spring-boot Examples
 *
 * Copyright (c) 2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package kr.pe.elex.examples;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Slf4j
@Service
public class StorageService {
	/*
	테스트용 임시 파일 저장소
	실제로는 파일시스템에 저장하거나 DB에 저장해야 한다.
	 */
	private final Map<String, byte[]> storage = new HashMap<>();

	/**
	 * 저장된 파일 목록
	 * @return
	 */
	Set<String> listFiles(){
		return storage.keySet();
	}

	/**
	 * 파일을 저장
	 * @param file
	 */
	void store(final @NotNull MultipartFile file) {
		try {
			storage.put(file.getOriginalFilename(), file.getBytes());
			log.info("File Uploaded... {}", file.getOriginalFilename());

		} catch (IOException e) {
			log.error("Unable to save a file.", e);
		}
	}

	/**
	 * 저장된 파일을 불러옴
	 * @param filename
	 * @return
	 */
	Resource loadAsResource(final String filename) {
		//return new FileSystemResource(new File(filename));
		return new ByteArrayResource(storage.get(filename));
	}
}
