template = require 'views/templates/messages'
CollectionView = require 'views/base/collection-view'
MessageView = require 'views/message-view'

module.exports = class MessagesView extends CollectionView

  template: template
  container: '#messageContainer'
  tagName: 'div'
  autoRender: true
  listSelector: '.messages'
  
  initialize:->
    @subscribeEvent 'messages:modelChanged', @doModelChange
    super
    
  doModelChange: ->
    # re render view now
    @render()
  
  initItemView: (item) ->
    # Instantiate an item view
    new MessageView {model:item, autoRender: true}
    
  