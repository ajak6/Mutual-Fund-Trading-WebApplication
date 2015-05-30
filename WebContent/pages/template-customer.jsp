<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<!-- Custom Theme JavaScript -->

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

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>
	<%-- <%@ page import="databeans.Customer" %>
<%
Customer user = (Customer) session.getAttribute("user");
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
				<!-- 	<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#"> <i class="fa fa-user fa-fw"></i>
						<i class="fa fa-caret-down"></i>
				</a>  -->
				<span>
					<li><form method="post" action="view_account_c.doc">
							<input type="submit" class="btn btn-success" value="View Account" />
						</form></li>
					<li><form method="post" action="changpwd.doc">
							<input type="submit" class="btn btn-info" value="Change Password" />
						</form></li>
					<li><form method="post" action="logout.doc">
							<input type="submit" class="btn btn-warning" value="Logout" />
						</form></li> <!-- <ul class="dropdown-menu dropdown-user"></form></li>
                        <li><a href="view_account_c.doc"><i class="fa fa-user fa-fw"></i> User Profile</a>
                        </li>
                        <li><a href="changpwd.doc"><i class="fa fa-gear fa-fw"></i> Change Password</a>
                        </li>
                        <li class="divider"></li>
                        <li><a href="logout.doc"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
                        </li>
                    </ul> --> <!-- /.dropdown-user -->
					</li> <!-- /.dropdown -->
				</span>
			</ul>
			<!-- /.navbar-top-links -->

			<div class="navbar-default sidebar" role="navigation">
				<div class="sidebar-nav navbar-collapse">
					<ul class="nav" id="side-menu">

						<li><a href="view_account_c.doc"><i
								class="fa fa-user fa-fw"></i> View Account</a></li>
						<li><a href="buyFund.doc"><i
								class="fa fa-shopping-cart fa-fw"></i> Purchase Fund</a></li>
						<li><a href="managefund.doc"><i
								class="fa fa-dollar fa-fw"></i> Sell Fund </a></li>
						<li><a href="cus_view_trans_his.doc"><i
								class="fa fa-history fa-fw"></i> Transaction History </a></li>
						<li><a href="fundResearch.doc"><i
								class="fa fa-book fa-fw"></i> Research Fund </a></li>
						<li><a href="requestCheck.doc"><i
								class="fa fa-money fa-fw"></i> Request Check </a></li>
					</ul>
				</div>
				<!-- /.sidebar-collapse -->
			</div>
			<!-- /.navbar-static-side -->
		</nav>

		<%-- <%
	}
%> --%>