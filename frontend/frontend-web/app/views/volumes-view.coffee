template = require 'views/templates/volumes'
CollectionView = require 'views/base/collection-view'
VolumeView = require 'views/volume-view'


module.exports = class VolumesView extends CollectionView

  template: template
  container: '#booksContainer'
  autoRender: true
  listSelector: '.volumes'
  
  initialize: ->
    @subscribeEvent 'volumes:modelChanged', @doModelChange
    super
    
  doModelChange: ->
    # re render view now
    @render()
    
  initItemView: (item) ->
    # Instantiate an item view
    item.set 'displayReviewLink', true
    new VolumeView {model:item, autoRender: true, container:'.volumes'}