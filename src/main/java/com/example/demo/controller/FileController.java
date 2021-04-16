package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.FileDB;
import com.example.demo.service.FileStorageService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/fc")
public class FileController {

	@Autowired
	private FileStorageService storageService;

	@RequestMapping(value = "/files/{fileId}", method = RequestMethod.GET)
	@ApiOperation(value = "API for downloading a file using file id")
	public ResponseEntity<byte[]> getFile(@PathVariable(name = "fileId", required = true) String fileId) {
		FileDB fileDB = storageService.getFile(fileId);

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
				.body(fileDB.getData());
	}
}
