package com.spring.controller;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.dto.ReplyVO;
import com.spring.request.DeleteReplyRequest;
import com.spring.request.ModifyReplyRequest;
import com.spring.request.PageMaker;
import com.spring.request.RegistReplyRequest;
import com.spring.request.SearchCriteria;
import com.spring.service.ReplyService;


@Controller
@RequestMapping("/summernote/*")
public class SummernoteActionController {
		
	@Autowired
	private ReplyService replyService;// = ReplyServiceImpl.getInstance();
	public void setReplyService(ReplyService replyService) {
		this.replyService=replyService;
	}
	
	//이렇게도 가능하다.
	/*@RequestMapping("list.do")
	public String list(HttpServletResponse response,
				 HttpServletRequest request)throws Exception{
	
		String url=null;
		
		int bno	= Integer.parseInt(request.getParameter("bno"));
		int page= Integer.parseInt(request.getParameter("page"));
		
		SearchCriteria cri = new SearchCriteria();
		cri.setPage(page);
		cri.setPerPageNum(10);
		try {
			Map<String,Object> dataMap = replyService.getReplyList(bno, cri);
			
			ObjectMapper mapper=new ObjectMapper();
			
			response.setContentType("application/json;charset=utf-8");
			PrintWriter out=response.getWriter();
			
			out.println(mapper.writeValueAsString(dataMap));
			
			out.close();
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
		return url;
	
	}*/

	
	@RequestMapping("list.do")
	@ResponseBody//내가 넘기는건 있는 그대로 스트링이야. 오지랖떨지말고 화면url로 바꾸지말고 스트링으로 넘겨서 리스폰스 바디에 붙여서보내.
	//잭슨형이 객체를 리턴하면 	제이슨형태의 형태로 헤더를 정해서 던지면 어뎁터는 리스폰스바디가 붙었으니 스트링으로 바꿔서	그대로 출력해버린대.
	public ResponseEntity<Map<String,Object>> list(int bno, SearchCriteria cri)throws Exception{
		
		ResponseEntity<Map<String,Object>> entity = null;
		
		try {
			Map<String, Object> dataMap = replyService.getReplyList(bno, cri);;
			entity = new ResponseEntity<Map<String,Object>>(dataMap,HttpStatus.OK);			
			//entity = 결과(dataMap)와 응답코드를 같이 보낸다.
			//HttpStats = 응답코드	
			
		}catch(SQLException e) {
			e.printStackTrace();
			entity = new ResponseEntity<Map<String,Object>>(HttpStatus.INTERNAL_SERVER_ERROR);
			//결과가 없으면 저걸 보낸다. 
			//http_status_int...머시기는 500에러
		}
		return entity;
	}
	
	@RequestMapping("regist.do")
	@ResponseBody//내가 넘기는건 있는 그대로 스트링이야. url로 바꾸지말고 스트링으로 넘겨서 리스폰스 바디에 붙여서보내.
	//잭슨형이 객체를 리턴하면 	제이슨형태의 형태로 헤더를 정해서 던지면 어뎁터는 리스폰스바디가 붙었으니 스트링으로 바꿔서	그대로 출력해버린대.
	public ResponseEntity<Integer> regist(@RequestBody//어뎁터는 돌려보내는게 스트링이 아니면 잭슨형 불러서 바꾼다는 뜻은 requestbody야 
						RegistReplyRequest registReq)throws Exception{
		
		//String result = "";

		ResponseEntity<Integer> entity = null;
		
		ReplyVO reply = registReq.toReplyVO();
				
		try {
			replyService.registReply(reply);
			
			Map<String, Object> dataMap = replyService.getReplyList(registReq.getBno(), new SearchCriteria());
			PageMaker pageMaker = (PageMaker)dataMap.get("pageMaker");
			int realEndPage = pageMaker.getRealEndPage();
			
			entity = new ResponseEntity<Integer>(realEndPage,HttpStatus.OK);			
			//entity = 결과(dataMap)와 응답코드를 같이 보낸다.
			//HttpStats = 응답코드	
			
			//if(true) throw new SQLException();
			
		}catch(SQLException e) {
			e.printStackTrace();
			entity = new ResponseEntity<Integer>(HttpStatus.INTERNAL_SERVER_ERROR);
			//결과가 없으면 저걸 보낸다. 
			//http_status_int...머시기는 500에러
		}
		
		return entity;
				
	}
	
	
	@RequestMapping("modify.do")
	@ResponseBody
	public ResponseEntity<String> modify(@RequestBody ModifyReplyRequest modifyReq) throws Exception {

		ResponseEntity<String> entity = null;
		
		ReplyVO reply = modifyReq.toReplyVO();

		try {
			replyService.modifyReply(reply);			
						
			entity = new ResponseEntity<String>(HttpStatus.OK);		
						
		} catch (SQLException e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return entity;
	}
	
	
	@RequestMapping("remove.do")
	@ResponseBody
	public ResponseEntity<Integer> remove(@RequestBody DeleteReplyRequest removeReq) throws Exception {
		
		ResponseEntity<Integer> entity = null;		
		
		try {
			replyService.removeReply(removeReq.getRno());

			SearchCriteria cri = new SearchCriteria();

			Map<String, Object> dataMap = replyService.getReplyList(removeReq.getBno(), cri);
			PageMaker pageMaker = (PageMaker) dataMap.get("pageMaker");
			int page = removeReq.getPage();
			int realEndPage = pageMaker.getRealEndPage();
			if (page > realEndPage) {
				page = realEndPage;
			}
			entity = new ResponseEntity<Integer>(page,HttpStatus.OK);			
			
		} catch (SQLException e) {
			e.printStackTrace();
			entity = new ResponseEntity<Integer>(HttpStatus.OK);			
		}

		return entity;

	}
	
}



