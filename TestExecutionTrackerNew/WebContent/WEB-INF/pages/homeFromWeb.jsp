<html lang="en">
<body>

    <table id="excelSheet"></table>

    <script type="text/javascript"> 
        $(document).ready(function () {
            $("#jqGrid").jqGrid({
                url: 'data',
                mtype: "GET",
                datatype: "jsonp",
                colModel: [
                    { label: 'Functionality', name: 'OrderID', key: true},
                    { label: 'Test Case Number', name: 'CustomerID'},
                    { label: 'Level1 Test Status', name: 'OrderDate'},
                    { label: 'Level1 User', name: 'Freight'},
                    { label: 'Level1 Date', name: 'ShipName'}
                ],
				viewrecords: true,
                pager: "#jqGridPager"
            });
        });
 
   </script>

    
</body>
</html>