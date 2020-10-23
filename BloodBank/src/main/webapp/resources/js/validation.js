
function validate(frm){
	alert("dsf");
	let recvName=frm.recvName.value;
	let recvAddrs=frm.recvAddrs.value;
	let recvMobNO=frm.recvMobNO.value;
	let flag=true;
	
	document.getElementById("recvNameId").innerHTML="";
	document.getElementById("recvAddrsId").innerHTML="";
	document.getElementById("recvMobNOId").innerHTML="";
	
	frm.jsFlag.value=true;
	alert("hi");
	
	if(recvName==""){
		flag=false;
		document.getElementById("recvNameId").innerHTML="Reciver name is mandatory(JS)";
	}
	else if(recvName.length<3 || recvName.length>15){
		flag=false;
		document.getElementById("recvNameId").innerHTML="Reciver name must have min 3 char and max 15(Js)";
	} 
	
	if(recvAddrs==""){
		flag=false;
		document.getElementById("recvAddrsId").innerHTML="Address is mandatory(JS)";
	}
	else if(recvAddrs.length<4 || recvAddrs.length>15){
		flag=false;
		document.getElementById("recvAddrsId").innerHTML="Adress must have min 4 char and max 15(JS)";
	} 
	
	if(recvMobNO==""){
		flag=false;
		document.getElementById("recvMobNOId").innerHTML="Mobile no. is mandatory(JS)";
	}
	else if(isNaN(recvMobNO)){
		flag=false;
		document.getElementById("recvMobNOId").innerHTML="Mobile no must be in numberic";
	}
	
	return flag;
}