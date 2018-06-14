package com.sicy.common.interceptor;

import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Set;

/**
 * 请求分析工具类
 * @author Administrator
 *
 */
public final class RequestUtils {
	private HttpServletRequest request = null;
	private HttpServletResponse response = null;
	private String ip = null;
	private boolean debug = false;
	
	private static ThreadLocal<RequestUtils> requestContext = new ThreadLocal<RequestUtils>() {
		@Override
		protected synchronized RequestUtils initialValue() {
			return new RequestUtils();
		}
	};

	private RequestUtils() {
	}

	public static void setRequestAttribute(String key, Object obj) {
		getContext().getRequest().setAttribute(key, obj);
	}

	public static String getWebRoot() {
		return RequestUtils.getContext().getRequest().getContextPath();
	}

	public static boolean isPost() {
		return "post".equalsIgnoreCase(getContext().getRequest().getMethod());
	}

	public static void redirectUrl(String url) {
		try {
			getResponse().sendRedirect(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T getRequestAttribute(String key) {
		Object obj = getContext().getRequest().getParameterMap().get(key);
		if (null == obj) {
			obj = getContext().getRequest().getAttribute(key);
		}
		return (T) obj;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public static void setRequest(HttpServletRequest request) {
		getContext().request = request;
	}

	static void setDebug(boolean debug) {
		getContext().debug = debug;
	}

	public static boolean isDebug() {
		return getContext().debug;
	}

	public static RequestUtils getContext() {
		return requestContext.get();
	}

	public static void setResponse(HttpServletResponse resp) {
		getContext().response = resp;
	}

	public static HttpServletResponse getResponse() {
		return getContext().response;
	}

	@SuppressWarnings("unchecked")
	public static String getStringParam(String name) {
		try {
			Object value = getRequestAttribute(name);
			if (null == value) {
				Set<String> keySet = getContext().getRequest()
						.getParameterMap().keySet();
				String formKey = ":" + name;
				for (String key : keySet) {
					if (key.endsWith(formKey)) {
						value = getContext().getRequest().getParameterMap()
								.get(key);

					}
				}
			}
			if (null != value) {
				if (value instanceof String[]) {
					return ((String[]) value)[0];
				} else {
					return (String) value;
				}
			}
		} catch (Exception e) {
		}
		return null;
	}

	public static Integer getIntParam(String name) {
		String value = getStringParam(name);
		if (StringUtils.isEmpty(value)) {
			return null;
		} else {
			try {
				return Integer.valueOf(value);
			} catch (Exception e) {
				return null;
			}
		}
	}

	public static Long getLongParam(String name) {
		String value = getStringParam(name);
		if (StringUtils.isEmpty(value)) {
			return null;
		} else {
			try {
				return Long.valueOf(value);
			} catch (Exception e) {
				return null;
			}
		}
	}

	public static Date getDateParam(String name) {
		String value = getStringParam(name);
		if (StringUtils.isEmpty(value)) {
			return null;
		} else {
			try {
				return java.sql.Date.valueOf(value);
			} catch (Exception e) {
				return null;
			}
		}
	}
	
	public static Double getDoubleParam(String name){
		String value = getStringParam(name);
		if (StringUtils.isEmpty(value)) {
			return null;
		} else {
			try {
				return Double.parseDouble(value);
			} catch (Exception e) {
				return null;
			}
		}
	}
	
	public static Float getFloatParam(String name){
		String value = getStringParam(name);
		if (StringUtils.isEmpty(value)) {
			return null;
		} else {
			try {
				return Float.parseFloat(value);
			} catch (Exception e) {
				return null;
			}
		}
	}
	
	public static Boolean getBoolean(String name){
		String value = getStringParam(name);
		if (StringUtils.isEmpty(value)) {
			return null;
		} else {
			try {
				return Boolean.parseBoolean(value);
			} catch (Exception e) {
				return null;
			}
		}
	}

	public static void setSessionAttribte(String key, Object obj) {
		getContext().request.getSession().setAttribute(key, obj);
	}

	@SuppressWarnings("unchecked")
	public static <T> T getSessionAttribte(String key) {
		return (T) getContext().request.getSession().getAttribute(key);
	}

	public static String getCookie() {
		Cookie[] cookies = getContext().request.getCookies();
		String tempCookie = "";
		if (null != cookies && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				tempCookie = tempCookie + cookie.getValue() + ";";
			}
		}
		return tempCookie;
	}

	public static String getSessionId() {
		return getContext().request.getSession().getId();
	}

	public static String getRequestIP() {
		if (null == getContext().ip) {
			HttpServletRequest request = getContext().request;
			String ip = request.getHeader("x-forwarded-for");
			if (ip == null || ip.length() == 0
					|| "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("Proxy-Client-IP");
			}
			if (ip == null || ip.length() == 0
					|| "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("WL-Proxy-Client-IP");
			}
			if (ip == null || ip.length() == 0
					|| "unknown".equalsIgnoreCase(ip)) {
				ip = request.getRemoteAddr();
			}
			getContext().ip = ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;;
		}
		return getContext().ip;
	}

	public static void main(String[] args) {

	}

}
