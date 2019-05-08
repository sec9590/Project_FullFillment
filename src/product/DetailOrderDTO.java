package product;

public class DetailOrderDTO {
	private int d_id;
	private int o_id;
	private int p_id;
	private String p_name;
	private int o_quantity;

	public DetailOrderDTO() {
		super();
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
