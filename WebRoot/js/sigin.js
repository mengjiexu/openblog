 var nameTag=0;
 var EmailTag=0;
 var passwordTag=0;
 var userid = -1;

 function logout(){
	 $.get("./user_logout",null,function(data){
		location.reload(); 
	 });
 }
 
 function loaduserinfo(){
	 $.get("./user_getuserinfo",null,function(data){
		 //alert(data);
		 if(data.indexOf("success")!=-1){
			 $("#login").css("display", "none");
			 $("#selfinfo").css("display", "block");
			 userid = 1;
		 }
	 });
 }
$("#re_email").change(function(){
	//alert('hello');
	$.get(
		"./user_checkemail?useremail="+$("#re_email").val(),
		null,
		function(data){
			if(data.indexOf("success")!=-1){
				EmailTag=1;	
				$("#re_email_message").empty();
			}else{
				//alert(data);
				$("#re_email_message").empty().append("<b style='color: red;'>email已存在</b>")	
			}
		}
	);
});

$("#re_password").change(function(){
	if($("#re_password").val().length>=8&&$("#re_password").val().length<=16){
		passwordTag=1;
		$("#re_password_message").empty();
	}else{
		$("#re_password_message").empty().append("<b style='color: red;'>密码(8~16位)</b>");
	}
});
function doRegister(){
	if($("#re_name").val().length>1&&EmailTag==1&&passwordTag==1){
		var name=$("#re_name").val();
	    var email=$("#re_email").val();
	    var password=$("#re_password").val();
	    //alert(nameTag+" "+EmailTag+" "+passwordTag);
	    //alert("sub_register");
	    $.get("./user_register?username="+name+"&useremail="+email+"&userpass="+password,null,
	    	function(data){
	    		alert(data);
	    	});
	}else{
		alert("请注意填写完整信息 ！");
	}
}
function doLogin(){
	var u_name=$("#userEmail").val();
	var u_password=$("#userPassword").val();
	if(u_name!=""&&u_password!=""){
	     //登陆
	     //alert("login....")
		$.get("./user_login?useremail="+u_name+"&userpass="+u_password,
				null,
				function(data){
				if(data.indexOf("success")!=-1){
					location.reload();
				}else{
					alert(data);
				}
		});
	}else{
		$("#loginMessage").empty().append("<b style='color: red;'>邮箱或密码不能为空</b>");
	}
}
function doFind(){
	$("#findPass").show();
	$("#register").hide();
}
function subFind(){
 var email=$("#find_Email").val();
 alert(email);
}
function cancelFind(){
	$("#findPass").hide();
	$("#register").show();
}
$('#sigModal').on('hide.bs.modal', function () {
   //清空
   $("#userEmail").val("");
   $("#userPassword").val("");
   $("#re_name").val("");
   $("#re_email").val("");
   $("#re_password").val("");
   $("#find_Email").val("");
   $("#findPass").hide();
   $("#register").show();
   $("#re_name_message").empty();
   $("#loginMessage").empty();
   $("#re_email_message").empty();
   $("#re_password_message").empty();
});

function loginByFace(){
	$("#loginByPass").hide();
	$("#loginByFace").show();
}
function canelFace(){
	$("#loginByPass").show();
	$("#loginByFace").hide();
}
/*用于传参*/
function GetUrlParms() {
    var args = new Object();
    var query = location.search.substring(1); //获取查询串   
    var pairs = query.split("&"); //在逗号处断开   
    for (var i = 0; i < pairs.length; i++) {
        var pos = pairs[i].indexOf('='); //查找name=value   
        if (pos == -1) continue; //如果没有找到就跳过   
        var argname = pairs[i].substring(0, pos); //提取name   
        var value = pairs[i].substring(pos + 1); //提取value   
        args[argname] = unescape(value); //存为属性   
    }
    return args;
}
//编译json对象为url字符串
var parseParam=function(param, key){ 
 var paramStr=""; 
 if(param instanceof String||param instanceof Number||param instanceof Boolean){ 
   paramStr+="&"+key+"="+encodeURIComponent(param); 
 }else{ 
   $.each(param,function(i){ 
     var k=(key==null?i:key+(param instanceof Array?"["+i+"]":"."+i)); 
     paramStr+='&'+parseParam(this, k); 
   }); 
 } 
 return paramStr.substr(1); 
};
$(loaduserinfo());
