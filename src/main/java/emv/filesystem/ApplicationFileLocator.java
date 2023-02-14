package emv.filesystem;

import java.io.ByteArrayInputStream;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import exception.SmartCardException;

public class ApplicationFileLocator {

	private LinkedList<ApplicationElementaryFile> files = new LinkedList<>();
	
	public List<ApplicationElementaryFile> getApplicationElementaryFiles() {
		return Collections.unmodifiableList(files);
	}
	
	public ApplicationFileLocator(byte[] data) {
		
		if (data.length % 4 != 0)
			throw new SmartCardException("AFL is not a multiple of 4");
		
		ByteArrayInputStream stream = new ByteArrayInputStream(data);
		
		while (stream.available() > 0) {
			byte[] temp = new byte[4];
			stream.read(temp, 0, temp.length);
			files.add(new ApplicationElementaryFile(temp));
		}
		
	}
}
