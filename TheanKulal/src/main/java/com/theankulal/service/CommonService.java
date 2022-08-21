package com.theankulal.service;

import org.springframework.web.multipart.MultipartFile;

import com.theankulal.dto.RequestDto;
import com.theankulal.dto.ResponseDto;
import com.theankulal.dto.UserDto;

public interface CommonService {

	ResponseDto<?> addSongAndDetails(MultipartFile file, RequestDto requestDto);

	ResponseDto<?> addUser(UserDto userDto);

	ResponseDto<?> getAllSongs();

}
