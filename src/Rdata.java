
public class Rdata {
	private short type;
	private short _class;
	private int ttl;
	private short length;
	
	public short getType() {
		return type;
	}
	public void setType(short type) {
		this.type = type;
	}
	public short get_class() {
		return _class;
	}
	public void set_class(short _class) {
		this._class = _class;
	}
	public int getTtl() {
		return ttl;
	}
	public void setTtl(int ttl) {
		this.ttl = ttl;
	}
	public short getLength() {
		return length;
	}
	public void setLength(short length) {
		this.length = length;
	}	
}
