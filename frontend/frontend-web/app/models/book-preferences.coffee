Collection = require 'models/base/collection'
BookPreference = require 'models/book-preference'

module.exports = class BookPreferences extends Collection

  model: BookPreference
  
  url: ->
    '/frontend-service/bookPreferences/user/' + $.cookie("userName")
  
  initialize: ->
    super
    @fetch(
      success:(model,response)->
        Chaplin.mediator.publish 'bookPreferences:modelChanged'
    )
