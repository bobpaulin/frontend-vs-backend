window.BookPreferences = class BookPreferences extends Backbone.Collection

  model: BookPreference
  
  url: ->
    '/frontend-service/bookPreferences/user/' + $.cookie("userName")
  