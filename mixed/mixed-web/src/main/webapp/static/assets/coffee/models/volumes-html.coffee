window.VolumesHtml = class VolumesHtml extends Backbone.Model

  url: ->
    '/mixed-web/cached/books/search/' + @get('keyword')
  
  parse:(response) ->
    {snippet: response}
  