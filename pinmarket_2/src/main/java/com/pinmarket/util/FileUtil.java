package com.pinmarket.util;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;

@Log4j
public class FileUtil {
	// 파일이 존재하는지 확인하는 메서드
	public static boolean exist(File file) throws Exception{
		return file.exists();
	}
	
	// 파일이 존재하는지 확인하는 메서드
	public static boolean exist(String fileName) throws Exception{
		log.info("파일 이름 : "+fileName);
		return toFile(fileName).exists();
	}
	
	// 문자열을 파일 객체로 만들어 주는 메서드
	public static File toFile(String fileName) throws Exception{
		return new File(fileName);
	}
	
	// 파일 지우기
	public static boolean delete(File file) throws Exception {
		return file.delete();
	}
	
	// 파일의 정보를 문자열로 넣어주면 지워주는 메서드
	// 파일은 realPath 정보의 파일명을 넘겨야 한다.
	public static boolean remove(String fileName) throws Exception {
		// 1. 문자열을 파일 객체로 만든다. 2. 있는지 확인한다. 3. 삭제한다. 4. 결과 리턴
		File file = toFile(fileName);
		// 파일이 존재하지 않는 경우 예외 발생
		if(!exist(file)) System.out.println("삭제하려는 파일이 존재하지 않습니다.");
		// 파일이 존재하는 경우 처리
		else if(!delete(file))
			System.out.println("삭제하려는 파일이 삭제되지 않았습니다.");
		else
			System.out.println("FileUtil.remove() - 파일이 삭제 되었습니다.");
		return true;
	}
	
	// 서버의 상대 주소를 절대 주소로 바꿔 주는 메서드
	public static String getRealPath(String path, String fileName, HttpServletRequest request) {
		String filePath = path + "/" + fileName;
		return request.getServletContext().getRealPath(filePath);
	}
	
	
	
	//확장자 문자열로 리턴
	public static String getExt(String fileName) {
		
		int lastIndex = fileName.lastIndexOf(".");
		String ext = fileName.substring(lastIndex+1);

		return ext;
	}
	//이미지 확장자 체크
	public static boolean extCheck(String fileName) {
		
		String []arrExp = {"jpg","png","gif","jfif","PNG"};
		String ext = getExt(fileName);
		boolean chk = false;
		
		for(int i=0;i<arrExp.length;i++) {
			if(arrExp[i].equals(ext)) {
				chk = true;
				break;
			}
		}
		return chk;
	}
	// 파일의 절대 위치를 받아서 중복되지 않는 File 객체를 리턴해 준다.
	// 중복된 파일에 대한 처리 - 중복이 되지 않는 파일 객체를 리턴해 준다.
	public static File noDuplicate(String fileFullName) throws Exception {
//			System.out.println("FileUtil.noDuplicate().fileFullName"+fileFullName);
		
		File file = null;
		int dotPos = fileFullName.lastIndexOf(".");
		//log.info("dotPos : "+dotPos);
		// image.jpg - fileName : image, ext : .jpg
		String fileName = fileFullName.substring(0, dotPos); // image
		String ext = fileFullName.substring(dotPos); // .jpg
		int cnt = 0; // 중복이 됐을 때 카운트를 중간에 넣는다. fileName + cnt + ext. 중복이 되면 cnt++ 한다.
		//log.info("fileName : "+fileName);
		//log.info("ext : "+ext);
		// 파일 정보확인
		//System.out.println("FileUtil.noDuplicate().fileName = " + fileName + ", ext = " + ext);
		
		while(true) {
			if(cnt == 0)
				file = toFile(fileFullName);
			else file = toFile(fileName + cnt + ext);
			// 중복되면 계속 반복처리한다. 중복이 되지 않으면 빠져 나간다.
			if(!exist(file)) break;
			// 중복이 된다.
			cnt++;
		}
		
		return file;
	}
	
	// 파일 서버에 올리는 메서드 - FileUpload 라이브러리 사용
	public static String upload(final String PATH, MultipartFile multiFile, HttpServletRequest request) throws Exception {
		String fileFullName = "";
		//log.info("[" + multiFile.getOriginalFilename() + "]");
		if(multiFile != null && !multiFile.getOriginalFilename().equals("")) {
			String fileName = multiFile.getOriginalFilename();
			// 서버의 파일명 중복을 배제한 File 객체
			File saveFile = noDuplicate(getRealPath(PATH, fileName, request)); 
			fileFullName = PATH + "/" + saveFile.getName();
			
			String fileExt = multiFile.getContentType().split("/")[1];
			
			// 실제적인 파일 업로드
			multiFile.transferTo(saveFile);
			
		} else {
			fileFullName = PATH + "/" + "noImage.jpg";
		}
		return fileFullName;
	}
	
	//썸네일 이미지 생성
	public static String thumbnailUpload(final String PATH, MultipartFile multiFile, HttpServletRequest request) throws Exception {
		String fileFullName = "";
		//log.info("[" + multiFile.getOriginalFilename() + "]");
		if(multiFile != null && !multiFile.getOriginalFilename().equals("")) {
			String fileName = "";
			fileName = "th_"+multiFile.getOriginalFilename();

			// 서버의 파일명 중복을 배제한 File 객체
			File saveFile = noDuplicate(getRealPath(PATH, fileName, request));
			fileFullName = PATH + "/" + saveFile.getName();

			if(extCheck(getExt(fileName))) {
				//File thSaveFile = noDuplicate(getRealPath("/upload/thumbAuctionImage", fileName, request));
				///upload/auctionImage에 있는 파일을 리사이징 하는거임 파일을 새로 만든다는 개념이 아닌!
				File thSaveFile = noDuplicate(getRealPath(PATH, fileName, request));
				FileOutputStream thumbnail = new FileOutputStream(thSaveFile);
				Thumbnailator.createThumbnail(multiFile.getInputStream(),thumbnail,250,210);
				thumbnail.close();
			}
			// 실제적인 파일 업로드 - 원래 첨부파일은 webapp안에 저장되면 안된다.
		} else {
			fileFullName = PATH + "/" + "noImage.jpg";
		}
		return fileFullName;
	}
}
