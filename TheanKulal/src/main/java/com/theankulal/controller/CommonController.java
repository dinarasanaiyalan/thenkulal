package com.theankulal.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.theankulal.dto.RequestDto;
import com.theankulal.dto.ResponseDto;
import com.theankulal.dto.UserDto;
import com.theankulal.service.CommonService;

@RestController
public class CommonController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	CommonService commonService;
	
	@PostMapping(value = "/addSongAndDetails")
	public ResponseEntity<?> addSongAndDetails(@RequestPart(name = "file") MultipartFile file, @RequestPart(name = "requestDto") String requestDto ) throws JsonMappingException, JsonProcessingException{
		logger.info("Enter into CommonController---------->");
		RequestDto request = new ObjectMapper().readValue(requestDto, RequestDto.class);
		ResponseDto<?> responseDto = commonService.addSongAndDetails(file,request);
		logger.info("Exit from CommonController--------->");
		return new ResponseEntity<ResponseDto<?>>(responseDto,HttpStatus.OK);
	}
	
	@PostMapping(value = "/addUser")
	public ResponseEntity<?> addUser(@RequestBody UserDto userDto){
		logger.info("Enter into CommonController----------->");
		ResponseDto<?> responseDto = commonService.addUser(userDto);
		logger.info("Exit from CommonController--------->");
		return new ResponseEntity<ResponseDto<?>>(responseDto,HttpStatus.OK);
	}
	
	@PostMapping(value = "/getAllSongs")
	public ResponseEntity<?> getAllSongs(){
		logger.info("Enter into CommonController----------->");
		ResponseDto<?> responseDto = commonService.getAllSongs();
		logger.info("Exit from CommonController----------->");
		return new ResponseEntity<ResponseDto<?>>(responseDto,HttpStatus.OK);
	}

}
