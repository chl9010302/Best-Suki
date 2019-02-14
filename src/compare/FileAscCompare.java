package compare;

import java.util.Comparator;

import client.ReceiveData;

public class FileAscCompare implements Comparator<ReceiveData> {
	
	
	public int compare(ReceiveData arg1, ReceiveData arg2) {
		return arg1.getFileDate().compareTo(arg2.getFileDate());
	}

}
