//로그인 버튼
$('#btn-login').on("click",()=>{
	var objData = {
			username : $('#username').val(),
			password : $('#password').val(),
	}
	//로그인 요청
	$.ajax({
		type:"post",
		url:"/auth/login",
		data:JSON.stringify(objData),
		contentType:"application/json",
		dataType:"json"
	}).
	done(function(res){
		alert(res.data);
		location.href="/"
	})
	.fail(function(error){
		alert("요청 에러");
	});
	
});
//회원가입버튼
$('#btn-join').on("click", ()=>{
	var objData = {
			username : $('#username').val(),
			password : $('#password').val(),
			email : $('#email').val()
	}
	//회원가입 요청
	$.ajax({
		type: "post",
		url: "/auth/joinPost",
		data: JSON.stringify(objData),
		contentType: "application/json",
		dataType: "json" //서버에서 넘어오는 data형식
	})
	.done(function(res){
		console.log(res);
		alert(res.data);
		location.href="/";
	})
	.fail(function(error){
		alert("요청 에러");
	});
	
});
