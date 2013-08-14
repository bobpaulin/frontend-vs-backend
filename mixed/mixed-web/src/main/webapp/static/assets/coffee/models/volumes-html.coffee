window.VolumesHtml = class VolumesHtml extends Backbone.Model

  url: ->
    '/mixed-web/main/search/' + @get('keyword')
  
  parse:(response) ->
    {snippet: response}
  