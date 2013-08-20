window.User = class User extends Backbone.Model

  url: ->
    '/frontend-service/user/' + @get('userName')
  
  initialize: ->
    super
    if $.cookie("userName")
      @set 'userName', $.cookie("userName")
    else
      $.cookie("userName", "bpaulin")
      @set 'userName', 'bpaulin'
    @fetch()
    