package com.wolf.springboot.domain.sys;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wolf.springboot.domain.BaseRepository;


@Repository
public interface UserRoleRepository extends BaseRepository<UserRole, Long> {

	public List<UserRole> findAllByUserId(Long userId);

}
