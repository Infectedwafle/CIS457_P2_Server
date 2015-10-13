import java.io.IOException;
import java.nio.ByteBuffer;


public class Header {
	private short id;
	private boolean request;
	private Header.Opcode opcode;
	private boolean authoritativeAnswer;
	private boolean truncated;
	private boolean recursion;
	private Header.ResponseCode responseCode;
	private short questionEntries;
	private short answerEntries;
	private short authorityRecords;
	private short additionalRecords;
	private short flags; //added not sure if needed. 
	
	public short getId() {
		return id;
	}

	public void setId(short id) {
		this.id = id;
	}

	public boolean isRequest() {
		return request;
	}

	public void setRequest(boolean request) {
		this.request = request;
	}

	public Header.Opcode getOpcode() {
		return opcode;
	}

	public void setOpcode(Header.Opcode opcode) {
		this.opcode = opcode;
	}

	public boolean isAuthoritativeAnswer() {
		return authoritativeAnswer;
	}

	public void setAuthortativeAnwser(boolean authortativeAnwser) {
		this.authoritativeAnswer = authortativeAnwser;
	}

	public boolean isTruncated() {
		return truncated;
	}

	public void setTruncated(boolean truncated) {
		this.truncated = truncated;
	}

	public boolean isRecursion() {
		return recursion;
	}

	public void setRecursion(boolean recursion) {
		this.recursion = recursion;
	}

	public Header.ResponseCode getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(Header.ResponseCode responseCode) {
		this.responseCode = responseCode;
	}

	public short getQuestionEntries() {
		return questionEntries;
	}

	public void setQuestionEntries(short questionEntries) {
		this.questionEntries = questionEntries;
	}

	public short getAnwserEntries() {
		return answerEntries;
	}

	public void setAnwserEntries(short answerEntries) {
		this.answerEntries = answerEntries;
	}

	public short getAuthorityRecords() {
		return authorityRecords;
	}

	public void setAuthorityRecords(short authorityRecords) {
		this.authorityRecords = authorityRecords;
	}

	public short getAdditionalRecords() {
		return additionalRecords;
	}

	public void setAdditionalRecords(short additionalRecords) {
		this.additionalRecords = additionalRecords;
	}
	//added by jpowers
	public void setFlags(short flags){
		this.flags = flags;
	}
	//added by jpowers
	public short getFlags(){
		return flags;
	}
	//Edited by Jpowers. 
	//Got my source here: https://github.com/musiKk/dns/blob/master/src/main/java/com/github/musikk/dns/Header.java
	public Header toBytes(ByteBuffer buf) {
		/*
		buf.setId(id);
		flags = (short) ((request ? 0 : 1) << 15);
		flags |= (opcode.getCode() & 0b1111) << 11;
		// AA, TC set in response
		flags |= (recursion ? 1 : 0) << 8;
		// RA, RCODE set in response

		buf.setFlags(flags);
		buf.setQuestionEntries(questionEntries);
		buf.setAnwserEntries(answerEntries);
		buf.setAuthorityRecords(authorityRecords);
		buf.setAdditionalRecords(additionalRecords);
		return this;*/
		return null;
	}
	
	public Header fromBytes(ByteBuffer buf) throws IOException {
		return null;
		//need to implement
	}
	
	public enum ResponseCode {
		NO_ERROR(0), FORMAT_ERROR(1), SERVER_FAILURE(2), NAME_ERROR(3),
		NOT_IMPLEMENTED(4), REFUSED(5);
		private final int code;

		private ResponseCode(int code) {
			this.code = code;
		}
		public int getCode() {
			return code;
		}
		public static Header.ResponseCode byCode(int code) {
			for (Header.ResponseCode r : values()) {
				if (r.code == code) {
					return r;
				}
			}
			throw new IllegalArgumentException("No response for code " + code + " exists.");
		}
	}

	public enum Opcode {
		QUERY(0), IQUERY(1), STATUS(2);
		private final int code;
		private Opcode(int code) {
			this.code = code;
		}
		public int getCode() {
			return code;
		}
		public static Header.Opcode byCode(int code) {
			for (Header.Opcode o : values()) {
				if (o.code == code) {
					return o;
				}
			}
			throw new IllegalArgumentException("No opcode for code "  + code + " exists.");
		}
	}	
}
