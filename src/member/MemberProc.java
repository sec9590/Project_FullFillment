package member;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/member/memberProcServlet")
public class MemberProc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberProc() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}

	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDAO mDao = null;
		MemberDTO member = null;
		RequestDispatcher rd = null;
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		List<String> pageList = new ArrayList<String>();
		String m_id = null;
		String m_password = null;
		String m_name = null;
		String m_tel = null;
		String m_field = null;
		
		switch(action) {
		case "login" :
			if (!request.getParameter("m_id").equals("")) {
				m_id = request.getParameter("m_id");
			}
			m_password = request.getParameter("m_password");
			
			mDao = new MemberDAO();
			int result = mDao.verifyIdPassword(m_id, m_password);
			String errorMessage = null;
			switch (result) {
			case MemberDAO.ID_PASSWORD_MATCH:
				break;
			case MemberDAO.ID_DOES_NOT_EXIST:
				errorMessage = "ID가 없음"; break;
			case MemberDAO.PASSWORD_IS_WRONG:
				errorMessage = "패스워드가 틀렸음"; break;
			case MemberDAO.DATABASE_ERROR:
				errorMessage = "DB 오류";
			}
			
			if (result == MemberDAO.ID_PASSWORD_MATCH) {
				member = mDao.searchById(m_id);
				session.setAttribute("memberId", m_id);
				session.setAttribute("memberName", member.getM_name());
				response.sendRedirect("main.jsp");
			} else {
				request.setAttribute("message", errorMessage);
				request.setAttribute("url", "login.jsp");
				rd = request.getRequestDispatcher("alertMsg.jsp");
		        rd.forward(request, response);
			}
			mDao.close();
			break;
			
		case "logout":			// 로그아웃할 때
			session.removeAttribute("memberId");
			session.removeAttribute("memberName");
			response.sendRedirect("login.jsp");
			break;
			
		case "signup":		// 회원 가입
			m_id = request.getParameter("m_id");
			m_password = request.getParameter("m_password");
			m_name = request.getParameter("m_name");
			m_tel = request.getParameter("m_tel");
			m_field = request.getParameter("m_field");
			member = new MemberDTO(m_id, m_password, m_name, m_tel, m_field);
			System.out.println(member.toString());
			
			mDao = new MemberDAO();
			mDao.insertMember(member);

			response.sendRedirect("login.jsp");
			mDao.close();
			break;
		}
		
		
	}
	
}