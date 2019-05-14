package product;

public class CommodityDTO {

	private int c_id;
	private int p_id;
	private int c_basic; // 기초재고
	private int c_close; // 기말재고
	private int c_in; // 입고
	private int c_out; // 출고
	private String c_time;

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

	public int getC_basic() {
		return c_basic;
	}

	public void setC_basic(int c_basic) {
		this.c_basic = c_basic;
	}

	public int getC_close() {
		return c_close;
	}

	public void setC_close(int c_close) {
		this.c_close = c_close;
	}

	public int getC_in() {
		return c_in;
	}

	public void setC_in(int c_in) {
		this.c_in = c_in;
	}

	public int getC_out() {
		return c_out;
	}

	public void setC_out(int c_out) {
		this.c_out = c_out;
	}

	public String getC_time() {
		return c_time;
	}

	public void setC_time(String c_time) {
		this.c_time = c_time;
	}

	public CommodityDTO(int c_id, int p_id, int c_basic, int c_close, int c_in, int c_out, String c_time) {
		super();
		this.c_id = c_id;
		this.p_id = p_id;
		this.c_basic = c_basic;
		this.c_close = c_close;
		this.c_in = c_in;
		this.c_out = c_out;
		this.c_time = c_time;
	}

	public CommodityDTO() {
		super();
	}

	@Override
	public String toString() {
		return "CommodityDTO [c_id=" + c_id + ", p_id=" + p_id + ", c_basic=" + c_basic + ", c_close=" + c_close
				+ ", c_in=" + c_in + ", c_out=" + c_out + ", c_time=" + c_time + "]";
	}

}
