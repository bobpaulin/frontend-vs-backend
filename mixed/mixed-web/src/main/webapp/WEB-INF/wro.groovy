groups {
  vendor {
    js("/static/assets/js/jquery-1.8.2.js")
    js("/static/assets/js/jquery.cookie.js")
    js("/static/assets/js/bootstrap.js")
    js("/static/assets/js/underscore.js")
    js("/static/assets/js/backbone.js")
    js("/static/assets/js/handlebars-1.0.0.beta.6.js")
    css("/static/assets/css/*.css")
  }
  app {
  	js("/WEB-INF/handlebars/*.hbs")
  	js("/static/assets/coffee/base/*.coffee")
  	js("/static/assets/coffee/models/*.coffee")
  	js("/static/assets/coffee/views/*.coffee")
  	js("/static/assets/coffee/client/*.coffee")
  }
}