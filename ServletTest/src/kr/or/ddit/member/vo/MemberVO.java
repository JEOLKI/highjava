package kr.or.ddit.member.vo;

/**
 * DB 테이블에 있는 컬럼을 기준으로 데이터를 객체화한 클래스이다.
 * @author PC-01
 * <p>
 * DB테이블의 '컬럼'이 이 클래스의 '멤버변수'가 된다. <br>
 * DB테이블의 컬럼과 클래스의 멤버변수를 매핑하는 역할을 수행한다. <br>
 * </p>
 */
public class MemberVO {
	private String mem_id;
	private String mem_name;
	private String mem_tel;
	private String mem_addr;
	private int atch_file_id;
	
	
	public int getAtch_file_id() {
		return atch_file_id;
	}

	public void setAtch_file_id(int atch_file_id) {
		this.atch_file_id = atch_file_id;
	}
	

	public MemberVO(String mem_id, String mem_name, String mem_tel, String mem_addr) {
		super();
		this.mem_id = mem_id;
		this.mem_name = mem_name;
		this.mem_tel = mem_tel;
		this.mem_addr = mem_addr;
	}

	public MemberVO() {
		//IBatis에서 오류 방지를 위한 기본생성자를 직접 명시적으로 만들어준다.
	}
	
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	public String getMem_tel() {
		return mem_tel;
	}
	public void setMem_tel(String mem_tel) {
		this.mem_tel = mem_tel;
	}
	public String getMem_addr() {
		return mem_addr;
	}
	public void setMem_addr(String mem_addr) {
		this.mem_addr = mem_addr;
	}
	
	@Override
	public String toString() {
		return  mem_id + "\t" + mem_name + "\t" + mem_tel + "\t\t" + mem_addr;
	}
	
	
	
}
