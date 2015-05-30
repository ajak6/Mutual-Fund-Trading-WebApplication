<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="databeans.Position"%>
<%@ page import="databeans.Fund"%>

<jsp:include page="template-customer.jsp" />
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">Sell Fund</h1>
		</div>
	</div>

	<div class="row">
		<div class="col-lg-8">
			<p>${message}</p>
			<p style="color: #ADADAD">Data precision might cause money loss.</p>
			<table class="table">
				<thead>
					<tr>
						<th>Fund</th>
						<th>Shares</th>
						<th>Shares Available</th>
						<th>Amount to Sell</th>
						<th>Sell</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="fundInfo" items="${fundInfoList}">
					<c:if test="${fundInfo.shares!=0}">
						<tr class="active">
							<form method="POST" action="sellfund.doc">
								<td>${fundInfo.name}<input type="hidden" name="fundToSell"
									value="${fundInfo.fund_id}" /></td>
								<td>${fundInfo.shares}<input type="hidden" name="shareHave"
									value="${fundInfo.shares}" /></td>
								<td>${fundInfo.availableShares}<input type="hidden" name="shareHave"
									value="${fundInfo.shares}" /></td>
								<td><input type="text" name="sellshare" value="0"></td>
								<td><input type="submit" value="Sell" /></td>
							</form>
						</tr>
						</c:if>
					</c:forEach>
				</tbody>
			</table>
			<jsp:include page="errorMessages.jsp" />
		</div>
	</div>
	<!-- /.row -->
</div>
<jsp:include page="template-bottom.jsp" />