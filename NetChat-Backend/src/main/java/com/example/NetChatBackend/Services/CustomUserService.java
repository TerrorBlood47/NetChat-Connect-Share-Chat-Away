package com.example.NetChatBackend.Services;

import com.example.NetChatBackend.Models.User;
import com.example.NetChatBackend.Repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserService  implements UserDetailsService {
	
	private UserRepository userRepository;
	
	public CustomUserService( UserRepository userRepository ) {
		this.userRepository = userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername( String username ) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username);
		
		if (user == null){
			throw new UsernameNotFoundException("User not found with username : " + username);
		}
		
		List< GrantedAuthority > authorities = new ArrayList<>();
		
		return new org.springframework.security.core.userdetails.User(user.getEmail()
				,user.getPassword(),authorities);
	}
}