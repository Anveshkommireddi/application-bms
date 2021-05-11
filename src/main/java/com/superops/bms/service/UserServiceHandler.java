package com.superops.bms.service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.superops.bms.entity.UserEntity;
import com.superops.bms.model.AddUserRequest;
import com.superops.bms.model.AddUserResponse;
import com.superops.bms.model.CommonResponse;
import com.superops.bms.model.TokenResponse;
import com.superops.bms.model.UserRequest;
import com.superops.bms.repository.UserRepository;
import com.superops.bms.util.UtilityInterface;

@Service
@Transactional(rollbackOn = Exception.class)
public class UserServiceHandler {

	private UserRepository userRepository;

	public UserServiceHandler(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public TokenResponse generateToken(UserRequest request) {

		Optional<UserEntity> userEntityOptional = userRepository.findByUserIdAndPassWord(request.getUserId(),
				request.getPassword());

		TokenResponse response = new TokenResponse();
		if (userEntityOptional.isEmpty()) {
			UtilityInterface.prepareAndAddError(response, "No User Exists with given Credentials", "/generateToken");
			return response;
		}

		UserEntity userEntity = userEntityOptional.get();
		String token = UUID.randomUUID().toString();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.HOUR_OF_DAY, 1);
		Date tokenUpDtTIme = cal.getTime();
		userEntity.setToken(token);
		userEntity.setTokenUpdtTime(tokenUpDtTIme);
		userRepository.save(userEntity);

		response.setToken(token);
		return response;
	}

	public CommonResponse addUser(AddUserRequest request) {

		Optional<UserEntity> userEntityOptional = userRepository.findById(Long.parseLong(request.getMobileNo()));
		if (userEntityOptional.isEmpty()) {
			CommonResponse response = new CommonResponse();
			UtilityInterface.prepareAndAddError(response, "User already exist with the same telephone Number", "/addUser");
			return response;
		}

		UserEntity userEntity = new UserEntity(Long.parseLong(request.getMobileNo()), request.getUserId(),
				request.getMailId(), request.getPassWord(), request.getRole());
		userRepository.save(userEntity);
		return new AddUserResponse(request.getMobileNo(), request.getUserId(), request.getMailId(), request.getRole());
	}

}
