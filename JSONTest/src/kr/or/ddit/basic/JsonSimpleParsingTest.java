package kr.or.ddit.basic;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * 공공데이터 포털의 OPEN API 이용 예제
 * (레시피 재료정보를 가져오는 예제) 
 */
public class JsonSimpleParsingTest {
	public static void main(String[] args) throws IOException, ParseException {
		String svcKey = "Grid_20150827000000000227_1";  // 레시피 재료 정보 조회 서비스 
		String apiKey = "1df7e8571e8df3f8cbc9b87691ca7d3e4d04f03c593d477e52bf67b03f0b6e1c"; // 개인별 발급. 신청해서 발급받아야한다.
		String startIdx = "1"; 			// 레시피 재료 시작 순번
		String endIndex = "5"; 			// 레시피 재료 종료 순번
		String recipeId = "195428"; 	// 레시피가 궁금한 음식 ID
		
		URL url = new URL("http://211.237.50.150:7080/openapi/" + apiKey 
							+ "/json/" + svcKey + "/" + startIdx + "/" + endIndex
							+ "?RECIPE_ID=" + recipeId );
		
		System.out.println(url);
		
		URLConnection urlConnection = url.openConnection();
		
		JSONParser parser = new JSONParser();
		
		Object obj = parser.parse(new InputStreamReader(urlConnection.getInputStream()));
		JSONObject jsonFile = (JSONObject) obj;
		
		JSONObject rootObj = (JSONObject) jsonFile.get(svcKey);
		
		long totalCnt = (long) rootObj.get("totalCnt"); // 전체 레시피 재료 수 
		
		endIndex = totalCnt + ""; // 레시피 재료의 마지막 순번 설정
		//--------------------------------------------------------
		// 구해온 전체 재료수를 이용하여 다시 요청함.
		
		url = new URL("http://211.237.50.150:7080/openapi/" + apiKey 
				+ "/json/" + svcKey + "/" + startIdx + "/" + endIndex
				+ "?RECIPE_ID=" + recipeId );
		
		urlConnection = url.openConnection();
		
		obj = parser.parse(new InputStreamReader(urlConnection.getInputStream()));
		jsonFile = (JSONObject) obj;
		
		rootObj = (JSONObject) jsonFile.get(svcKey);
		
		JSONObject result = (JSONObject) rootObj.get("result");
		String code = (String) result.get("code");
		
		if(code.equals("INFO-000")) { // 결과가 정상 상태이면...
			JSONArray list = (JSONArray) rootObj.get("row");
			
			for(Object tmpObj : list) {
				
				JSONObject tmpJson = (JSONObject) tmpObj;
				
				System.out.println("순번 : " + tmpJson.get("ROW_NUM"));
				System.out.println("레시피ID : " + tmpJson.get("RECIPE_ID"));
				System.out.println("재료명 : " + tmpJson.get("IRDNT_NM"));
				System.out.println("용량 : " + tmpJson.get("IRDNT_CPCTY"));
				System.out.println("재료구분 : " + tmpJson.get("IRDNT_TY_NM"));
				System.out.println("------------------------------------");
				
			}
		}
	}
}

