package com.theankulal.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.theankulal.dto.RequestDto;
import com.theankulal.dto.ResponseDto;
import com.theankulal.dto.SongsDetailsDto;
import com.theankulal.dto.UserDto;
import com.theankulal.entity.SongsDetails;
import com.theankulal.entity.UserDetails;
import com.theankulal.repository.SongsDetailsRepository;
import com.theankulal.repository.UserDetailsRepository;
import com.theankulal.service.CommonService;
import com.theankulal.util.CommonConditions;
import com.theankulal.util.CommonConstant;

@Service
public class CommonServiceImpl implements CommonService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	UserDetailsRepository userDetailsRepository;
	
	@Resource
	SongsDetailsRepository songsRepository;
	
	@Resource
	CommonConditions commonConditions;
	
	@Autowired
	CommonConstant commonConstant;

	@Override
	public ResponseDto<?> addSongAndDetails(MultipartFile file, RequestDto requestDto) {
		logger.info("Enter into CommonServiceImpl of service addSongAndDetails");
		ResponseDto<?> responseDto = new ResponseDto<>();
		try {
			if(null != file && commonConditions.checkNulAndEmpty(requestDto.getUserId())) {
				UserDetails userDetails = userDetailsRepository.findById(Long.valueOf(requestDto.getUserId())).get();
				SongsDetails songDetails = new SongsDetails();
				songDetails.setAudioByte(file.getBytes());
				songDetails.setSongName(file.getOriginalFilename());
				songDetails.setUserDetails(userDetails);
				songDetails.setSingerName(requestDto.getSingerName());
				songDetails.setMovieName(requestDto.getMovieName());
				songDetails.setMusicDriector(requestDto.getMusicDriector());
				songDetails.setSongType(requestDto.getTypeOfSong());
				songsRepository.save(songDetails);
				responseDto.setStatus(CommonConstant.SUCCESS);
				responseDto.setMessage("Saved successfully");
			}else {
				responseDto.setStatus(CommonConstant.ERROR);
				responseDto.setMessage("Error Uploading File");
			}
		} catch (Exception e) {
			logger.error("Error in CommonServiceImpl of service addSongAndDetails ",e);
			responseDto.setErrorMessage(e.getMessage());
			responseDto.setStatus(CommonConstant.ERROR);
		}
		return responseDto;
	}

	@Override
	public ResponseDto<?> addUser(UserDto userDto) {
		logger.info("Enter into CommonServiceImpl of service addUser");
		ResponseDto<?> responseDto = new ResponseDto<>();
		try {
			if(commonConditions.checkNulAndEmpty(userDto.getId())) {
				UserDetails userDetails = userDetailsRepository.findById(Long.valueOf(userDto.getId())).get();
				userDetails.setUserName(userDto.getUserName());
				userDetails.setPassword(userDto.getPassword());
				userDetails.setPhoneNo(userDto.getPhoneNo());
				userDetailsRepository.save(userDetails);
				responseDto.setStatus(CommonConstant.SUCCESS);
				responseDto.setMessage("Updated successfully");
			}else {
				UserDetails userDetails = new UserDetails();
				userDetails.setUserName(userDto.getUserName());
				userDetails.setPassword(userDto.getPassword());
				userDetails.setPhoneNo(userDto.getPhoneNo());
				userDetailsRepository.save(userDetails);
				responseDto.setStatus(CommonConstant.SUCCESS);
				responseDto.setMessage("Added successfully");
			}
		} catch (Exception e) {
			responseDto.setStatus(CommonConstant.ERROR);
			responseDto.setMessage(e.getMessage());
			logger.error("Error in CommonServiceImpl of service addUser ",e);
		}
		logger.info("Exit from CommonServiceImpl of service addUser");
		return responseDto;
	}

	@Override
	public ResponseDto<?> getAllSongs() {
		logger.info("Enter into CommonServiceImpl of service getAllSongs");
		ResponseDto<List<SongsDetailsDto>> responseDto = new ResponseDto<>();
		List<SongsDetailsDto> songsDetailsDtoList = new ArrayList<>();
		try {
			List<SongsDetails> songsDetailsList = songsRepository.findAll();
			songsDetailsList.forEach(songs->{
				SongsDetailsDto songsDetails = new SongsDetailsDto();
				BeanUtils.copyProperties(songs, songsDetails);
				songsDetails.setTypeOfSong(songs.getSongType());
				songsDetails.setSongByte(songs.getAudioByte());
				songsDetailsDtoList.add(songsDetails);
			});
			responseDto.setResponseData(songsDetailsDtoList);
			responseDto.setStatus(CommonConstant.SUCCESS);
		} catch (Exception e) {
			logger.error("Error in CommonServiceImpl of service getAllSongs ",e);
			responseDto.setStatus(CommonConstant.ERROR);
			responseDto.setMessage(e.getMessage());
		}
		logger.info("Exit from CommonServiceImpl of service getAllSongs");
		return responseDto;
	}

}
