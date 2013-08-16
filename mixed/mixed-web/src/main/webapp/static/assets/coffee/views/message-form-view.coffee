window.MessageFormView = class MessageFormView extends View

  template: Handlebars.templates['message-form']
  className: 'row-fluid'
  id: 'newMessageContainer'
  bookId: null
  
  events: {
    'click #submitMessage' : 'submitMessage'
  }
  
  initialize:->
    super
    @bookId = arguments[0].bookId
    @render()
  
  submitMessage:->
    @model.create({userName:$.cookie('userName'), bookId:@bookId, messageText:$('[name="messageText"]').val()})

  render:->
  
    @$el.html($('#newMessageContainer').html())
    $('#newMessageContainer').html @el
    this