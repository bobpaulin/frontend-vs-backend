Controller = require 'controllers/base/controller'
HomePageView = require 'views/home-page-view'
ReviewPageView = require 'views/review-page-view'
NavView = require 'views/nav-view'
BookPreferencesView = require 'views/book-preferences-view'
MessagesView = require 'views/messages-view'
MessageFormView = require 'views/message-form-view'
VolumesView = require 'views/volumes-view'
VolumeView = require 'views/volume-view'
BookPreferencesModel = require 'models/book-preferences'
UserModel = require 'models/user'
MessagesModel = require 'models/messages'
VolumesModel = require 'models/volumes'
VolumeModel = require 'models/volume'

module.exports = class HomeController extends Controller

  bookPreferences: null	

  initialize: ->
    super
    @subscribeEvent 'bookPreferences:modelChanged', @loadBooks
  
  index: ->
    @view = new HomePageView
    user = new UserModel
    @bookPreferences = new BookPreferencesModel
    new NavView({model:user})
    new BookPreferencesView collection:@bookPreferences
    
  reviewPage:(params) ->
    user = new UserModel
    volume = new VolumeModel({bookId:params.bookId})
    messages = new MessagesModel({bookId:params.bookId})
    
    @view = new ReviewPageView({model:volume})
    
    new NavView({model:user})
    new VolumeView({model:volume})
    new MessagesView({collection:messages, bookId:params.bookId})
    new MessageFormView({model:user, messages:messages, bookId:params.bookId})
  
  loadBooks:->
    @bookPreferences.each (model) ->
      volumes = new VolumesModel({keyword: model.get('keyword')})
      new VolumesView({model:volumes})
