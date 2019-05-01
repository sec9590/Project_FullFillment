package product;

public class OrdersDTO {
	private int o_id;
	private String o_name;
	private String o_tel;
	private String o_address;
	private int p_id;
	private String p_name;
	private int o_quantity;
	private String o_time;
	
	public OrdersDTO() {
	}
	
	
	public OrdersDTO(int o_id, String o_name, String o_tel, String o_address, int p_id, String p_name, int o_quantity,
			String o_time) {
		this.o_id = o_id;
		this.o_name = o_name;
		this.o_tel = o_tel;
		this.o_address = o_address;
		this.p_id = p_id;
		this.p_name = p_name;
		this.o_quantity = o_quantity;
		this.o_time = o_time;
	}


	public int getO_id() {
		return o_id;
	}


	public void setO_id(int o_id) {
		this.o_id = o_id;
	}


	public String getO_name() {
		return o_name;
	}


	public void setO_name(String o_name) {
		this.o_name = o_name;
	}


	public String getO_tel() {
		return o_tel;
	}


	public void setO_tel(String o_tel) {
		this.o_tel = o_tel;
	}


	public String getO_address() {
		return o_address;
	}


	public void setO_address(String o_address) {
		this.o_address = o_address;
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


	public String getO_time() {
		return o_time;
	}


	public void setO_time(String o_time) {
		this.o_time = o_time;
	}


	@Override
	public String toString() {
		return "OrdersDTO [o_id=" + o_id + ", o_name=" + o_name + ", o_tel=" + o_tel + ", o_address=" + o_address
				+ ", p_id=" + p_id + ", p_name=" + p_name + ", o_quantity=" + o_quantity + ", o_time=" + o_time + "]";
	}
	
	
}
