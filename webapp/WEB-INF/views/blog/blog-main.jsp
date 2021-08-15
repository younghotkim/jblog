<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
</head>

<body>
	<div id="wrap">

		<!-- 개인블로그 해더 -->
		<c:import url="/WEB-INF/views/includes/blog-header.jsp"></c:import>

		<div id="content" class="clearfix">
			<div id="profilecate_area">
				<div id="profile">


					<c:choose>

						<c:when test="${ empty blogVo.logoFile}">

							<img id="proImg" src="${pageContext.request.contextPath}/assets/images/spring-logo.jpg">

						</c:when>

						<c:otherwise>
							<img id="proImg" src="${pageContext.request.contextPath }/upload/${blogVo.logoFile}">
						</c:otherwise>

					</c:choose>
					<div id="nick">${blogVo.userName}(${blogVo.id})님</div>
				</div>
				<div id="cate">
					<div class="text-left">
						<strong>카테고리</strong>
					</div>
					<ul id="cateList" class="text-left">
						
						<c:forEach items="${categoryList }" var="categoryVo">
							<li><a href="${pageContext.request.contextPath}/${blogVo.id}?crtCate=${categoryVo.cateNo}">${categoryVo.cateName }</a></li>
						</c:forEach>
						

					</ul>
				</div>
			</div>
			<!-- profilecate_area -->

			<div id="post_area">

				<c:choose>
					<c:when test="${!empty postVo}">
						<div id="postBox" class="clearfix">
							<div id="postTitle" class="text-left" data-postno="${postVo.postNo }">
								<strong>${postVo.postTitle }</strong>
							</div>
							<div id="postDate" class="text-left">
								<strong>${postVo.regDate }</strong>
							</div>
							<div id="postNick">${postVo.userName }(${postVo.id })님</div>
						</div>
						<!-- //postBox -->

						<div id="post">${postVo.postContent }</div>
						<!-- //post -->
					</c:when>
					<c:otherwise>
						<!-- 글이 없는 경우 -->
						<div id="postBox" class="clearfix">
							<div id="postTitle" class="text-left">
								<strong>등록된 글이 없습니다.</strong>
							</div>
						</div>
					</c:otherwise>
				</c:choose>
				<div id="comment_area">
						<c:if test="${!empty authUser && !empty postVo}">
							<div id = comment_input>
								<table>
									<tr>
										<td id="commentId">${authUser.userName }</td>
										<td><input id="cmtContent" type="text" name="content" value=""></td>
										<td><button id="commentBtn">저장</button></td>
									</tr>
								</table>
							</div>	
						</c:if>						
				</div>
				<div id="list">
					<div id="listTitle" class="text-left">
						<strong>카테고리의 글</strong>
					</div>
					<table>
						<colgroup>
							<col style="">
							<col style="width: 20%;">
						</colgroup>
					
						<c:if test="${!empty postList }">
							<c:forEach items="${postList}" var="postList">
								<tr>
									<td class="text-left"><a href="${pageContext.request.contextPath}/${blogVo.id}?crtCate=${postList.cateNo}&postNo=${postList.postNo}">${postList.postTitle }</a></td>
									<td class="text-right">${postList.regDate }</td>
								</tr>
							</c:forEach>
						</c:if>
					</table>
				</div>
				<!-- //list -->
			</div>
			<!-- //post_area -->



		</div>
		<!-- //content -->
		<div class=></div>
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp"></c:import>



	</div>
	<!-- //wrap -->
</body>
</html>