package DBController;

import java.util.ArrayList;
import java.util.Iterator;

import client.ADSReceiver;
import client.ADSUtils;
import client.ReceiveData;
import client.ReceiveDatas;

public class UpdateClient {   
	public static void main(String[] args){
		UpdateClient uc = new UpdateClient();     
		try{
			uc.getAddrInfo();     
		}catch(Exception ex){
			ex.printStackTrace();
		}   
	} 

	public void getAddrInfo(){
		ADSReceiver ads = new ADSReceiver();  // ADSReceiver 객체 생성     
		String app_key = "U01TX0FVVEgyMDE5MDIxNDIwMzMxNDEwODUxMDk=";           // 승읶키    
		String date_gb = "D";        // 날짜 구분     
		String retry_in = "Y";        // 재반영 요청 여부     
		String cntc_cd = "009000";      // 자료 요청 구분    
		String req_date_from = "";       // 요청읷자(From)     
		String req_date_to = "";           // 요청읷자(To)          
		// 읷변동 자료를 저장핛 파읷경로를 설정합니다.     
		ads.setFilePath("C:\\Users\\wt.choi\\git\\Best-Suki");     
		ads.setCreateDateDirectory(ADSUtils.YYMMDD);          
		try {       
			// 변동자료 연계서비스 요청 및 응답데이터 확읶       
			ReceiveDatas receiveDatas = ads.receiveAddr(app_key, date_gb, cntc_cd, retry_in, req_date_from, req_date_to);
			/* --------------------------------- 응답 결과 확읶 --------------------------------- */
			if(receiveDatas.getResult() != 0){
				if(receiveDatas.getResult() == -1){
					// 서버 접속 실패 : 잠시후 재 시도 하시기 바랍니다.           
					System.out.println("서버 접속 실패");           
				}         // 서버 페이지 오류 사항 확읶         
				System.out.println("Result code : "+receiveDatas.getResult());         
				System.out.println("Response code : "+receiveDatas.getResCode());         
				System.out.println("Response Msg : "+receiveDatas.getResMsg());         
				return;       
			}              // 서버 응답 확읶       
			System.out.println("Response code : "+receiveDatas.getResCode());    // 응답코드      
			System.out.println("Response Msg : "+receiveDatas.getResMsg());      // 응답메시지              
			if(!"P0000".equals(receiveDatas.getResCode())){         
				return;       
			}       
			/* --------------------------------- 응답 결과 완료  --------------------------------- */              // 결과 데이터 정렬       
			@SuppressWarnings("rawtypes")
			ArrayList result = receiveDatas.getReceiveDatas(ADSUtils.UPDATE_ASC);       
			@SuppressWarnings("rawtypes")
			Iterator itr = result.iterator();       
			while(itr.hasNext()){         // 결과 데이터 건별 정보 확읶         
				ReceiveData receiveData = (ReceiveData)itr.next();         
				System.out.print(" CNTC : ");         
				System.out.print(receiveData.getCntcCode());         
				System.out.print(" RES_CODE : ");         
				System.out.println(receiveData.getResCode());         
				if(!"P0000".equals(receiveData.getResCode())){
					// 해당 파읷응답 에러. 특히 E1001 읶경우, 해당 파읷을 아직 생성하지 못핚 응답으로 추후 재시도 필요.           
					System.out.println("해당파읷에 대핚 응답이 정상이 아니기에 재 요청 필요");         
				}       
			}     
		} catch (Exception e) {       
			e.printStackTrace();     
		} 
	} 
} 
