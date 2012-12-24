
$(document).ready(function() {
      $('#newMoveBlock').hide();
      
      console.log('loggin');
      $.get('movebuilder.html', function(data) {
    	  console.log('appending');
    	 $('body').append(data); 
   	  console.log('ebodyd');
      });
      
      
      
      
});