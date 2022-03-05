package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
 * Servlet implementation class OrderNowServlet
 */
@WebServlet("/order-now")
public class OrderNowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrderNowServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();

			UserModel auth = (UserModel) request.getSession().getAttribute("auth");

			if (auth != null) {
				String productId = request.getParameter("id");
				int productQuantity = Integer.parseInt(request.getParameter("quantity"));
				if (productQuantity <= 0) {
					productQuantity = 1;
				}
				Order orderModel = new Order();
				orderModel.setId(Integer.parseInt(productId));
				orderModel.setUid(auth.getId());
				orderModel.setQunatity(productQuantity);
				orderModel.setDate(formatter.format(date));

				OrderDao orderDao = new OrderDao(DBConnection.getConnection());
				boolean result = orderDao.insertOrder(orderModel);
				if (result) {
					ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
					if (cart_list != null) {
						for (Cart c : cart_list) {
							if (c.getId() == Integer.parseInt(productId)) {
								cart_list.remove(cart_list.indexOf(c));
								break;
							}
						}
					}
					response.sendRedirect("order.jsp");
				} else {
					out.println("order failed");
				}
			} else {
				response.sendRedirect("login.jsp");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
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
