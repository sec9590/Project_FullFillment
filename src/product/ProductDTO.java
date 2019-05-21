package product;

public class ProductDTO {
	private int p_id;
	private String p_name;
	private String p_img;
	private String p_price;
	private int p_quantity;
	private String buycode;
	
	
	
	public ProductDTO(String p_name, String p_img, String p_price, int p_quantity, String buycode) {
		super();
		this.p_name = p_name;
		this.p_img = p_img;
		this.p_price = p_price;
		this.p_quantity = p_quantity;
		this.buycode = buycode;
	}

	public ProductDTO(int p_id, String p_name, String p_img, String p_price, int p_quantity, String buycode) {
		super();
		this.p_id = p_id;
		this.p_name = p_name;
		this.p_img = p_img;
		this.p_price = p_price;
		this.p_quantity = p_quantity;
		this.buycode = buycode;
	}

	public ProductDTO() {
		// TODO Auto-generated constructor stub
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

	public String getP_img() {
		return p_img;
	}

	public void setP_img(String p_img) {
		this.p_img = p_img;
	}

	public String getP_price() {
		return p_price;
	}

	public void setP_price(String p_price) {
		this.p_price = p_price;
	}

	public int getP_quantity() {
		return p_quantity;
	}

	public void setP_quantity(int p_quantity) {
		this.p_quantity = p_quantity;
	}

	public String getBuycode() {
		return buycode;
	}

	public void setBuycode(String buycode) {
		this.buycode = buycode;
	}

	@Override
	public String toString() {
		return "ProductDTO [p_id=" + p_id + ", p_name=" + p_name + ", p_img=" + p_img + ", p_price=" + p_price
				+ ", p_quantity=" + p_quantity + ", buycode=" + buycode + "]";
	}
	
	
	
}
