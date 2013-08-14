window.View = class View extends Backbone.View
  
  container: null
  
  constructor: (options) ->
    # Apply options
    if (options)
      _.extend this, _.pick options, ['container']
    super
  
  initialize:->
    super
    @listenTo(@model, 'sync', @render)
    @listenTo(@model, 'change', @render)
    @listenTo(@model, 'destroy', @remove)
  
  render: ->
    @$el.html(@template(@model.toJSON()))
    if @container?
      $(@container).append @el
    this