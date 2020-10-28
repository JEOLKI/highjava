package kr.or.ddit.board.main;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.BoardVO;

/*
 * 위의 테이블을 작성하고 게시판을 관리하는
다음 기능들을 구현하시오.

기능 구현하기 ==> 전체 목록 출력, 새글작성, 수정, 삭제, 검색 
 
------------------------------------------------------------

게시판 테이블 구조 및 시퀀스

create table jdbc_board(
    board_no number not null,  -- 번호(자동증가)
    board_title varchar2(100) not null, -- 제목
    board_writer varchar2(50) not null, -- 작성자
    board_date date not null,   -- 작성날짜
    board_content clob,     -- 내용
    constraint pk_jdbc_board primary key (board_no)
);
create sequence board_seq
    start with 1   -- 시작번호
    increment by 1; -- 증가값
		
----------------------------------------------------------

// 시퀀스의 다음 값 구하기
//  시퀀스이름.nextVal

 */
public class BoardMain {
	
	private IBoardService service;

	private Scanner scan;

	public BoardMain() {
		service = BoardServiceImpl.getInstance();
		scan = new Scanner(System.in);
	}
	
	public static void main(String[] args) {
		BoardMain boardobj = new BoardMain();
		boardobj.start();
	}
	
	/**
	 * 메뉴를 출력하는 메서드
	 */
	public void displayMenu(){
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  BOARD");
		System.out.println("----------------------");
		System.out.println("  === 작 업 선 택 ===");
		System.out.println("  1. 전체 목록 출력");
		System.out.println("  2. 글 작성");
		System.out.println("  3. 글 수정");
		System.out.println("  4. 글 삭제");
		System.out.println("  5. 글 검색");
		System.out.println("  6. 작업 끝.");
		System.out.println("----------------------");
		System.out.print("원하는 작업 선택 >> ");
	}
	
	
	/**
	 * 프로그램 시작메서드
	 */
	public void start(){
		int choice;
		do{
			displayMenu(); //메뉴 출력
			choice = scan.nextInt(); // 메뉴번호 입력받기
			switch(choice){
				case 1 :  // 전체 목록 출력
					dispalyAllBoard();
					break;
				case 2 :  // 글 작성
					insertBoard();
					break;
				case 3 :  // 글 수정
					updateBoard();
					break;
				case 4 :  // 글 삭제
					deleteBoard();
					break;
				case 5 :  // 글 검색
					searchBoard();
					break;
				case 6 :  // 작업 끝
					System.out.println("작업을 마칩니다.");
					break;
				default :
					System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		}while(choice!=6);
	}
	
	
	/**
	 * 게시글의 정보를 검색하는 메서드
	 */
	private void searchBoard() {
		scan.nextLine(); // 버퍼 비우기
		
		System.out.println();
		System.out.println("검색할 게시글의 정보를 입력하세요.");
		
		System.out.println("게시글 번호 >>");
		String boardNo = scan.nextLine().trim();
		
		System.out.println("게시글 제목 >>");
		String boardTitles = scan.nextLine().trim();
		
		System.out.println("게시글 작성자 >>");
		String boardWriter = scan.nextLine().trim();
		
		System.out.println("게시글 작성일자 >>");
		String boardDate = scan.nextLine().trim();
		
		System.out.println("게시글 내용 >>");
		String boardContent = scan.nextLine().trim();
		
		BoardVO board = new BoardVO();
			
		board.setBoard_no(boardNo);
		board.setBoard_content(boardTitles);
		board.setBoard_writer(boardWriter);
		board.setBoard_date(boardDate);
		board.setBoard_content(boardContent);
		
		List<BoardVO> boardList = service.getSearchBoard(board);
		
		System.out.println();
		System.out.println("---------------------------------------------------------");
		System.out.println(" 글 번호\t글제목\t작성자\t작성일자\t\t\t글 내용");
		System.out.println("---------------------------------------------------------");
		
		if (boardList == null || boardList.size() == 0 ) {
			System.out.println("검색된 게시글이 없습니다.");
		}else {
			for (BoardVO board2 : boardList) {
				System.out.println(board2);
			}
		}
		
		System.out.println("--------------------------------------------------");
		System.out.println("출력 작업 끝.");
	}
	
	/**
	 * 게시글을 삭제하기 위한 메서드 (입력반은 글번호를 이용하여 삭제한다.)
	 */
	private void deleteBoard() {
		System.out.println();
		System.out.println("삭제할 게시글의 번호를 입력하세요 >> ");
		
		int BoardNo = 0;
		try {
			BoardNo = scan.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("번호를 입력해 주세요");
			scan.nextLine();
			return;
		}

		int cnt = service.deleteBoard(BoardNo);

		if (cnt > 0) {
			System.out.println("게시글 삭제 성공!");
		} else {
			System.out.println("게시글 삭제 실패!");
		}
		
	}

	/**
	 * 게시글의 정보를 수정하기 위한 메서드
	 */
	private void updateBoard() {
		System.out.println();

		System.out.println("수정할 게시글의 정보를 입력하세요");
		System.out.println("수정할 게시글의 번호 >> ");
		String boardNO = scan.nextLine();
		
		scan.nextLine(); // 입력버퍼 지우기
		System.out.println("새로운 게시글의 제목 >> ");
		String boardTitle = scan.nextLine();
		
		System.out.println("새로운 게시글의 내용 >> ");
		String boardContent= scan.nextLine();
		
		BoardVO board = new BoardVO();
		
		board.setBoard_no(boardNO);
		board.setBoard_title(boardTitle);
		board.setBoard_content(boardContent);
	

		int cnt = service.updateBoard(board);

		if (cnt > 0) {
			System.out.println("게시글 정보를 수정했습니다.");
		} else {
			System.out.println("게시글 정보 수정 실패!!!");
		}
		
	}

	/**
	 * 게시글의 목록을 출력하는 메서드
	 */
	private void dispalyAllBoard() {
		System.out.println();
		System.out.println("---------------------------------------------------------");
		System.out.println(" 글 번호\t글제목\t작성자\t작성일자\t\t\t글 내용");
		System.out.println("---------------------------------------------------------");

		
		List<BoardVO> boardList = service.dispalyAllBoard();
		
		for (BoardVO board : boardList) {
			System.out.println(board);
		}

		System.out.println("---------------------------------------------------------");
		System.out.println("출력 작업 끝.");
		
	}

	/**
	 * 게시글을 작성하여 추가하는 메서드
	 */
	private void insertBoard() {
		System.out.println();
		System.out.println("작성할 게시글의 정보를 입력하세요");
		System.out.println("글 제목 >> ");
		
		scan.nextLine();
		String boardTitle = scan.nextLine();
		
		System.out.println("작성자 >> ");
		String boardWriter = scan.next();
		
		System.out.println("글 내용 >> ");
		scan.nextLine();
		String boardContent = scan.nextLine();
		
		BoardVO board = new BoardVO();
		
		board.setBoard_title(boardTitle);
		board.setBoard_writer(boardWriter);
		board.setBoard_content(boardContent);
		
		int cnt = service.insertBoard(board);
		
		if (cnt > 0) {
			System.out.println("게시글 작성 성공!!");
		} else {
			System.out.println("게시글 작성 실패!!");
		}
		
	}

}
