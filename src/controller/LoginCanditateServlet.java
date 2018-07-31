package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.google.gson.Gson;

import BO.CandidateLogin;
import DAO.CandidateDAO;

/**
 * Servlet implementation class LoginCanditateServlet
 */
@WebServlet("/logcan")
public class LoginCanditateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Resource(name="oracledb")
	public DataSource dataSource;       
	   	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CandidateDAO.dataSource=this.dataSource;
		
		}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
CandidateDAO.dataSource = this.dataSource;
		
		InputStreamReader reader = new InputStreamReader(req.getInputStream());
		BufferedReader br = new BufferedReader(reader);
		String jsonString = br.readLine();
		
		
		Gson gson = new Gson();
		CandidateLogin can =  gson.fromJson(jsonString, CandidateLogin.class);
		
		int i = CandidateDAO.fetchCandidate(can);
			
		if(i>0) {
			System.out.println("login succesful");
			String resp ="Welcome, "+can.getUname();
			res.getWriter().write(gson.toJson(resp));
			
		}else {
			res.getWriter().write("-1");
		}
	}
	}

