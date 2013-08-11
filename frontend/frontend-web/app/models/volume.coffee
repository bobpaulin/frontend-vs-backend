Model = require 'models/base/model'


module.exports = class Volume extends Model

  url: ->
    '/frontend-service/books/book/' + @get('bookId')
  
  initialize: ->
    super
    @fetch(
      success:(model,response)->
        Chaplin.mediator.publish 'volume:modelChanged'
    )
