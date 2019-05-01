package product;

public class WaybillDTO {
	private int w_id;
	private int o_id;
	private String o_name;
	private String o_tel;
	private String o_address;
	private String p_name;
	private int o_quantity;
	
	public WaybillDTO() {
	}
	
	public WaybillDTO(int w_id, int o_id, String o_name, String o_tel, String o_address, String p_name,
			int o_quantity) {
		super();
		this.w_id = w_id;
		this.o_id = o_id;
		this.o_name = o_name;
		this.o_tel = o_tel;
		this.o_address = o_address;
		this.p_name = p_name;
		this.o_quantity = o_quantity;
	}

	public int getW_id() {
		return w_id;
	}

	public void setW_id(int w_id) {
		this.w_id = w_id;
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
		return "WaybillDTO [w_id=" + w_id + ", o_id=" + o_id + ", o_name=" + o_name + ", o_tel=" + o_tel
				+ ", o_address=" + o_address + ", p_name=" + p_name + ", o_quantity=" + o_quantity + "]";
	}
	
	
}
