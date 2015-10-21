import java.io.IOException;
import java.nio.ByteBuffer;

public class Question {
	private byte[] qName = new byte [256];
	private short qType;
	private short qClass;
	private String questionName;

	public short getqType() {
		return qType;
	}

	public void setqType(short qType) {
		this.qType = qType;
	}

	public short getqClass() {
		return qClass;
	}

	public void setqClass(short qClass) {
		this.qClass = qClass;
	}

	public String getqName() {
		return questionName;
	}

	public byte[] toBytes() throws IOException {
		ByteBuffer buf = ByteBuffer.wrap(new byte[260]);
		
		buf.put(qName);		
		buf.putShort(qType);
		buf.putShort(qClass);

		return buf.array();
	}
	
	public Question fromBytes(ByteBuffer buf) {
		String name = "";
		int index = 0;
		byte item = buf.get();
		qName[index] = item;
		index++;
		while(item != 0){
			
			for(int i = 0; i < (int) item; i++){
				byte temp = buf.get();
				qName[index] = item;
				index++;
				name += (char) temp;
			}
			
			item = buf.get();
			qName[index] = item;
			index++;
			if(item != 0){
				name += ".";
			}
		}
		
		questionName = name;
		qType = buf.getShort();
		qClass = buf.getShort();
		return this;
	}
	
	public String toString(){
		return  "name: " + questionName + " " +
				"type: " + qType + " " +
				"class: " + qClass;
	}
}