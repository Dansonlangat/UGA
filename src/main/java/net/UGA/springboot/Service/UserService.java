package net.UGA.springboot.Service;

import net.UGA.springboot.dto.UserRegistrationDto;
import net.UGA.springboot.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService{
	User save(UserRegistrationDto registrationDto);
}