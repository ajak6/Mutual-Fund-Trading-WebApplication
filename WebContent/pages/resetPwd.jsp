<jsp:include page="template-employee.jsp" />
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">Reset Customer's Password</h1>
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
							<form role="form" action="resetpwd.doe">
								<div class="form-group">
									<label>Input Customer's username: </label> <input class="form-control"
										placeholder="Enter userName" name="userName" type="text">
										<br/>
									<label>Input New Password: </label> <input class="form-control"
										placeholder="Enter new password" name="newPassword" type="text">
										<br/>
									<label>Confirm New Password: </label> <input class="form-control"
										placeholder="Confirm new password" name="confirmPassword" type="text">
								</div>
								<br>
                                <input type="submit"  class="btn btn-lg btn-success btn-block" value="Reset"/>
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
