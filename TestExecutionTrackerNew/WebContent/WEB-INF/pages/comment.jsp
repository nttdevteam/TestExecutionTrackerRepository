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
	
	<div>
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
			<thead>
			    <tr>
			      <th width="100">Commented On </th>
			      <th width="100">Commented By</th>
			      <th width="100">Comment</th>
			    </tr>
			</thead>
			<tbody>
				<c:forEach items="${commentList}" var="commentObject">
				    <tr>      
				        <td>${commentObject.commentDate}</td>
				        <td>${commentObject.commentUser}</td>
				        <td>${commentObject.comment}</td>
				    </tr>
				</c:forEach>
			</tbody>
		</table>
		
		<br></br>
	
		
		<div class="container" style="width:60%">
			<form class="form-signin" method="post">
				<textarea name="newComment" class="form-control" placeholder="Enter New comment" required autofocus ></textarea>
				<br></br>
				<div>
					<input class="btn btn-primary" type="submit" value="Enter Comment">
				</div>
			</form>
		</div>
	</div>

	
	
	
	<div class="container">

    </div> <!-- /container -->
	
</body>
