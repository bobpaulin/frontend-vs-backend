window.MessageFormView = class MessageFormView extends View

  template: Handlebars.templates['message-form']
  className: 'row-fluid'
  container: '#newMessageContainer'
  bookId: null
  messages: null
  
  events: {
    'click #submitMessage' : 'submitMessage'
  }
  
  initialize:->
    super
    @bookId = arguments[0].bookId
    @messages = arguments[0].messages
    @listenTo(@messages, 'sync', @render)
  
  submitMessage:->
    @messages.create({userName:$.cookie('userName'), bookId:@bookId, messageText:$('[name="messageText"]').val()})
