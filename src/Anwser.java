import java.nio.ByteBuffer;


public class Anwser {
	String name;
	short type;
	short aClass;
	int ttl;
	short rdlength;
	int rdata;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public short getType() {
		return type;
	}
	public void setType(short type) {
		this.type = type;
	}
	public short getaClass() {
		return aClass;
	}
	public void setaClass(short aClass) {
		this.aClass = aClass;
	}
	public int getTtl() {
		return ttl;
	}
	public void setTtl(int ttl) {
		this.ttl = ttl;
	}
	public short getRdlength() {
		return rdlength;
	}
	public void setRdlength(short rdlength) {
		this.rdlength = rdlength;
	}
	public int getRdata() {
		return rdata;
	}
	public void setRdata(int rdata) {
		this.rdata = rdata;
	}
	
	public Anwser fromBytes(ByteBuffer buf){
		
		
		
		
		return this;
	}
}
