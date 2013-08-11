template = require 'views/templates/message-form'
View = require 'views/base/view'

module.exports = class MessageFormView extends View

  template: template
  className: 'row-fluid'
  container: '#messageFormContainer'
  autoRender: true
  events: {
    'click #submitMessage' : 'submitMessage'
  }
  
  submitMessage:->
    @options.messages.create({userName:@model.get('userName'), bookId:@options.bookId, messageText:$('[name="messageText"]').val()})
