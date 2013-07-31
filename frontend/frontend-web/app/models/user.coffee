Model = require 'models/base/model'


module.exports = class User extends Model

  url: ->
    '/frontend-service/user/' + @get('userName')
  
  initialize: ->
    super
    if $.cookie("userName")
      @set 'userName', $.cookie("userName")
    else
      $.cookie("userName", "bpaulin")
      @set 'userName', 'bpaulin'
    @fetch(
      success:(model,response)->
        Chaplin.mediator.publish 'user:modelChanged'
    )
    