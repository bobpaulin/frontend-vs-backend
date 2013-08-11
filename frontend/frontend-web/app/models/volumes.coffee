Collection = require 'models/base/collection'
Volume = require 'models/volume'


module.exports = class Volumes extends Collection

  model: Volume
  keyword: null

  url: ->
    '/frontend-service/books?search=' + @keyword
  
  initialize: ->
    super
    @keyword = arguments[0].keyword
    @fetch(
      success:(model,response)->
        Chaplin.mediator.publish 'volumes:modelChanged'
    )
