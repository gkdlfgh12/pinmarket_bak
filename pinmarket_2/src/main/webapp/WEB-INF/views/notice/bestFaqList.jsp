<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<div class="container mb-3 mt-3">
	<h2 class="text-center mb-4 pb-2 text-primary fw-bold">FAQ</h2>
  <p class="text-center mb-5">
    	자주 묻는 질문
  </p>
	<div class="accordion" id="accordionExample">
	<c:if test="${list != null}">
	<c:forEach var="vo" items="${list}" varStatus="i">
	  <div class="card">
	    <div class="card-header" id="headingOne">
	      <h2 class="mb-0">
	        <button class="btn btn-link btn-block text-left" type="button" data-toggle="collapse" data-target="#collapse${i.count}" aria-expanded="true" aria-controls="collapse${i.count}">
	          ${vo.title}
	        </button>
	      </h2>
	    </div>
	
	    <div id="collapse${i.count}" class="collapse" aria-labelledby="headingOne" data-parent="#accordionExample">
	      <div class="card-body">
	        ${vo.content}
	      </div>
	    </div>
	  </div>
	</c:forEach>  
	</c:if>
	</div>
</div>