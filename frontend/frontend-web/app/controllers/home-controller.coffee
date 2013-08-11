Controller = require 'controllers/base/controller'
HomePageView = require 'views/home-page-view'
ReviewPageView = require 'views/review-page-view'
NavView = require 'views/nav-view'
ReviewPageTitleView = require 'views/review-page-title-view'
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

  user: null
  bookPreferences: null
  volume: null
  messages: null  
  volumes: null

  initialize: ->
    super
    @subscribeEvent 'bookPreferences:modelChanged', @loadBooks
  
  index: ->
    @view = new HomePageView
    @initUser()    
    @initIndexModel()
    new NavView({model:@user})
    new BookPreferencesView collection:@bookPreferences
    
  reviewPage:(params) ->
    @initUser()
    @initReviewModel(params.bookId)
    
    @view = new ReviewPageView()
    
    new NavView({model:@user})
    new ReviewPageTitleView({model:@volume})
    new VolumeView({model:@volume})
    new MessagesView({collection:@messages, bookId:params.bookId})
    new MessageFormView({model:@user, messages:@messages, bookId:params.bookId})
  
  loadBooks:->
    @bookPreferences.each (model) ->
      if @volumes?
        @volumes.keyword = model.get('keyword')
        @volumes.fetch()
      else
        @volumes = new VolumesModel({keyword: model.get('keyword')})
      new VolumesView({collection:@volumes})

  initUser: ->
    @user = new UserModel unless @user?
    
  initIndexModel: ->
    @bookPreferences = new BookPreferencesModel unless @bookPreferences?
    
  initReviewModel:(bookId) ->
    @volume = new VolumeModel({bookId:bookId}) unless @volume?
    @messages = new MessagesModel({bookId:bookId}) unless @messages?
