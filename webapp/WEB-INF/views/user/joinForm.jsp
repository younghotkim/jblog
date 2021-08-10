<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">

<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>

</head>
<body>
	<div id="center-content">

		<!-- 메인 해더 -->
		<c:import url="/WEB-INF/views/includes/main-header.jsp"></c:import>


		<div>
			<form id="joinForm" method="post" action="${pageContext.request.contextPath}/user/join">
				<table>
					<colgroup>
						<col style="width: 100px;">
						<col style="width: 170px;">
						<col style="">
					</colgroup>
					<tr>
						<td><label for="txtId">아이디</label></td>
						<td><input id="txtId" type="text" name="id"></td>
						<td><button id="btnIdCheck" type="button">아이디체크</button></td>
					</tr>
					<tr>
						<td></td>
						<td id="tdMsg" colspan="2"></td>
					</tr>
					<tr>
						<td><label for="txtPassword">패스워드</label></td>
						<td><input id="txtPassword" type="password" name="password" value=""></td>
						<td></td>
					</tr>
					<tr>
						<td><label for="txtUserName">이름</label></td>
						<td><input id="txtUserName" type="text" name="userName" value=""></td>
						<td></td>
					</tr>
					<tr>
						<td><span>약관동의</span></td>
						<td colspan="3"><input id="chkAgree" type="checkbox" name="agree" value=""> <label for="chkAgree">서비스 약관에 동의합니다.</label></td>
					</tr>
				</table>
				<div id="btnArea">
					<button id="btnJoin" class="btn" type="submit">회원가입</button>
				</div>

			</form>

		</div>


		<!-- 메인 푸터  자리-->
		<c:import url="/WEB-INF/views/includes/main-footer.jsp"></c:import>

	</div>

</body>


<script type="text/javascript">
	/* Ajax로 회원가입하기

	 $("#btnJoin").on("click", function() {

	 event.preventDefault();

	 console.log("btnJoin.click")

	 var userVo = {

	 id : $("#txtId").val(),
	 password : $("#txtPassword").val(),
	 userName : $("#txtUserName").val()

	 };

	 console.log(userVo)

	 $.ajax({

	 url : "${pageContext.request.contextPath }/user/join",
	 type : "post",
	 contentType : "application/json",
	 data : JSON.stringify(userVo),

	 dataType : "json",
	 success : function(count) {

	 console.log(count + "건 회원가입 성공")

	 },
	 error : function(XHR, status, error) {
	 console.error(status + " : " + error);
	 }
	 });

	 });
	
	 */

	$("#joinForm").on("submit", function() {

		console.log("joinForm.submit")

		var id = $("#txtId").val();

		if (id.length < 1) {

			alter("아이디를 입력해주세요.")

			return false;

		}

		var idCheck = $("#tdMsg").text();

		if (idCheck != "사용할 수 있는 아이디 입니다.") {

			alter("아이디 중복체크를 해주세요.")

			return false;

		}

		var password = $("#txtPassword").val();

		if (password.length < 1) {

			alter("패스워드를 입력해주세요.")

			return false;

		}

		var name = $("#txtUserName").val();

		if (name.length < 1) {

			alert("이름을 입력해주세요.")

			return false;

		}

		var agree = $("#chkAgree").is(" :checked");

		if (agree == false) {

			alter("약관에 동의해 주세요.")

			return false;

		}

		return true;

	});

	$("#btnIdCheck").on("click", function() {

		console.log("btnIdCheck.click")

		var id = $("#txtId").val();

		$.ajax({
			url : "${pageContext.request.contextPath }/user/idCheck",
			type : "get",
			contentType : "application/json",
			data : {
				id : id
			},

			dataType : "json",
			success : function(count) {
				/*성공시 처리해야될 코드 작성*/

				if (count > 0) {

					$("#tdMsg").html("다른 아이디로 가입해 주세요.");

				} else if (id == "") {

					$("#tdMsg").html("아이디를 입력해주세요");
				}

				else {

					$("#tdMsg").html("사용할 수 있는 아이디 입니다.");

				}

			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});

	});
</script>


</html>