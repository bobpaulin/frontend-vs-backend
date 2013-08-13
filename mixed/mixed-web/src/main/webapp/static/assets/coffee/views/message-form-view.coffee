window.MessageFormView = class MessageFormView extends View

  template: Handlebars.templates['message-form']
  className: 'row-fluid'
  events: {
    'click #submitMessage' : 'submitMessage'
  }
  
  submitMessage:->
    @options.messages.create({userName:@model.get('userName'), bookId:@options.bookId, messageText:$('[name="messageText"]').val()})
