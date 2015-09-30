var page = require('webpage').create();
page.viewportSize = { width: ${u.width?c}, height: ${u.height?c} };
page.clipRect = { top: 0, left: 0, width: 1024, height: 800 };
page.open('${u.url}', function(status) {
  console.log("Status: " + status);
  if(status === "success") {
    page.render('${u.outputPath}');
  }
  phantom.exit();
  page.close();
});