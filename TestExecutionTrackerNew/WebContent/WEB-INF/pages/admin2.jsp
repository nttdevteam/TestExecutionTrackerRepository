<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
	<head>
	    <meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <link href="../css/bootstrap.min.css" rel="stylesheet">
	
	    <link href="../css/ie10-viewport-bug-workaround.css" rel="stylesheet">
	    <link href="../css/main.css" rel="stylesheet">
	    <link href="../css/signin.css" rel="stylesheet">
	    <script src="../js/ie-emulation-modes-warning.js"></script>
  	</head>
	<script src="../js/jquery.min.js"></script>
	<style>
		p.dotted {border-style: dotted;}
		p.dashed {border-style: dashed;}
		div.solid {border-style: solid;}
		p.double {border-style: double;}
		p.groove {border-style: groove;}
		p.ridge {border-style: ridge;}
		p.inset {border-style: inset;}
		p.outset {border-style: outset;}
		p.none {border-style: none;}
		p.hidden {border-style: hidden;}
		p.mix {border-style: dotted dashed solid double;}
	</style>
	<body  style="padding-top: 10px;">
		<div align="center"> Test Execution Tracker</div>
		<div align="center">	Logged in User Name : ${userName} </div>
		<div align="right" style="padding-right:2.5%">	<form action="../a/logout" method="post" ><input  class="btn btn-primary" type="submit" value="logout" style='padding:2;font-size:10px'> </form></div>
		
	    		
	    <div class="solid" style="margin:10px" >
			<div align="center">
		    	<h4 class="form-signin-heading"><u>Project Details</u></h4>
			</div>

			<div align="center">
		    	<select>
					<c:forEach items="${PROJECT_LIST}" var="project">
					   <option value="${project.id}">${project.projectName}</option>
					</c:forEach>
				</select> 
			</div>
			<div align="center"> 
				<table id="projectList" style="width: 95%;" border="1">  
					<thead>
					    <tr>
					      	<th width="30%" >Test Cycles</th>
					      	<th width="10%" >upload</th>
					      	<th width="10%" >Delete</th>
					    </tr>
				  	</thead>
				  	<tbody>
						<c:forEach items="${PROJECT_LIST}" var="project.cycleNames">
							<tr>
						        <td name='cycleNameList' id='cycleNameList${project.id}' >${project.cycleNameList}</td>
						        <td name='upload' id='upload${project.id}' ><div style='padding-top: 10px;' align="center">  <form action='upload/${project.id}' > <input class='btn btn-primary' style='padding:2;font-size:10px;' type='submit' value='Upload File' name ='upload' id='uploadInput/${project.id}' /> </form> </div></td>
						        <td name='delete' id='delete${project.id}' ><div style='padding-top: 10px;' align="center">  <form action='delete/${project.id}' > <input class='btn btn-primary' style='padding:2;font-size:10px;' type='submit' value='Delete Project' name ='delete' id='deleteInput/${project.id}' /> </form> </div></td>
						    </tr>
						</c:forEach>
				   	</tbody>
				</table>
				<br/>
			</div>
			<!-- 
			<div align="center"> 
				<table id="projectList" style="width: 95%;" border="1">  
					<thead>
					    <tr>
							<th width="10%" >Project ID</th>
					      	<th width="20%" >Project Name</th>
					      	<th width="30%" >Test Cycles</th>
					      	<th width="10%" >upload</th>
					      	<th width="10%" >Delete</th>
					    </tr>
				  	</thead>
				  	<tbody>
						<c:forEach items="${PROJECT_LIST}" var="project">
						    <tr>
						        <td name='projectId' id='projectId${project.id}' >${project.id}</td>
						        <td name='projectName' id='projectName${project.id}' >${project.projectName}</td>
						        <td name='cycleNameList' id='cycleNameList${project.id}' >${project.cycleNameList}</td>
						        <td name='upload' id='upload${project.id}' ><div style='padding-top: 10px;' align="center">  <form action='upload/${project.id}' > <input class='btn btn-primary' style='padding:2;font-size:10px;' type='submit' value='Upload File' name ='upload' id='uploadInput/${project.id}' /> </form> </div></td>
						        <td name='delete' id='delete${project.id}' ><div style='padding-top: 10px;' align="center">  <form action='delete/${project.id}' > <input class='btn btn-primary' style='padding:2;font-size:10px;' type='submit' value='Delete Project' name ='delete' id='deleteInput/${project.id}' /> </form> </div></td>
						    </tr>
						</c:forEach>
				   	</tbody>
				</table>
				<br/>
			</div>
			<div align="center">
				<table style="width: 20%;" >
					<tr>
		        		<td width="10%"><div style='padding-top: 10px;' align="center">  <input class='btn btn-primary' style='padding:2;font-size:10px;' type='submit' value='Add Project' name ='addProject' id='addProjectButton' /> </div></td>
		        		<td width="10%"><div style='padding-top: 10px;' align="center">  <input class='btn btn-primary' style='padding:2;font-size:10px;' type='submit' value='Back!' name ='back' id='backButton' /> </div></td>
		        	</tr>
				</table>
			</div>
			
			 -->
			 
			<br/>					
		</div> 
	</body>
	
	<script>
		console.debug(JSON.stringify("${PROJECT_LIST}"));
		$( document ).ready(function() {
/////////////////////////////////////////////////////////Project Name Input////////////////////////////////////////////////////////////				
			$(  "#projectList" ).on('focusout blur', "[name='projectName']", function () {
				console.debug("project Name focus out");
				if($( this ).html().indexOf("<input") == 0)
				{
					var htmlValue = $(this).find("[name='projectNameInput']").val();
					$( this ).empty();
					$( this ).html(htmlValue);	
					//updateRow(this.id.substring(9), "projectName", htmlValue);
				}
			});
			
			$( "#projectList"  ).on("click", "[name='projectName']", function() {
				console.debug("project name clicked : "+ $( this ).html().indexOf("<input"));
				if($( this ).html().indexOf("<input") !=0)
				{
					var htmlValue = $( this ).html();
					$( this ).empty();
					$( this ).html("<input type='text' name='projectNameInput' id='projectNameInput' value='"+ htmlValue +"'> </input>")
					$( "#projectNameInput" ).focus();
					$( "#projectNameInput" ).bind( "focusout blur", function() {$( "[name='projectName']" ).trigger('focusout blur');});
				}
			});

/////////////////////////////////////////////////////////Cycle Input////////////////////////////////////////////////////////////				
			$(  "#projectList" ).on('blur', "[name='cycleNameListDiv']", function () {
				console.debug("cycleNameList focus out>");
				if($( this ).html().indexOf("<input") == 0)
				{
					var htmlValue = $(this).find("[name='cycleNameListInput']").val();
					$( this ).empty();
					$( this ).html(htmlValue);	
					//updateRow(this.id.substring(9), "projectName", htmlValue);
				}
			});
			
			$( "#projectList"  ).on("click", "[name='cycleNameList']", function() {
				console.debug("cycleNameList name clicked"+$( this ).html().indexOf("<input") );
				if($( this ).html().indexOf("<input") !=29)
				{
					var htmlValue = $( this ).html();
					$( this ).empty();
					$( this ).html("<div name='cycleNameListDiv' ><input type='checkbox' name='cycleNameListInput' value='Cycle1'>Cycle1<br></br><input type='checkbox' name='cycleNameListInput' value='Cycle2'>Cycle2<br></br><input type='checkbox' name='cycleNameListInput' value='Cycle3'>Cycle3<br></br><input type='checkbox' name='cycleNameListInput' value='Cycle4'>Cycle4<br></br><input type='checkbox' name='cycleNameListInput' value='Cycle5'>Cycle5</input> </div>");
					$( "#cycleNameListInput" ).focus();
					//$( "#cycleNameListInput" ).bind( "focusout blur", function() {$( "[name='cycleNameList']" ).trigger('focusout blur');});
				}
			});

						
			
			
			
			
/////////////////////////////////////////////////////////Add Project////////////////////////////////////////////////////////////				

			$("#addProjectButton").on("click", function(){
				var rowCount = $('#projectList tr').length;
				var projectIdTd = "<td name='projectId' id='projectId"+rowCount+"' >"+rowCount+"</td>";
				var projectNameTd = "<td name='projectName' id='projectName"+rowCount+"' >projectName "+rowCount+"</td>";
				var cycleNameListTd = "<td name='cycleNameList' id='cycleNameList"+rowCount+"' >Cycle1</td>";
				var uploadTd = "<td id='upload"+rowCount+"' ><div style='padding-top: 10px;' align='center'>  <form action='upload/"+rowCount+"' > <input class='btn btn-primary' style='padding:2;font-size:10px;' type='submit' value='Upload File' name ='upload' id='uploadInput/"+rowCount+"' /> </form> </div></td>";
				var deleteProjectTd = "<td id='deleteProject"+rowCount+"'><div style='padding-top: 10px;' align='center'>  <form action='delete/${project.id}' > <input class='btn btn-primary' style='padding:2;font-size:10px;' type='submit' value='Delete Project' name ='delete' id='deleteInput/"+rowCount+"' /> </form> </div></td>";

				
				var rowData = "<tr>"+projectIdTd+projectNameTd+cycleNameListTd+uploadTd+deleteProjectTd+"</tr>";
				
				$('#projectList tr:last').after(rowData);
				//$('#projectList').refresh();
			});
			
			

		});
		function projectNameClicked(){
			console.debug("clicked");			
		}		
	</script>
</html>
