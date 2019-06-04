package com.wolf.springboot.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.wolf.springboot.domain.sys.User;
import com.wolf.springboot.entity.Message;

/**
 * 
 * <p>
 * Title: BaseController
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author wyx
 * @date 2019年4月10日
 */
public class BaseController {

	private Logger logger = LogManager.getLogger(getClass());

	private static final String PAGE_MODULE_ID = "PAGE_MODULE_ID";

	public User getCurUser() {
		UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder
				.getContext().getAuthentication();
		User curUser = (User) authenticationToken.getPrincipal();
		logger.info(curUser.getId() + "---->" + curUser.toString());
		return curUser;
	}

	/**
	 * 
	 * @Title: initPageButton
	 * @Description: 初始化模块权限按钮
	 * @param modelMap
	 */
	protected void initPageButton(ModelMap modelMap) {
		HttpServletRequest request = getRequest();
		modelMap.addAllAttributes(request.getParameterMap());
		Long moduleId = Long.valueOf(request.getParameter(PAGE_MODULE_ID));
	}

	/**
	 * 得到request对象
	 */
	protected HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		return request;
	}

	/**
	 * 得到response对象
	 */
	protected HttpServletResponse getResponse() {
		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getResponse();
		return response;
	}

	/**
	 * 得到session对象
	 */
	protected HttpSession getSession() {
		HttpServletRequest request = getRequest();
		if (request == null) {
			return null;
		} else {
			return request.getSession();
		}
	}
	
	protected Message getMsg() {
		return new Message();
	}

}
