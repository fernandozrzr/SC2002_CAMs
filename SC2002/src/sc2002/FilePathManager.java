package sc2002;

public class FilePathManager {
	
	private static String[] filePathList;
	
	//constructor
	public FilePathManager(String[] filePathList){
		this.filePathList = filePathList;
	}
	
	public static String getFilePath(int domain) {
		filePathList[0] = "src\\student_list.txt";
		filePathList[1] = "src\\staff_list.txt";
		
	return filePathList[domain-1];
	}
	
	
	

}
