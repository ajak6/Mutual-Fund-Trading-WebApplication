
<jsp:include page="template-employee.jsp" />
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Registration</title>

    <!-- Bootstrap Core CSS -->
    <link href="../bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="../bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="../dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="../bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="html5shiv.js"></script>
        <script src="respond.min.js"></script>
    <![endif]-->

</head> 

<body>
  
    <div id="page-wrapper">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Customer Registration</h3>
                    </div>
                    <div class="panel-body">
                     <jsp:include page="errorMessages.jsp"></jsp:include>
                        <form role="form" method="post">
                            <fieldset>
                            <div class="form-group"><!-- placeholder="First Name" -->
                                    <input class="form-control"  name="custFirstName" value ="${form.custFirstName}" placeholder="First Name" type="text" autofocus>
                                </div> 
                            <div class="form-group">
                                    <input class="form-control" placeholder="Last Name"  value ="${form.custLastName}" name="custLastName" type="text" >
                                </div>
                            <div class="form-group">
                                    <input class="form-control" placeholder="address Line 1" name="custAddress1" value="${form.custAddress1}" type="text">
                                </div>
                                
                            <div class="form-group">
                                    <input class="form-control" placeholder="address Line 2" name="custAddress2" value="${form.custAddress2}" type="text" >
                                </div>

                                <div class="form-group">
                                    <input class="form-control" placeholder="City" name="custCity" value="${form.custCity }" type="text" >
                                </div>

                            <div class="form-group">
                                    <input class="form-control" placeholder="State" name="custState" value="${form.custState }" type="text" >
                                </div>

                            <div class="form-group">
                                    <input class="form-control" placeholder="Zip" name="custZip" type="text" >
                                </div>

                            <div class="form-group">
                                    <input class="form-control" placeholder="Intial Cash Value" name="custCashValue" type="text" >
                                </div>


                                <div class="form-group">
                                    <input class="form-control" placeholder="username" name="custUsername" type="text" >
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="Password" name="custPassword" type="password" value="">
                                </div>
                                <!-- Change this to a button or input when using this as a form -->
                                <input type="submit"  class="btn btn-lg btn-success btn-block" value="Register"/>
                               
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- jQuery -->
    <script src="../bower_components/jquery/dist/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="../bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="../bower_components/metisMenu/dist/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="../dist/js/sb-admin-2.js"></script>

</body>

</html>
