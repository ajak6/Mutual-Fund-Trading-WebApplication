<jsp:include page="template-customer.jsp" />
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">Request Check</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-body">
					<div class="row">
						<div class="col-lg-6">
						<jsp:include page="errorMessages.jsp"></jsp:include>
							<form role="form">
								<div class="form-group">
									<label>Current Available Balance: </label>
									<p class="form-control-static"><fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2"> ${availableBalance/100}</fmt:formatNumber></p>
								</div>
								<div class="form-group">
									<label>Current Main Balance: </label>
									<p class="form-control-static"><fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2"> ${mainBalance/100}</fmt:formatNumber></p>
								</div>
								<div class="form-group">
									<label>Cash to withdrawal</label> <input class="form-control"
										placeholder="Enter amount" name="amount" type="text">
								</div>
                                <input type="submit"  class="btn btn-lg btn-success btn-block" value="Submit"/>
							</form>
						</div>
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
