package com.smart.base.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smart.mvc.model.Result;
import com.smart.sso.client.ApplicationUtils;
import com.smart.sso.client.SessionPermission;

/**
 * 首页管理
 * 
 * @author Joe
 */
@Controller
@RequestMapping("/admin/admin")
public class AdminController {

	@RequestMapping(method = RequestMethod.GET)
	public String execute() {
		return "/admin/admin";
	}

	@RequestMapping(value = "/menu", method = RequestMethod.GET)
	public @ResponseBody Result menu(HttpServletRequest request) {
		SessionPermission sessionPermission = ApplicationUtils.getSessionPermission(request);
		// 如果配置的权限拦截器，则获取登录用户权限下的菜单，没有权限拦截限制的情况下，获取当前系统菜单呈现
		return Result.createSuccessResult().setData(
				sessionPermission == null ? ApplicationUtils.getApplicationPermission(request) : sessionPermission.getMenuList());
	}
}