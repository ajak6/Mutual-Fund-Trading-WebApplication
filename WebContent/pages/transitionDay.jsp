<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="template-employee.jsp" />
<style type="text/css">
.form-control {
	width: 40%;
}
</style>
<script src="../js/epoch_classes.js"></script>
<script type="text/javascript">
	var bas_cal, dp_cal, ms_cal;
	window.onload = function() {
		dp_cal = new Epoch('dp_cal', 'popup', document
				.getElementById('date_field'));
	};
</script>
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">Transition Day</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-body">
					<div class="row">
						<div class="col-lg-12">
							<jsp:include page="errorMessages.jsp"></jsp:include>

							<form method="POST" action="transitionDay.doe">
								<div class="form-group">
									<p>
										<label>Enter the transition Date</label> <input type="text"
											class="form-control" placeholder="mm/dd/yyyy"
											name="transitDay" id="date_field" required="required">
									</p>
									<p>
										<input type="button" class="btn btn-info"
											value="choose a date" style="width:30%;" onclick="dp_cal.toggle();" /> Please
										click the button to choose a date or input a date. (* The date
										should be after last trading date )
									</p>
								</div>

								<label>Enter fund prices</label>
								<table class="table table-bordered table-hover table-striped">
									<thead>
										<tr>
											<th>ID</th>
											<th>Name</th>
											<th>Symbol</th>
											<th>Last Price</th>
											<th>Last Trading Date</th>
											<th>Input Fund Price ($)</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="FundInfo" items="${allFundInfo}">
											<tr>
												<td><input type="hidden" name="fundIdList"
													value="${FundInfo.fund_id}"> <label>
														${FundInfo.fund_id}</label></td>
												<td><label> ${FundInfo.name}</label></td>
												<td><label> ${FundInfo.symbol}</label></td>
												<td><label> <fmt:formatNumber type="number"
															minFractionDigits="2" value="${FundInfo.price}" /></label></td>
												<td><label> <fmt:formatDate type="date"
															pattern="MM/dd/yyyy" value="${FundInfo.price_date}" /></label></td>
												<td><input type="text" class="form-control input-sm"
													name="priceList" required="required"></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>

								<input type="submit" class="btn btn-lg btn-success btn-block"
									value="Submit" />

							</form>
						</div>
						<!-- /.col-lg-6 (nested) -->
					</div>
					<!-- /.row (nested) -->
				</div>
				<!-- /.panel-body -->
			</div>
			<!-- /.panel -->
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
</div>
<!-- /#page-wrapper -->

<jsp:include page="template-bottom.jsp" />

