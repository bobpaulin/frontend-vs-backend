template = require 'views/templates/message-create'
View = require 'views/base/view'

module.exports = class MessageCreatePageView extends View
  autoRender: true
  className: 'message-create-page'
  template: template
  container: 'body'