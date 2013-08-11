template = require 'views/templates/nav'
View = require 'views/base/view'


module.exports = class NavView extends View

  template: template
  className: 'navbar'
  container: '.navigation'
  autoRender: false
  
  initialize: ->
    @subscribeEvent 'user:modelChanged', @doModelChange
    super
    
  doModelChange: ->
    # re render view now
    @render()