template = require 'views/templates/review'
View = require 'views/base/view'

module.exports = class ReviewPageView extends View
  autoRender: true
  className: 'review-page'
  template: template
  container: 'body'
  