<html>
	<head>
	    <title>Test Execution Tracker</title>
	
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


	<body style="padding-top: 5px;">
		<div align="center" id="navBar"> Test Execution Tracker</div>
		
		
		<div align="center"> 
			<table id="navigationDiv" style="width:95%">
				<tr>
					<td style="width:33%">
						<div align="left" id="projectNameDiv" ></div>
						<div align="left" id="cycleNameDiv" ></div>
						<input  class="btn btn-primary" type="submit" value="Filter Table" id="filterButton" style='padding:2;font-size:10px'> 
					</td>
					<td style="width:33%">
						<div align="center" id="reportDiv" >
							<input  onclick="location.href='reportHome'" class="btn btn-primary" type="submit" value="Full Report" id="reportButton" style='padding:2;font-size:10px' >
							<input  onclick="location.href='dateWiseReport'" class="btn btn-primary" type="submit" value="Datewise report" id="reportButton" style='padding:2;font-size:10px' >
							
							</input>
						</div>
					</td>
					<td style="width:33%">
						<div align="right">User: ${userName}<form action="../a/logout" method="post" ><input  class="btn btn-primary" type="submit" value="logout" style='padding:2;font-size:10px'> </form></div>
					</td>
				</tr>
			</table>
		</div>
		<div align="left">
			<div align="left" id="filterDiv" style="border:1px solid green; border-width: thin;width:60%; margin-left: 2.5%" >
				<table style="width:95%">
					<tr>
						<td style="width:15%">Select Column</td>
						<td style="width:15%">
							<select id="filerName">
								<option>Select Filter Name</option>
								<option value="fsdName">FSD</option>
								<option value="functionality">Functionality</option>
								<option value="testCaseNumber">Test Case Number</option>
								<option value="level1User">Level 1 User</option>
								<option value="level2User">Level 2 User</option>
								<option value="level1TestStatus">Level 1 Test Status</option>
								<option value="level2TestStatus">Level 2 Test Status</option>
							</select>
						</td>
						<td>
							<select id="filerValue">
							</select>
							<div id="searchDiv">
								<input type="text" name="searchText" id="searchText" placeholder="Search.....">
								<input  class="btn btn-primary" type="submit" value="Search" id="searchButton" style='padding:2;font-size:10px'>
							</div> 
						</td>
						<td>
						</td>
					</tr>
				</table>
			</div> 
		</div>
		<br/>
		<div align="center"> 
			
			<table id="navigationDiv" style="width: 95%">
				<tr>
					<td><div align="left" ><input  class="btn btn-primary" type="submit" value="Previous" id="previous" style='padding:2;font-size:10px'></div></td>
					<td>
						<div align="center" >
							Go to Page <select id="pageDropDown"></select>
						</div>
					</td>
					<td><div align="Right" ><input  class="btn btn-primary" type="submit" value="Next" id="next" style='padding:2;font-size:10px'></div></td>
				</tr>
			</table>
			<table id="excelSheet" style="width: 95%;" border="1">  
				<thead>
				    <tr>
				    
				      <th width="4%">FSD</th>
				      <th width="10%">Functionality</th>
				      <th >Test Case Number</th>
				      <th width="5%">Browser</th>
				      <th width="5%">Level1 Test Status</th>
				      <th width="5%">Level1 User</th>
				      <th width="7%">Level1 Date</th>
				      <th width="5%">ALM Defect</th>
				      <th width="5%">Test Evidence Uploaded</th>
				      <th width="5%">Level2 BV Status</th>
				      <th width="5%">Level2 User</th>
				      <th width="7%">Level2 Date</th>
				      <th width="2%">Comments</th>
				      <th width="2%">History</th>
				    </tr>
			  	</thead>
			  	<tbody>
			   	</tbody>
			</table>
		</div>			
	</body>

