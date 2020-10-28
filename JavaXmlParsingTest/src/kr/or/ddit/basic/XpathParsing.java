package kr.or.ddit.basic;

import java.io.File;
import java.io.FileReader;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class XpathParsing {
/**
 * 자바에서 내장 패키지(javax.xml.xpath)로 제공하는 라이브러리로 XML형식의 웹문서, 파일, 문자열을 파싱하는데 사용함.
 * 
 *  item : <item>요소를 모두 선택함.
 *  /item : "/"루트 노드의 자식 노드중에서 <item> 엘리먼트를 선택함(앞에 "/"가 들어가면 절대 경로)
 *  item/book : <item>엘리먼트의 자식 노드 중에서 <book> 엘리먼트를 선택 (상대경로)
 *  // : 현재 노드의 위치와 상관없이 지정된 노드부터 탐색 
 *  //item : 위치와 상관없이 엘리먼트 이름이 <item>인 모든 엘리먼트
 *  item/@id : 모든 <item> 엘리먼트의 id속성 노드를 모두 선택함
 *  item[k] : <item>엘리먼트 중에서 k번째 <item> 엘리먼트 중에서 k번째 <item> 엘리먼트
 *  item[@attr = val] : attr이라는 속성이 val값을 가지는 모든 <item>엘리먼트 (@:속성의 의미)
 *
 *	get set이용하여 수정도 가능하게 할수있다.
 */
	public void parse() {
		try {
			File file = new File(getClass().getResource("./book.xml").getPath());
			FileReader fr = new FileReader(file);
			
			// XML Document객체 생성
			InputSource is = new InputSource(fr);
			Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
			
			// xpath 생성
			XPath xPath = XPathFactory.newInstance().newXPath();
			
			// NodeList 가져오기 : data 아래에 있는 모든 book을 선택하기
			NodeList bookList = (NodeList) xPath.evaluate("//data/book", document, XPathConstants.NODESET); // 찾기원하는 객체를 지칭할 수식을 넣어준다. // 리턴타입
			System.out.println("//data/book");
			System.out.println("-----------------------------------");
			for (int i = 0; i < bookList.getLength(); i++) {
				System.out.println(bookList.item(i).getTextContent()); // book안의 text값을 모두 추출 모두 연결해서 보여준다.
			}
			System.out.println("-----------------------------------");
			
			// kind 속성이 JAVA인 모든 Node의 isbn attribute값 가져오기
			Node node = (Node) xPath.evaluate("//*[@kind='JAVA']", document, XPathConstants.NODE);
			System.out.println(node.getAttributes().getNamedItem("isbn").getTextContent());
			
			// isbn 이 B001인 Node의 textContent값 가져오기
			System.out.println(xPath.evaluate("//*[@isbn='B001']", document, XPathConstants.STRING));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		XpathParsing parser = new XpathParsing();
		parser.parse();
	}
	
	
}
