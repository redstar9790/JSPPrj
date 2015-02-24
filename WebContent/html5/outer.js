function init()
{
	var openDoc = document.getElementById("open-doc");
	openDoc.onclick = openDocClick;
}
function openDocClick()
{
	window.frames[0].location.href="http://www.newlecture.com";
}
window.onload = init;