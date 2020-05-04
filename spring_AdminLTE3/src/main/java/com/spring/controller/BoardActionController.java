package com.spring.controller;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.dto.BoardVO;
import com.spring.request.ModifyBoardRequest;
import com.spring.request.PageMaker;
import com.spring.request.RegistBoardRequest;
import com.spring.request.SearchCriteria;
import com.spring.service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardActionController {
	
	@Autowired
	private BoardService boardService;// = BoardServiceImpl.getInstance();
	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}
	
	@RequestMapping("list.do")
	public String list(SearchCriteria cri, Model model,
					 HttpServletRequest request)throws Exception{
		String url = "board/list";
		
		Map<String, Object> dataMap = boardService.getBoardList(cri);
		
		//request 방식으로 예전처럼 넣기
		/*request.setAttribute("memberList", (List<MemberVO>)dataMap.get("memberList"));
		request.setAttribute("pageMaker", (PageMaker)dataMap.get("pageMaker"));*/
		
		//하나넣기
		//model.addAttribute("pageMaker", (PageMaker)dataMap.get("pageMaker"));
		
		//다 넣기
		model.addAllAttributes(dataMap);
		

		return url;
		
	}
	

	
	@RequestMapping("registForm.do")
	public String registForm(Model model)throws Exception{
		
		String url = "board/registBoard";
		
		return url;		
	}
	
	
	@RequestMapping("modifyForm.do")
	public String modifyForm(int bno,Model model)throws Exception{
		
		String url = "board/modifyBoard";
		BoardVO board = boardService.getBoardForModify(bno);
		
		model.addAttribute("board", board);
		
		return url;
		
	}
	
	@RequestMapping("remove.do")
	public void remove(int bno,HttpServletResponse response)throws Exception{
		
		boardService.remove(bno);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.println("<script>");
		out.println("alert('삭제되었습니다');");
		out.println("window.opener.location.reload(true);window.close();");
		out.println("</script>");	
	}
	
	@RequestMapping("regist.do")
	public void regist(RegistBoardRequest registReq,
			HttpServletResponse response)throws Exception{
				
		BoardVO board = registReq.toBoardVO();
		
		boardService.write(board);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.println("<script>");
		out.println("window.opener.location.href='list.do';window.close();");
		out.println("</script>");
		
		
	}
	
	@RequestMapping("modify.do")
	public String modify(ModifyBoardRequest modifyReq, int bno,
			String state, PageMaker pageMaker,SearchCriteria cri,
			Model model)throws Exception{
		String url="redirect:detail.do";

		pageMaker.setCri(cri);
		
		url=url+pageMaker.makeQuery();		
		
		BoardVO board = modifyReq.toBoardVO();	
		
		boardService.modify(board);
		state = "modify";
		
		model.addAttribute("state", state);
		model.addAttribute("bno", bno);
		
		return url;
		
	}
	
	@RequestMapping("detail.do")
	public String detail(int bno, String state,Model model)throws Exception{
		String url = "board/detailBoard";
		
		BoardVO board =new BoardVO();		
		
			if(state.equals("modify")) {
				 board = boardService.getBoardForModify(bno);
			}else if(state.equals("list")){
				 board = boardService.getBoard(bno);
			}				

		model.addAttribute("board", board);
		return url;
		
	}
}
