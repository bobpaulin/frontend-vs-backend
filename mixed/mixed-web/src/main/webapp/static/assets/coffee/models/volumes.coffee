window.Volumes = class Volumes extends Backbone.Collection

  model: Volume

  url: ->
    '/frontend-service/books/user/' + $.cookie("userName")
