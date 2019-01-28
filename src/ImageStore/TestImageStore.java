package ImageStore;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TestImageStore {
	private String returndpath;
	
	public TestImageStore(String testdetailnumber, String FileSrc) {
		returndpath = Directorycreate(testdetailnumber);
		Imagestore(FileSrc,returndpath);
	}
	private String Directorycreate(String testdetailnumber) {
		try {
			String path = "C://AcademyImage//" + testdetailnumber;
			// 파일 객체 생성
			File file = new File(path);
			// !표를 붙여주어 파일이 존재하지 않는 경우의 조건을 걸어줌
			if (!file.exists()) {
				// 디렉토리 생성 메서드
				file.mkdirs();
				System.out.println("created directory successfully!");				
			} else {
				System.out.println("aleary Exist  directory.");				
			}
			
			return path;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}
		
		return null;
	}
	private boolean Imagestore(String FileSrc, String directorypath) {
		String imagePath = FileSrc;
		BufferedImage image = null;	
		String fileNm=null;
		String fileformat=null;
		try {
			fileNm = imagePath.substring(imagePath.lastIndexOf("/") + 1);
			image = ImageIO.read(new File(imagePath));
			fileformat = fileNm.substring(fileNm.lastIndexOf(".") + 1);

			if(fileformat.length() > 0) 
				ImageIO.write(image, fileformat , new File(directorypath+"//"+fileNm));
			else 
				ImageIO.write(image, "png" , new File(directorypath+"//"+fileNm));
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally {
			
		}
		return false;
	}
	public static void main(String[] args) {
	
	}

}
