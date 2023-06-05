
<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

	<div class="container">
		<h1>Enter todo details:</h1>

		<form:form method="post" modelAttribute="todo">

			<fieldset class="mb-3">

				<!-- the path is mapped to description variable of Todo class -->
				<form:label path="description">description</form:label>
				<form:input type="text" path="description" required="required" />
				<form:errors path="description" cssClass="text-warning" />
				<!-- give tips:enter at least 5 characters-->

			</fieldset>

			<fieldset class="m3">
				<form:label path="targetDate">target date</form:label>
				<form:input type="text" path="targetDate" required="required" />
				<form:errors path="targetDate" cssClass="text-warning" />

			</fieldset>

			<!-- these two hidden inputs correspond to variables of 
		    the todo attribute created in todocontroller-->
			<form:input type="hidden" path="id" />
			<form:input type="hidden" path="done" />

			<input type="submit" class="btn btn-success" />

		</form:form>

	</div>
	
	<%@ include file="common/footer.jspf" %>

	<script type="text/javascript">
		$('#targetDate').datepicker({
			format : 'yyyy-mm-dd',
			startDate : '-3d'
		});
	</script>