<script>
	$( document ).ready(function() {
		var projectName = "";
		var cycleName = "";
		var type ="HOME";
		fetchData(type);
		
		$("#next").on("click", function(){
			fetchData("NEXT");
		});
		$("#previous").on("click", function(){
			fetchData("PREVIOUS");
		});
		$("#pageDropDown").on("change", function(){
			fetchData("PAGE");
		});
		
		$("#filterDiv").hide();
		$("#filerValue").hide();
		$("#searchDiv").hide();
		
		$("#filterButton").on("click", function(){
			
			if ( $("#filterDiv").css('display') == 'none' ){
				$("#filterDiv").show();
			}
			else
			{
				$("#filterDiv").hide();	
			}
		});
		
		$("#filerName").on("change", function(){
			getUnique($("#filerName").val());
		});
		$("#filerValue").on("change", function(){
			setFilter($("#filerName").val(), $("#filerValue").val());
		});
		$("#searchButton").on("click", function(){
			setFilter($("#filerName").val(), $("#searchText").val());
		});
		
	});
	function setFilter(filterName, filterValue){
		var queryString="&filterName="+filterName+"&filterValue="+filterValue;
		
		$.ajax({
			url: "setFilter",
			type: "post",
			data: queryString,
			success: function(){
				//alert("Filter Set");
				fetchData("HOME");			
			}
		});
	}
	function clearFilter(){
		
		$.ajax({
			url: "clearFilter",
			type: "post",
			success: function(){
				//alert("Filter cleared");
				//fetchData("HOME");			
			}
		});
	}
	function getUnique(rowName){
		var queryString="&rowName="+rowName;
		
		if(rowName =="testCaseNumber")
		{
			$("#searchDiv").show();
			$('#filerValue').hide();
		}
		else
		{
			$.ajax({
				url: "getUnique",
				type: "post",
				data: queryString,
				success: function(data){
					$('#filerValue').find('option').remove().end();
					$('#filerValue').append( new Option("Select Value", "") );
					$.each(data, function(idx, elem){
						$('#filerValue').append( new Option(elem) );
					});	
					$('#filerValue').show();
					$("#searchDiv").hide();
				}
			});	
		}
	}
	function fetchData(callType){
		var queryString = "&callType="+callType;
		if(callType == "PAGE")
		{
			queryString = queryString + "&pageNumber="+$('#pageDropDown').val();
		}
		$.ajax({
			url: "data",
			type: "post",
			data: queryString,
			success: function(data){
	        var table = $("#excelSheet tbody");
	        var count = 1;
	        if(data.error){
	        	alert("No Data Found");
	        	window.location.href = "projectCycleSelection"; 
	        	return;
	        }

	        if(data.lastPage){
	        	alert("This is the last Page");
	        	return;
	        }
	        if(data.firstPage){
	        	alert("This is the First Page");
	        	return;
	        }
			$('#excelSheet tbody > tr').remove();

				$.each(data.list, function(idx, elem){
					//console.debug(count+ " --- "+elem.functionality+" ----- "+elem.testCaseNumber+" ----- "+elem.browser);
					count=count+1;
					
					var tableData = "<tr> ";
					var fsdName = "<td name ='fsdName' id='fsdName"+elem.id+"'>"+elem.fsdName+"</td> ";
					var functionality = "<td name ='functionality' id='functionality"+elem.id+"'>"+elem.functionality+"</td> ";
					var testCaseNumber = " <td name ='testCaseNumber' id='testCaseNumber"+elem.id+"'>"+elem.testCaseNumber+"</td> ";
					var browser = "<td name ='browser' id='browser"+elem.id+"'>"+elem.browser+" </td> ";
					var level1TestStatus = "<td name ='level1TestStatus' id='level1TestStatus"+elem.id+"'>"+elem.level1TestStatus+" </td> ";
					var level1User = "<td name ='level1User' id='level1User"+elem.id+"'>"+elem.level1User+" </td> ";
					
					var level1DateValue = elem.level1Date;
					if(level1DateValue == null) level1DateValue = "";
					var level1Date = "<td name ='level1Date' id='level1Date"+elem.id+"'>"+level1DateValue.substring(0,10)+" </td> ";
					var almDefect = "<td name ='almDefect' id='almDefect"+elem.id+"'>"+elem.almDefect+" </td> ";
					var testEvidenceUploaded = "<td name ='testEvidenceUploaded' id='testEvidenceUploaded"+elem.id+"'>"+elem.testEvidenceUploaded+" </td> ";
					var level2BVStatus = "<td name ='level2BVStatus' id='level2BVStatus"+elem.id+"'>"+elem.level2BVStatus+" </td> ";
					var level2User = "<td name ='level2User' id='level2User"+elem.id+"'>"+elem.level2User+" </td> ";

					
					var level2DateValue = elem.level2Date;
					//alert(level2DateValue == null);
					if(level2DateValue == null) level2DateValue = "";
					var level2Date = "<td name ='level2Date' id='level2Date"+elem.id+"'>"+level2DateValue.substring(0,10)+" </td> ";
					
					var tableData= tableData+fsdName+functionality+testCaseNumber+
										browser+level1TestStatus+level1User+level1Date+
										almDefect+testEvidenceUploaded+level2BVStatus+
										level2User+level2Date+"</td>";
					tableData = tableData + "<td><div style='padding: 1px;'>  <form action='comment/"+elem.id+"' > <input class='btn  btn-primary' style='padding:2;font-size:10px' type='submit' value='Comments' name ='comments' id='comments"+elem.id+"'/> </form> </div></td>"; 
					tableData = tableData + "<td><div style='padding: 1px;'>  <form action='history/"+elem.id+"' > <input class='btn btn-primary' style='padding:2;font-size:10px;' type='submit' value='History' name ='history' id='history"+elem.id+"'/> </form> </div></td>"; 
					tableData = tableData + "</tr>";
					
			        table.append(tableData);
			    });
				$("#projectNameDiv").html("Project Name: "+data.projectName);
				$("#cycleNameDiv").html("Cycle Name: "+data.cycleName);

				var options = $("#pageDropDown");
				var currentSelection = $("#pageDropDown").val();
				$('#pageDropDown').find('option').remove().end();
				for (var n = 0; n < data.pageCount; ++ n)
				{
					$('#pageDropDown').append( new Option(n+1,n+1) );
				}
				if(data.pageCount == 0)$('#pageDropDown').append( new Option(1,1) );
				
				
				$("#pageDropDown option[value="+currentSelection+"]").attr('selected','selected');
				
////////////////////////////////////////////////////Browser Input/////////////////////////////////////////////////////////////////				
				$( "[name='browser']"  ).on('focusout blur', function () {
					console.debug("browser focus out");
					if($( this ).html().indexOf("<input") == 0)
					{
						var htmlValue = $(this).find("[name='browserInput']").val();
						if(htmlValue==null || htmlValue == "" || htmlValue.trim() == "")return;
						$( this ).empty();
						$( this ).html(htmlValue.trim());
						updateRow(this.id.substring(7), "browser", htmlValue);
					}
				});
				
				$( "[name='browser']"  ).click(function() {
					console.debug("browser clicked");
					if($( this ).html().indexOf("<input") !=0)
					{
						var htmlValue = $( this ).html();
						$( this ).empty();
						$( this ).html("<input type='text' name='browserInput' id='browserInput' value='"+ htmlValue +"'> </input>")
						$( "#browserInput" ).focus();
						$( "#browserInput" ).bind( "focusout blur", function() {$( "[name='browser']" ).trigger('focusout blur');});
					}
				});
				
				
/////////////////////////////////////////////////////////ALM Defect Input////////////////////////////////////////////////////////////				
				$( "[name='almDefect']"  ).on('focusout blur', function () {
					console.debug("browser focus out");
					if($( this ).html().indexOf("<input") == 0)
					{
						var htmlValue = $(this).find("[name='almDefectInput']").val();
						if(htmlValue==null || htmlValue == "" || htmlValue.trim() == "")return;
						$( this ).empty();
						$( this ).html(htmlValue.trim());
						updateRow(this.id.substring(9), "almDefect", htmlValue);
					}
				});
				$( "[name='almDefect']"  ).click(function() {
					if($( this ).html().indexOf("<input") !=0)
					{
						var htmlValue = $( this ).html();
						$( this ).empty();
						$( this ).html("<input type='text' name='almDefectInput' id='almDefectInput' value='"+ htmlValue +"'> </input>")
						$( "#almDefectInput" ).focus();
						$( "#almDefectInput" ).bind( "focusout blur", function() {$( "[name='almDefect']" ).trigger('focusout blur');});
					}
				});

				
////////////////////////////////////////////////////Level1 Test Status Input/////////////////////////////////////////////////////////////////				
				
				$( "[name='level1TestStatus']"  ).click(function() {
					if($( this ).html().indexOf("<input") !=0)
					{
						//console.debug("clicked");
						var htmlValue = $( this ).html();
						$( this ).empty();
						$( this ).html("<input type='radio' name='level1TestStatusInput' value='Pass'> Pass<br><input type='radio' name='level1TestStatusInput' value='Fail'> Fail<br><input type='radio' name='level1TestStatusInput' value='NotTested'> Not Tested<br><input type='radio' name='level1TestStatusInput' value='Invalid'> Invalid<br><input type='radio' name='level1TestStatusInput' value='Blocked'>Blocked<br>")	
						$( "[name='level1TestStatusInput']" ).click(function(e){
							//console.debug($( "[name='level1TestStatusInput']:checked" ).val());
							
							console.debug($(this).parent());
							updateRow($(this).parent()[0].id.substring(16), "level1TestStatus", $( "[name='level1TestStatusInput']:checked" ).val());
							$( this ).empty();
							//console.debug($( this ).parent().html());
							$( this ).parent().html($( "[name='level1TestStatusInput']:checked" ).val());
							e.stopPropagation();
						})
					}
				});
				
				
////////////////////////////////////////////////////Test Evidence Uploaded Input/////////////////////////////////////////////////////////////////				
				
				$( "[name='testEvidenceUploaded']"  ).click(function() {
					if($( this ).html().indexOf("<input") !=0)
					{
						var htmlValue = $( this ).html();
						$( this ).empty();
						$( this ).html("<input type='radio' name='testEvidenceUploadedInput' value='Yes'> Yes<br><input type='radio' name='testEvidenceUploadedInput' value='No'> No<br>")
						
						$( "[name='testEvidenceUploadedInput']" ).click(function(e){
							updateRow($(this).parent()[0].id.substring(20), "testEvidenceUploaded", $( "[name='testEvidenceUploadedInput']:checked" ).val());
							$( this ).empty();
							$( this ).parent().html($( "[name='testEvidenceUploadedInput']:checked" ).val());
							e.stopPropagation();
						})
					}
					
				});
				$( "[name='level2BVStatus']"  ).click(function() {
					if($( this ).html().indexOf("<input") !=0)
					{
						var htmlValue = $( this ).html();
						$( this ).empty();
						$( this ).html("<input type='radio' name='level2BVStatusInput' value='Pass'> Pass<br><input type='radio' name='level2BVStatusInput' value='Fail'> Fail<br><input type='radio' name='level2BVStatusInput' value='NotTested'> Not Tested<br><input type='radio' name='level1TestStatusInput' value='Invalid'> Invalid<br><input type='radio' name='level1TestStatusInput' value='Blocked'>Blocked<br>")						
						
						$( "[name='level2BVStatusInput']" ).click(function(e){
							updateRow($(this).parent()[0].id.substring(14), "level2BVStatus", $( "[name='level2BVStatusInput']:checked" ).val());
							$( this ).empty();
							$( this ).parent().html($( "[name='level2BVStatusInput']:checked" ).val());
							e.stopPropagation();
						})
					}
				});
	    }});
	}
	function updateRow(rowId, columnName, columnValue)
	{
		console.debug("update Row Called."+rowId);
		var updateRowUrl = "updateExcelSheetPost";
		var queryString = "&rowId="+rowId+"&columnName="+columnName+"&columnValue="+columnValue;
		$.ajax({
			url: updateRowUrl,
			type : 'POST',
			data : queryString,
			success: function(){
				alert("Database updated");
			}
		});
		updateRowInBrowser(rowId);
	}
	function updateRowInBrowser(rowId)
	{
		console.debug("get Row: "+rowId);
		var updateRowUrl = "getExcelSheetRow";
		var queryString = "&rowId="+rowId;
		$.ajax({
			url: updateRowUrl,
			type : 'POST',
			data : queryString,
			success: function(data){
				//alert("Database updated");
				level1User = "#level1User"+rowId;
				level2User = "#level2User"+rowId;
				
				level1Date = "#level1Date"+rowId;
				level2Date = "#level2Date"+rowId;
				
				$(level1User).html(data.list[0].level1User);
				$(level2User).html(data.list[0].level2User);
				
				if(data.list[0].level1Date != null && data.list[0].level1Date != "" && data.list[0].level1Date != 'undefined')
				{
					$(level1Date).html(data.list[0].level1Date.substring(0,10));
				}
				
				if(data.list[0].level2Date != null && data.list[0].level2Date != "" && data.list[0].level2Date != 'undefined')
				{
					$(level2Date).html(data.list[0].level2Date.substring(0,10));
				}
			}
		});
	}
</script>
</html>