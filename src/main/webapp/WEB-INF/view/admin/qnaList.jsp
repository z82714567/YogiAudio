<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="/WEB-INF/view/admin/layout/header.jsp" %>
            
<!-- Content -->
	
	<!-- 수평선 -->
	<hr class="my-5" />
	
	<!-- empty 키워드는 변수가 null이거나 비어있는 경우를 확인 -->
	<c:if test="${empty qnaList }">
		<h1 style="display: flex; justify-content: center;">QnA가 존재하지 않습니다</h1>
	</c:if>
	
	<!-- qnaList가 비어있으면 테이블 자체가 보이지 않도록 함 -->
	<c:if test="${not empty qnaList }">
	<!-- Hoverable Table rows -->
              <div class="card" style="margin: 0px 20px;">
                <h5 class="card-header">QnA</h5>
                <div class="table-responsive text-nowrap">
                  <table class="table table-hover">
                    <thead>
                      <tr>
                        <th>번호</th>
                        <th>작성자</th>
                        <th>제목</th>
                        <th>답변 여부</th>
                        <th>등록일</th>
                        <th>삭제</th>
                      </tr>
                    </thead>
                    <tbody class="table-border-bottom-0">
                    <!-- 반복문 -->
	               		<c:forEach var="qna" items="${qnaList }">
		                 	<tr>
		            		  <td>${qna.id}</td>
		            		  <td>${qna.writerName}</td>
		            		  <td><a href="/admin/qnaView/${qna.id}">${qna.title}</a></td>
		            		  <td style="padding-left: 40px;">${qna.answerYn}</td>
		            		  <td>${qna.formatCreatedAt()}</td>
		            		  <td>
		            		  	<a data-id="${qna.id }" name="deleteButton" class="btn btn-primary" style="color: white">삭제</a>
		            		  </td>
		                   	</tr>
	                  	</c:forEach>
               		<!-- 반복문 끝-->
                    </tbody>
                  </table>
                </div>
              </div>
	<!--/ Hoverable Table rows -->
	</c:if>
              
              <!-- flex 속성으로 가운데정렬하기 -->
              <ul class="pagination" style="display: flex; justify-content: center; padding-right: 10%; margin-top: 10px;">
				  
				  <!-- pageVO.prev가 참(시작페이지가 1이 아닐 때)이면 이전버튼 li태그 생성 -->
				  <c:if test="${pageVO.prev }">
				  	<li class="page-item">
				  		<a class="page-link" href="/admin/qnaList?page=${pageVO.startPage - 1 }"><i class="fas fa-chevron-left"></i></a>
				  	</li>
				  </c:if>
				  
				  <!-- 반복문 -->
				  <c:forEach var="i" begin="${pageVO.startPage }" end="${pageVO.endPage }" step="1">
				  <!-- 페이지 번호 클릭했을 때 Controller에서 pageVO.setCri(cri) 메서드를 통해, 현재 페이지 값 가져올 수 있음 -->
				  <li class="page-item ${pageVO.cri.page eq i ? 'active' : ''}">
				  	<!-- Controller에서 Criteria가 매개변수로 있어서, 여기서 page값 파라미터로 보낼때 받을 수 있음 -->
				  	<a class="page-link" href="/admin/qnaList?page=${i }">${i }</a>
				  </li>
				  </c:forEach>
				  <!-- 반복문 끝 -->
				  
				  <!-- pageVO.nexr가 참(끝페이지 * 페이지당 게시글 수가 총 갯수보다 작으면)이면 다음버튼 li태그 생성 -->
				  <c:if test="${pageVO.next }">
				  	<li class="page-item">
				  		<a class="page-link" href="/admin/qnaList?page=${pageVO.endPage + 1 }"><i class="fas fa-chevron-right"></i></a>
				  	</li>
				  </c:if>
				  
			  </ul>
              
              


<!-- / Content -->

<script src="/js/admin/qnalist.js"></script>
<%@ include file="/WEB-INF/view/admin/layout/footer.jsp" %>            
		
		
		