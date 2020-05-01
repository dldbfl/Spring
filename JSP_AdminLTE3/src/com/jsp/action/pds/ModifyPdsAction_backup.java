package com.jsp.action.pds;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.jsp.action.Action;
import com.jsp.dto.AttachVO;
import com.jsp.dto.BoardVO;
import com.jsp.dto.MemberVO;
import com.jsp.dto.PdsVO;
import com.jsp.request.BoardRequest;
import com.jsp.request.MemberRegistRequest;
import com.jsp.request.ModifyBoardRequest;
import com.jsp.request.SearchCriteria;
import com.jsp.service.BoardService;
import com.jsp.service.BoardServiceImpl;
import com.jsp.service.MemberServiceImpl;
import com.jsp.service.PdsService;
import com.jsp.utils.GetUploadPath;
import com.jsp.utils.MakeFileName;

public class ModifyPdsAction_backup implements Action {

	private PdsService pdsService;
	public void setPdsService(PdsService pdsService) {
		this.pdsService = pdsService;
	}

	
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				
		String url = null;
					
		try {
			/*pdsVO.setAttachList(null);
			
			saveFile(request,response);
			System.out.println(pdsVO);
			pdsService.modify(pdsVO);*/
			
			PdsVO pds = saveFile(request);
			
			pdsService.modify(pds);
			
		}catch(Exception e) {
			e.printStackTrace();
			url = "error/500_error";
		}		
		
		// 화면결정
		//url = "redirect:detail.do?pno="+pno;	
				
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		
		out.println("<script>");
		out.println("alert(\"자료 수정이 정상적으로 완료 되었소\");");
		out.println("window.opener.location.reload(true);");
		out.println("window.close();");
		out.println("</script>");
				
		return url;
	}
	
	//	업로드 파일 환경 설정
	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;             //
	private static final int MAX_FILE_SIZE    = 1024 * 1024 * 40;        //
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50;        //


	private PdsVO saveFile(HttpServletRequest request) throws Exception {

		PdsVO pdsVO = new PdsVO();
		List<AttachVO> attachList = new ArrayList<AttachVO>();
		
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
		
		//실제 저장 경로를 설정. <---- 여기서 요일별로 나눠야해				
		String uploadPath = GetUploadPath.getUploadPath("pds.upload");
		
		/*//세션
		HttpSession session = request.getSession();
		MemberVO loginUser=(MemberVO)session.getAttribute("loginUser");
		//등록일자
		Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String regdate = format.format(date);
        
        System.out.println("등록유저 : "+loginUser.getId()+" 등록일자 : "+ regdate);
		//사람, 요일별 업로드패스
        uploadPath = uploadPath + File.separator + loginUser.getId()+ File.separator + regdate;*/
        
        
		File file = new File(uploadPath);
		if(!file.mkdirs()) {
			System.out.println(uploadPath+"가 이미 존재하거나 실패했습니다.");
		};
		
		//request로부터 받는 파일을 추출해서 저장
	try {
		List<FileItem> formItems = upload.parseRequest(request);			
		
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
					
					pdsVO.setAttachList(attachList);
					
					System.out.println("FilePath : "+FilePath);
					

					File storeFile = new File(FilePath);
					//local HDD에 저장.
					item.write(storeFile);			
					
					
					
										
				}else { // 파일 외 따른거 집어넣기
					
					switch (item.getFieldName()) {
					case "title":	String title = item.getString("utf-8");
					pdsVO.setTitle(title);
					break;				
					
					case "writer":	String writer = item.getString("utf-8");
					pdsVO.setWriter(writer);
					break;
					
					case "content":	String content = item.getString("utf-8");
					pdsVO.setContent(content);
					break;	
					
					case "pno":	int pno = Integer.parseInt(item.getString("utf-8"));
					pdsVO.setPno(pno);	
					break;
					
					
									
					}
					
				}
			}
		}*/
		
		
	} catch(Exception e) {
		e.printStackTrace(); //개발 중 에러 확인
	}
	
		
		pdsVO.setAttachList(pdsService.getPds(pdsVO.getPno()).getAttachList());
		
		return pdsVO;
		
/*		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.println("<script>");
		out.println("window.opener.location.href='list.do';window.close();");
		out.println("</script>");
*/		
		
		
	}

}









