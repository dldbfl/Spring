package com.jsp.action.board;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.jsp.action.Action;
import com.jsp.dto.AttachVO;
import com.jsp.dto.BoardVO;
import com.jsp.dto.MemberVO;
import com.jsp.dto.BoardVO;
import com.jsp.service.BoardService;
import com.jsp.service.BoardService;
import com.jsp.utils.GetUploadPath;
import com.jsp.utils.MakeFileName;
import com.jsp.utils.MakeLogForException;

public class BoardRegistAction implements Action{
	
	private BoardService boardService;
	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}
		
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		
		String url="board/regist_success";				
		
		
		//	3. service(VO) 결과
		  	try {

		  		BoardVO board = filUpload(request);
				
		  		boardService.regist(board);				
				
			} catch (Exception e) {
				//	4. 결과에 따른 화면 결정.
				url = "board/regist_fail";
			} 	 	
						
		return url;
	}
		

	//	업로드 파일 환경 설정
	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;         //
	private static final int MAX_FILE_SIZE    = 1024 * 1024 * 40;        //
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50;        //


	private BoardVO filUpload(HttpServletRequest request) throws Exception{

		BoardVO board = new BoardVO();
		List<AttachVO> attachList = new ArrayList<AttachVO>();	
		
		//	1. 파라메터 데이터 저장 : request.getParameter() X -> enctype
		
		//업로드를 위한 환경 설정 적용
		DiskFileItemFactory factroy = new DiskFileItemFactory();
		//저장을 위한 threshold memory 적용
		factroy.setSizeThreshold(MEMORY_THRESHOLD);
		//임시 저장 위치 결정
		factroy.setRepository(new File(System.getProperty("java.io.tmpdir")));		
		ServletFileUpload upload = new ServletFileUpload(factroy);
		
		//업로드 파일의 크기 적용.
		upload.setFileSizeMax(MAX_FILE_SIZE);
		
		//업로드 request 크기 적용.
		upload.setSizeMax(MAX_REQUEST_SIZE);
		
		//실제 저장 경로를 설정.	
		String uploadPath = GetUploadPath.getUploadPath("board.upload");
		
		/*//세션
		HttpSession session = request.getSession();
		MemberVO loginUser=(MemberVO)session.getAttribute("loginUser");
		//등록일자
		Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String regdate = format.format(date);
        
        System.out.println("등록유저 : "+loginUser.getId()+" 등록일자 : "+ regdate);		
		
		//사람, 요일별 업로드패스
        uploadPath = uploadPath + File.separator + loginUser.getId()+ File.separator + regdate; */
        
        
		File file = new File(uploadPath);
		if(!file.mkdirs()) {
			System.out.println(uploadPath+"가 이미 존재하거나 실패했습니다.");
		};
		
		//request로부터 받는 파일을 추출해서 저장
		try {
			List<FileItem> formItems = upload.parseRequest(request);	
			
			String writer = null; // request.getParameter("writer")
			String title = null; // request.getParameter("title");
			String content = null;// request.getParameter("content");
			//int bno = -1; // Integer.parseInt(request.getParameter("bno"))
			
			for (FileItem item : formItems) {
				//	1.1	필드
				if(item.isFormField()) {
					//	파라메터 구분 (파라메터 이름)
					switch (item.getFieldName()) {
					case "title":	
						title = item.getString("utf-8");
					break;				
					
					case "writer":	
						writer = item.getString("utf-8");
					break;
					
					case "content":	
						content = item.getString("utf-8");
					break;	
					}
					
				}else {
					//	1.2	파일
					//	summernote의 input의 name인 files를 제외함
					if(!item.getFieldName().equals("uploadFile")) continue;
					
					String fileName = new File(item.getName()).getName();
					fileName = MakeFileName.toUUIDFileName(fileName, "$$");
					String filePath = uploadPath + File.separator + fileName;
					File storeFile = new File(filePath);
					
					//	1.5	파일저장
					//	local HDD에 저장.
					try {
						item.write(storeFile);
					} catch (Exception e) {						
						e.printStackTrace();
						throw e;
					}
					
					//	AttachVO 생성
					AttachVO attach = new AttachVO();

					//	DB에 저장할 attach에 file 내용 추가.
					attach.setFileName(fileName);
					attach.setUploadPath(uploadPath);
					attach.setFileType(fileName.substring(fileName.lastIndexOf(".")+1));
					
					//	List<AttachVO>  내용 추가
					attachList.add(attach);
					
					System.out.println("upload file : " + attach);
				}
			}
			
			board.setAttachList(attachList);
			board.setWriter(writer);
			board.setContent(content);
			board.setTitle(title);
			
			
		}catch(FileUploadException e) {
			e.printStackTrace();
			throw e;	
		}

		
		return board;
		
		/*//파일 개수 확인
		if(formItems != null && formItems.size() > 0) {
			for(FileItem item : formItems) {//form items 반복해서 꺼내는 구문
				if(!item.isFormField() && item.getFieldName().equals("uploadFile")) {//파일일 경우 해당-업로드된 파일 저장
				//if(item.getFieldName().contains("uploadFile"){
									
					AttachVO attachVO = new AttachVO();
					System.out.println("item : "+ item);
					//String fileName = item.getName().toString().substring(-1,5);	
										
					String realfileName = item.getName();
					attachVO.setFileName(realfileName);
					System.out.println("fileName : "+realfileName);

					String fileName = item.getName().substring(item.getName().lastIndexOf("."));		
					//String fileType = item.getContentType(); //파일타입
					String fileType = fileName.substring(1);
					attachVO.setFileType(fileType);
					System.out.println("fileType : "+fileType);

						
										
					fileName = MakeFileName.toUUIDFileName(fileName,"");
					//String FilePath = uploadPath + File.separator + loginUser.getId()+ File.separator + regdate + File.separator + fileName;
					String FilePath = uploadPath + File.separator +fileName;
										
					
					
					
					attachVO.setUploadPath(FilePath);
									
					attachList.add(attachVO);				
					
					boardVO.setAttachList(attachList);
					
					System.out.println("FilePath : "+FilePath);
					

					File storeFile = new File(FilePath);
					//local HDD에 저장.
					item.write(storeFile);			
					
					
					
										
				}else { // 파일 외 따른거 집어넣기
					
					switch (item.getFieldName()) {
					case "title":	String title = item.getString("utf-8");
					boardVO.setTitle(title);
					break;				
					
					case "writer":	String writer = item.getString("utf-8");
					boardVO.setWriter(writer);
					break;
					
					case "content":	String content = item.getString("utf-8");
					boardVO.setContent(content);
					break;	
					

					}
					
				}
			}
		}
		
		
	}*/
		
/*		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.println("<script>");
		out.println("window.opener.location.href='list.do';window.close();");
		out.println("</script>");
*/		
		
		
	}
}




