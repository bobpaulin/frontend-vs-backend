Collection = require 'models/base/collection'
Message = require 'models/message'

module.exports = class Messages extends Collection

  model: Message
  
  url: ->
    '/frontend-service/messages/' + $.cookie("userName")
  
  initialize: ->
    super
    @fetch()
