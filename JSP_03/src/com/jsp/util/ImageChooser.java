package com.jsp.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ImageChooser {

	static String file_path = "";

	/**
	 * 이미지를 선택하는 모달창을 띄워주는 메소드
	 * @return 선택한 파일 전체 경로를 String으로 리턴
	 */
	public static String imageChooser() {

		Stage dialog = new Stage(StageStyle.UTILITY);

		dialog.initModality(Modality.APPLICATION_MODAL);

		dialog.setTitle("이미지 파일을 선택해주세요");

		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));

		File selectFile = fileChooser.showOpenDialog(dialog);

		if (selectFile != null) {

			System.out.println("OPEN : " + selectFile.getPath());
			// 파일 전체 경로
			file_path = selectFile.getPath();

			// // 파일 경로를 화면에 띄움
			// filePath.setText(file_path);

		}
		return file_path;
	}

	/**
	 * 파일 전체 경로에서 파일 이름만 추출하는 메소드
	 * @param file_path		(파일 전체 경로와 파일명)
	 * @return	fileName	(파일 이름)
	 */
	public static String getFileName(String file_path) {

		File inputFile = new File(file_path);
		String fileName = inputFile.getName();
		
		inputFile.getPath();
		
		return fileName;
	}
	
	/**
	 * 파일전체 경로에서 파일명을 제외한 경로만 받는 메소드
	 * @param file_path (파일 전체경로와 파일명)
	 * @return filePath (파일의 경로)
	 */
	public static String getPath(String file_path) {

		File inputFile = new File(file_path);
		String filePath = inputFile.getPath();
		return filePath;
	}
	
	/**
	 * 파일 이름에서 UUID를 추가한 이름을 만드는 메소드
	 * @param filename
	 * @return uuidFile
	 */
	public static String getUUIDfileName(String filename) {
	//	String uuidFile = uuidText.uuidName(filename);
		return filename;
	}
	
	/**
	 * 
	 * @param uuidFile(uuid파일 이름)
	 * @param folderName(저장할 폴더 이름)
	 * @return path(DB에 저장할 파일 전체 경로)
	 */
	public static String getDBfilePath(String folderName) {
		String path =  "c:\\TTF\\" + folderName + "\\";
		return path;
	}

	/**
	 * 이미지 파일을 c:\\TTF\\profileImg 경로에 복사하는 메소드
	 * @param originFile 	(Client가 선택한 파일의 전체 경로)
	 * @param fileName		(선택한 파일 이름)
	 * @param folderName  (경로상에서 파일이 들어갈 폴더이름)
	 */
	public static void imageSender(String originFile, String uuidFileName, String folderName) {
		
		FileInputStream fis = null;
		FileOutputStream fos = null;
		
		try {
			//복사할 대상 파일을 지정
			File file = new File(originFile);
			
			fis = new FileInputStream(file);
			
			// .mkdirs를 하면 자동으로 경로상의 폴더들도 생성해준다.
			String path = "c:\\heyLawyer\\" + folderName;	//폴더 경로
			File Folder = new File(path);
			
			//해당 디렉토리가 없을 경우 디렉토리를 생성한다.
			if(!Folder.exists()) {
				try {
					Folder.mkdirs();
					System.out.println("폴더가 생성되었습니다.");
				} catch (Exception e) {
					e.getStackTrace();
				}
			} else {
				System.out.println("폴더가 이미 생성되어있습니다.");
			}
			//복사된 파일의 위치를 지정한다.
			fos = new FileOutputStream(new File(path + "\\" + uuidFileName));
			System.out.println(fos.toString());
			int readBuffer = 0;
			byte[] buffer = new byte[1024];
			while((readBuffer = fis.read(buffer)) != -1) {
				fos.write(buffer, 0, readBuffer);
			}
			System.out.println("파일이 서버로 전송되었습니다.");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 선택한 파일을 미리보기 할 수 있도록 Image객체를 만들어주는 메소드
	 * @param filepath
	 * @return img(이미지객체)      
	 */
	public static Image getImage(String filepath) {
		FileInputStream input = null;
		if (filepath == null) { return null; }
		
		File file = new File(filepath);
		// 파일경로에 등록된 해당 파일 삭제메소드
		// file.delete();  
		if (!file.exists()) { return null; }
		try {
			input = new FileInputStream(filepath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Image img = new Image(input);
		return img;
	}
}
