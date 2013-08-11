template = require 'views/templates/review-page-title'
View = require 'views/base/view'


module.exports = class ReviewPageTitleView extends View

  template: template
  tagName: 'h1'
  container: '.pageTitle'
  autoRender: true
  
  initialize: ->
    @subscribeEvent 'volume:modelChanged', @doModelChange
    super
    
  doModelChange: ->
    # re render view now
    @render()