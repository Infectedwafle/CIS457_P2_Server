import java.nio.ByteBuffer;
public class Question {
    private String domainString;
	private short qType;
	private short qClass;
	private int questionName;
    private ByteBuffer question;
    /*public Question(String message, short type, short c){
         domainString = message;
         qType = type;
         qClass = c;
         updateQuestion();
    }*/
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
        updateQuestion();
	}
    public String getdomainString(){
        return domainString;
    }
    public void setdomainString(String in){
        this.domainString = in;
        updateQuestion();
    }

public int getqName() {
		return questionName;
	}

    public Question fromBytes(ByteBuffer buf) {
		/*id = buf.getShort();
		System.out.println(id + "fag");
		int flags = buf.getShort();
		request = ((flags >> 15) & 1) == 0;
		opcode = buf.getShort((flags >> 11) & 0b1111);
		authoritativeAnswer = ((flags >> 10) & 1) == 1;
		truncated = ((flags >> 9) & 1) == 1;
		recursion = ((flags >> 8) & 1) == 1;
		canRecurse = ((flags >> 7) & 1) == 1;
		responseCode = buf.getShort(flags & 0b1111);
*/
		questionName = buf.getInt();
		qType = buf.getShort();
		qClass = buf.getShort();
		//additionalRecords = buf.getShort();

		return this;
    }
	

    //This method is run whenever an instance variable is changed so the
    private void updateQuestion(){
        this.question.allocate(400);
        String[] tokens = domainString.split("[.]");
        for(String token : tokens){
            question.putShort((short) token.length());
            question.put(token.getBytes());
        }
        question.putShort((short) 0);
        question.putShort(qType);
        question.putShort(qClass);
    }

    public ByteBuffer getQuestion(){
        return question;
    }

}