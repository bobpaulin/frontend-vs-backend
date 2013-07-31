Controller = require 'controllers/base/controller'
HomePageView = require 'views/home-page-view'
MessagesView = require 'views/messages-view'
UserView = require 'views/user-view'
UserModel = require 'models/user'
MessagesModel = require 'models/messages'

module.exports = class HomeController extends Controller
  index: ->
    @view = new HomePageView
    user = new UserModel
    messages = new MessagesModel
    new UserView({model:user})
    new MessagesView collection:messages
