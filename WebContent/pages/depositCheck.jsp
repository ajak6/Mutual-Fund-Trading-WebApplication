<jsp:include page="template-employee.jsp" />
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">Deposit Check</h1>
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
							<form role="form">
								<div class="form-group">
								<jsp:include page="errorMessages.jsp"></jsp:include>
									<label>Input Customer's Username</label> <input class="form-control"
										placeholder="Enter username" name="username">
								</div>
								<div class="form-group">
									<label>Cash to Deposit</label> <input class="form-control"
										placeholder="Enter amount" name="amount">
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
