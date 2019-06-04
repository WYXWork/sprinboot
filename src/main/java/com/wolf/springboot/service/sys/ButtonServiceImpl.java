package com.wolf.springboot.service.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wolf.springboot.domain.sys.Button;
import com.wolf.springboot.domain.sys.ButtonRepository;
import com.wolf.springboot.service.BaseServiceImpl;

/**
 * 
 * <p>
 * Title: ModuleServiceImpl
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author wyx
 * @date 2019年4月16日
 */
@Service("buttonService")
public class ButtonServiceImpl extends BaseServiceImpl<Button, Long, ButtonRepository> implements ButtonService {
	
	@Autowired
	private ButtonRepository dao;

}
