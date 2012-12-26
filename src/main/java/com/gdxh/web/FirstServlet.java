package com.gdxh.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(name = "FirstServlet", urlPatterns = { "/firstservlet" })
public class FirstServlet extends HttpServlet {

	
	private static final long serialVersionUID = 1076709679457974176L;

	private static final Logger log = LoggerFactory
			.getLogger(FirstServlet.class);
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
	PrintWriter out = response.getWriter();
	log.info("This is log");
	out.println("hello,world...");
	out.close();
	}

}
