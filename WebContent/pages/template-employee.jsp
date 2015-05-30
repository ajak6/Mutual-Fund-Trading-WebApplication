<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Carnegie Financial Services</title>

<!-- Bootstrap Core CSS -->
<link href="../bower_components/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet">

<!-- MetisMenu CSS -->
<link href="../bower_components/metisMenu/dist/metisMenu.min.css"
	rel="stylesheet">

<!-- Timeline CSS -->
<link href="../dist/css/timeline.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="../dist/css/sb-admin-2.css" rel="stylesheet">

<!-- Morris Charts CSS -->
<link href="../bower_components/morrisjs/morris.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="../bower_components/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">

<!-- Calendar Styles -->
<link rel="stylesheet" type="text/css"
	href="../bower_components/calendar/epoch_styles.css" />

<script src="../js/epoch_classes.js"></script>
<script type="text/javascript">
	var bas_cal, dp_cal, ms_cal;
	window.onload = function() {
		dp_cal = new Epoch('dp_cal', 'popup', document
				.getElementById(‘date_field’));
	};
</script>

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>
	<%-- <%@ page import="databeans.Employee" %>
<%
    Employee user = (Employee) session.getAttribute("user");
	if (user == null) {
%>
				<span class="menu-item"><a href="login.do">Login</a></span><br/>
				<span class="menu-item"><a href="register.do">Register</a></span><br/>
<%
    } else {
%> --%>
	<div id="wrapper">

		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-static-top" role="navigation"
			style="margin-bottom: 0">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Carnegie Financial Services</a>
			</div>
			<!-- /.navbar-header -->

			<ul class="nav navbar-top-links navbar-right">
				<!-- <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-user fa-fw"></i>  <i class="fa fa-caret-down"></i>
                    </a> -->

				<li><form method="post" action="changpwd.doe">
						<input type="submit" class="btn btn-info" value="Change Password" />
					</form></li>
				<li><form method="post" action="empLogout.doe">
						<input type="submit" class="btn btn-warning" value="Logout" />
					</form> <!-- <ul class="dropdown-menu dropdown-user">
                        <li><a href="ViewAccount_e.jsp"><i class="fa fa-user fa-fw"></i> User Profile</a>
                        </li>
                        <li><a href="changpwd.doe"><i class="fa fa-gear fa-fw"></i> Change Password</a>
                        </li>
                        <li class="divider"></li>
                        <li><a href="empLogout.doe"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
                        </li>
                    </ul> --> <!-- /.dropdown-user --></li>
				<!-- /.dropdown -->
			</ul>
			<!-- /.navbar-top-links -->

			<div class="navbar-default sidebar" role="navigation">
				<div class="sidebar-nav navbar-collapse">
					<ul class="nav" id="side-menu">

						<!-- Registration links -->
						<li><a> <i class="fa fa-files-o fa-fw"></i> Registration
								Panel<span class="fa arrow"></span></a>
							<ul class="nav nav-second-level">
								<li><a href="custRegistration.doe">New Customer
										Registration</a></li>
								<li><a href="employeeRegister.doe">New Employee
										Registration</a></li>
							</ul> <!-- /.nav-second-level --></li>
						<!-- registration links ends -->
						<li><a href="resetpwd.doe"><i
								class="fa fa-rotate-right fa-fw"></i> Reset Customer Password </a></li>
						<li><a href="view_account_e.doe"><i
								class="fa fa-files-o fa-fw"></i> View Customer Account </a></li>
						<li><a href="epm_view_trans_his.doe"><i
								class="fa fa-history fa-fw"></i> Customer Transaction History </a></li>
						<li><a href="depositCheck.doe"> <i
								class="fa fa-money fa-fw"></i> Deposit Check
						</a></li>
						<li><a href="managefund.doe"><i
								class="fa fa-dollar fa-fw"></i> Create Fund </a></li>
						<li><a href="transitionDay.doe"><i
								class="fa fa-calendar fa-fw"></i> Transition Day </a></li>
					</ul>
				</div>
				<!-- /.sidebar-collapse -->
			</div>
			<!-- /.navbar-static-side -->
		</nav>
		<%-- <%
	}
%>    --%>