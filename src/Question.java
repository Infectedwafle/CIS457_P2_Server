import java.nio.ByteBuffer;

public class Question {
	private String domainString;
	private short qType;
	private short qClass;
	private long questionName;
	private ByteBuffer question;

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

	public String getdomainString() {
		return domainString;
	}

	public long getqName() {
		return questionName;
	}

	public Question fromBytes(ByteBuffer buf) {
		String name = "";
		byte item = buf.get();
		while(item != 0){
			
			for(int i = 0; i < (int) item; i++){
				byte temp = buf.get();
				name += (char) temp;
			}
			
			item = buf.get();
			if(item != 0){
				name += ".";
			}
		}
		
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