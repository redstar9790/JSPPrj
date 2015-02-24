function init()
{
	var divBtn1 = document.getElementById("div-btn1");
	var divBtn2 = document.getElementById("div-btn2");
	var divBtn3 = document.getElementById("div-btn3");
	var divBtn4 = document.getElementById("div-btn4");
	divBtn1.onclick = addNode;
	divBtn2.onclick = addElement;
	divBtn3.onclick = rmvElement;
	divBtn4.onclick = copyElement;
}
function addNode()
{
	var txt1 = document.createTextNode("안녕하세요");
	var div1 = document.getElementById("div1");
	div1.appendChild(txt1);
}
function addElement()
{
	var element1 = document.createElement("img");
	var div1 = document.getElementById("div1");
	element1.src="flower.jpg";
	div1.appendChild(element1);
}
function rmvElement()
{
	var div1 = document.getElementById("div1");
	var last = div1.lastChild;
	div1.removeChild(last);
}
function copyElement()
{
	var div1 = document.getElementById("div1");
	var clone = div1.cloneNode(true);
	var target = document.body;
	target.appendChild(clone);
}
window.onload=init;