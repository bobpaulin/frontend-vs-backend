

 window.Messages = class Messages extends Backbone.Collection

  model: Message
  bookId: null
  
  url: ->
    '/frontend-service/messages/book/' + @bookId
  
  initialize: ->
    super
    @bookId = arguments[1].bookId
