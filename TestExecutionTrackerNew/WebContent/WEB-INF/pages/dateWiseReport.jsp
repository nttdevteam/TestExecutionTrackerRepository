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
		<script src="../js/canvasjs.min.js"></script>
  	</head>
	<body>
		<div align="center" id="navBar">Test Execution Tracker - Comment Details</div>
		<br></br>
		<div align="center">Level-1 Datewise details</div>
		
		<table>
			<tr>
				<td>
					<div align="center"> 
						<table style="width:95%" id="report">
							<thead>
								<tr>
									<th>Date</th>
									<th>Total Tests</th>
									<th>Passed Tests</th>
									<th>Failed Tests</th>
									<th>Not tested Tests</th>
									<th>Blocked Tests</th>
									<th>Invalid Tests</th>
								</tr>
							</thead>
							<c:forEach items="${report1}" var="map">
								<c:forEach items="${map}" var="list">
									<tr>
										<td>
											${list.key}
										</td>
										<td>
											${list.value.totalTests}
										</td>
										<td>
											${list.value.l1PassedTests}
										</td>
										<td>
											${list.value.l1FailedTests}
										</td>
										<td>
											${list.value.l1NotTestedTests}
										</td>
										<td>
											${list.value.l1BlockedTests}
										</td>
										<td>
											${list.value.l1InvalidTests}
										</td>
									</tr>
								</c:forEach>
							</c:forEach>
							
						</table>
					</div>
				</td>
				<td>
					<div id="chartContainer1" style="height: 200px; width: 100%;"></div>
				</td>
			</tr>
		</table>
		
		<br></br>
		<br></br>
		<br></br>
		<div align="center">Level-2 Datewise details</div>
		
		<table>
			<tr>
				<td>
					<div align="center"> 
						<table style="width:95%" id="report">
							<thead>
								<tr>
									<th>Date</th>
									<th>Total Tests</th>
									<th>Passed Tests</th>
									<th>Failed Tests</th>
									<th>Not tested Tests</th>
									<th>Blocked Tests</th>
									<th>Invalid Tests</th>
								</tr>
							</thead>
							<c:forEach items="${report2}" var="map">
								<c:forEach items="${map}" var="list">
									<tr>
										<td>
											${list.key}
										</td>
										<td>
											${list.value.totalTests}
										</td>
										<td>
											${list.value.l1PassedTests}
										</td>
										<td>
											${list.value.l1FailedTests}
										</td>
										<td>
											${list.value.l1NotTestedTests}
										</td>
										<td>
											${list.value.l1BlockedTests}
										</td>
										<td>
											${list.value.l1InvalidTests}
										</td>
									</tr>
								</c:forEach>
							</c:forEach>
							
						</table>
					</div>
				</td>
				<td>
					<div id="chartContainer2" style="height: 200px; width: 100%;"></div>
				</td>
			</tr>
		</table>
	</body>
	
	<script type="text/javascript">
		window.onload = function () {
			var chart1 = new CanvasJS.Chart("chartContainer1", ${chart1});
			var chart2 = new CanvasJS.Chart("chartContainer2", ${chart2});
			chart1.render();
			chart2.render();
		};
	</script>
</html>
