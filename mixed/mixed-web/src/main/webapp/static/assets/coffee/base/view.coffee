window.View = class View extends Backbone.View
  
  container: null
  
  initialize:->
    super
    @listenTo(@model, 'sync', @render);
    @listenTo(@model, 'destroy', @remove);
  
  render: ->
    @$el.html(@template(@model.toJSON()))
    if @container?
      $(@container).append @el
    this