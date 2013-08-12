Collection = require 'models/base/collection'
Volume = require 'models/volume'


module.exports = class Volumes extends Collection

  model: Volume

  url: ->
    '/frontend-service/books/user/' + $.cookie("userName")
  fetch:->
    super(
      success:(model,response)->
        Chaplin.mediator.publish 'volumes:modelChanged'
     )
  
  initialize: ->
    super
    @fetch()
