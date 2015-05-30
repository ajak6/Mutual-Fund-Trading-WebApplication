<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="databeans.Position"%>
<%@ page import="databeans.Customer"%>
<%@ page import="databeans.Fund"%>
<%@ page import="databeans.Fund_Price_History"%>

<jsp:include page="template-customer.jsp" />
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">View Account</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-8">
			<table class="table">
				<tbody>
					<tr class="success">
						<td>UserName</td>
						<td>${target.username}</td>
					</tr>
					<tr class="active">
						<td>Name</td>
						<td>${target.firstName} ${target.lastName}</td>
					</tr>
					<tr class="success">
						<td>Address</td>
						<td>${target.addr_line1} ${target.addr_line2} ${target.city}</td>
					</tr>
					<tr class="active">
						<td>Last Trading Day</td>
						<td><fmt:formatDate type="date" pattern="MM/dd/yyyy" value="${Tradingdate}" /></td>
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
			<table class="table table-bordered">
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
	<!-- /.row -->
</div>
<jsp:include page="template-bottom.jsp" />