<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="mx-auto">
			<a class="navbar-brand" href="index.jsp">Shopping Cart</a>
		</div>
	<div class="container mr-auto">
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto mt-2 mt-lg-0">
				<li class="nav-item active"><a class="nav-link" href="index.jsp">Home</a></li>
				<li class="nav-item"><a class="nav-link" href="cart.jsp">Cart
						<span class="badge badge-danger">${cart_list.size()}</span>
				</a></li>
				<%
					if (auth != null) {
				%>
				<li class="nav-item"><a class="nav-link" href="order.jsp">Orders</a></li>
				<li class="nav-item"><a class="nav-link" href="log-out">Logout</a></li>
				<%
					} else {
				%>
				<li class="nav-item"><a class="nav-link" href="login.jsp">Login</a></li>
				<%
					}
				%>
			</ul>
		</div>
	</div>
</nav>