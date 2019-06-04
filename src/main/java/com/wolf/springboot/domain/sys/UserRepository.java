package com.wolf.springboot.domain.sys;

import org.springframework.stereotype.Repository;

import com.wolf.springboot.domain.BaseRepository;

/**
 * 
 * <p>Title: UserRepository</p>
 * <p>Description: </p>
 * @author wyx
 * @date 2019年4月17日
 */
@Repository
public interface UserRepository extends BaseRepository<User, Long> {

	public User findByUsername(String username);

}
