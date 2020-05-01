package com.jsp.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

//@WebFilter("/*")
public class FilterTest implements Filter {

	public void destroy() {
		// 톰캣이 끝날때 한번만
		System.out.println("Filter : destroy()");
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpReq = (HttpServletRequest)request;
		
		System.out.println("Filter : doFilter()" + httpReq.getRequestURI());

		chain.doFilter(httpReq, response);
				
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		// 최초 요청이 시작될때 한번만
		System.out.println("Filter : init()");

		
	}

}
