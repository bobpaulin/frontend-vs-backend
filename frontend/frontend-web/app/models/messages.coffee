Collection = require 'models/base/collection'
Message = require 'models/message'

module.exports = class Messages extends Collection

  model: Message
  bookId: null
  
  url: ->
    '/frontend-service/messages/book/' + @bookId
  
  initialize: ->
    super
    @bookId = arguments[0].bookId
    @fetch()
