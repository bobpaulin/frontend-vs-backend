window.UserLoggedInView = class UserLoggedInView extends View

  template: Handlebars.templates['user-logged-in']
  tagName: 'p'
  className: 'pull-right'
  id: 'userContainer'
  container: '#nav-container'
  
  render:->
    super
    $('#userContainer').addClass('navbar-text');