/*
 * Spring-boot Examples
 *
 * Copyright (c) 2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package kr.pe.elex.examples;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
public class MyController {
	@Autowired
	private StorageService storageService;

	@GetMapping(path = {"/"})
	public String index(@NotNull Model model) {
		model.addAttribute("files", storageService.listFiles());
		return "main";
	}

	/**
	 * 파일 업로드
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@PostMapping(path = {"/upload"})
	public String upload(final @RequestParam("file") MultipartFile file,
	                     final @NotNull RedirectAttributes redirectAttributes) {
		storageService.store(file);

		// 업로드 후 새로 고침
		redirectAttributes.addFlashAttribute("message",
				"You successfully uploaded " + file.getOriginalFilename() + "!");
		return "redirect:/";
	}

	/**
	 * 파일 다운로드
	 * @param filename
	 * @return
	 */
	@GetMapping("/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

		final Resource file = storageService.loadAsResource(filename);

		final ContentDisposition contentDisposition = ContentDisposition.attachment()
				.filename(filename)
				.build();

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION,
						contentDisposition.toString())
				.body(file);
	}
}
