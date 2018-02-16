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
					
	    <div class="solid" style="margin-left:20%;margin-right:20%" >
			<br></br>
			<div align="center">
		    	<select id="projectDropDown">
		    		<option value="0">Select Project</option>
					<c:forEach items="${PROJECT_LIST}" var="project">
					   <option value="${project.id}">${project.projectName}</option>
					</c:forEach>
				</select> 
				<input  class="btn btn-primary" type="submit" value="Add Project" id="showHideAddProjectDiv" style='padding:2;font-size:10px'> 
			</div>
			<br/>
			<div class="solid" id="newProjectDiv" align="center" style="margin:10px">
					<br/>
		 		  	<input style="width: 20%" type="text" class="form-control" placeholder="Enter new Project Name" required autofocus id="newProjectName" >
		 		  	<br/>
		 		  	<input type="submit" class="btn btn-primary" value="Add Project" style='padding:2;font-size:12px' id="submitNewProject"> 
		 		  	<br></br>
			</div>	
			<br/>
			
			<div align="center"> 
				<table id="cycleList" style="width: 40%;" border="1">  
					<thead>
					    <tr>
					      	<th width="30%" >Test Cycles</th>
					      	<th width="10%" >Upload File</th>
					      	<th width="10%" >Delete</th>
					    </tr>
				  	</thead>
				  	<tbody>
				  		<tr></tr>
				   	</tbody>
				</table>
				<br/>
			</div>
			<div align="center">
				<table style="width: 20%;" >
					<tr> 
		        		<td width="10%"><div style='padding-top: 10px;' align="center">  <input class='btn btn-primary' style='padding:2;font-size:10px;' type='submit' value='Add Cycle' name ='addCycle' id='addCycleButton' /> </div></td>
		        	</tr>
				</table>
			</div>
			<br/>
		</div>
	 
	</body>
	
	<script>
		$("#addCycleButton").hide();
		$("#newProjectDiv").hide();
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
			/////////////////////////////////////////////////////////Add Project////////////////////////////////////////////////////////////				
			$("#showHideAddProjectDiv").on("click", function(){
				$("#newProjectDiv").show();
			});
			$("#submitNewProject").on("click", function(){
				$("#newProjectDiv").hide();
				var projectId = $('#projectDropDown option').length; 
				addProject(projectId, $("#newProjectName").val());

				var option = new Option($("#newProjectName").val(), projectId);
				$('#projectDropDown').append($(option));
			});
			
			
			
			
			/////////////////////////////////////////////////////////Populate Cycle////////////////////////////////////////////////////////////				
			$("#projectDropDown").on("change", function(){
				$('#cycleList tbody > tr').remove();
				var projectId = $(this).val() - 1;
				
				if(projectId==-1)
				{
					$("#addCycleButton").hide();
					return;
				}
				$("#addCycleButton").show();
				
				console.debug(projectId);
				console.debug(projectList[projectId]);
				
				$( projectList[projectId].cycleList ).each(function(index, element) {
					var cycleNameListTd = "<td name='cycleNameList' id='cycleNameList"+index+"' >"+element+"</td>";
					var uploadTd = "<td id='upload"+index+"' ><input id='fileDialog' fileId="+index+" type='file' name='fileupload'/> </td>";
					var deleteProjectTd = "<td name='deleteCycle' id='deleteCycle"+index+"'><div style='padding-top: 10px;' align='center'> <input class='btn btn-primary' style='padding:2;font-size:10px;' type='submit' value='Delete Cycle' name ='delete' id='deleteInput/"+index+"' /> </div></td>";
					rowData = "<tr>"+cycleNameListTd+uploadTd+deleteProjectTd+"</tr";
					$("#cycleList  tbody:last-child").append(rowData);
				});
			});
			
			
			/////////////////////////////////////////////////////////Add Cycle////////////////////////////////////////////////////////////				
			$("#addCycleButton").on("click", function(){
				var index = $('#cycleList tr').length - 1;
				var cycleNameListTd = "<td name='cycleName' id='cycleName"+index+"' ><input name='cycleNameInput' id='cycleNameInput' type='text' placeholder='Enter cycle Name'> </input></td>";
				var uploadTd = "<td id='upload"+index+"' ><input id='fileDialog' fileId="+index+" type='file' name='fileupload'/> </td>";
				var deleteProjectTd = "<td name='deleteCycle' id='deleteCycle"+index+"'><div style='padding-top: 10px;' align='center'><input class='btn btn-primary' style='padding:2;font-size:10px;' type='submit' value='Delete Cycle' name ='delete' id='deleteInput/"+index+"' /> </div></td>";

				
				var rowData = "<tr>"+cycleNameListTd+uploadTd+deleteProjectTd+"</tr>";
				
				$('#cycleList tr:last').after(rowData);
				//$('#projectList').refresh();
			});
			
			
			/////////////////////////////////////////////////////////Cycle Name Change////////////////////////////////////////////////////////////				
			$("#cycleList").on('focusout blur', "[name='cycleName']", function () {
				console.debug("browser focus out");
				if($( this ).html().indexOf("<input") == 0)
				{
					var htmlValue = $(this).find("[name='cycleNameInput']").val();
					if(htmlValue==null || htmlValue==""){
						console.debug("html Value: "+htmlValue);
						return;
					}
					else{
						$( this ).empty();
						$( this ).html(htmlValue);	
						addCycle($("#projectDropDown").val(), htmlValue);
					}
				}
			});
			$("#cycleList").on("click","[name='cycleName']" , function() {
				if($( this ).html().indexOf("<input") !=0)
				{
					var htmlValue = $( this ).html();
					$( this ).empty();
					$( this ).html("<input type='text' name='cycleNameInput' id='cycleNameInput' value='"+ htmlValue +"'> </input>")
					$( "#cycleNameInput" ).focus();
					$( "#cycleNameInput" ).bind( "focusout blur", function() {$( "[name='cycleName']" ).trigger('focusout blur');});
				}
			});
			

			/////////////////////////////////////////////////////////Delete Cycle////////////////////////////////////////////////////////////				
			$("#cycleList").on("click","[name='deleteCycle']" , function() {
				cycleId = $(this).attr("id").substring(11);
				var deleteCycleResponse = deleteCycle($("#projectDropDown").val(), cycleId);
				
				if(deleteCycleResponse)
				{
					$($(this).closest("tr")).remove();
				}
				else
				{
					alert("Technical Error");
					return false;
				}
			});
			
			
			
			/////////////////
			
			$("#cycleList").on("click","#fileDialog" , function() {
				var projectId=$("#projectDropDown").val();
				var cycleId = $(this).attr('fileId');
				//cycleId = cycleId.substring(10);
				var url="uploadFile/"+projectId+"/"+cycleId;
				
				$(this).fileupload({
					url: url,
					maxFileSize: 2097152,
					async:false,
					done: function (e, data) {
						if(data.success)
							alert("File Uploaded");
						else
							alert("File cannot be uploaded as cycle has already started");
					},
					change: function (e, data) {
	  			        $.each(data.files, function (index, file) {
							console.debug('1. Selected file: ' + file.name);
				            console.debug('2. File size: '+file.size);
				            console.debug('3. File Type: '+file.type);
							//e.preventDefault();
						});
					},
					fail: function(e, data) {
						  alert('Fail!');
					}
				});
			});
		});
		/////////////////
		function addProject(projectId, projectName){
			var addProjectUrl = "addProject";
			var queryString = "&projectId="+projectId+"&projectName="+projectName;
			$.ajax({
				url: addProjectUrl,
				type : 'POST',
				data : queryString,
				success: function(){
					alert("Project added");
				}
			});
		}
		
		//Adds cycle at the very end of the existing list.
		function addCycle(projectId, cycleName){
			var url = "addCycle";
			var queryString = "&projectId="+projectId+"&cycleName="+cycleName;
			$.ajax({
				url: url,
				type : 'POST',
				data : queryString,
				success: function(){
					alert("Cycle Added");
				}
			});
		}
		function updateCycleName(projectId, cycleName, cycleId){
			var url = "updateCycle";
			var queryString = "&projectId="+projectId+"&cycleName="+cycleName+"&cycleId="+cycleId;
			$.ajax({
				url: url,
				type : 'POST',
				data : queryString,
				success: function(){
					alert("Cycle Updated");
				}
			});
		}
		function deleteCycle(projectId, cycleId){
			var result = false;
			var url = "deleteCycle";
			var queryString = "&projectId="+projectId+"&cycleId="+cycleId;
			$.ajax({
				url: url,
				type : 'POST',
				data : queryString,
				async: false,
				success: function(data){
					//console.debug("---"+JSON.stringify(data.sucess));
					var jsonString = jQuery.parseJSON( data );
					if(jsonString.success)
					{
						console.debug("return true");
						result = true;	
					}
					else
					{
						console.debug("return false");
						result = false;	
					}
				},
				error: function(){
					alert("Cycle not Deleted");
					result = false;
				}
			});
			return result;
		}
	</script>
</html>