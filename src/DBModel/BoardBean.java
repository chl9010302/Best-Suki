package DBModel;

public class BoardBean { 
	//s
	private String BoardId           ;
	private String Subtitle     	 ;
	private String Filepath          ;
	private String Radio1      		 ;
	private String Radio2   		 ;
	private String Radio3      		 ;
	private String Radio4     	  	 ;
	private String Radio5			 ;
	private String Flag        		 ;
	private int Count;
	
	public int getCount() {
		return Count;
	}
	public void setBoardId(int count) {
		Count = count;
	}
	
	public String getBoardId() {
		return BoardId;
	}
	public void setBoardId(String boardId) {
		BoardId = boardId;
	}
	public String getSubtitle() {
		return Subtitle;
	}
	public void setSubtitle(String subtitle) {
		Subtitle = subtitle;
	}
	public String getFilepath() {
		return Filepath;
	}
	public void setFilepath(String filepath) {
		Filepath = filepath;
	}
	public String getRadio1() {
		return Radio1;
	}
	public void setRadio1(String radio1) {
		Radio1 = radio1;
	}
	public String getRadio2() {
		return Radio2;
	}
	public void setRadio2(String radio2) {
		Radio2 = radio2;
	}
	public String getRadio3() {
		return Radio3;
	}
	public void setRadio3(String radio3) {
		Radio3 = radio3;
	}
	public String getRadio4() {
		return Radio4;
	}
	public void setRadio4(String radio4) {
		Radio4 = radio4;
	}
	public String getRadio5() {
		return Radio5;
	}
	public void setRadio5(String radio5) {
		Radio5 = radio5;
	}
	public String getFlag() {
		return Flag;
	}
	public void setFlag(String flag) {
		Flag = flag;
	}
}
