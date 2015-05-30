
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

<!-- Morris Charts CSS -->
<link href="../bower_components/morrisjs/morris.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="../bower_components/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">

<style type="text/css">
body {
	background: url(../img/Financial-Services.jpg) repeat-y center top;
	background-size: 100%;
}

.container {
	padding: 150px 0px;
	text-align: center;
}

.btn-lg, .btn-group-lg>.btn {
	line-height: 3;
}

</style>
</head>

<body>

   <div class="container">
   <img src="../img/logo.png" style="align: center" />
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="row clearfix">
                <div class="col-md-6 column">
                    <a href="login.doc"><button type="button" class="btn btn-lg btn-info btn-block">Customer Login</button></a>
                </div>
                <div class="col-md-6 column">
                   <!--  <a href="ViewAccount_e.jsp"><button type="button" class="btn btn-block btn-warning btn-lg">Employee Login</button></a> -->
                   <!-- it will go to controller to see if session is active then show dashboard else show login page -->
                   <a href="login.doe"><button type="button" class="btn btn-block btn-warning btn-lg">Employee Login</button></a>
              <!--       <a href="login.jsp"><button type="button" class="btn btn-block btn-warning btn-lg">Employee Login</button></a> -->
                </div>
            </div>
        </div>
    </div>

    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="../bower_components/jquery/dist/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="../bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="../bower_components/metisMenu/dist/metisMenu.min.js"></script>

    <!-- Morris Charts JavaScript -->
    <script src="../bower_components/raphael/raphael-min.js"></script>
    <script src="../bower_components/morrisjs/morris.min.js"></script>
    <script src="../js/morris-data.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="../dist/js/sb-admin-2.js"></script>

</body>

</html>
