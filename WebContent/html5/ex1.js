/*	function printResult()
	{
		var btnPrint = document.getElementById("btn-print");
		var x,y;
		x = prompt("x값을 입력하세요",0);
		y = prompt("y값을 입력하세요",0);
		x = parseInt(x);
		y = parseInt(y);
		btnPrint.value=x+y;
	}
	function init()
	{
		var btnPrint = document.getElementById("btn-print");
		btnPrint.onclick = printResult;		
	}
	window.onload = init;*/
	function printResult()
	{
		var btnPrint = document.getElementById("btn-sum");
		var x = document.getElementById("txt-x");
		var y = document.getElementById("txt-y");
		x = prompt("x값을 입력하세요",0);
		y = prompt("y값을 입력하세요",0);
		x = parseInt(x);
		y = parseInt(y);
		btnPrint.value=x+y;
	}
	function init()
	{
		var btnPrint = document.getElementById("btn-print");
		btnPrint.onclick = printResult;		
	}
	window.onload = init;