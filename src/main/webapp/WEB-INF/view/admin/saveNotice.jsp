<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="/WEB-INF/view/admin/layout/header.jsp" %>
            
<!-- Content -->
	
	<!-- 수평선 -->
	<hr class="my-5" />
	
	<!-- Form -->
	<div class="col-md-6"  style="margin-left: 25%;">
	<form id="form" action="" enctype="multipart/form-data">
                  <div class="card mb-4">
                    <h5 class="card-header">공지사항 등록</h5>
                    <div class="card-body">
                      <div class="mb-3">
                        <label for="exampleFormControlInput1" class="form-label">제목</label>
                        <input name="title" type="text" class="form-control" id="title--input" placeholder="">
                      </div>
                     
                      <div>
                        <label for="exampleFormControlTextarea1" class="form-label">내용</label>
                        <!-- 서머노트 id 설정하기 -->
                        <textarea name="content" class="form-control" id="content" rows="11"></textarea>
                      </div>
                      
                      <br>
                      <div class="mb-3">
                        <label for="" class="form-label">파일 첨부</label>
                        
                        <input name="files" class="form-control" type="file" multiple="">
                      </div>
                      
                      <!-- name 똑같이 설정해서 DTO의 List<> files에 매핑 가능함 -->
                      <!-- <div class="mb-3">
                        <label for="" class="form-label">파일 첨부</label>
                        <input name="files" class="form-control" type="file">
                      </div> -->
                      
                      <button id="button" class="btn btn-primary" style="width: 100%">등록</button>
                      
                    </div>
                  </div>
	</form>                  
	</div>
	<!-- Form end -->
	
<!-- / Content -->



<!-- 서머노트 라이브러리 -->
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>

<script>
	$('#content').summernote(
			{
				tabsize : 2,
				height : 200,
				toolbar : [ [ 'style', [ 'style' ] ],
						[ 'font', [ 'bold', 'underline', 'clear' ] ],
						[ 'color', [ 'color' ] ],
						[ 'para', [ 'ul', 'ol', 'paragraph' ] ],
						[ 'table', [ 'table' ] ],
						[ 'insert', [ 'link', 'picture', 'video' ] ],
						[ 'view', [ 'fullscreen', 'codeview', 'help' ] ] ],
				
			});
</script>

<script src="/js/admin/savenotice.js"></script>
<%@ include file="/WEB-INF/view/admin/layout/footer.jsp" %>            
		
		
		