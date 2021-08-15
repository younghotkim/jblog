<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>
</head>

<body>
	<div id="wrap">

		<!-- 개인블로그 해더 -->
		<c:import url="/WEB-INF/views/includes/blog-header.jsp"></c:import>

		<div id="content">
			<ul id="admin-menu" class="clearfix">
				<li class="tabbtn selected"><a href="${pageContext.request.contextPath }/${blogVo.id}/admin/basic">기본설정</a></li>
				<li class="tabbtn"><a href="${pageContext.request.contextPath }/${blogVo.id}/admin/category">카테고리</a></li>
				<li class="tabbtn"><a href="${pageContext.request.contextPath }/${blogVo.id}/admin/writeForm">글작성</a></li>
			</ul>
			<!-- //admin-menu -->

			<div id="admin-content">

				<table id="admin-cate-list">
					<colgroup>
						<col style="width: 50px;">
						<col style="width: 200px;">
						<col style="width: 100px;">
						<col>
						<col style="width: 50px;">
					</colgroup>
					<thead>
						<tr>
							<th>번호</th>
							<th>카테고리명</th>
							<th>포스트 수</th>
							<th>설명</th>
							<th>삭제</th>
						</tr>
					</thead>
					<tbody id="cateList">
						<!-- 리스트 영역 -->

						<!-- 리스트 영역 -->
					</tbody>
				</table>

				<table id="admin-cate-add">
					<colgroup>
						<col style="width: 100px;">
						<col style="">
					</colgroup>
					<tr>
						<td class="t">카테고리명</td>
						<td><input type="text" name="name" value=""></td>
					</tr>
					<tr>
						<td class="t">설명</td>
						<td><input type="text" name="desc"></td>
					</tr>
				</table>

				<div id="btnArea">
					<button id="btnAddCate" class="btn_l" type="submit">카테고리추가</button>
				</div>

			</div>
			<!-- //admin-content -->
		</div>
		<!-- //content -->


		<!-- 개인블로그 푸터 -->
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp"></c:import>


	</div>
	<!-- //wrap -->
</body>

<script type="text/javascript">

	$(document).ready(function() {

		console.log("화면로딩")
		
		fetchList();

	});
	
	$("#btnAddCate").on("click", function() {
		
		event.preventDefault();
		
		console.log("카테고리 추가")
		
		var categoryVo = {
				
			id : "${blogVo.id}",
			cateName : $("[name=name]").val(),
			description : $("[name=desc]").val()
		}
		
		$.ajax({

			url : "${pageContext.request.contextPath }/${blogVo.id}/admin/category/add",
			type : "post",
			contentType : "application/json",
			data : JSON.stringify(categoryVo),

			dataType : "json",
			success : function(categoryVo) {

				render(cateVo, "add");
				
				
				$("[name=name]").val("");
				$("[name=desc]").val("");

			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});

		
		
	});
	
	$("#cateList").on("click", ".btnCateDel", function() {
		
		var cateNo = $(this).data("no");
		
		$.ajax({

			url : "${pageContext.request.contextPath }/${blogVo.id}/admin/category/delete",
			type : "post",
			contentType : "application/json",
			data : JSON.stringify(cateNo),

			dataType : "json",
			success : function(count) {

				if (count > 0) {
					
					$("#t-" + cateNo).remove();
					
				} else if (count === 0) {
					
					alert("삭제 할 수 없습니다.")
					
				}

			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
		
		
	});
	
	////////////////////fetch////////////////////////
	
	function fetchList() {
		
		var id = "${blogVo.id}";
		
		console.log(id);
		
		$.ajax({

			url : "${pageContext.request.contextPath }/${blogVo.id}/admin/category/list",
			type : "post",
			contentType : "application/json",
			data : JSON.stringify(id),

			dataType : "json",
			success : function(categoryList) {

				for (var i = 0; i<categoryList.length; i++) {
					
					render(categoryList[i], "list");
					
				}

			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
		
		
	}
	
	
	
	////////////////////render///////////////////////
	
	function render(categoryVo, type) {
		
		var str = "";
		
		str += '<tr id="t-'+ categoryVo.cateNo +'">';
		str += '	<td>' + categoryVo.cateNo + '</td>';
		str += '	<td>' + categoryVo.cateName + '</td>';
		str += '	<td>' + categoryVo.postCount + '</td>';
		str += '	<td>' + categoryVo.description + '</td>';
		str += '    <td class="text-center">';
		str += '    <img class="btnCateDel" data-no="' + categoryVo.cateNo + '"src="${pageContext.request.contextPath}/assets/images/delete.jpg">';
		str += '	</td>';
		str += '</tr>';
		
		if(type === "list") {
			
			$("#cateList").append(str);
			
		} else if(type === "add") {
			
			$("#cateList").prepend(str);
			
		} else {
			
			consle.log("타입을 지정해 주세요");
			
		}
		
	}	
		
</script>



</html>