template = require 'views/templates/volumes'
View = require 'views/base/view'


module.exports = class VolumesView extends View

  template: template
  container: '#booksContainer'
  autoRender: true
  
  initialize: ->
    @subscribeEvent 'volumes:modelChanged', @doModelChange
    super
    
  doModelChange: ->
    # re render view now
    @render()