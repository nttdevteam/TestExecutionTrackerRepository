<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
	<head>
	    <title>Test Execution Tracker</title>
	
	    <meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <link href="../css/bootstrap.min.css" rel="stylesheet">
	
	    <link href="../css/ie10-viewport-bug-workaround.css" rel="stylesheet">
	    <link href="../css/main.css" rel="stylesheet">
	    <script src="../js/ie-emulation-modes-warning.js"></script>
		<script src="../js/jquery.min.js"></script>
  	</head>
	<body>
		<div align="center" id="navBar">Test Execution Tracker - Comment Details</div>
		<br></br>
		<div align="center"> 
			<table style="width:95%" id="report">
				<thead>
					<tr>
						<th>FSD</th>
						<th>Functionality</th>
						<th>Tests Count</th>
						<th>Level-1 Passed Tests</th>
						<th>Level-1 Failed Tests</th>
						<th>Level-1 Not tested Tests</th>
						<th>Level-2 Passed Tests</th>
						<th>Level-2 Failed Tests</th>
						<th>Level-2 Not tested Tests</th>
					</tr>
				</thead>
				<c:forEach items="${report.fsdFunctionality}" var="fsd">
					<tr>
						<td>
							${fsd.key}
						</td>
					</tr>
					<c:forEach items="${fsd.value}" var="functionality">
						<tr>
							<td></td>
							<td>
								${functionality.key}
							</td>
							<td>
								${functionality.value.totalTests}
							</td>
							<td>
								${functionality.value.l1PassedTests}
							</td>
							<td>
								${functionality.value.l1FailedTests}
							</td>
							<td>
								${functionality.value.l1NotTestedTests}
							</td>
							<td>
								${functionality.value.l2PassedTests}
							</td>
							<td>
								${functionality.value.l2FailedTests}
							</td>
							<td>
								${functionality.value.l2NotTestedTests}
							</td>
						</tr>
					</c:forEach>
				</c:forEach>
			</table>
		</div>
		<br></br>
	</body>
</html>
