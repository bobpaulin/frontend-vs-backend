template = require 'views/templates/user'
View = require 'views/base/view'


module.exports = class UserView extends View

  template: template
  className: 'row-fluid'
  container: '#userContainer'
  autoRender: true
  
  initialize: ->
    @subscribeEvent 'user:modelChanged', @doModelChange
    super
    
  doModelChange: ->
    # re render view now
    @render()