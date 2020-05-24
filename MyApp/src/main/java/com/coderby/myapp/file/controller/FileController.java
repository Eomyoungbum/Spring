package com.coderby.myapp.file.controller;

import java.io.IOException;
import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.coderby.myapp.file.model.FileVO;
import com.coderby.myapp.file.service.IFileService;

@Controller
public class FileController {

	@Autowired
	IFileService fileService;

	@RequestMapping("/file")
	public ModelAndView fileHome() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/file/index");
		return mav;
	}

	@GetMapping("/file/new")
	public String uploadFile(Model model) {
		model.addAttribute("dir","/");
		return "/file/form";
	}

	@PostMapping("/file/new")
	public String uploadFile(@RequestParam(value="dir", required=false, defaultValue="/") String dir,
			@RequestParam MultipartFile file, RedirectAttributes redirectAttrs) {
		try {
			if(file!=null && !file.isEmpty()) {
				FileVO newFile = new FileVO();
				newFile.setDirectoryName(dir);
				newFile.setFileName(file.getOriginalFilename());
				newFile.setFileSize(file.getSize());
				newFile.setFileContentType(file.getContentType());
				newFile.setFileData(file.getBytes());
				fileService.uploadFile(newFile);
			}
		}catch(IOException e) {
			e.printStackTrace();
			redirectAttrs.addFlashAttribute("message", e.getMessage());
		}
		return "redirect:/file/list";
	}

	@RequestMapping("/file/gallery")
	public String getImageList(
			@RequestParam(value="dir", required=false, defaultValue="/images")String dir,
			Model model) {
		model.addAttribute("fileList", fileService.getImageList(dir));
		return "/file/list";
	}
	
	@RequestMapping("/file/list")
	public String getFileList(Model model) {
		model.addAttribute("fileList",fileService.getAllFileList());
		return "/file/list";
	}
	
	@RequestMapping("/file/list/{dir}")
	public String getFileListByDir(@PathVariable String dir, Model model) {
		model.addAttribute("fileList", fileService.getFileList("/"+dir));
		return "/file/list";
	}
	
	@GetMapping({"/img/{fileId}","/pds/{fileId}"})
	public ResponseEntity<byte[]> getImageFile(@PathVariable int fileId){
		FileVO file = fileService.getFile(fileId);
		final HttpHeaders headers = new HttpHeaders();
		if(file != null) {
			String[] mtypes = file.getFileContentType().split("/");
			headers.setContentType(new MediaType(mtypes[0], mtypes[1]));
			headers.setContentDispositionFormData("attachment", file.getFileName(),
					Charset.forName("UTF-8"));
			headers.setContentLength(file.getFileSize());
			return new ResponseEntity<byte[]>(file.getFileData(),headers,HttpStatus.OK);
		}else {
			return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping("/file/delete/{fileId}")
	public String deleteFile(@PathVariable int fileId) {
		fileService.deleteFile(fileId);
		return "redirect:/file/list";
	}
	
	@RequestMapping("/file/updateDir")
	public String updateDirectory(int[] fileIds, String directoryName) {
		fileService.updateDirectory(fileIds, directoryName);
		return "redirect:/file/list";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
