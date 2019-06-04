package com.wolf.springboot.domain.sys;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.wolf.springboot.domain.AbstractDomain;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * <p>Title: User</p>
 * <p>Description: </p>
 * @author wyx
 * @date 2019年4月10日
 */
@Getter
@Setter
@DynamicUpdate
@DynamicInsert
@Entity
@Table(name="SYS_USER")
public class User extends AbstractDomain implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private Integer age;

	@Column(nullable = false)
	private String username;

	@Column(nullable = false)
	private String password;
	

	@Transient
	private Collection<GrantedAuthority> authorities;

	@Override
	public String toString() {
		return "User [name=" + name + ", age=" + age + "]";
	}

	public User() {
		super();
	}

	public User(String name, Integer age) {
		super();
		this.name = name;
		this.age = age;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
