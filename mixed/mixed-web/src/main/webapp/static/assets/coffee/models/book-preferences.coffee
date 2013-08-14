window.BookPreferences = class BookPreferences extends Backbone.Collection

  model: BookPreference
  
  url: ->
    '/mixed-web/main/bookPreferences/user/' + $.cookie("userName")
  