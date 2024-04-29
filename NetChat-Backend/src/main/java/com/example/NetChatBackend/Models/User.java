package com.example.NetChatBackend.Models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "\"user\"")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String fullname;
	private String email;
	private String profile_pic;
	private String password;
	
	@Override
	public boolean equals( Object o ) {
		if ( this == o ) return true;
		if ( o == null || getClass() != o.getClass() ) return false;
		User user = (User) o;
		return Objects.equals(id, user.id) &&
				Objects.equals(fullname, user.fullname)
				&& Objects.equals(email, user.email)
				&& Objects.equals(profile_pic, user.profile_pic)
				&& Objects.equals(password, user.password);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, fullname, email, profile_pic, password);
	}
}
