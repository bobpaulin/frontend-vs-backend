template = require 'views/templates/message'
View = require 'views/base/view'

module.exports = class MessageView extends View

  template: template
  className: 'row-fluid'
  