template = require 'views/templates/book-preference'
View = require 'views/base/view'

module.exports = class BookPreferenceView extends View

  template: template
  tagName: 'li'
  