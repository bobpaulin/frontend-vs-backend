

 window.Messages = class Messages extends Backbone.Collection

  model: Message
  bookId: null
  
  url: ->
    '/mixed-web/main/messages/book/' + @bookId
  
  initialize: ->
    super
    @bookId = arguments[1].bookId
