package kr.or.ddit.structural.proxy;

public class RealImage implements Image{
	
	private String fileName;
	
	public RealImage(String fileName) {
		this.fileName = fileName;

		loadFromDisk(fileName);
		
	}
	
	private void loadFromDisk(String fileName2) {
		System.out.println("디스크로부터 로딩 중... => " + fileName);
	}

	@Override
	public void dispaly() {
		System.out.println("화면 출력 하고 있음 => " + fileName);
	}
	
}
