window.VolumesView = class VolumesView extends View

  template: Handlebars.templates['volumes']
  
  render: ->
    super
    @model.forEach(@initItemView, this)
    this
    
  initItemView: (item) ->
    # Instantiate an item view
    item.set 'displayReviewLink', true
    currentView = new VolumeView {model:item}
    $('.volumes').append(currentView.render().el)