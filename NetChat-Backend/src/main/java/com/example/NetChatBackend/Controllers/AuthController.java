package com.example.NetChatBackend.Controllers;


import com.example.NetChatBackend.Config.TokenProvider;
import com.example.NetChatBackend.Exceptions.UserException;
import com.example.NetChatBackend.Models.User;
import com.example.NetChatBackend.Repositories.UserRepository;
import com.example.NetChatBackend.Requests.LoginRequest;
import com.example.NetChatBackend.Response.AuthResponse;
import com.example.NetChatBackend.Services.CustomUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/auth")
public class AuthController {
	
	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	private TokenProvider tokenProvider;
	private CustomUserService customUserService;
	
	public AuthController( UserRepository userRepository, PasswordEncoder passwordEncoder, TokenProvider tokenProvider, CustomUserService customUserService ) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.tokenProvider = tokenProvider;
		this.customUserService = customUserService;
	}
	
	
	@PostMapping("/signup")
	public ResponseEntity< AuthResponse > createUserHandler( @RequestBody User user) throws UserException {
		String email = user.getEmail();
		String fullname = user.getFullname();
		String password = user.getPassword();
		
		User isUser = userRepository.findByEmail(email);
		
		if(user == null){
			throw new UserException("Email is used with another account : "  + email);
		}
		
		User createdUser = new User();
		createdUser.setFullname(fullname);
		createdUser.setEmail(email);
		createdUser.setPassword(passwordEncoder.encode(password));
		
		userRepository.save(createdUser);
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String jwt = tokenProvider.generateToken(authentication);
		AuthResponse res = new AuthResponse(jwt,true);
		
		return new ResponseEntity<AuthResponse>(res, HttpStatus.ACCEPTED);
	
	}
	
	
	@PostMapping("signin")
	public ResponseEntity<AuthResponse> LoginHandler(@RequestBody LoginRequest req){
		String email = req.getEmail();
		String password = req.getPassword();
		
		Authentication authentication = authenticate(email,password);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String jwt = tokenProvider.generateToken(authentication);
		AuthResponse res = new AuthResponse(jwt,true);
		
		return new ResponseEntity<AuthResponse>(res, HttpStatus.ACCEPTED);
	}
	
	public Authentication authenticate(String username, String password){
		UserDetails userDetails  = customUserService.loadUserByUsername(username);
		
		if(userDetails == null){
			throw new BadCredentialsException("Invalid Username ");
		}
		
		if(!passwordEncoder.matches(password, userDetails.getPassword())){
			throw new BadCredentialsException("Invalid Password for Username");
		}
		
		return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
	}
}
