package addressapi;

import java.util.ArrayList;

public class AddressMain {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] n = new int[3];
	    // 첫번쨰 인자 (검색할 주소명) 도로명, 지번  다 가능
		// 두번쨰 인자 검색한 결과 값이 페이지 10 이면 10페이지 내용을 본다는 뜻
		// 세번째 인자 결과값의 갯수 50이면 50
		// 네번쨰 인자 빈 리스트를 넘겨서 리스트를 반환받음
		// 
		System.out.println(	AddressLoad.find("가산동", 1, 1, new ArrayList<String>() , n).toString());
	}
}
