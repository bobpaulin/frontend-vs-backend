template = require 'views/templates/volume'
View = require 'views/base/view'

module.exports = class VolumeView extends View

  template: template
  className: 'row-fluid'
  autoRender: true
  container: '#bookContainer'
  
  initialize: ->
    @subscribeEvent 'volume:modelChanged', @doModelChange
    super
    
  doModelChange: ->
    # re render view now
    @render()