package product;

public class CommodityDTO {

	private int c_id;
	private int p_id;
	private int basic; // 기초재고
	private int close; // 기말재고
	private int in; // 입고
	private int out; // 출고

	@Override
	public String toString() {
		return "CommodityDTO [c_id=" + c_id + ", p_id=" + p_id + ", basic=" + basic + ", close=" + close + ", in=" + in
				+ ", out=" + out + "]";
	}

	public CommodityDTO() {
		super();
	}

	public CommodityDTO(int c_id, int p_id, int basic, int close, int in, int out) {
		super();
		this.c_id = c_id;
		this.p_id = p_id;
		this.basic = basic;
		this.close = close;
		this.in = in;
		this.out = out;
	}

	public int getC_id() {
		return c_id;
	}

	public void setC_id(int c_id) {
		this.c_id = c_id;
	}

	public int getP_id() {
		return p_id;
	}

	public void setP_id(int p_id) {
		this.p_id = p_id;
	}

	public int getBasic() {
		return basic;
	}

	public void setBasic(int basic) {
		this.basic = basic;
	}

	public int getClose() {
		return close;
	}

	public void setClose(int close) {
		this.close = close;
	}

	public int getIn() {
		return in;
	}

	public void setIn(int in) {
		this.in = in;
	}

	public int getOut() {
		return out;
	}

	public void setOut(int out) {
		this.out = out;
	}

}
