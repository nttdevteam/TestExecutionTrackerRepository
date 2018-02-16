<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
	<head>
	    <title>Test Execution Tracker</title>
	
	    <meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <link href="../../css/bootstrap.min.css" rel="stylesheet">
	
	    <link href="../../css/ie10-viewport-bug-workaround.css" rel="stylesheet">
	    <link href="../../css/main.css" rel="stylesheet">
	    <script src="../../js/ie-emulation-modes-warning.js"></script>
		<script src="../../js/jquery.min.js"></script>
  	</head>
	<body>
		<div align="center" id="navBar">Test Execution Tracker - Comment Details</div>
			<table style="width:100%">
				<tr>
					<td>
						<div align="left">
							<form class="form-signin" method="get" action="../login" modelAttribute="loginForm">
								<input  class="btn btn-primary" type="submit" value="Back" style="padding:2px;font-size:12px; margin:2px">
							</form> 
						</div>
					</td>
					<td>
						<div align="right">
							<form class="form-signin" action="../a/logout" method="post" >
								<input  class="btn btn-primary" type="submit" value="logout!" style='padding:2px;font-size:12px; margin:2px'> 
							</form>
						</div>
					</td>	
			</table>
			<br></br>
			<div align="center">
				<table id="historyTable" style="width: 60%;" border="1">  
					<thead border="1">
					    <tr  border="1">
					      <th width="100">Updated On </th>
					      <th width="100">Updated By</th>
					      <th width="100">Updates</th>
					    </tr>
					</thead>
					<tbody>
						<c:forEach items="${historyList}" var="history">
						    <tr>      
						        <td>${history.updateDate}</td>
						        <td>${history.updateUser}</td>
						        <td>${history.historyData}</td>
						    </tr>
						</c:forEach>
					</tbody>
				</table>
				<br/>
				<div class="container" style="width: 60%">
					
					<form class="form-signin" method="get" action="../login" modelAttribute="loginForm">
						<input  class="btn btn-primary" type="submit" value="Back"  style='padding:2px;font-size:12px; margin:2px'>
					</form> 
			
			    </div> <!-- /container -->
			</div>	
	</body>
</html>