<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="myStyles.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>States</title>
</head>
<body>
	<c:choose>
		<c:when test="${! empty state}">
			<ul>
				<li>${state.abbreviation}</li>
				<li>${state.name}</li>
				<li>${state.capital}</li>
				<li><script
						src='https://maps.googleapis.com/maps/api/js?v=3.exp'></script>
					<div style='overflow: hidden; height: 440px; width: 700px;'>
						<div id='gmap_canvas' style='height: 440px; width: 700px;'></div>
						<style>
#gmap_canvas img {
	max-width: none !important;
	background: none !important
}
</style>
					</div>
					<script type='text/javascript'>
						function init_map() {
							var myOptions = {
								zoom : 10,
								center : new google.maps.LatLng(
										${state.latitude}, ${state.longitude}),
								mapTypeId : google.maps.MapTypeId.ROADMAP
							};
							map = new google.maps.Map(document
									.getElementById('gmap_canvas'), myOptions);
							marker = new google.maps.Marker({
								map : map,
								position : new google.maps.LatLng(
										${state.latitude}, ${state.longitude})
							});
							infowindow = new google.maps.InfoWindow(
									{
										content : '<strong>${state.capital}</strong>,${state.name}, United States<br>'
									});
							google.maps.event.addListener(marker, 'click',
									function() {
										infowindow.open(map, marker);
									});
							infowindow.open(map, marker);
						}
						google.maps.event.addDomListener(window, 'load',
								init_map);
					</script></li>
				<!-- <a href="http://maps.google.com/?q=${state.latitude},${state.longitude}">${state.capital}</a></li> -->
			</ul>

		</c:when>
		<c:otherwise>
		No state found
	</c:otherwise>
	</c:choose>

	<br />
</body>
</html>