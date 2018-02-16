<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
		<script src="../js/jquery.min.js"></script>
	
	    <script src="../js/jquery.ui.widget.js"></script>
		<script src="../js/jquery.iframe-transport.js"></script>
		<script src="../js/jquery.fileupload.js"></script> 
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
		<form action="projectCycleSelection" method="post">
		    <div class="solid" style="margin-left:20%;margin-right:20%" >
				<br></br>
				<div align="center">
			    	<select id="projectDropDown" name="projectDropDown">
			    		<option value="0">Select Project</option>
						<c:forEach items="${PROJECT_LIST}" var="project">
						   <option value="${project.id}">${project.projectName}</option>
						</c:forEach>
					</select> 
				</div>
				<br/>
				<div align="center">
			    	<select id="cycleDropDown" name="cycleDropDown">
			    		<option value="0">Select Cycle</option>
					</select> 
				</div>
				<br/>
				<div align="center">
				  		<input type="hidden" class="btn btn-primary" name="selectedProjectName" id="selectedProjectName">
						<input type="hidden" class="btn btn-primary" name="selectedCycleName" id ="selectedCycleName">
		 		  		<input type="submit" class="btn btn-primary" value="Submit Project Cycle Selection" style='padding:2;font-size:12px' id="submitProjectCycleSelection">
				</div>
				<br/>
			</div>
	  	</form>
	</body>
	
	<script>
		var projectList = [];
		<c:forEach var="project" items="${PROJECT_LIST}">
			var cycleListObj = [];
			<c:forEach var="cycle" items="${project.cycleNames}">
				cycleListObj.push('${cycle}');
			</c:forEach>
			projectObj = { id: '${project.id}', projectName: '${project.projectName}',cycleNameList: '${project.cycleNameList}',cycleList: cycleListObj};
		    projectList.push(projectObj);                                  
		</c:forEach>
	
		$( document ).ready(function() {
			
			$("#submitProjectCycleSelection").hide();
			$("#cycleDropDown").hide();
			
			/////////////////////////////////////////////////////////Populate Cycle////////////////////////////////////////////////////////////				
			$("#projectDropDown").on("change", function(){
				$("#cycleDropDown").show();

				$('#cycleDropDown') .find('option').remove().end().append('<option value="0">Select Cycle</option>').val('Select Cycle');
				var projectId = $(this).val() - 1;

				if(projectId == "-1"){
					$("#cycleDropDown").hide();
				}
				
				var options = $("#cycleDropDown");

				$( projectList[projectId].cycleList ).each(function(index, element) {
					var cycleDropDown = "<option value='cycleNameList"+index+"'>"+element+"</option>"; 
					options.append(cycleDropDown);
				});
				
				console.debug("2: "+JSON.stringify($(this).find("option:selected").text()));
				$("#selectedProjectName").val($(this).find("option:selected").text());
				
			});
			$("#cycleDropDown").on("change", function(){
				$("#submitProjectCycleSelection").show();
				$("#selectedCycleName").val($(this).find("option:selected").text());
				console.debug("---"+$(this).find("option:selected").text());
				
				console.debug("---: "+$("#selectedCycleName").val());
				
			});
		});
	</script>
</html>