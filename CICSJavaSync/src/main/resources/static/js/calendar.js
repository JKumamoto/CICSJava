var addEditEventModal=new bootstrap.Modal(
	document.getElementById("add-edit-event-modal"),
	{
		keyboard: false
	}
),
formEvent=document.getElementById("add-event-form"),
addEventButton=document.getElementById("add-new-event-btn"),
updateEventButton=document.getElementById("update-event-btn"),
deleteEventButton=document.getElementById("btn-delete-event"),
todayDate=moment().startOf("day"),
YM=todayDate.format("MM-YYYY"),
TODAY=todayDate.format("DD-MM-YYYY"),
config={
	dateFormat:"d/m/Y H:i",
	enableTime: true,
	time_24hr: true
};

flatpickr("#eventStart",config),
flatpickr("#eventEnd",config);

document.addEventListener("DOMContentLoaded", function() {
	var calendarEl = document.getElementById("fullcalendar");
	var calendar = new FullCalendar.Calendar(calendarEl, {
		themeSystem: "bootstrap",
		initialView: "dayGridMonth",
		headerToolbar: {
			left: "prev,next today",
			center: "title",
			right: "dayGridMonth,timeGridWeek,timeGridDay"
		},
		droppable: true,
		editable: true,
		dateClick: function(e){
			var t=new Date;
			let n=e.date;
			e={
				dateFormat: "d/m/Y H:i",
				time_24hr: true,
				enableTime: true,
				defaultDate: [n=new Date(n.setHours(t.getHours(),t.getMinutes(),0,0))]
			};
			flatpickr("#eventStart",e).redraw(),
			flatpickr("#eventEnd",e).redraw(),
			addEventButton.removeAttribute("hidden"),
			updateEventButton.setAttribute("hidden", true),
			deleteEventButton.setAttribute("hidden", true),
			addEditEventModal.show();
		},
		eventClick: function(e){
			var event = e.event;
			//var start = moment(event.start).format("DD/MM/YY HH:mm"), end = moment(event.end).format("DD/MM/YY HH:mm");
			addEventButton.setAttribute("hidden", true),
			updateEventButton.removeAttribute("hidden"),
			deleteEventButton.removeAttribute("hidden"),
			document.getElementById("add-edit-event-modal-title").innerHTML = "Edit Event",
			document.getElementById("event-title").value = event.title,
			document.getElementById("event-description").value = event.description,
			document.getElementById("event-id").value = event.id,
			document.getElementById("event-category").value = event.classNames,
			document.getElementById("eventStart").flatpickr().clear(),
			document.getElementById("eventEnd").flatpickr().clear();
			flatpickr("#eventStart", {
				dateFormat: "d/m/Y H:i",
				time_24hr: true,
				enableTime: true,
				defaultDate: event.start
			}),
			flatpickr("#eventEnd", {
				dateFormat: "d/m/Y H:i",
				time_24hr: true,
				enableTime: true,
				defaultDate: event.end
			}),
			addEditEventModal.show();
			console.log("start = " + moment(event.start).format("DD/MM/YY HH:mm") + " fim= " + moment(event.end).format("DD/MM/YY HH:mm"));
		},
		events: 'https://fullcalendar.io/demo-events.json'
	});
	calendar.render();
});


