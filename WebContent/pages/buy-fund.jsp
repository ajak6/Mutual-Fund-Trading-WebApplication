<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<%@ page import="databeans.Customer"%>
<%@ page import="databeans.Fund"%>>

<jsp:include page="template-customer.jsp" />

<script type="text/javascript">
	
</script>


<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">Buy Funds</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>

	<div class="row">
		<div class="col-lg-8">
			<label>Current Available Balance: ${balance}</label>
			<form method="POST" action="buyFund.doc">
				<div class="form-group">
					<label>Amount: </label><input type="text" id="amount" name="amount">
					$ <input class="btn btn-default" type="submit" name="action"
						value="Submit" onClick="success.jsp" /><br/>
					<c:forEach var="error" items="${errors}">
						<h3 style="color: red">${error}</h3>
					</c:forEach>
					<c:forEach var="messages" items="${message}">
						<h3 style="color: red">${messages}</h3>
					</c:forEach>

					<p style="color: #ADADAD">Data precision might cause your money loss.</p>
					<table class="table table-bordered table-hover table-striped">
						<thead>
							<tr>
								<th>Fund Name</th>
								<th>Fund Symbol</th>
								<th>Select to Buy</th>
							</tr>
						</thead>

						<tbody>
							<c:forEach var="fund" items="${ fundlist }">
								<tr>
									<td>${ fund.name }</td>
									<td>${ fund.symbol }</td>
									<td align="center"><input type="radio" name="fundName"
										value="${fund.name}" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>

					<br /> <br />
					</div>
			</form>
		</div>
	</div>
	</div>
<jsp:include page="template-bottom.jsp" />