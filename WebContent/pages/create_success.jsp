<jsp:include page="template-employee.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="databeans.Fund"%>
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">Fund has been created successfully</h1>
			<a href="managefund.doe"><input type="button" class="btn btn-success" value="Create another fund"></a>
		</div>
	</div>
	<c:if test="${fundList!=null}">
		<div class="row">
			<p>${message}</p>
			<h2>Existing Funds:</h2>
			<table class="table">
			<thead>
					<tr>
						<th>Fund Name</th>
						<th>Fund Symbol</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach var="fund" items="${fundList}">
				<tr class="active">
							<td>${fund.name}</td>
							<td>${fund.symbol}</td>
						</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
	</c:if>
</div>
<jsp:include page="template-bottom.jsp" />