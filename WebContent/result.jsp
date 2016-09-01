<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>States</title>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="myStyles.css">
<body style="background-image: url('${bingImage}') ">
	<!-- http://www.bing.com/HPImageArchive.aspx?format=js&idx=0&n=1&mkt=en-US -->
	<c:choose>
		<c:when test="${! empty state}">
			<a
				href="https://www.cia.gov/library/publications/the-world-factbook/geos/us.html"
				target="_blank"><img
				src="image/${state.abbreviation.toLowerCase()}.png" width="15%" /></a>
			<ul class="stateData">
				<h1>${state.name}(${state.abbreviation})</h1>
				<li><h2>Capital: ${state.capital}</h2></li>
				<li><h2>
						Population:
						<fmt:formatNumber pattern="##,###,000" value="${state.population}" />
					</h2></li>
				<li><h2>Statehood: ${state.dob}</h2></li>
				<li><h2>
						Tax Rate:
						<fmt:formatNumber pattern="$0.00##" value="${state.taxrate}"
							type="currency" />
					</h2></li>
			</ul>
			<div>
				<a href="https://simple.wikipedia.org/wiki/${state.name}" target="_blank">
					<iframe class="wiki" align="right" height="65%" width="48%" src="https://simple.wikipedia.org/wiki/${state.name}"></iframe>
				</a>
			</div>
			<!-- <a >Put Google map BELOW here</a> -->
			<!-- Source #1: <iframe src="https://www.google.com/maps/embed/v1/place?q=${state.latitude},${state.longitude}
                     &zoom=5
                     &key=xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx">
                 </iframe> -->
			<!-- Source #2: http://embedgooglemaps.com/en/ -->
			<script src='https://maps.googleapis.com/maps/api/js?key=AIzaSyDNvUM1WrERX0if8WeUuJW22mSgfA0oR8M&callback=initMap'></script>
			<div class='trans' id='gmap_canvas' style='height: 440px; width: 700px;'></div>
			<script type='text/javascript'>
				function init_map() {
					var myOptions = {
						zoom : 10,
						center : new google.maps.LatLng(${state.latitude},${state.longitude}),
						mapTypeId : google.maps.MapTypeId.ROADMAP
					};
					map = new google.maps.Map(document
							.getElementById('gmap_canvas'), myOptions);
					marker = new google.maps.Marker({
						map : map,
						position : new google.maps.LatLng(${state.latitude},${state.longitude})
					});
					infowindow = new google.maps.InfoWindow(
							{
								content : '<strong>${state.capital}</strong><br>${state.name}, USA  --<br>'
							});
					google.maps.event.addListener(marker, 'click', function() {
						infowindow.open(map, marker);
					});
					infowindow.open(map, marker);
				}
				google.maps.event.addDomListener(window, 'load', init_map);
			</script>
			<!-- <a >Put Google Map ABOVE Here</a> -->
		</c:when>
		<c:otherwise>
			<h1 class="lost">No state found</h1>
		</c:otherwise>
	</c:choose>
	<div class="input">
		<p>State Search<form action="GetStateData.do" method="GET">
			<select name="name">
				<c:forEach var="state" items="${allStates}">
					<option value="${state}">${state}</option>
				</c:forEach>
			</select>
			<!--Full Name:<input type="text" name="name"/>-->
			<input type="submit" value="<Search>" />
		</form></p>

		<br>
		<!--<form action="GetStateData.do" method="GET">
			Abbreviation:<input type="text" name="abbr" size="4"/> 
			<input type="submit" value="<Quick Search>" />-->
		</form>
		<!-- <h3 style="visibility:hidden">Add New State:</h3>
		<form action="NewState.do" method="POST" style="visibility:hidden">
			Abbrev:
			<input type="text" name="abbreviation" value="PR"/><br>
			Name:
			<input type="text" name="name" value="Puerto Rico"/><br>
			Capital: 
			<input type="text" name="capital" value="San Juan"/><br>
			<input type="submit" value="Add State" />
		</form> -->
	</div>
	<center>
		<form action="GetNextStateData.do" method="GET">
			<input class="button" type="submit" name="previous"
				value="${previousState}" /> <input class="button" type="submit"
				name="next" value="${nextState}" />
		</form>
	</center>
</body>
</html>