package member;

public class MemberDTO {
	private String m_id;
	private String m_password;
	private String m_name;
	private String m_tel;
	private String m_job;
	private String m_field;
	
	public MemberDTO() {

	}
	
	
	public MemberDTO(String m_id, String m_password, String m_name, String m_tel, String m_job, String m_field) {
		this.m_id = m_id;
		this.m_password = m_password;
		this.m_name = m_name;
		this.m_tel = m_tel;
		this.m_job = m_job;
		this.m_field = m_field;
	}


	public String getM_id() {
		return m_id;
	}


	public void setM_id(String m_id) {
		this.m_id = m_id;
	}


	public String getM_password() {
		return m_password;
	}


	public void setM_password(String m_password) {
		this.m_password = m_password;
	}


	public String getM_name() {
		return m_name;
	}


	public void setM_name(String m_name) {
		this.m_name = m_name;
	}


	public String getM_tel() {
		return m_tel;
	}


	public void setM_tel(String m_tel) {
		this.m_tel = m_tel;
	}


	public String getM_field() {
		return m_field;
	}


	public void setM_field(String m_field) {
		this.m_field = m_field;
	}



	public String getM_job() {
		return m_job;
	}


	public void setM_job(String m_job) {
		this.m_job = m_job;
	}


	@Override
	public String toString() {
		return "MemberDTO [m_id=" + m_id + ", m_password=" + m_password + ", m_name=" + m_name + ", m_tel=" + m_tel
				+ ", m_job=" + m_job + ", m_field=" + m_field + "]";
	}
}
