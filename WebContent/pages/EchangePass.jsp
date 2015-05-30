<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <jsp:include page="template-employee.jsp" />   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Password change Form</title>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
   
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
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
</head>
<body>
		<div id="page-wrapper">
            <div class="row">
              <div class="col-lg-12">
                    <h1 class="page-header">Change your password </h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-10">
                    <div class="panel panel-default">
                        <div class="panel-heading">We highly recommend our employees to change their password frequently    </div>
                        <div class="panel-body">
                        <jsp:include page="errorMessages.jsp"></jsp:include>
                            <div class="row">
                              <!-- /.col-lg-6 (nested) -->
								<div class="col-lg-4">
                  <form role="form" method="post" action="changpwd.doe" >
                            <fieldset>
                           
                                <div class="form-group">
                                    <input class="form-control" placeholder="current password" name="oldPassword" value="" type="password" autofocus>
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="new Password" name="newPassword" value="" type="password">
                                </div>
                                 <div class="form-group">
                                    <input class="form-control" placeholder="Confirm Password" name="confirmPassword" value="" type="password">
                                </div>
                               
                               
                                <!-- Change this to a button or input when using this as a form -->
                                <input type="submit" class="btn btn-lg btn-success btn-block" value="Change Password"/>
                            </fieldset>
                        </form>
                                    <h1>&nbsp;</h1>
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

    
   


</body>

<jsp:include page="template-bottom.jsp" />