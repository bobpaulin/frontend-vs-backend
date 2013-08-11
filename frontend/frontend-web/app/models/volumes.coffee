Model = require 'models/base/model'


module.exports = class Volumes extends Model

  url: ->
    '/frontend-service/books?search=' + @get('keyword')
  
  initialize: ->
    super
    @fetch(
      success:(model,response)->
        Chaplin.mediator.publish 'volumes:modelChanged'
    )
