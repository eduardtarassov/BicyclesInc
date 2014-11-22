/**
 * 
 */
document.getElementById("unitPrice").onchange = document.getElementById("amount").onchange =
	function()
	{
		document.getElementById("valueParagraph").innerHTML =
			document.getElementById("amount").value * document.getElementById("unitPrice").value;
	};