<jsp:include page="template-employee.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="databeans.Fund"%>
<div id="page-wrapper">
	<div class="row">
		<div class="col-md-4 col-md-offset-4">
			<div class="login-panel panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">Create Fund:</h3>
				</div>
				<div class="panel-body">
					<form role="form" method="post" action="creatfund.doe">
						<fieldset>
							<div class="form-group">
								<input class="form-control" placeholder="Fund Symbol:"
									name="fundSym" type="text" required="required">
							</div>
							<jsp:include page="errorMessages.jsp"></jsp:include>
							<div class="form-group">
								<input class="form-control" placeholder="Fund Name"
									name="fundName" type="text" required="required">
							</div>
							<!-- Change this to a button or input when using this as a form -->
							<label>
								<button type="submit" class="btn btn-danger" name="action"
									value="Create Fund">Add</button>
							</label>
						</fieldset>
					</form>
				</div>
			</div>
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