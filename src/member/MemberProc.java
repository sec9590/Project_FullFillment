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
import javax.servlet.http.Cookie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@WebServlet("/memberProcServlet")
public class MemberProc extends HttpServlet {
	private static final Logger LOG = LoggerFactory.getLogger(MemberProc.class);
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
		String m_id = null;
		String m_password = null;
		String m_name = null;
		String m_tel = null;
		String m_job = null;
		String m_field = null;
		String field = null;
		String page = null;
		int pagecount = 0;
		int curPage = 0;
		int pageNo = 0;
		List<String> pageList = new ArrayList<String>();
		Cookie[] cookies = null;
		String cookieId = null;
		String url = null;
		
		if(!action.equals("login")) {
			cookies = request.getCookies();
			for (Cookie cookie: cookies) {
				if (cookie.getName().equals("Yellow"))
					cookieId = cookie.getValue();
			} 
			request.setAttribute("cookieId", cookieId);
		}
		
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
				cookieId = member.getM_id();
				Cookie cookie = new Cookie("Yellow", cookieId);
								
				/*session.setAttribute("memberId", m_id);
				session.setAttribute("memberName", member.getM_name());
				session.setAttribute("memberJob", member.getM_job());
				session.setAttribute("memberField", member.getM_field());*/
				
				String pg = member.getM_job();		
								
				switch(pg) {
				case "0" :				
					cookie.setPath("/project02/");
					url = "admin/index.jsp";
					
					break;
				case "1" :
					cookie.setPath("/project02/");
					field = "OrdersProcServlet?action=buyinglist&field=" + member.getM_field();
					request.setAttribute("field", member.getM_field());				
					/*rd = request.getRequestDispatcher("buying/index.jsp");
					rd.forward(request, response);*/
					url = "buying/index.jsp";				
					break;
				case "2" :
					cookie.setPath("/project02/");
					field = "WaybillProcServlet?action=carrierlist&field=" + member.getM_field();
					request.setAttribute("field", member.getM_field());
					/*rd = request.getRequestDispatcher("carrier/index.jsp");
					rd.forward(request, response);*/
					url = "carrier/index.jsp";		
					break;
				}
				LOG.info("로그인성공");							
				response.addCookie(cookie);
				System.out.println("coo : " + cookie);
				// request.setAttribute("cookieId", cookieId);
				session.setAttribute(cookieId + "memberId", m_id);
				session.setAttribute(cookieId + "memberName", member.getM_name());
				session.setAttribute(cookieId + "memberJob", member.getM_job());
				session.setAttribute(cookieId + "memberField", member.getM_field());
				System.out.println("이름 : " + member.getM_name());
				System.out.println("필드 : " + member.getM_field());
				
				response.sendRedirect(url);
				
			} else {
				request.setAttribute("message", errorMessage);
				request.setAttribute("url", "login.jsp");
				rd = request.getRequestDispatcher("alertMsg.jsp");
		        rd.forward(request, response);
			}
			mDao.close();
			break;
			
		case "logout":			// 로그아웃할 때
			cookies = request.getCookies();
			cookieId = null;
			for (Cookie cookie: cookies) {
				if (cookie.getName().equals("Yellow"))
					cookieId = cookie.getValue();
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
			session.removeAttribute(cookieId+"memberId");
			session.removeAttribute(cookieId+"memberName");
			session.removeAttribute(cookieId+"memberJob");
			session.removeAttribute(cookieId+"memberField");
			//session.invalidate();
			response.sendRedirect("index.jsp");
			break;
			
		case "signup":		// 회원 가입
			m_id = request.getParameter("m_id");
			m_password = request.getParameter("m_password");
			m_name = request.getParameter("m_name");
			m_tel = request.getParameter("m_tel");
			m_job = request.getParameter("m_job");
			m_field = request.getParameter("m_field");
			member = new MemberDTO(m_id, m_password, m_name, m_tel, m_job, m_field);
			LOG.trace(member.toString());
			
			mDao = new MemberDAO();
			mDao.insertMember(member);

			response.sendRedirect("index.jsp");
			mDao.close();
			break;
			
		// 회원목록 조회
		case "member":
			if (!request.getParameter("page").equals("")) {
				curPage = Integer.parseInt(request.getParameter("page"));
			}
			mDao = new MemberDAO();
			pagecount = mDao.getCount();
			if (pagecount == 0) // 데이터가 없을 때 대비
				pagecount = 1;
			pageNo = (int) Math.ceil(pagecount / 10.0);
			if (curPage > pageNo) // 경계선에 걸렸을 때 대비
				curPage--;
			session.setAttribute("currentMemberPage", curPage);
			// 리스트 페이지의 하단 페이지 데이터 만들어 주기
			page = null;
			page = "<a href=#>&laquo;</a>&nbsp;";
			pageList.add(page);
			for (int i = 1; i <= pageNo; i++) {
				page = "&nbsp;<a href=memberProcServlet?action=member&page=" + i + ">" + i + "</a>&nbsp;";
				pageList.add(page);
			}
			page = "&nbsp;<a href=#>&raquo;</a>";
			pageList.add(page);
			
			List<MemberDTO> memberAll = mDao.memberAll(curPage);
			request.setAttribute("memberAll", memberAll);
			request.setAttribute("pageList", pageList);
			rd = request.getRequestDispatcher("admin/member_list.jsp");
			rd.forward(request, response);
		}
	}
	
}