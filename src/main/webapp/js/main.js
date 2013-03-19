
var buildings = [
    { "id": "G01" },
    { "id": "G02" },
    { "id": "G02" }
];

var offset = 0;
var grabTarget = null;

$(document).ready(function() {

	$('#buildingsdragbar').on({
    mousedown:function(e){
      e.preventDefault();
      $(document).on({mousemove:function(f){
        if(!e.offsetY) e.offsetY = e.pageY - $(e.target).offset().top;
        $('#buildings').css("height", f.pageY-e.offsetY);
      }});
    },
    touchstart:function(e) {
      grabTarget = $(this);
      offset = e.originalEvent.changedTouches[0].pageY - $(e.target).offset().top;
    },
    touchmove:function(e) {
      e.preventDefault();
      $('#buildings').css("height", e.originalEvent.changedTouches[0].pageY - offset);
    }
  });
  $(document).on({
    mouseup:function(e){
      $(document).unbind('mousemove');
    }
  });

  $.getJSON("json/buildings.json", function(data) {
	  $.each(data,function() {
		  $('#buildings').append(ich.buildingTmpl(this));
	  });
  });
  
  
});