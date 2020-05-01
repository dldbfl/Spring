package com.jsp.action.board;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.dto.AttachVO;
import com.jsp.request.PageMaker;
import com.jsp.service.BoardService;
import com.jsp.service.BoardService;
import com.jsp.utils.CreatePageMaker;

public class BoardRemoveAction implements Action {

	private BoardService boardService;
	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "board/remove_success";

		int bno = Integer.parseInt(request.getParameter("bno"));

		
		// bno에 대한 attachList 확보
		List<AttachVO> attachList = null;
		try {
			attachList = boardService.getBoard(bno).getAttachList();

			// 각 attachlist 를 이용 파일을 삭제.
			if(attachList != null) {
				for (AttachVO attach : attachList) {
					String storedFilePath = attach.getUploadPath() + File.separator
							+ attach.getFileName();
					File file = new File(storedFilePath);
					if (file.exists()) {
						file.delete();
					}
	
				}
			}
			//DB 내용 삭제
			boardService.remove(bno);
			
			PageMaker pageMaker = CreatePageMaker.make(request);
			
			request.setAttribute("pageMaker", pageMaker);
		} catch (Exception e1) {
			url="error/500";
			e1.printStackTrace();
		}

		return url;
	}

}








