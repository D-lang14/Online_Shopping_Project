package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.OrderDao;
import com.pojo.Cart;
import com.pojo.Order;
import com.pojo.UserModel;
import com.util.DBConnection;

/**
 * Servlet implementation class CheckOutServlet
 */
@WebServlet("/cart-check-out")
public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CheckOutServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try (PrintWriter out = response.getWriter()) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
			UserModel auth = (UserModel) request.getSession().getAttribute("auth");
			if (cart_list != null && auth != null) {
				for (Cart c : cart_list) {
					Order order = new Order();
					order.setId(c.getId());
					order.setUid(auth.getId());
					order.setQunatity(c.getQuantity());
					order.setDate(formatter.format(date));

					OrderDao oDao = new OrderDao(DBConnection.getConnection());
					boolean result = oDao.insertOrder(order);
					if (!result)
						break;
				}
				cart_list.clear();
				response.sendRedirect("order.jsp");
			} else {
				if (auth == null) {
					response.sendRedirect("login.jsp");
				}
				response.sendRedirect("cart.jsp");
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
