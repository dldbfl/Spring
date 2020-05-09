package com.spring.request;

import java.util.Arrays;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.spring.dto.PdsVO;
import com.spring.dto.ReplyVO;

public class ModifyPdsRequest {
	
	private int pno;
	


	private String title;
	private String content;
	private String writer;
	private MultipartFile[] uploadFile;
	private int[] deleteFile;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public MultipartFile[] getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(MultipartFile[] uploadFile) {
		this.uploadFile = uploadFile;
	}
	public int getPno() {
		return pno;
	}
	public void setPno(int pno) {
		this.pno = pno;
	}
	
	public int[] getDeleteFile() {
		return deleteFile;
	}
	public void setDeleteFile(int[] deleteFile) {
		this.deleteFile = deleteFile;
	}
	
	public PdsVO toPdsVO() {
		PdsVO pds = new PdsVO();
		pds.setPno(this.pno);
		pds.setContent(this.content);
		pds.setTitle(this.title);
		pds.setWriter(this.writer);
		return pds;
	}
	@Override
	public String toString() {
		return "ModifyPdsRequest [pno=" + pno + ", title=" + title + ", content=" + content + ", writer=" + writer
				+ ", uploadFile=" + Arrays.toString(uploadFile) + ", deleteFile=" +deleteFile + "]";
	}
	
	
	
}










