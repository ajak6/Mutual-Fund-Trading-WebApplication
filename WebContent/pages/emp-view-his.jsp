<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<%@ page import="databeans.Customer"%>
<%@ page import="databeans.Fund"%>
<%@ page import="databeans.Transaction"%>
<jsp:include page="template-employee.jsp" />
<div id="page-wrapper">
	<div class="row">

		<h2>Employee View Customer's History</h2>
		<br />
		<h3>Customer Name:</h3>

		<div class="panel-body">
			<form action="epm_view_trans_his.doe" method="get">
				<input type="text" name="customer" />
				<button class="btn btn-default" type="submit">

					<i class="fa fa-search"></i>
				</button>
			</form>
			<jsp:include page="errorMessages.jsp"></jsp:include>
		</div>
	 <c:if test="${transactionlist!=null}">
		<table class="table table-bordered">
			<thead>
				<tr>
					<th style="text-align: center">Transaction Date</th>
					<th style="text-align: center">Operation</th>
					<th style="text-align: center">Fund Name</th>
					<th style="text-align: center">Number of Shares</th>
					<th style="text-align: center">Share Price ($)</th>
					<th style="text-align: center">Dollar Amount ($)</th>
				</tr>
			</thead>

			<tbody>

				<c:forEach var="transaction" items="${ transactionlist }">


					<tr>

						<td>
					
						<c:if test="${transaction.execute_date!=null}">
						
    					<fmt:formatDate type="date" pattern="MM/dd/yyyy" value="${transaction.execute_date}"/>
						</c:if>
						<c:if test="${transaction.execute_date==null}">
    							Pending
						</c:if>
						</td>
						<td>${transaction.transaction_type}</td>
						<td>${transaction.fund_name}</td>
						<td>${transaction.share_number}</td>
						<td style="text-align: right">${transaction.share_price}</td>
						<td style="text-align: right">${transaction.amount}</td>


					</tr>

				</c:forEach>
			</tbody>

		</table>
</c:if>

		<%-- <c:forEach var="error" items="${errors}">
			<h3 style="color: red">${error}</h3>
		</c:forEach>
		<c:forEach var="messages" items="${message}">
			<h3 style="color: red">${messages}</h3>
		</c:forEach>
 --%>

	</div>
</div>

<jsp:include page="template-bottom.jsp" />

