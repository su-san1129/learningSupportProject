$('#viewDailyReportForm').change(function(){
  $('#viewDailyReportForm').submit();
});
$('#dailyReportSelectForm').change(function(){
  var id = $(this).val();
  $('.showDailyReport').hide();
  $('#dailyReport' + id).show();
})