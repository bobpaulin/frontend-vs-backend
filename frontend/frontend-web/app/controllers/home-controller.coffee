Controller = require 'controllers/base/controller'
HomePageView = require 'views/home-page-view'
MessageCreatePageView = require 'views/message-create-page-view'
NavView = require 'views/nav-view'
MessagesView = require 'views/messages-view'
MessageFormView = require 'views/message-form-view'
VolumesView = require 'views/volumes-view'
UserModel = require 'models/user'
MessagesModel = require 'models/messages'
VolumesModel = require 'models/volumes'

module.exports = class HomeController extends Controller
  
  messages: null
  volumes: null
  user: null
  
  index: ->
    @view = new HomePageView
    @getModels()
    new NavView({model:@user})
    new MessagesView collection:@messages
    new VolumesView({model:@volumes})
    
  createMessagePage: ->
    @view = new MessageCreatePageView
    @getModels()
    new NavView({model:@user})
    new MessageFormView({model:@user, messages:@messages})
    new MessagesView collection:@messages
  	
  getModels: ->
    @user = new UserModel unless @user?
    @messages = new MessagesModel unless @messages?
    @volumes = new VolumesModel unless @volumes?
  	
