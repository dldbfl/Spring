package com.jsp.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.dispatcher.ViewResolver;
import com.jsp.dto.MemberVO;

public class MemberDisabledFilter implements Filter {
		
	private ViewResolver viewResolver;
	
	private List<String> checkURLs=new ArrayList<String>(); 
		
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpReq = (HttpServletRequest)request;
		HttpServletResponse httpResp = (HttpServletResponse)response;
		
		
		HttpSession session = httpReq.getSession();
				
		//대상 uri 확인
		String uri=httpReq.getRequestURI();
		
		if(!excludeCheck(uri)) {
			chain.doFilter(request, response);
			return;
		}
		
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
				System.out.println(loginUser.toString());
		if(loginUser != null && loginUser.getEnabled()!=1) {  //로그인한 상태고,정지상태고,
			for(String url : checkURLs) {
				if(uri.contains(url)) { //url이 대상에 걸리면,
					url="commons/checkDisabled";
					/*System.out.println("요청 Url = " + "'"+reqUrl+"'" );*/
					viewResolver.view(httpReq, httpResp, url);
					return;
				}					
			}
		}
			chain.doFilter(request, response);		
				
	}
	
	

	public void init(FilterConfig fConfig) throws ServletException {
		String paramValue=fConfig.getInitParameter("checkURL");
		StringTokenizer st= new StringTokenizer(paramValue,",");
		while(st.hasMoreTokens()) {
			String urlkey = st.nextToken();
			checkURLs.add(urlkey);
		}
		//hasMoreToken은 delimeter제한 ','이런거 제한
		//hasMoreElement는 delimeter포함. 단 Tokenizer 마지막 파라미터가 true이어야함. 
		//기본값은 false임
		System.out.println("정지대상 필터링 되는 것들 : "+checkURLs);
		
		String viewResolverType = fConfig.getInitParameter("viewResolver");

		try {
			Class<?> cls = Class.forName(viewResolverType);	
			this.viewResolver=(ViewResolver)cls.newInstance();
			System.out.println("[MemberDisabledFilter]"+viewResolverType + "가 준비되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("[MemberDisabledFilter]"+viewResolverType + "가 준비되지 않았습니다.");
		}
		
	}
	
	private boolean excludeCheck(String url) {
		for(String exURL: checkURLs) {
			if(url.contains(exURL)) {
				return true;
			}
		}
		return false;
	}
	
}
