package com.sicy.common.controller;

import com.sicy.common.interceptor.RequestUtils;

import javax.servlet.http.HttpServletResponse;

/**
 * 其他controller可继承此类，提供响应工具
 * @author github.com/sicy
 *
 */
public class BaseController {
	
	/**
	 * 直接向服务端响应数据
	 * @param content
	 * @param response
	 */
	public static void renderText(final String content,	HttpServletResponse response) {
		try {
			response = initHeader(response);
			response.getWriter().write(content);
			response.getWriter().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 一个重载
	 * @param content
	 */
	public static void renderText(final String content) {
		HttpServletResponse response = RequestUtils.getResponse();
		renderText(content, response);
	}

	private static HttpServletResponse initHeader(HttpServletResponse response) {
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		return response;
	}

}
