<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <div class="row">
  	<div class="col-md-5">
  		<div class="dataTables_info" id="datatable_info" role="status" aria-live="polite">
  			Showing ${info.startRow } to ${info.endRow } of ${info.total } entries  
  		</div>
  	</div>
  	<div class=" col-md-5">
		 <div class="" id="" style="float: right">
			 <ul class="pagination">
			 	
			    <li class=""><a href="javascript:goPage(${info.pageNum == 1 ? 1 : info.pageNum - 1 })" >&laquo;</a></li>
			    
			    <c:if test="${info.pages <= 5 }">
					<c:forEach var="count" varStatus="index" begin="1" end="${info.pages }">
					    <li class="${count == info.pageNum ?  'active' : '' }"><a href="javascript:goPage(${count })" class="">${count }</a></li>
					 </c:forEach>
			    </c:if>
			    
			    <c:if test="${info.pages > 5 }">
				 	<c:choose>
				 		<c:when test="${info.pageNum > 2 && info.pageNum < (info.pages -1) }">
						    <c:forEach var="count" varStatus="index" begin="${info.pageNum -2}" end="${info.pageNum + 2}">
						    	<li class="${count == info.pageNum ?  'active' : '' }"><a href="javascript:goPage(${count })" class="">${count }</a></li>
						    </c:forEach>
				 		</c:when>
				 		<c:when test="${info.pageNum == 2 }">
						    <c:forEach var="count" varStatus="index" begin="${info.pageNum - 1}" end="${info.pageNum + 3}">
						    	<li class="${count == info.pageNum ?  'active' : '' }"><a href="javascript:goPage(${count })" class="">${count }</a></li>
						    </c:forEach>
				 		</c:when>
 						<c:when test="${info.pageNum == 1 }">
						    <c:forEach var="count" varStatus="index" begin="${info.pageNum}" end="${info.pageNum + 4}">
						    	<li class="${count == info.pageNum ?  'active' : '' }"><a href="javascript:goPage(${count })" class="">${count }</a></li>
						    </c:forEach>
				 		</c:when>
				 		<c:when test="${info.pageNum == info.pages }">
						    <c:forEach var="count" varStatus="index" begin="${info.pageNum - 4}" end="${info.pages}">
						    	<li class="${count == info.pageNum ?  'active' : '' }"><a href="javascript:goPage(${count })" class="">${count }</a></li>
						    </c:forEach>
				 		</c:when>
				 		<c:when test="${info.pageNum == (info.pages - 1)}">
						    <c:forEach var="count" varStatus="index" begin="${info.pageNum - 3}" end="${info.pageNum +1 }">
						    	<li class="${count == info.pageNum ?  'active' : '' }"><a href="javascript:goPage(${count })" class="">${count }</a></li>
						    </c:forEach>
				 		</c:when>
				 	</c:choose>
				 </c:if>
			    
			    <%-- <c:forEach var="count" varStatus="index" begin="${info.pageNum > 2 ? info.pageNum -2 : 1}" end="${info.pageNum + 2 > info.pages ? info.pages : info.pageNum+2}">
			    	<li><a href="javascript:goPage(${count })" class="">${count }</a></li>
			    </c:forEach> --%>
			    
			    <li><a href="javascript:goPage(${info.pageNum == info.pages ? info.pageNum : info.pageNum + 1 })">&raquo;</a></li>
			</ul>
		</div>
  	</div>
  	<div class="col-md-1"></div>
 </div>