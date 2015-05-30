<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="databeans.Position"%>
<%@ page import="databeans.Customer"%>
<%@ page import="databeans.Fund"%>
<%@ page import="databeans.Fund_Price_History"%>
<jsp:include page="template-employee.jsp" />
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">View Customer Account</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-8">
			<div class="input-group custom-search-form">
				<form action="view_account_e.doe">
					<input type="text" name="username"
						placeholder="Please Enter Username..." size="30" width="15"
						required="required">
					<button class="btn btn-default" type="submit">
						<i class="fa fa-search"></i>
					</button>
				</form>
				<p>${message}</p >
			</div>
		</div>
	</div>
	<c:if test="${target!=null}">
		<div class="row">
			<div class="col-lg-6">

				<table class="table table-bordered">
					<tbody>
						<tr class="success">
							<td>UserName</td>
							<td>${target.username}</td>
						</tr>
						<tr class="active">
							<td>Name</td>
							<td>${target.firstName}${target.lastName}</td>
						</tr>
						<tr class="success">
							<td>Address</td>
							<td>${target.addr_line1}${target.addr_line2}${target.city}</td>
						</tr>
						<tr class="active">
							<td>Last Trading Day</td>
							<td><fmt:formatDate type="date" pattern="MM/dd/yyyy"
									value="${Tradingdate}" /></td>
						</tr>
						<tr class="success">
							<td>Cash Balance ($)</td>
							<td>${cash}</td>
						</tr>
						<tr class="active">
							<td>Available Cash ($)</td>
							<td>${availableCash}</td>
						</tr>
						<!-- new column -->
					</tbody>
				</table>
				<table class="table">
					<thead>
						<tr>
							<th>Fund</th>
							<th>Shares</th>
							<th>Shares Available</th>
							<th>Price ($)</th>
							<th>Value ($)</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="fundInfo" items="${fundInfoList}">
							<c:if test="${fundInfo.shares!=0}">
								<tr class="danger">
									<td>${fundInfo.name}</td>
									<td>${fundInfo.shares}</td>
									<td>${fundInfo.availableShares}</td>
									<td style="text-align: right"><fmt:formatNumber
											value="${fundInfo.price}" type="number" pattern="#,##0.00" />
									</td>
									<td style="text-align: right"><fmt:formatNumber
											value="${fundInfo.value}" type="number" pattern="#,##0.00" /></td>

								</tr>
							</c:if>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</c:if>
	<!-- /.row -->
</div>
<jsp:include page="template-bottom.jsp" />