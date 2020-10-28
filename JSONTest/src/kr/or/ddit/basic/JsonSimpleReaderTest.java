package kr.or.ddit.basic;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonSimpleReaderTest {
	public static void main(String[] args) throws IOException, ParseException {
		
		FileReader fr = new FileReader("d:/D_Other/myJsonFile.txt"); // 문자기반이기 때문에 fileReader사용
		JSONParser parser = new JSONParser();
		
		Object obj = parser.parse(fr); // 파라미터 : 리더, 스트링
		JSONObject jsonFile = (JSONObject) obj; // JSONObject 메서드 호출하기 위해 캐스팅
		
		String name = (String) jsonFile.get("name"); // 오브젝트 타입이기 때문에 캐스팅
		String job = (String) jsonFile.get("job");
		long age = (long) jsonFile.get("age");
		String addr = (String) jsonFile.get("addr");
		
		System.out.println("name : " + name);
		System.out.println("job : " + job);
		System.out.println("age : " + age);
		System.out.println("addr : " + addr);
		
		JSONArray list = (JSONArray) jsonFile.get("singerList");
		
		Iterator<JSONObject> it = list.iterator();
		
		JSONObject singer;
		
		while (it.hasNext()) {
			singer = it.next();
			System.out.printf("이름 : %s, \t성별 : %s, \t나이 : %d\n",
								singer.get("name"), singer.get("gender"), singer.get("age"));
		}
	}
}
