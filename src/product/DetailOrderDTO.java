package product;

public class DetailOrderDTO {
	private int d_id;
	private int o_id;
	private int p_id;
	private String p_name;
	private String p_price;
	private int p_count;
	private int total;
	private int o_quantity;
	private int p_total;
	
	

	public int getP_total() {
		return p_total;
	}

	public void setP_total(int p_total) {
		this.p_total = p_total;
	}

	public DetailOrderDTO() {
		super();
	}
	
	public int getP_count() {
		return p_count;
	}

	public void setP_count(int p_count) {
		this.p_count = p_count;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getP_price() {
		return p_price;
	}

	public void setP_price(String p_price) {
		this.p_price = p_price;
	}

	public int getD_id() {
		return d_id;
	}
	public void setD_id(int d_id) {
		this.d_id = d_id;
	}
	public int getO_id() {
		return o_id;
	}
	public void setO_id(int o_id) {
		this.o_id = o_id;
	}
	public int getP_id() {
		return p_id;
	}
	public void setP_id(int p_id) {
		this.p_id = p_id;
	}
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public int getO_quantity() {
		return o_quantity;
	}
	public void setO_quantity(int o_quantity) {
		this.o_quantity = o_quantity;
	}
	@Override
	public String toString() {
		return "DetailOrderDTO [d_id=" + d_id + ", o_id=" + o_id + ", p_id=" + p_id + ", p_name=" + p_name
				+ ", o_quantity=" + o_quantity + "]";
	}

	
	
	
	
}
