window.MessagesView = class MessagesView extends View

  template: Handlebars.templates['messages']
  tagName: 'div'
  
  render: ->
    super
    @model.forEach(@initItemView, this)
    this
    

  initItemView: (item) ->
    # Instantiate an item view
    currentView = new window.MessageView {model:item}
    $('.messages').append(currentView.render().el)
    
  