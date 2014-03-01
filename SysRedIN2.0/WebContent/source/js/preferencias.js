$(document).ready(function(){
			document.getElementById('form-edit').style.display="block";
			document.getElementById('form-senha').style.display="none";
			document.getElementById('form-delete').style.display="none";

			document.getElementById('li1').className="active";

	$("#edit").click(
		function(){
			document.getElementById('form-edit').style.display="block";
			document.getElementById('form-senha').style.display="none";
			document.getElementById('form-delete').style.display="none";

			document.getElementById('li1').className="active";
			document.getElementById('li2').className="";
			document.getElementById('li3').className="";
	});

	$("#senha").click(
		function(){
			document.getElementById('form-edit').style.display="none";
			document.getElementById('form-senha').style.display="block";
			document.getElementById('form-delete').style.display="none";		
			
			document.getElementById('li1').className="";
			document.getElementById('li2').className="active";
			document.getElementById('li3').className="";
	});

	$("#delete").click(
		function(){
			document.getElementById('form-edit').style.display="none";
			document.getElementById('form-senha').style.display="none";
			document.getElementById('form-delete').style.display="block";

			document.getElementById('li1').className="";
			document.getElementById('li2').className="";
			document.getElementById('li3').className="active";

			var num = Math.floor((Math.random() * (999999-1+1))+1);
			document.getElementById('codigo').innerText = num; 
	});

	$("#bDelete").click(
		function(){
			var cod = document.getElementById('codigo').innerText;
			var confCod = document.getElementById('inCodigo').value;
			if (cod == confCod) {
				
			};
	});
});