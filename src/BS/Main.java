package BS;

public class Main {
	/**
	 * Academy Manament Program  
	 */
	
	// 메인클래스 실행
	LoginView loginView;
	TestFrm testFrm;

	public static void main (String[] args){
		Main main = new Main();
		main.loginView = new LoginView(); // 로그인창 보이기
		main.loginView.setMain(main); // 로그인창에게 메인 클래스 보내기
	}
	
	public void showFrameTest() {
		loginView.dispose(); // 로그인창 닫기
		this.testFrm = new TestFrm(); // 로그인이 되었을 때 보이는 창
	}
}