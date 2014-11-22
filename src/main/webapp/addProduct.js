/**
 * 
 */

document.getElementById("part").onclick =
	function()
	{
		//hide the <div> for bicycle properties and show the <div> for part properties
		document.getElementById("bicycleProperties").style.display = "none";
		document.getElementById("partProperties").style.display = "block";
	};

	
document.getElementById("bicycle").onclick =
	function()
	{
		//show the <div> for bicycle properties and hide the <div> for part properties
		document.getElementById("bicycleProperties").style.display = "block";
		document.getElementById("partProperties").style.display = "none";
	};
	
document.getElementById("addAnotherMaterial").onclick =
	function()
	{
		//add another drop-down list for a material
		var select = document.getElementById("material0").cloneNode(true);
		var br = document.createElement("BR");
		
		var materialList = document.getElementsByClassName("material");

		select.setAttribute("id", "material" + materialList.length);
		select.setAttribute("name", "material" + materialList.length);
		
		var partProperties = document.getElementById("partProperties");
		var addAnotherMaterial = document.getElementById("addAnotherMaterial");
		
		partProperties.insertBefore(select, addAnotherMaterial);
		partProperties.insertBefore(br, addAnotherMaterial);
	};

document.getElementById("removeLastMaterial").onclick =
	function()
	{
		//remove the last drop-down list for a material if there is more than 1
		var materialListLength = document.getElementsByClassName("material").length;
		
		if(materialListLength > 1)
		{
			var partProperties = document.getElementById("partProperties");
			
			partProperties.removeChild(document.getElementsByClassName("material")[materialListLength - 1]);
			partProperties.removeChild(partProperties.getElementsByTagName("BR")[materialListLength - 1]);
		}
	};
	
	document.getElementById("addAnotherPart").onclick =
	function()
	{
		//add another drop-down list for a part, and another for its quantity
		var select = document.getElementById("part0").cloneNode(true);
		var text = document.getElementById("quantity0").cloneNode(true);
		var br = document.createElement("BR");
		
		var partList = document.getElementsByClassName("part");
		
		var string = "part" + partList.length;
		select.setAttribute("id", string);
		select.setAttribute("name", string);
		
		string = "quantity" + partList.length;
		text.setAttribute("id", string);
		text.setAttribute("name", string);
		
		var bicycleProperties = document.getElementById("bicycleProperties");
		var addAnotherPart = document.getElementById("addAnotherPart");
		
		bicycleProperties.insertBefore(select, addAnotherPart);
		bicycleProperties.insertBefore(text, addAnotherPart);
		bicycleProperties.insertBefore(br, addAnotherPart);
	};

document.getElementById("removeLastPart").onclick =
	function()
	{
		//remove the last drop-down list for a part and its quantity if there is more than 1 list for parts
		var partListLength = document.getElementsByClassName("part").length;
		
		if(partListLength > 1)
		{
			var bicycleProperties = document.getElementById("bicycleProperties");
			
			bicycleProperties.removeChild(document.getElementsByClassName("part")[partListLength - 1]);
			bicycleProperties.removeChild(document.getElementsByClassName("quantity")[partListLength - 1]);
			bicycleProperties.removeChild(bicycleProperties.getElementsByTagName("BR")[partListLength + 1]);
		}
	};