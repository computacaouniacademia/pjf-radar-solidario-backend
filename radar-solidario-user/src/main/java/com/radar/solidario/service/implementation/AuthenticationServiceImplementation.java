package com.radar.solidario.service.implementation;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.radar.solidario.dto.authentication.AuthenticationFPDTO;
import com.radar.solidario.dto.authentication.AuthenticationFRDTO;
import com.radar.solidario.dto.authentication.AuthenticationRDTO;
import com.radar.solidario.dto.user.UserPDTO;
import com.radar.solidario.entity.Authentication;
import com.radar.solidario.entity.Role;
import com.radar.solidario.repository.AuthenticationRepository;
import com.radar.solidario.service.AuthenticationService;
import com.radar.solidario.service.processor.AuthenticationProcessor;
import com.radar.solidario.util.Encryptor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AuthenticationServiceImplementation implements AuthenticationService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private AuthenticationProcessor authenticationProcessor;

	@Autowired
	private AuthenticationRepository authenticationRepository;

	@Override
	public AuthenticationFRDTO findById(Long id) {
		log.info("Start - AuthenticationServiceImplementation.findById - Id: {}", id);

		Authentication authentication = this.authenticationProcessor.exists(id);
		AuthenticationFRDTO authenticationFRDTO = this.mapper.map(authentication, AuthenticationFRDTO.class);

		log.info("End - AuthenticationServiceImplementation.findById - AuthenticationFRDTO: {}", authenticationFRDTO);
		return authenticationFRDTO;
	}

	@Override
	public AuthenticationFRDTO findByEmail(String email) {
		log.info("Start - AuthenticationServiceImplementation.findByEmail - Email: {}", email);

		Authentication authentication = this.authenticationProcessor.exists(email);
		AuthenticationFRDTO authenticationFRDTO = this.mapper.map(authentication, AuthenticationFRDTO.class);

		log.info("End - AuthenticationServiceImplementation.findByEmail - AuthenticationFRDTO: {}",
				authenticationFRDTO);
		return authenticationFRDTO;
	}

	@Override
	public AuthenticationRDTO include(UserPDTO userPDTO, List<Role> roles) {
		log.info("Start - AuthenticationServiceImplementation.include - UserPDTO: {} - List<Role>: {}", userPDTO,
				roles);

		Authentication authentication = this.mapper.map(userPDTO, Authentication.class);
		authentication.setRole(roles);
		authentication.setIsLocked(false);

		String encodedPassword = Encryptor.encode(authentication.getPassword());
		authentication.setPassword(encodedPassword);

		authentication = this.authenticationRepository.save(authentication);

		AuthenticationRDTO authenticationRDTO = this.mapper.map(authentication, AuthenticationRDTO.class);
		authenticationRDTO.setPassword(userPDTO.getAuthentication().getPassword());

		log.info("End - AuthenticationServiceImplementation.include - AuthenticationRDTO: {}", authenticationRDTO);
		return authenticationRDTO;
	}

	@Override
	public AuthenticationRDTO edit(AuthenticationFPDTO authenticationFPDTO) {
		log.info("Start - AuthenticationServiceImplementation.edit - AuthenticationFPDTO: {}", authenticationFPDTO);

		this.authenticationProcessor.exists(authenticationFPDTO.getEmail());

		Authentication authentication = this.mapper.map(authenticationFPDTO, Authentication.class);
		authentication = this.authenticationProcessor.merge(authentication);

		String encodedPassword = Encryptor.encode(authentication.getPassword());
		authentication.setPassword(encodedPassword);

		authentication = this.authenticationRepository.save(authentication);
		AuthenticationRDTO authenticationRDTO = this.mapper.map(authentication, AuthenticationRDTO.class);

		log.info("End - AuthenticationServiceImplementation.edit - AuthenticationFRPDTO: {}", authenticationRDTO);
		return authenticationRDTO;
	}
}
