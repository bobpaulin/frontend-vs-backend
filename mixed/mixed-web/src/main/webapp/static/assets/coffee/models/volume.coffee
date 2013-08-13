window.Volume = class Volume extends Backbone.Model

  url: ->
    '/frontend-service/books/book/' + @get('bookId')
  
